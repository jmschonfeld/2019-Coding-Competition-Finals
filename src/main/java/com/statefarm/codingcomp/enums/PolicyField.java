package com.statefarm.codingcomp.enums;

import com.statefarm.codingcomp.model.Policy;

public enum PolicyField {
	TYPE,
	STATUS,
	STATE,
	PREMIUM_AMOUNT,
	AGE,
	NUM_ACCIDENTS;
	
	public String getValue(Policy policy) {
		switch (this) {
		case TYPE:
			return policy.getPolicyType();
		case STATUS:
			return policy.getPolicyStatus().name();
		case STATE:
			return null;
		}
		return null;
	}
}
