package com.statefarm.codingcomp.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.LegendPosition;

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
		
		Map<String, Number> series = new HashMap<>();
		for (Policy policy : policies) {
			switch (dataType) {
			case TYPE:
			case STATUS:
			case STATE:
				
			}
			
			/*int incidents = 0;
			for (int year : years) {
				incidents += yearCatIncidents.getOrDefault(year + "", new HashMap<String, Integer>())
				.getOrDefault(cat, 0);
			}
			chart.addSeries(cat, incidents);*/
		}
	}
	
	public void barChart(int xDataType, int yDataType) {
		
	}
/*
	public void update(ControlFilters filters) {
		int chartType = filters.getChartType();
		
		Chart chart = null;
		if (chartType == ControlFilters.CHART_BAR) {
			chart = buildBarChart(filters);
		} else if (chartType == ControlFilters.CHART_PIE) {
			chart = buildPieChart(filters);
		}
		
		if (chart == null) {
			return;
		}
		
		if (currentXPanel != null) {
			this.remove(currentXPanel);
		}
		
		final Chart fChart = chart;
		
		new Thread(new Runnable() {

			public void run() {
				SwingWrapper wrapper = new SwingWrapper(fChart);
				JFrame frame = wrapper.displayChart();
				frame.setVisible(false);
				currentXPanel = wrapper.getXChartPanel();
				ChartView.this.add(currentXPanel);
				ChartView.this.repaint();
			}
			
		}).start();
		
		
	}
	
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
