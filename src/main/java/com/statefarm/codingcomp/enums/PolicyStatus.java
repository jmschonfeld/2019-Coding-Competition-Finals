package com.statefarm.codingcomp.enums;

public enum PolicyStatus {
	ACTIVE, CANCELLED_BY_POLICYHOLDER, CANCELLED_BY_COMPANY;
	
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
	
	public static PolicyStatus from(int num) {
		if(num <= 2) {
			return ACTIVE;
		} else if(num <= 6) {
			return CANCELLED_BY_POLICYHOLDER;
		} else  {
			return CANCELLED_BY_COMPANY;
		}
	}
}
