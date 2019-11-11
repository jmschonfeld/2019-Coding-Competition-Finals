package com.statefarm.codingcomp.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.statefarm.codingcomp.model.Policy;

public enum PolicyField {
	TYPE,
	STATUS,
	STATE,
	PREMIUM_AMOUNT,
	AGE,
	NUMBER_OF_ACCIDENTS;
	
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
	
	public Object getValue(Policy policy) {
		switch (this) {
		case TYPE:
			return policy.getPolicyType();
		case STATUS:
			return String.join(" ", Arrays.asList(policy.getPolicyStatus().name().split("_"))
					.stream()
					.map(part -> part.charAt(0) + part.substring(1).toLowerCase())
					.collect(Collectors.toList())).replaceAll(" By ", " by ");
		case STATE:
			return policy.getState();
		case PREMIUM_AMOUNT:
			return policy.getAnnualPremium();
		case AGE:
			return policy.getAge();
		case NUMBER_OF_ACCIDENTS:
			return policy.getNumberOfAccidents();
		}
		return null;
	}
}
