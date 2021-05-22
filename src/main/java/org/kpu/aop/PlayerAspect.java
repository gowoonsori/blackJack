package org.kpu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.kpu.blackjack.dao.PlayerDAO;
import org.kpu.blackjack.domain.Consts;
import org.kpu.blackjack.domain.Result;
import org.kpu.blackjack.domain.Round;
import org.kpu.exception.GameOverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PlayerAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(PlayerAspect.class);

	@Autowired
	PlayerDAO dao;
	
	@Autowired
	Round round;
	
	@Before(value="execution(* org.kpu.blackjack.services.*.*(..))")
	public void serviceMethodLog(JoinPoint jp) {
		logger.debug("실행 메서드 : BlackJackService." + jp.getSignature().getName());
	}
	
	@After(value="execution(* org.kpu.blackjack.services.*.*(..))")
 	public void updatePlayer(JoinPoint jp) throws Exception {
		if(round.getResult() != Result.DRAW) {
			logger.debug(round.getPlayer().getName()+"님이 "+ round.getResult() + " 하셔서 "+round.getPlayer().getMoney()+"원 남았습니다.");
			dao.update(round.getPlayer());
		}
		if(round.getGameMessage().equals(Consts.PLAYER_SPLITS)) {
			if(round.getSplitHand().getRightResult() != Result.DRAW ) {
				logger.debug(round.getPlayer().getName()+"님이 "+ round.getSplitHand().getRightResult() + " 하셔서 "+round.getPlayer().getMoney()+"원 남았습니다.");
				dao.update(round.getPlayer());
			}else if(round.getSplitHand().getLeftResult() != Result.DRAW) {
				logger.debug(round.getPlayer().getName()+"님이 "+ round.getSplitHand().getRightResult() + " 하셔서 "+round.getPlayer().getMoney()+"원 남았습니다.");
				dao.update(round.getPlayer());
			}
		}else if(round.getGameMessage().equals(Consts.LOW_CREDITS_MESSAGE)) {
			logger.info("gameOver");
			dao.delete(round.getPlayer().getName());
			throw new GameOverException();
		}
	}
}
