package com.cdg.game.trump.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Player {
	
	private String name;
	private List<Trump> cardList;
	private int replayCount;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setCardList(List<Trump> cardList) {
		this.cardList = cardList;
	}

	public List<Trump> getCardList() {
		return cardList;
	}
	
	public int getReplayCount() {
		return replayCount;
	}

	public void increaseReplayCount() {
		this.replayCount++;
	}

	public boolean hasDuplicationCard(Trump target) {
		return cardList.stream()
				.anyMatch(card -> card == target);
	}
	
	public int getScore() {
		return cardList.stream()
				.collect(Collectors.summingInt(Trump::getNo));
	}
}
