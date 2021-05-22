package org.kpu.blackjack.services;

import org.kpu.blackjack.domain.Round;
import org.kpu.exception.ChangeBettingMoneyException;

public interface BlackJackService {

	Round startGame(String name) throws Exception;
	
	Round startRound();

	Round hitPlayer();

	Round playerDoubles() throws Exception;
	
	Round playerStands();

	Round changeBet(String betSize) throws ChangeBettingMoneyException;

	Round playerSplits();

	Round splitLeftHitPlayer();

	Round splitLeftPlayerStands();

	Round splitRightHitPlayer();

	Round splitRightPlayerStands();

}
