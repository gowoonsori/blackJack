package org.kpu.blackjack;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kpu.blackjack.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MyBatisTest {

	@Autowired
	private SqlSessionFactory sqlFactory;
	
	private static final String namespace = "org.kpu.blackjack.mapper.UserMapper";
	
	@Test
	public void getMoneyTest() throws Exception {
		try(SqlSession session = sqlFactory.openSession()) {
			Player player = session.selectOne(namespace + ".getMoney","hong");
			System.out.println(player);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
