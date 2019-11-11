package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.statefarm.codingcomp.enums.PolicyField;
import com.statefarm.codingcomp.enums.YAxis;
import com.statefarm.codingcomp.model.Policy;

public class ChartCreatorPanel extends JPanel implements ActionListener, DataReceiver {
	private static final long serialVersionUID = 7124665277931954713L;
	
	private static final PolicyField[] xVals = PolicyField.values();
	private static final YAxis[] yVals = YAxis.values();
	
	private JComboBox<String> xSelector, ySelector;

	private List<Policy> data;
	
	ChartCreatorPanel(List<Policy> data) {
		this.data = data;
		this.setBorder(BorderFactory.createTitledBorder("Create Chart"));
		
		//TODO
		this.setLayout(new FlowLayout());
		
		xSelector = new JComboBox<String>(genNames(xVals, false));
		this.add(genPanel("X Variable:", xSelector));
		ySelector = new JComboBox<String>(genNames(yVals, true));
		this.add(genPanel("Y Variable:", ySelector));
		
		JButton displayChart = new JButton("Generate");
		displayChart.addActionListener(this);
		this.add(displayChart);
	}
	
	private JPanel genPanel(String label, JComboBox<String> combo) {
		combo.setPreferredSize(new Dimension(100, 20));
		JLabel jlbl = new JLabel(label);
		JPanel lblPanel = new JPanel();
		lblPanel.setLayout(new BorderLayout());
		lblPanel.add(jlbl, BorderLayout.WEST);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(lblPanel);
		panel.add(combo);
		return panel;
	}
	
	private String[] genNames(PolicyField[] fields, boolean addDash) {
		List<String> lst = Arrays.asList(fields).stream().map(item -> item.getUserFriendlyName()).collect(Collectors.toList());
		if (addDash) {
			lst.add(0, "-");
		}
		return lst.toArray(new String[0]);
	}
	
	private String[] genNames(YAxis[] fields, boolean addDash) {
		List<String> lst = Arrays.asList(fields).stream().map(item -> item.getUserFriendlyName()).collect(Collectors.toList());
		if (addDash) {
			lst.add(0, "-");
		}
		return lst.toArray(new String[0]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread() {
			@Override
			public void run() {
				ChartBuilder builder = new ChartBuilder(data);
				if (ySelector.getSelectedIndex() == 0) {
					builder.pieChart(xVals[xSelector.getSelectedIndex()]);
				} else {
					builder.barChart(xVals[xSelector.getSelectedIndex()], yVals[ySelector.getSelectedIndex() - 1]);
				}
			}
		}.start();
	}

	@Override
	public void dataUpdated(List<Policy> policies) {
		this.data = policies;
	}
}
