package org.kpu.blackjack.control;

import org.kpu.blackjack.domain.Round;
import org.kpu.blackjack.services.BlackJackService;
import org.kpu.exception.ChangeBettingMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public final class BlackJackController {

	@Autowired
	private BlackJackService blackJackService;
	
	@PostMapping("/game")
	public ModelAndView game(@RequestParam String nickname) throws Exception {
		Round round = blackJackService.startGame(nickname);
		return new ModelAndView("game","round",round);
	}
	
	@GetMapping("/start")
	public Round startGame() {
		Round round = blackJackService.startRound();
		return round;
	}

	@RequestMapping("/double")
	public Round playerDoubles() throws Exception {
		Round round =blackJackService.playerDoubles();
		return round;
	}

	@RequestMapping("/hit")
	public Round hitPlayer() {
		Round round =blackJackService.hitPlayer();
		return round;
	}

	@RequestMapping("/stand")
	public Round playerStands() {
		Round round = blackJackService.playerStands();
		return round;
	}

	@RequestMapping("/split")
	public Round playerSplits() {
		Round round = blackJackService.playerSplits();
		return round;
	}
	
	@RequestMapping("/splitLeftHit")
	public Round splitLeftHitPlayer() {
		Round round = blackJackService.splitLeftHitPlayer();
		return round;
	}
	
	@RequestMapping("/splitLeftstand")
	public Round splitLeftPlayerStands() {
		Round round =  blackJackService.splitLeftPlayerStands();
		return round;
	}
	
	@RequestMapping("/splitRightHit")
	public Round splitRightHitPlayer() {
		Round round = blackJackService.splitRightHitPlayer();
		return round;
	}
	
	@RequestMapping("/splitRightstand")
	public Round splitRightPlayerStands() {
		Round round = blackJackService.splitRightPlayerStands();
		return round;
	}
	
	@RequestMapping("/change")
	public Round changeBet(@RequestParam("money") String money) throws ChangeBettingMoneyException{
		Round round = blackJackService.changeBet( money);
		return round;
	}
}
