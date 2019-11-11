package com.statefarm.codingcomp.gui;

public class Range {

	private int min;
	private int max;
	
	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean includes(double val) {
		return min <= val && val <= max;
	}
	
	@Override
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean showDollars) {
		String prefix = showDollars ? "$" : "";
		
		if (min == 0) {
			return "< " + prefix + max;
		} else if (max == Integer.MAX_VALUE) {
			return "> " + prefix + min;
		} else {
			return prefix + min + " - " + prefix + max;	
		}
	}
}
