package com.dave.day3;

public class Coordinate {
	private Integer x;
	private Integer y;
	private VerticleLine vLine;
	private HorizontalLine hLine;
	
	public Coordinate() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
	public void moveX(Integer distance) {
		this.x += distance;
	}
	
	public void moveY(Integer distance) { 
		this.y += distance;
	}
	
	public Integer manhattanDistance() {
		return Math.abs(x) + Math.abs(y);
	}

	public VerticleLine getvLine() {
		return vLine;
	}

	public void setvLine(VerticleLine vLine) {
		this.vLine = vLine;
	}

	public HorizontalLine gethLine() {
		return hLine;
	}

	public void sethLine(HorizontalLine hLine) {
		this.hLine = hLine;
	}
	
}
