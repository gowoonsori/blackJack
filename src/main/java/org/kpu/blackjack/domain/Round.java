package org.kpu.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Round {
	private final List<Card> playerCards = new ArrayList<Card>();
	private String playerHandValue = "";
	private SplitHand splitHand = null;
	private boolean playerHasBlackJack;
	private boolean playerCanSplit;
	private boolean bustPlayer;
	private Result result = Result.DRAW; 
	private Player player;
	private int playerBet = Consts.STARTING_BET;			

	private final List<Card> dealerCards = new ArrayList<Card>();
	private int dealerHandValue;
	private String gameMessage = "";
	//private double playerCredits;  //
	
	/*카드 값 계산*/
	public void calculateHandValues(boolean thePlayerIsFinishedDrawingCards) {
		int total = 0;
		int numberOfDealersAces = 0;
		int numberOfPlayersAces = 0;

		/*player 계산*/
		for (Card card : this.playerCards) {
			total = total + card.getRank().getCardValue();
			numberOfPlayersAces = ifAceThenIncrementAceCount(numberOfPlayersAces, card);
		}
		this.playerHandValue = String.valueOf(total);	
		/*A가 있을 경우 1or11로 계산할 수 있다.*/
		if ((total <= 11) && numberOfPlayersAces != 0) {
			if (thePlayerIsFinishedDrawingCards) {  //Player가 stand했다면 or을 안보여주어도 됨
				this.playerHandValue = String.valueOf(total + 10);
			} else {
				this.playerHandValue = this.playerHandValue + " or "+ String.valueOf(total + 10);
			}
		}
		
		/*딜러 계산*/
		total = 0;
		for (Card card : this.dealerCards) {
			total = total + card.getRank().getCardValue();
			numberOfDealersAces = ifAceThenIncrementAceCount(numberOfDealersAces, card);
		}
		if ((total <= 11) && numberOfDealersAces != 0) {
			this.dealerHandValue = total + 10;
		} else {
			this.dealerHandValue = total;
		}
	}

	/*Card가 Ace인지 확인해서 ace개수 return*/
	private int ifAceThenIncrementAceCount(int numberOfAces, Card card) {
		if ((card.getRank().getCardValue() == 1)) {
			numberOfAces++;
		}
		return numberOfAces;
	}

	/*21을 넘었는지 검사*/
	public boolean checkBust(List<Card> cards, boolean isPlayer, boolean isSplit) {	
		int maxHandValueAllowed = Consts.TWENTY_ONE;
		int total = 0;
		
		for (Card card : cards) {
			total = total + card.getRank().getCardValue();
		}
		if (isPlayer) {
			if (total > maxHandValueAllowed) {
				this.bustPlayer = true;
				this.result = Result.LOSE;
				this.gameMessage = Consts.PLAYER_BUST;
				this.player.setMoney(this.player.getMoney() - this.playerBet);
				checkIfPlayerLowOnCredits();
				return true;
			}
		} else {
			if (total > maxHandValueAllowed) {
				this.gameMessage = Consts.DEALER_BUST;
				this.result = Result.WIN;
				if (!isSplit) {
					this.player.setMoney(this.player.getMoney()+ this.playerBet);
				} 
				else {				
					if (!this.splitHand.isSplitLeftBust()) {
						this.splitHand.setSplitLeftGameMessage(Consts.DEALER_BUST);
						this.splitHand.setLeftResult(Result.WIN);
					}
					if (!this.splitHand.isSplitRightBust()) {
						this.splitHand.setSplitRightGameMessage(Consts.DEALER_BUST);
						this.splitHand.setRightResult(Result.WIN);
					}
					if (!this.splitHand.isSplitLeftBust()&& !this.splitHand.isSplitRightBust()) {
						this.player.setMoney(this.player.getMoney() + 2 * this.playerBet);
					} else {
						this.player.setMoney(this.player.getMoney() + this.playerBet);
					}
				}
				return true;
			}
		}
		return false;
	}

	/*플레이어 BlackJack인지 검사*/
	public boolean hasPlayerABlackJack() {
		int totalPlayer = 0;
		int numberOfPlayersAces = 0;
		
		for (Card card : this.playerCards) {
			totalPlayer = totalPlayer + card.getRank().getCardValue();
			numberOfPlayersAces = ifAceThenIncrementAceCount(numberOfPlayersAces, card);
		}
		if ((totalPlayer == 11) && (this.playerCards.size() == 2) && (numberOfPlayersAces == 1)) {
			this.playerHasBlackJack = true;
			return true;
		} else {
			return false;
		}
	}

	/*딜러 BlackJack만들 수 있는지 검사*/
	public boolean dealerCanNotMakeBlackJack() {
		int cardValueOfAce = Rank.ACE.getCardValue();
		int cardValueOfTenOrRoyal = Rank.TEN.getCardValue();
		
		if ((this.dealerCards.get(0).getRank().getCardValue() != cardValueOfAce)
				&& (this.dealerCards.get(0).getRank().getCardValue() != cardValueOfTenOrRoyal)) {
			return true;
		} else {
			return false;
		}
	}

	/*딜러는 17을 넘으면 무조건 stand*/
	public boolean dealerMustStand() {
		int total = 0;
		int numberOfDealersAces = 0;
		
		for (Card card : this.dealerCards) {
			total = total + card.getRank().getCardValue();
			numberOfDealersAces = ifAceThenIncrementAceCount(numberOfDealersAces, card);
		}
		for (int i = 0; i < numberOfDealersAces; i++) {
			if (total <= 11)total = total + 10;
		}
		if ((total >= Consts.MIN_VALUE_THAT_DEALER_STANDS) && (total <= Consts.TWENTY_ONE)) {
			return true;
		} else {
			return false;
		}
	}

	/*누가 이겼는지 검사*/
	public void checkWhoWon() {
		int totalPlayer = 0;
		int totalDealer = 0;
		int numberOfPlayersAces = 0;
		int numberOfDealersAces = 0;

		for (Card card : this.playerCards) {
			totalPlayer = totalPlayer + card.getRank().getCardValue();
			numberOfPlayersAces = ifAceThenIncrementAceCount(
					numberOfPlayersAces, card);
		}
		
		totalPlayer = adjustHandValueForAces(totalPlayer, numberOfPlayersAces);
		if ((totalPlayer == Consts.TWENTY_ONE) && (this.playerCards.size() == 2)) {
			this.playerHasBlackJack = true;
		}
		
		for (Card card : this.dealerCards) {
			totalDealer = totalDealer + card.getRank().getCardValue();
			numberOfDealersAces = ifAceThenIncrementAceCount(numberOfDealersAces, card);
		}
		totalDealer = adjustHandValueForAces(totalDealer, numberOfDealersAces);

		if (totalPlayer == totalDealer) {
			if (this.playerHasBlackJack && (this.dealerCards.size() > 2)) {
				this.gameMessage = Consts.PLAYER_WINS_WITH_BLACKJACK;
				this.result = Result.WIN;
				this.player.setMoney(this.player.getMoney() + 1.5 * this.playerBet);
			} else if ((totalDealer == Consts.TWENTY_ONE)&& (this.dealerCards.size() == 2)&& (!this.playerHasBlackJack)) {
				this.gameMessage = Consts.DEALER_WINS_WITH_BLACKJACK;
				this.result = Result.LOSE;
				this.player.setMoney(this.player.getMoney() - this.playerBet);
				checkIfPlayerLowOnCredits();
			} else {
				this.gameMessage = Consts.DRAW;
				this.result = Result.DRAW;
			}
		}

		if (totalPlayer > totalDealer) {
			if (this.playerHasBlackJack) {
				this.gameMessage = Consts.PLAYER_WINS_WITH_BLACKJACK;
				this.player.setMoney(this.player.getMoney() + 1.5 * this.playerBet);
			} else {
				this.gameMessage = Consts.PLAYER_WINS;
				this.player.setMoney(this.player.getMoney() + this.playerBet);
			}
			this.result = Result.WIN;
		}

		if (totalPlayer < totalDealer) {
			if ((totalDealer == Consts.TWENTY_ONE)&& (this.dealerCards.size() == 2)) {
				this.gameMessage = Consts.DEALER_WINS_WITH_BLACKJACK;
			} else {
				this.gameMessage = Consts.PLAYER_LOSES;
			}
			this.result = Result.LOSE;
			this.player.setMoney(this.player.getMoney() - this.playerBet);
			checkIfPlayerLowOnCredits();
		}
	}

	/*최소 배팅금액보다 잔액이 적은지 검사*/
	public void checkIfPlayerLowOnCredits() {
		if (this.player.getMoney() < Consts.LOW_CREDITS_VALUE) {
			this.player.setMoney( Consts.STARTING_CREDITS );
			this.gameMessage = Consts.LOW_CREDITS_MESSAGE;
		}
	}

	/*split할 수 있는 지 검사*/
	public void checkIfPlayerCanSplit() {
		if (this.playerCards.get(0).getRank().getCardValue() == this.playerCards.get(1).getRank().getCardValue()) {
			this.setPlayerCanSplit(true);
		}
	}

	/*split수행 메서드*/
	public void playerSplits() {
		this.splitHand = new SplitHand();
		this.splitHand.getSplitLeftCards().add(this.playerCards.get(0));
		this.splitHand.getSplitRightCards().add(this.playerCards.get(1));
		this.gameMessage = Consts.PLAYER_SPLITS;
	}

	/*split후 이겼는지 검사*/
	public void checkWhoWonAfterSplit() {
		int totalPlayer = 0;
		int totalDealer = 0;
		int numberOfPlayersAces = 0;
		int numberOfDealersAces = 0;

		if (!this.splitHand.isSplitLeftBust()) {
			for (Card card : this.splitHand.getSplitLeftCards()) {
				totalPlayer = totalPlayer + card.getRank().getCardValue();
				numberOfPlayersAces = ifAceThenIncrementAceCount(
						numberOfPlayersAces, card);
			}
			
			totalPlayer = adjustHandValueForAces(totalPlayer,numberOfPlayersAces);
			for (Card card : this.dealerCards) {
				totalDealer = totalDealer + card.getRank().getCardValue();
				numberOfDealersAces = ifAceThenIncrementAceCount(
						numberOfDealersAces, card);
			}
			
			totalDealer = adjustHandValueForAces(totalDealer,numberOfDealersAces);
			if (totalPlayer == totalDealer) {
				this.splitHand.setSplitLeftGameMessage(Consts.DRAW);
				this.splitHand.setLeftResult(Result.DRAW);
			}
			if (totalPlayer > totalDealer) {
				this.splitHand.setSplitLeftGameMessage(Consts.PLAYER_WINS);
				this.player.setMoney(this.player.getMoney() + this.playerBet);
				this.splitHand.setLeftResult(Result.WIN);
			}
			if (totalPlayer < totalDealer) {
				this.splitHand.setSplitLeftGameMessage(Consts.PLAYER_LOSES);
				this.player.setMoney(this.player.getMoney() - this.playerBet);
				this.splitHand.setLeftResult(Result.LOSE);
			}
		}

		if (!this.splitHand.isSplitRightBust()) {
			totalPlayer = 0;
			totalDealer = 0;
			numberOfPlayersAces = 0;
			numberOfDealersAces = 0;

			for (Card card : this.splitHand.getSplitRightCards()) {
				totalPlayer = totalPlayer + card.getRank().getCardValue();
				numberOfPlayersAces = ifAceThenIncrementAceCount(numberOfPlayersAces, card);
			}
			
			totalPlayer = adjustHandValueForAces(totalPlayer,numberOfPlayersAces);
			for (Card card : this.dealerCards) {
				totalDealer = totalDealer + card.getRank().getCardValue();
				numberOfDealersAces = ifAceThenIncrementAceCount(numberOfDealersAces, card);
			}
			
			totalDealer = adjustHandValueForAces(totalDealer,numberOfDealersAces);			
			if (totalPlayer == totalDealer) {
				this.splitHand.setSplitRightGameMessage(Consts.DRAW);
				this.splitHand.setRightResult(Result.DRAW);
			}		
			if (totalPlayer > totalDealer) {
				this.splitHand.setSplitRightGameMessage(Consts.PLAYER_WINS);
				this.player.setMoney(this.player.getMoney() + this.playerBet);
				this.splitHand.setRightResult(Result.WIN);
			}		
			if (totalPlayer < totalDealer) {
				this.splitHand.setSplitRightGameMessage(Consts.PLAYER_LOSES);
				this.player.setMoney(this.player.getMoney() - this.playerBet);
				this.checkIfPlayerLowOnCredits();
				this.splitHand.setRightResult(Result.LOSE);
			}
		}
	}
	
	/*A가 있다면 11로쓰였을때 값 계산*/
	private int adjustHandValueForAces(int total, int numberOfAces) {
		if ( (total <= 11) && (numberOfAces != 0) ) total = total + 11;
		return total;
	}

	public boolean isBustPlayer() {return bustPlayer;}
	public void setBustPlayer(boolean bustPlayer) {this.bustPlayer = bustPlayer;}
	public int getPlayerBet() {return playerBet;}
	public void setPlayerBet(int playerBet) {this.playerBet = playerBet;}
	public List<Card> getDealerCards() {return dealerCards;}
	public List<Card> getPlayerCards() {return playerCards;}
	public String getGameMessage() {return gameMessage;}
	public void setGameMessage(String gameMessage) {this.gameMessage = gameMessage;}
	public boolean isPlayerHasBlackJack() {return playerHasBlackJack;}
	public void setPlayerHasBlackJack(boolean playerHasBlackJack) {this.playerHasBlackJack = playerHasBlackJack;}
	public String getPlayerHandValue() {return playerHandValue;}
	public void setPlayerHandValue(String playerHandValue) {	this.playerHandValue = playerHandValue;}
	public int getDealerHandValue() {return dealerHandValue;	}
	public void setDealerHandValue(int dealerHandValue) {this.dealerHandValue = dealerHandValue;	}
	public boolean isPlayerCanSplit() {	return playerCanSplit;}
	public void setPlayerCanSplit(boolean playerCanSplit) {	this.playerCanSplit = playerCanSplit;}
	public SplitHand getSplitHand() {	return splitHand;}
	public void setSplitHand(SplitHand splitHand) {	this.splitHand = splitHand;}
	public Player getPlayer() {return this.player;}
	public void setPlayer(Player player) {this.player = player;}
	public Result getResult() {return this.result;}
	public void setResult(Result result) {this.result = result;}
}
