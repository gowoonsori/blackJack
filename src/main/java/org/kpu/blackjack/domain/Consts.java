package org.kpu.blackjack.domain;


public final class Consts  {
	  public static final int STARTING_CREDITS = 10000000;  //시작 금액(천만원)
	  public static final int STARTING_BET = 10000;	 //시작 배팅금액(만원)
	  public static final int LOW_CREDITS_VALUE = 1000; //최소 배팅 금액(천원)
	  public static final int  TWENTY_ONE = 21;
	  public static final int  MIN_VALUE_THAT_DEALER_STANDS = 17;	//17이하여야 한장 더 받을 수 있음
	  public static final String PLAYER_WINS = "이겼습니다!";
	  public static final String PLAYER_WINS_WITH_BLACKJACK = "플레이어가 BlackJack으로 이겼습니다.";
	  public static final String DEALER_WINS_WITH_BLACKJACK = "딜러가 BlackJack으로 졌습니다.";
	  public static final String PLAYER_LOSES = "졌습니다.";
	  public static final String DRAW = "비겼습니다.";
	  public static final String PLAYER_BUST = "플레이어의 카드합이 21을 넘어셔 졌습니다.";
	  public static final String DEALER_BUST = "딜러의 카드합이 21을 넘어서 이겼습니다.";
	  public static final String LOW_CREDITS_MESSAGE = "돈을 모두 탕진하였습니다.";
	  public static final String PLAYER_SPLITS = "Split";
}
