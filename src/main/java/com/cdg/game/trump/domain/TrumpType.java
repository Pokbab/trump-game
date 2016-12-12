package com.cdg.game.trump.domain;

public enum TrumpType {
	SPADE("S"), 
	HEART("H"), 
	CLOVER("C"), 
	DIAMOND("D");
	
	private String displayCode;
	
	private TrumpType(String displayCode) {
		this.displayCode = displayCode;
	}
	
	public String getDisplayCode() {
		return displayCode;
	}
}
