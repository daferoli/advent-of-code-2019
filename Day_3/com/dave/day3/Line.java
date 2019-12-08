package com.dave.day3;

public abstract class Line {
	private Integer magnitude;
	private Line previousLine;

	public Line() {
		this.magnitude = 0;
		this.previousLine = null;
	}

	public Integer getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(Integer magnitude) {
		this.magnitude = magnitude;
	}
	
	public Line getPreviousLine() {
		return previousLine;
	}

	public void setPreviousLine(Line previousLine) {
		this.previousLine = previousLine;
	}
	
	public Integer calculateTotalMagnitude() {
		System.out.println(magnitude);
		System.out.println(previousLine == null);
		if(previousLine == null) {
			return magnitude;
		} else {
			return magnitude + previousLine.calculateTotalMagnitude();
		}
	}
}
