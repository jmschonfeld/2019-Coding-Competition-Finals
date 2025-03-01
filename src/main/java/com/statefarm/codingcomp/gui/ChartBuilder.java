package com.statefarm.codingcomp.gui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.LegendPosition;

import com.statefarm.codingcomp.enums.PolicyField;
import com.statefarm.codingcomp.enums.YAxis;
import com.statefarm.codingcomp.model.Policy;

public class ChartBuilder {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private List<Policy> policies;
	private String desc;
	
	public ChartBuilder(List<Policy> policies) {
		this(policies, "");
	}
	
	public ChartBuilder(List<Policy> policies, String desc) {
		this.policies = policies;
		this.desc = desc;
	}
	
	private Map<String, List<Policy>> getBuckets(PolicyField dataType) {
		Range[] ranges;
		switch (dataType) {
		case PREMIUM_AMOUNT:
			ranges = RangeFilters.premiumAmountRanges;
			break;
		case AGE:
			ranges = RangeFilters.ageRanges;
			break;
		case NUMBER_OF_ACCIDENTS:
			ranges = RangeFilters.numAccidentRanges;
			break;
		default:
			ranges = null;	
		}
		
		Map<String, List<Policy>> buckets = new LinkedHashMap<>();
		
		boolean useDollar = dataType == PolicyField.PREMIUM_AMOUNT;
		if (ranges != null) {
			for (Range range : ranges) {
				buckets.put(range.toString(useDollar), new ArrayList<>());
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
				List<Policy> bucket = buckets.getOrDefault(name, new ArrayList<>());
				bucket.add(policy);
				buckets.put(name, bucket);
				break;
			case PREMIUM_AMOUNT:
			case AGE:
			case NUMBER_OF_ACCIDENTS:
				double val;
				if (value instanceof Integer) {
					val = (int) value;
				} else {
					val = (double) value;
				}
				name = getRange(ranges, val).toString(useDollar);
				buckets.get(name).add(policy);
				break;
			}
		}
		return buckets;
	}
	
	public void pieChart(PolicyField dataType) {
		PieChart chart = new PieChartBuilder()
				.width(WIDTH)
				.height(HEIGHT)
				.title("Pie chart (" + this.desc + ")")
				.build();
		
		Map<String, Integer> series = new LinkedHashMap<>();
		
		Map<String, List<Policy>> buckets = getBuckets(dataType);
		for (String name : buckets.keySet()) {
			series.put(name, buckets.get(name).size());
		}
		
		for (String name : series.keySet()) {
			chart.addSeries(name, series.get(name));
		}
		
		display(chart);
	}
	
	public void barChart(PolicyField xAxis, YAxis yAxis) {
		CategoryChart chart = new CategoryChartBuilder()
				.width(WIDTH)
				.height(HEIGHT)
				.title("Bar chart (" + this.desc + ")")
				.xAxisTitle(xAxis.toString())
				.yAxisTitle(yAxis.toString())
				.build();
		
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setAvailableSpaceFill(0.96);
		chart.getStyler().setOverlapped(false);
		
		Map<String, List<Policy>> buckets = getBuckets(xAxis);
		List<String> xData = new ArrayList<>();
		List<Double> yData = new ArrayList<>();
		for (String name : buckets.keySet()) {
			System.out.println(name + ": " + buckets.get(name).size());
			double value = 0;
			for (Policy policy : buckets.get(name)) {
				switch (yAxis) {
				case NUMBER_OF_POLICIES:
					value++;
					break;
				case NUMBER_OF_ACCIDENTS:
				case AVERAGE_NUMBER_OF_ACCIDENTS:
					value += policy.getNumberOfAccidents();
					break;
				case ANNUAL_PREMIUM:
				case AVERAGE_ANNUAL_PREMIUM:
					value += policy.getAnnualPremium();
					break;
				}
			}
			if (yAxis == YAxis.AVERAGE_ANNUAL_PREMIUM || yAxis == YAxis.AVERAGE_NUMBER_OF_ACCIDENTS) {
				value /= buckets.get(name).size();
			}
			xData.add(name);
			yData.add(value);
		}
		chart.addSeries("data", xData, yData);
		
		display(chart);
	}
	
	private void display(Chart chart) {
		JFrame frame = new SwingWrapper(chart).displayChart();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	private Range getRange(Range[] ranges, double value) {
		for (Range range : ranges) {
			if (range.includes(value)) {
				return range;
			}
		}
		return null;
	}
}
