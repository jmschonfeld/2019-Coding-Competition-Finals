package com.statefarm.codingcomp;

import java.util.HashSet;
import java.util.List;

import com.statefarm.codingcomp.model.Policy;
import com.statefarm.codingcomp.reader.Reader;

public class Analyzer {

	public static void main(String[] args) throws Exception {
		List<Policy> policies = new Reader().read();
		//TODO - find something interesting in the data
	}
	
	public static String[] findPolicyTypes(List<Policy> policies) {
		HashSet<String> types = new HashSet<String>();
		for (Policy policy : policies) {
			types.add(policy.getPolicyType());
		}
		return types.toArray(new String[0]);
	}
	
	public static String[] findStates(List<Policy> policies) {
		HashSet<String> states = new HashSet<String>();
		for (Policy policy : policies) {
			states.add(policy.getState());
		}
		return states.toArray(new String[0]);
	}
}
