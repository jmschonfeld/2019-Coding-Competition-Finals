package com.statefarm.codingcomp.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;

import com.statefarm.codingcomp.enums.PolicyField;
import com.statefarm.codingcomp.model.Policy;

public class ChartBuilder {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private List<Policy> policies;
	
	public ChartBuilder(List<Policy> policies) {
		this.policies = policies;
	}
	
	public void pieChart(PolicyField dataType) {
		PieChart chart = new PieChartBuilder()
				.width(WIDTH)
				.height(HEIGHT)
				.title("Pie chart")
				.build();
		
		Map<String, Integer> series = new HashMap<>();
		
		Range[] ranges;
		switch (dataType) {
		case PREMIUM_AMOUNT:
			ranges = RangeFilters.premiumAmountRanges;
			break;
		case AGE:
			ranges = RangeFilters.ageRanges;
			break;
		case NUM_ACCIDENTS:
			ranges = RangeFilters.numAccidentRanges;
			break;
		default:
			ranges = null;	
		}
		boolean useDollar = dataType == PolicyField.PREMIUM_AMOUNT;
		if (ranges != null) {
			for (Range range : ranges) {
				series.put(range.toString(useDollar), 0);
			}
		}
		
		for (Policy policy : policies) {
			Object value = dataType.getValue(policy);
			String name;
			switch (dataType) {
			case TYPE:
			case STATUS:
			case STATE:
				name = value.toString();
				series.put(name, series.getOrDefault(name, 0) + 1);
				break;
			case PREMIUM_AMOUNT:
			case AGE:
			case NUM_ACCIDENTS:
				double val;
				if (value instanceof Integer) {
					val = (int) value;
				} else {
					val = (double) value;
				}
				name = getRange(ranges, val).toString(useDollar);
				series.put(name, series.get(name) + 1);
				break;
			}
		}
		
		for (String name : series.keySet()) {
			chart.addSeries(name, series.get(name));
		}
		
		display(chart);
	}
	
	public void barChart(int xDataType, int yDataType) {
		
	}
	
	private void display(Chart chart) {
		new SwingWrapper(chart).displayChart();
	}
	
	private Range getRange(Range[] ranges, double value) {
		for (Range range : ranges) {
			if (range.includes(value)) {
				return range;
			}
		}
		return null;
	}
	
	/*
	private Chart buildBarChart(ControlFilters filters) {
		CategoryChart chart = new CategoryChartBuilder()
				.width(WIDTH)
				.height(HEIGHT)
				.title("Bar chart")
				.xAxisTitle("Year")
				.yAxisTitle("Incidents")
				.build();
		
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setAvailableSpaceFill(0.96);
		chart.getStyler().setOverlapped(false);
		
		for (String cat : categories) {
			int[] incidents = new int[years.length];
			for (int i = 0; i < years.length; i++) {
				incidents[i] = yearCatIncidents.getOrDefault(years[i] + "", new HashMap<String, Integer>())
						.getOrDefault(cat, 0);
			}
			chart.addSeries(cat, years, incidents);
		}
		
		return chart;
	}*/
}
