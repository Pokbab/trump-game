package com.cdg.game.trump.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cdg.game.trump.domain.Player;
import com.cdg.game.trump.exception.TrumpException;

public class GameServiceTest {
	
	private GameService gameService;
	
	@Before
	public void setup() throws Exception {
		this.gameService = new GameService();
	}
	
	@Test
	public void test플레이어를_등록한다() throws Exception {
		// Given
		String playerName = "최강훈";
		
		Player player = new Player();
		player.setName(playerName);
		
		// When
		gameService.registPlayer(player);
		List<Player> actual = gameService.getPlayerList();

		// Then
		assertTrue(actual.size() == 1);
		assertEquals(playerName, actual.get(0).getName());
	}
	
	@Test(expected = TrumpException.class)
	public void test플레이어는_4명까지_등록가능하다() throws Exception {
		// When
		while (true) {
			gameService.registPlayer(new Player());
		}
	}
	
	@Test
	public void test숫자의_합이_가장_작은_Player가_이긴다() throws Exception {
		// Given
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		
		gameService.registPlayer(player1);
		gameService.registPlayer(player2);
		gameService.registPlayer(player3);
		gameService.setCardList(CardGenerator.generateCardList());

		// When
		Player actual = gameService.play();

		// Then
		for (Player player : gameService.getPlayerList()) {
			if (player == actual) {
				continue;
			}
			
			assertTrue(actual.getScore() < player.getScore());
		}
	}
	
	@Test
	public void test승자가_2명이상인경우_남은인원끼리_재경기를_한다() throws Exception {
		// Given
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		
		gameService.registPlayer(player1);
		gameService.registPlayer(player2);
		gameService.registPlayer(player3);
		gameService.registPlayer(player4);
		gameService.setCardList(CardGenerator.generateCardList());

		// When
		Player actual = gameService.play();

		// Then
		for (Player player : gameService.getPlayerList()) {
			if (player == actual || player.getReplayCount() != actual.getReplayCount()) {
				continue;
			}
			
			assertTrue(actual.getScore() < player.getScore());
		}
	}
}
