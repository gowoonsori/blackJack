package org.kpu.blackjack.domain;

public enum Rank {
	ACE(1,"Ace"), TWO(2,"Two"), THREE(3,"Three"), FOUR(4,"Four"), FIVE(5,"Five"), 
	SIX(6,"Six"), SEVEN(7,"Seven"), EIGHT(8,"Eight"), NINE(9,"Nine"), TEN(10,"Ten"), JACK(10,"Jack"), QUEEN(10,"Queen"), KING(10,"King");
	
	

	private final int cardValue;
	private final String cardString;

	Rank(int cardValue,String str) {
		this.cardValue = cardValue;
		this.cardString = str;
	}

	public int getCardValue() {
		return cardValue;
	}
	public String getCardString() {
		return cardString;
	}
}
