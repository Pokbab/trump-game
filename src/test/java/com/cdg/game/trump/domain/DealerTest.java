package com.cdg.game.trump.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cdg.game.trump.service.CardGenerator;

public class DealerTest {

	private static final int TOTAL_COUNT_CARD_NUM = 4 * 13;
	
	@Test
	public void test딜러는_카드를_섞어서_플레이어에게_나눠준다() throws Exception {
		// Given
		Dealer dealer = new Dealer();
		dealer.setCardList(CardGenerator.generateCardList());
		
		List<Player> playerList = new ArrayList<>();
		Player player1 = new Player();
		playerList.add(player1);
		Player player2 = new Player();
		playerList.add(player2);
		Player player3 = new Player();
		playerList.add(player3);

		// When
		dealer.distribute(playerList);

		// Then
		long actual = playerList.stream()
			.filter(player -> player.getCardList().size() == TOTAL_COUNT_CARD_NUM / playerList.size())
			.count();
		assertEquals(3, actual);
	}
	
	@Test
	public void test카드는_중복없이_나눠줘야한다() throws Exception {
		// Given
		Dealer dealer = new Dealer();
		dealer.setCardList(CardGenerator.generateCardList());
		
		List<Player> playerList = new ArrayList<>();
		Player player1 = new Player();
		playerList.add(player1);
		Player player2 = new Player();
		playerList.add(player2);
		Player player3 = new Player();
		playerList.add(player3);

		// When
		dealer.distribute(playerList);

		// Then
		Trump target = player1.getCardList().get(0);
		boolean actual = false;
		for (Player player : playerList) {
			if (player.hasDuplicationCard(target)) {
				actual = true;
				break;
			}
		}
		assertTrue(actual);
	}
}
