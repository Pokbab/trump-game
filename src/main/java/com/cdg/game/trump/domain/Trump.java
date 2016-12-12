package com.cdg.game.trump.domain;

public final class Trump {
	
	private TrumpType type;
	
	private int no;
	
	public Trump(TrumpType type, int no) {
		this.type = type;
		this.no = no;
	}

	public TrumpType getType() {
		return type;
	}

	public int getNo() {
		return no;
	}

	@Override
	public String toString() {
		return "Trump [type=" + type + ", no=" + no + "]";
	}
}
