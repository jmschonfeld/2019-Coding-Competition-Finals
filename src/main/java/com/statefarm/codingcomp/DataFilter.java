package com.statefarm.codingcomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.statefarm.codingcomp.enums.PolicyStatus;
import com.statefarm.codingcomp.gui.Range;
import com.statefarm.codingcomp.model.Policy;

public class DataFilter {

	private List<Policy> policies;
	
	public DataFilter(List<Policy> policies) {
		this.policies = policies;
	}
	
	private DataFilter filter(Function<? super Policy, Boolean> filter) {
		List<Policy> filtered = new ArrayList<>();
		for (Policy policy : policies) {
			if (filter.apply(policy)) {
				filtered.add(policy);
			}
		}
		this.policies = filtered;
		return this;
	}
	
	private boolean contains(Object[] objects, Object value) {
		return Arrays.asList(objects).contains(value);
	}
	
	public List<Policy> getPolicies() {
		return policies;
	}
	
	public DataFilter policyType(String... types) {
		return filter(policy -> contains(types, policy.getPolicyType()));
	}
	
	public DataFilter policyStatus(PolicyStatus... statuses) {
		return filter(policy -> contains(statuses, policy.getPolicyStatus()));
	}
	
	public DataFilter state(String... states) {
		return filter(policy -> contains(states, policy.getState()));
	}
	
	public DataFilter annualPremium(Range range) {
		return filter(policy -> range.includes(policy.getAnnualPremium()));
	}
	
	public DataFilter age(int age) {
		return filter(policy -> age == policy.getAge());
	}
	
	public DataFilter ageRange(Range range) {
		return filter(policy -> range.includes(policy.getAge()));
	}
	
	public DataFilter numAccidentsRange(Range range) {
		return filter(policy -> range.includes(policy.getNumberOfAccidents()));
	}
}
