package com.statefarm.codingcomp.model;

import com.statefarm.codingcomp.enums.PolicyStatus;

public class Policy {

	private String[] policy;
	
	public Policy(String[] policy) {
		this.policy = policy;
	}

	public String getPolicyType() {
		return policy[27];
	}

	public PolicyStatus getPolicyStatus() {
		return PolicyStatus.from(Integer.parseInt(policy[33]));
	}

	public String getState() {
		return policy[34];
	}

	public double getAnnualPremium() {
		return Double.parseDouble(policy[38]);
	}

	public int getAge() {
		return Integer.parseInt(policy[39]);
	}

	public int getNumberOfAccidents() {
		return Integer.parseInt(policy[40]);
	}
}
