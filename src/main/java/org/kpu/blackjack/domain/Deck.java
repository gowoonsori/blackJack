package org.kpu.blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Deck {
	//한 덱에 카드를 담을 list
	private final List<Card> cardsInDeck = new ArrayList<Card>();

	public Deck() {
		newDeck();
	}
	
	/*새로운 덱으로 교체*/
	public void newDeck() {
		cardsInDeck.clear();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cardsInDeck.add(new Card(suit, rank));
			}
		}
	}
	
	/*덱의 남은 카드 수 get*/
	public int getRemainCards() {
		return cardsInDeck.size();
	}

	/*덱에서 한개의 카드 랜덤으로 추출*/
	public Card dealRandomCard() {
		Card card = this.cardsInDeck.get(Deck.randomInRange(0, this.cardsInDeck.size() - 1));
		this.cardsInDeck.remove(card);
		return card;
	}

	/*남은 카드 수에서 랜덤 숫자 뽑기*/
	static int randomInRange(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("범위를 벗어났습니다.");
		}
		Random random = new Random();
		int randomNum = random.nextInt(max - min + 1) + min;
		return randomNum;
	}

}
