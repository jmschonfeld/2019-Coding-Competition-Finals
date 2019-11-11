package com.statefarm.codingcomp.test;

import java.util.List;

import com.statefarm.codingcomp.DataFilter;
import com.statefarm.codingcomp.model.Policy;
import com.statefarm.codingcomp.reader.Reader;

public class Test {

	public static void main(String[] args) throws Exception {
		List<Policy> policies = new Reader().read();
		int total = 0;
		for (Policy policy : policies) {
			total += policy.getNumberOfAccidents();
		}
		System.out.println(total);
		DataFilter filter = new DataFilter(policies);
		System.out.println(filter.getPolicies().size());
	}
	
}
