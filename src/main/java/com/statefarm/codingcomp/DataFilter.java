package com.statefarm.codingcomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.statefarm.codingcomp.enums.PolicyStatus;
import com.statefarm.codingcomp.model.Policy;

public class DataFilter {

	private List<Policy> policies;
	
	public DataFilter(List<Policy> policies) {
		this.policies = policies;
	}
	
	public DataFilter policyType(String... types) {
		List<String> typeList = Arrays.asList(types);
		List<Policy> filtered = new ArrayList<>();
		for (Policy policy : policies) {
			if (typeList.contains(policy.getPolicyType())) {
				filtered.add(policy);
			}
		}
		policies = filtered;
		return this;
	}
	
	public DataFilter policyStatus(PolicyStatus... statuses) {
		return this;
	}
	
	public DataFilter state(String... states) {
		return this;
	}
	
	public DataFilter annualPremium(double min, double max) {
		return this;
	}
	
	public DataFilter age(int age) {
		return this;
	}
	
	public DataFilter ageRange(int min, int max) {
		return this;
	}
	
	public DataFilter numAccidentsRange(int min, int max) {
		return this;
	}
}
