package com.cdg.game.trump.domain;

import java.util.Collections;
import java.util.List;

public class Dealer {
	
	private List<Trump> cardList;

	public Dealer setCardList(List<Trump> cardList) {
		this.cardList = cardList;
		return this;
	}
	
	public Dealer shuffle() {
		Collections.shuffle(cardList);
		return this;
	}

	public void distribute(List<Player> playerList) {
		int cardQuantity = cardList.size();
		int playerQuantity = playerList.size();
		
		int divideNum = cardQuantity / playerQuantity;
		int fromIndex = 0;
		for (Player player : playerList) {
			List<Trump> dividedCardList = cardList.subList(fromIndex, fromIndex + divideNum);
			player.setCardList(dividedCardList);
			
			fromIndex = divideNum;
		}
	}
}
