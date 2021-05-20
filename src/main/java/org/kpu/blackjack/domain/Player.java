package org.kpu.blackjack.domain;

public class Player {
	private String name;
	private double money;
	
	public Player(String name) {
		this.name = name;
		this.money = Consts.STARTING_CREDITS;
	}
	
	
	public String getName() {
		return this.name;
	}
	public double getMoney() {
		return this.money;
	}
	public void setMoney(double money) { this.money = money;}
	
	@Override
	public String toString() {
		return "name : " + this.name + ", money : " + money;
	}
}
