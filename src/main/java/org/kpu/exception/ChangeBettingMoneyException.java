package org.kpu.exception;

public class ChangeBettingMoneyException extends Exception {
	private static final long serialVersionUID = 1001L;

    public ChangeBettingMoneyException() {
    	super("배팅금액이 남은 금액보다 큽니다.");
    }
  
}
