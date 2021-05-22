package org.kpu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class BlackJackControllerAdvice {
	
    @ExceptionHandler(ChangeBettingMoneyException.class)
    public ResponseEntity<String> moneyExceptionHandle(ChangeBettingMoneyException e) {
        return new ResponseEntity<>("배팅money가 잔액을 초과했습니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GameOverException.class)
    public ResponseEntity<String> gameOverhandleException(GameOverException e) {
    	 return new ResponseEntity<>("돈을 탕진하셨습니다.", HttpStatus.PRECONDITION_FAILED);
    }
}