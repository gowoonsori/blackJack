package org.kpu.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public final class SplitHand {
	private final List<Card> splitLeftCards = new ArrayList<Card>();
	private String splitLeftGameMessage = "";
	private String splitLeftHandValue = "";
	private boolean splitLeftBust;
	private Result leftResult=Result.DRAW;
	
	private final List<Card> splitRightCards = new ArrayList<Card>();
	private String splitRightGameMessage = "";
	private String splitRightHandValue = "";
	private boolean splitRightBust;
	private Result rightResult = Result.DRAW;

	/*Card가 21을 넘었는지 확인 */
	public boolean checkIfSplitHandBust(List<Card> cards, boolean isSplitLeft) {
		int total = 0;
		for (Card card : cards) {
			total = total + card.getRank().getCardValue();
		}
		if (isSplitLeft) {
			if (total > Consts.TWENTY_ONE) {
				this.splitLeftBust = true;
				this.splitLeftGameMessage = Consts.PLAYER_BUST;
				this.leftResult = Result.LOSE;
				return true;
			}
		} else {
			if (total > Consts.TWENTY_ONE) {
				this.splitRightBust = true;
				this.splitRightGameMessage = Consts.PLAYER_BUST;
				this.rightResult = Result.LOSE;
				return true;
			}
		}
		return false;
	}

	/*카드 값 계산후 message set*/
	public void calculateSplitHandValues(List<Card> cards,
		boolean playerFinishedDrawingCards, boolean isSplitLeft) {
		int total = 0;
		int numberOfPlayersAces = 0;

		for (Card card : cards) {
			total = total + card.getRank().getCardValue();
			numberOfPlayersAces = ifAceThenIncrementAceCount(numberOfPlayersAces, card);
		}

		if (isSplitLeft) {
			this.splitLeftHandValue = this.calculateHandValue(playerFinishedDrawingCards, total, numberOfPlayersAces);
		} else {
			this.splitRightHandValue = this.calculateHandValue(playerFinishedDrawingCards, total, numberOfPlayersAces);
		}
	}

	/*카드 값 계산*/
	private String calculateHandValue(boolean playerFinishedDrawingCards, int total, int numberOfAces) {
		String handValue;
		handValue = String.valueOf(total);
		if ((total <= 11) && numberOfAces != 0) {
			if (playerFinishedDrawingCards) handValue = String.valueOf(total + 10);
			 else handValue = handValue + " or " + String.valueOf(total + 10);
		}	
		return handValue;
	}

	/*Ace개수 증가*/
	private int ifAceThenIncrementAceCount(int numberOfAces, Card card) {
		if (card.getRank().getCardValue() == 1) numberOfAces++;
		return numberOfAces;
	}
	
	
	public String getSplitLeftGameMessage() { return splitLeftGameMessage;	}
	public String getSplitRightGameMessage() {	return splitRightGameMessage;}
	public boolean isSplitLeftBust() {	return splitLeftBust;}
	public boolean isSplitRightBust() {	return splitRightBust;}
	public String getSplitLeftHandValue() {return splitLeftHandValue;}
	public String getSplitRightHandValue() {	return splitRightHandValue;}
	public List<Card> getSplitLeftCards() {	return splitLeftCards;}
	public List<Card> getSplitRightCards() {	return splitRightCards;}
	public Result getLeftResult() {	return leftResult;}
	public Result getRightResult() {	return rightResult;}

	public void setSplitLeftGameMessage(String splitLeftGameMessage) {this.splitLeftGameMessage = splitLeftGameMessage;	}
	public void setSplitRightGameMessage(String splitRightGameMessage) {this.splitRightGameMessage = splitRightGameMessage;}
	public void setSplitLeftBust(boolean splitLeftBust) {this.splitLeftBust = splitLeftBust;}
	public void setSplitRightBust(boolean splitRightBust) {	this.splitRightBust = splitRightBust;}
	public void setSplitLeftHandValue(String splitLeftHandValue) {	this.splitLeftHandValue = splitLeftHandValue;}
	public void setSplitRightHandValue(String splitRightHandValue) {	this.splitRightHandValue = splitRightHandValue;}
	public void setLeftResult(Result result) {	this.leftResult= result;}
	public void setRightResult(Result result) {	this.rightResult= result;}
}