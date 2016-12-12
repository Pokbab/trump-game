package com.cdg.game.trump.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.cdg.game.trump.domain.Dealer;
import com.cdg.game.trump.domain.Player;
import com.cdg.game.trump.domain.Trump;
import com.cdg.game.trump.exception.TrumpException;

public class GameService {
	private static final int MAX_PLAYER_COUNT = 4;
	private static final int FIRST_INDEX_LIST = 0;
	
	private Dealer dealer;
	
	private List<Player> playerList;
	
	private List<Trump> cardList;
	
	public GameService() {
		this.playerList = new ArrayList<Player>();
		this.dealer = new Dealer();
	}

	public void registPlayer(Player player) {
		if (playerList.size() > MAX_PLAYER_COUNT) {
			throw new TrumpException("플레이어는 4명까지 등록가능합니다.");
		}
		playerList.add(player);
	}

	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public Player play() {
		List<Player> playerList = this.playerList;
		Player winner = null;
		
		do {
			dealer.setCardList(cardList)
			.shuffle()
			.distribute(playerList);
			
			int minCount = playerList.stream()
					.mapToInt(Player::getScore)
					.min()
					.getAsInt();
			
			List<Player> winnerList = playerList.stream()
					.filter(player -> player.getScore() == minCount)
					.collect(toList());
			
			if (winnerList.size() == 1) {
				winner = winnerList.get(FIRST_INDEX_LIST);
			} else {
				playerList = winnerList;
				playerList.forEach(player -> player.increaseReplayCount());
			}
			
		} while (winner == null);
		
		return winner;
	}
	
	public void setCardList(List<Trump> cardList) {
		this.cardList = cardList;
	}
}
