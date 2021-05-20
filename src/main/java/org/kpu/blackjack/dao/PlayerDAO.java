package org.kpu.blackjack.dao;

import org.kpu.blackjack.domain.Player;

public interface PlayerDAO {
	public Player read(String name) throws Exception;
 
	public void add(Player player) throws Exception;

	public void delete(String name) throws Exception;

	public void update(Player player) throws Exception;
}
