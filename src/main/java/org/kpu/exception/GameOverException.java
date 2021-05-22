package org.kpu.exception;

public class GameOverException extends Exception{
	private static final long serialVersionUID = 1000L;

    public GameOverException() {
    	super("Game Over");
    }
}
