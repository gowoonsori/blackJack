package org.kpu.blackjack.services;

import org.kpu.blackjack.domain.Round;

public interface BlackJackService {

	Round startGame(String name) throws Exception;
	
	Round startRound();

	Round hitPlayer();

	Round playerDoubles();
	
	Round playerStands();

	Round changeBet(String betSize);

	Round playerSplits();

	Round splitLeftHitPlayer();

	Round splitLeftPlayerStands();

	Round splitRightHitPlayer();

	Round splitRightPlayerStands();

}
