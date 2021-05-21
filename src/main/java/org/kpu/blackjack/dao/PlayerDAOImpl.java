package org.kpu.blackjack.dao;

import org.apache.ibatis.session.SqlSession;
import org.kpu.blackjack.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.SERIALIZABLE)
public class PlayerDAOImpl implements PlayerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "org.kpu.blackjack.mapper.PlayerMapper";
	
	@Transactional(readOnly=true)
	public Player read(String name) throws Exception {
		Player player = sqlSession.selectOne(namespace+".getMoney", name);
		return player;   
	}
 
	public void add(Player player) throws Exception {
		sqlSession.insert(namespace + ".insert", player);
	}

	public void delete(String name) throws Exception {
		sqlSession.delete(namespace + ".delete", name);
	}

	public void update(Player player) throws Exception {
	    sqlSession.update(namespace + ".update", player);
	}
}
