package com.jack12324.eop.util;

public enum Upgrade{
	SPEED("speed",10),
	ENERGY("energy",10);
	
	private String name;
	private int maxStack;
	private Upgrade(String name, int maxStack){
		this.name=name;
		this.maxStack=maxStack;
	}
	public int getMax(){
		return maxStack;
	}
}
