package com.statefarm.codingcomp.gui;

public class RangeFilters {
	public static final Range[] premiumAmountRanges = {
			new Range(0, 499),
			new Range(500, 999),
			new Range(1000, 1499),
			new Range(1500, 1999),
			new Range(2000, Integer.MAX_VALUE)
	};
	
	public static final Range[] ageRanges = {
			new Range(0, 19),
			new Range(20, 64),
			new Range(65, Integer.MAX_VALUE)
	};
	
	public static final Range[] numAccidentRanges = {
			new Range(0, 0),
			new Range(1, 1),
			new Range(2, 2),
			new Range(3, Integer.MAX_VALUE)
	};
}
