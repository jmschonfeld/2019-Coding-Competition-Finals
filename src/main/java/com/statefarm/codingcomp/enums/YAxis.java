package com.statefarm.codingcomp.enums;

public enum YAxis {
	NUMBER_OF_POLICIES,
	NUMBER_OF_ACCIDENTS,
	AVERAGE_NUMBER_OF_ACCIDENTS,
	ANNUAL_PREMIUM,
	AVERAGE_ANNUAL_PREMIUM;
	
	public String getUserFriendlyName() {
		String[] parts = this.name().toLowerCase().split("_");
		StringBuilder builder = new StringBuilder();
		for (String part : parts) {
			builder.append(part.substring(0, 1).toUpperCase());
			builder.append(part.substring(1));
			builder.append(" ");
		}
		return builder.toString();
	}
}
