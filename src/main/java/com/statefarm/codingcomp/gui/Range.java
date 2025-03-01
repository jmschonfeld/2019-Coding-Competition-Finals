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
		
		if (min == max) {
			return prefix + min;
		} else if (min == 0) {
			return "< " + prefix + (max + 1);
		} else if (max == Integer.MAX_VALUE) {
			return "> " + prefix + (min - 1);
		} else {
			return prefix + min + " - " + prefix + max;	
		}
	}
}
