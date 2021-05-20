package org.kpu.blackjack.domain;


public final class Card {
	private final Suit suit;  	//문양 (스페이드,클로버,다이아,하트)
	private final Rank rank;	//숫자(A,2,3,4,5,6,7,8,9,10,J(10),Q(10),K(10)

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public Card(Suit suit, Rank rank) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

}
