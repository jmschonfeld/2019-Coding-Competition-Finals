package com.statefarm.codingcomp.test;

import java.util.List;

import com.statefarm.codingcomp.DataFilter;
import com.statefarm.codingcomp.enums.PolicyField;
import com.statefarm.codingcomp.enums.YAxis;
import com.statefarm.codingcomp.gui.ChartBuilder;
import com.statefarm.codingcomp.model.Policy;
import com.statefarm.codingcomp.reader.Reader;

public class Test {

	public static void main(String[] args) throws Exception {
		List<Policy> policies = new Reader().read();
		DataFilter filter = new DataFilter(policies);
		
		new ChartBuilder(filter.getPolicies()).barChart(PolicyField.STATE, YAxis.NUM_POLICIES);
		new ChartBuilder(filter.getPolicies()).barChart(PolicyField.STATE, YAxis.ANNUAL_PREMIUM);
		//new ChartBuilder(filter.getPolicies()).pieChart(PolicyField.STATUS);
	}
	
}
