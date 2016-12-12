package com.cdg.game.trump.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cdg.game.trump.domain.Trump;
import com.cdg.game.trump.domain.TrumpType;

public final class CardGenerator {
	
	private static final int MAX_CARD_NUM = 13;
	private static List<Trump> cardList;
	
	private CardGenerator() { }
	
	public static List<Trump> generateCardList() {
		if (cardList != null) {
			return cardList;
		}
		
		cardList = new ArrayList<>();
		
		Arrays.asList(TrumpType.values()).stream()
			.forEach(trumpType -> {
				
				for (int i = 1; i <= MAX_CARD_NUM; i++) {
					cardList.add(new Trump(trumpType, i));
				}
				
			});
		return cardList;
	}
}
