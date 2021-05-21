package org.kpu.blackjack.services;

import org.kpu.blackjack.dao.PlayerDAO;
import org.kpu.blackjack.domain.Deck;
import org.kpu.blackjack.domain.Player;
import org.kpu.blackjack.domain.Result;
import org.kpu.blackjack.domain.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.SERIALIZABLE)
public final class BlackJackServiceImpl implements BlackJackService {
	
	@Autowired
	private Deck deck;
	
	@Autowired
	private Round round;
	
	@Autowired
	private PlayerDAO playerDao;
	
	private boolean playerDoubledBet;

	@Override
	public Round startGame(String name) throws Exception {
		Player player = playerDao.read(name);
		if(player == null) {
			System.out.println(name);
			player = new Player(name);
			playerDao.add(player);
		}
		round.setPlayer(player);
		return round;
	}
	
	
	@Override
	public Round startRound() {
		boolean playerFinishedDrawingCards = false;
		round.setResult(Result.DRAW);
		round.setSplitHand(null);
		round.setBustPlayer(false);
		round.setPlayerHasBlackJack(false);
		round.setPlayerCanSplit(false);
		round.setGameMessage("");
		if (this.playerDoubledBet) {
			round.setPlayerBet(round.getPlayerBet() / 2);
			this.playerDoubledBet = false;
		}
		round.getDealerCards().clear();
		round.getPlayerCards().clear();

		if(deck.getRemainCards() < 3) deck.newDeck();
		round.getDealerCards().add(deck.dealRandomCard());
		round.getPlayerCards().add(deck.dealRandomCard());
		round.getPlayerCards().add(deck.dealRandomCard());

		round.calculateHandValues(playerFinishedDrawingCards);
		round.checkIfPlayerCanSplit();
		return round;
	}

	@Override
	public Round hitPlayer() {	
		boolean isPlayer = true;
		boolean playerFinishedDrawingCards = false;
		boolean isSplit = false;
		
		if(deck.getRemainCards() < 1) deck.newDeck();
		round.getPlayerCards().add(deck.dealRandomCard());
		round.checkBust(round.getPlayerCards(), isPlayer, isSplit);
		round.calculateHandValues(playerFinishedDrawingCards);
		
		return round;
	}

	@Override
	public Round playerDoubles() {	
		boolean isPlayer = true;
		boolean playerFinishedDrawingCards = false;
		boolean isSplit = false;
		round.setPlayerBet(2 * round.getPlayerBet());
		this.playerDoubledBet = true;
		
		if(deck.getRemainCards() < 1) deck.newDeck();
		round.getPlayerCards().add(deck.dealRandomCard());
		playerFinishedDrawingCards = true;
		if (round.checkBust(round.getPlayerCards(), isPlayer, isSplit)) {
			round.calculateHandValues(playerFinishedDrawingCards);
			return round;
		}
		this.playerStands();
		return round;
	}

	@Override
	public Round playerStands() {
		boolean isPlayer = false;
		boolean playerFinishedDrawingCards = true;
		boolean isSplit = false;
		if (round.hasPlayerABlackJack() && round.dealerCanNotMakeBlackJack()) {
			round.checkWhoWon();
			round.calculateHandValues(playerFinishedDrawingCards);
			return round;
		}

		if(deck.getRemainCards() < 3) deck.newDeck();
		while (!round.dealerMustStand()) {
			round.getDealerCards().add(deck.dealRandomCard());
			if (round.hasPlayerABlackJack()
					&& round.getDealerCards().size() == 2) {
				round.checkWhoWon();
				round.calculateHandValues(playerFinishedDrawingCards);
				return round;
			}
			if (round.checkBust(round.getDealerCards(), isPlayer, isSplit)) {
				round.calculateHandValues(playerFinishedDrawingCards);
				return round;
			}
		}
		round.checkWhoWon();
		round.calculateHandValues(playerFinishedDrawingCards);
		return round;
	}
	
	/*배팅 금액 변경*/
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Round changeBet(String betSize) {		
		if (Integer.valueOf(betSize) <= 0) {
			throw new IllegalArgumentException("Invalid Value " + betSize);			
		}
		
		round.setPlayerBet(Integer.valueOf(betSize));
		playerDoubledBet = false;
		return round;
	}


	@Override
	public Round playerSplits() {
		round.playerSplits();
		return round;
	}

	@Override
	public Round splitLeftHitPlayer() {	
		boolean isSplitLeft = true;
		boolean splitLeftFinishedDrawingCards = false;
		boolean bust = false;
		
		if(deck.getRemainCards() < 1) deck.newDeck();
		round.getSplitHand().getSplitLeftCards().add(deck.dealRandomCard());
		bust = round.getSplitHand().checkIfSplitHandBust( round.getSplitHand().getSplitLeftCards(), isSplitLeft);
		if (bust) {
			round.getPlayer().setMoney(round.getPlayer().getMoney() - round.getPlayerBet());
		}
		round.getSplitHand().calculateSplitHandValues(round.getSplitHand().getSplitLeftCards(),
				splitLeftFinishedDrawingCards , isSplitLeft);
		
		return round;
	}

	@Override
	public Round splitLeftPlayerStands() {
		boolean isSplitLeft = true;
		boolean splitLeftFinishedDrawingCards = true;
		round.getSplitHand().calculateSplitHandValues(round.getSplitHand().getSplitLeftCards(),splitLeftFinishedDrawingCards , isSplitLeft);
		
		return round;
	}

	@Override
	public Round splitRightHitPlayer() {
		boolean isSplitLeft = false;
		boolean splitRightFinishedDrawingCards = false;
		boolean bust = false;
		
		if(deck.getRemainCards() < 1) deck.newDeck();
		round.getSplitHand().getSplitRightCards().add(deck.dealRandomCard());
		bust = round.getSplitHand().checkIfSplitHandBust(round.getSplitHand().getSplitRightCards(), isSplitLeft);
		if (bust) {
			round.getPlayer().setMoney(round.getPlayer().getMoney() - round.getPlayerBet());
			round.checkIfPlayerLowOnCredits();
		}
		round.getSplitHand().calculateSplitHandValues(round.getSplitHand().getSplitRightCards(),splitRightFinishedDrawingCards , isSplitLeft);
		
		return round;
	}

	@Override
	public Round splitRightPlayerStands() {
		boolean isSplit = true;
		boolean isSplitLeft = false;
		boolean splitRightFinishedDrawingCards = true;
		round.getSplitHand().calculateSplitHandValues(round.getSplitHand().getSplitRightCards(),splitRightFinishedDrawingCards , isSplitLeft);
		
		if(deck.getRemainCards() < 3) deck.newDeck();
		while (!round.dealerMustStand()) {
			round.getDealerCards().add(deck.dealRandomCard());

			if (round.checkBust(round.getDealerCards(), false, isSplit)) {
				round.calculateHandValues(true);	
				return round;
			}
		}

		round.calculateHandValues(true);
		round.checkWhoWonAfterSplit();
		
		return round;
	}
}
