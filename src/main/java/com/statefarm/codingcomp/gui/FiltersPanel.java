package com.statefarm.codingcomp.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FiltersPanel extends JPanel implements ItemListener {
	private static final long serialVersionUID = 4645758996733040911L;
	
	private DataReceiver object;
	
	private JComboBox<String> chartTypeSelector, xAxisSelector;
	
	private JLabel xAxisTitle;

	public FiltersPanel(DataReceiver object) {
		this.object = object;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Filter Data"));
		JLabel typeLabel = new JLabel("Chart Type");
		typeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		this.add(typeLabel);
		chartTypeSelector = new JComboBox<String>();
		chartTypeSelector.addItem("Pie Chart");
		chartTypeSelector.addItem("Bar Graph");
		//chartTypeSelector.addItem("Line Graph");
		chartTypeSelector.addItemListener(this);
		this.add(chartTypeSelector);
		/*
		this.add(Box.createVerticalStrut(30));
		
		xAxisTitle = new JLabel("Slice By");
		xAxisTitle.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		this.add(xAxisTitle);
		xAxisSelector = new JComboBox<String>();
		xAxisSelector.addItem("Year");
		xAxisSelector.addItem("Category/Country");
		xAxisSelector.addItemListener(this);
		this.add(xAxisSelector);*/
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			//this.filters.setChartType(chartTypeSelector.getSelectedIndex() + 1);
			//this.filters.setXAxis(xAxisSelector.getSelectedIndex() + 1);
			switch (chartTypeSelector.getSelectedIndex()) {
			case 0:
				//xAxisTitle.setText("Slice By");
				break;
			default:
				//xAxisTitle.setText("X Axis");
				break;
			}
			
			//TODO: Update object with filters
		}
	}
}
