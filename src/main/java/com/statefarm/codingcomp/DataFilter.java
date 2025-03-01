package com.statefarm.codingcomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.statefarm.codingcomp.enums.PolicyStatus;
import com.statefarm.codingcomp.gui.Range;
import com.statefarm.codingcomp.model.Policy;

public class DataFilter {

	private List<Policy> policies;
	
	private String description = "";
	
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
		addDesc("Type=" + concat(types));
		return filter(policy -> contains(types, policy.getPolicyType()));
	}
	
	public DataFilter policyStatus(PolicyStatus... statuses) {
		String[] strs = Arrays.asList(statuses).stream().map(item -> item.getUserFriendlyName()).collect(Collectors.toList()).toArray(new String[0]);
		addDesc("Status=" + concat(strs));
		return filter(policy -> contains(statuses, policy.getPolicyStatus()));
	}
	
	public DataFilter state(String... states) {
		addDesc("State=" + concat(states));
		return filter(policy -> contains(states, policy.getState()));
	}
	
	public DataFilter annualPremium(Range range) {
		addDesc("Premium=" + range.toString());
		return filter(policy -> range.includes(policy.getAnnualPremium()));
	}
	
	public DataFilter age(int age) {
		addDesc("Age=" + age);
		return filter(policy -> age == policy.getAge());
	}
	
	public DataFilter ageRange(Range range) {
		addDesc("Age=" + range.toString());
		return filter(policy -> range.includes(policy.getAge()));
	}
	
	public DataFilter numAccidentsRange(Range range) {
		addDesc("Accidents=" + range.toString());
		return filter(policy -> range.includes(policy.getNumberOfAccidents()));
	}
	
	private String concat(String[] vals) {
		String s = "";
		for (int i = 0; i < vals.length; i++) {
			s += vals[i];
			if (i < vals.length - 1) {
				s += "/";
			}
		}
		return s;
	}
	
	private void addDesc(String d) {
		if (!this.description.equals("")) {
			description += ", ";
		}
		description += d;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
