package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.statefarm.codingcomp.Analyzer;
import com.statefarm.codingcomp.DataFilter;
import com.statefarm.codingcomp.enums.PolicyStatus;
import com.statefarm.codingcomp.model.Policy;

public class FiltersPanel extends JPanel implements ItemListener {
	private static final long serialVersionUID = 4645758996733040911L;
	
	private DataReceiver object;
	private List<Policy> data;
	
	private String[] policyTypes, policyStatuses, states;
	
	private static final String[] premiumRanges = getPremiumRangeTitles(), ageRanges = getRangeTitles(RangeFilters.ageRanges), accidentRanges = getRangeTitles(RangeFilters.numAccidentRanges);
	
	private JComboBox<String> typeSelector, statusSelector, stateSelector, premiumSelector, ageSelector, accidentSelector;

	public FiltersPanel(DataReceiver object, List<Policy> data) {
		this.data = data;
		this.object = object;
		this.policyTypes = Analyzer.findPolicyTypes(data);
		Arrays.sort(policyTypes);
		List<String> ptypes = new ArrayList<String>(Arrays.asList(policyTypes));
		ptypes.add(0, "-");
		this.policyTypes = ptypes.toArray(new String[0]);
		this.states = Analyzer.findStates(data);
		Arrays.sort(states);
		List<String> stypes = new ArrayList<String>(Arrays.asList(states));
		stypes.add(0, "-");
		this.states = stypes.toArray(new String[0]);
		this.policyStatuses = new String[PolicyStatus.values().length + 1];
		this.policyStatuses[0] = "-";
		for (int i = 0; i < PolicyStatus.values().length; i++) {
			this.policyStatuses[i + 1] = PolicyStatus.values()[i].getUserFriendlyName();
		}
		
		
		this.setBorder(BorderFactory.createTitledBorder("Filter Data"));
		this.setLayout(new FlowLayout());
		
		typeSelector = new JComboBox<String>(policyTypes);
		this.add(genPanel("Policy Type:", typeSelector));
		
		statusSelector = new JComboBox<String>(policyStatuses);
		this.add(genPanel("Policy Status:", statusSelector));
		
		stateSelector = new JComboBox<String>(states);
		this.add(genPanel("State:", stateSelector));
		
		premiumSelector = new JComboBox<String>(premiumRanges);
		this.add(genPanel("Premium:", premiumSelector));

		ageSelector = new JComboBox<String>(ageRanges);
		this.add(genPanel("Age:", ageSelector));

		accidentSelector = new JComboBox<String>(accidentRanges);
		this.add(genPanel("Accidents:", accidentSelector));
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			
			DataFilter filter = new DataFilter(data);
			if (typeSelector.getSelectedIndex() > 0) {
				filter = filter.policyType(typeSelector.getItemAt(typeSelector.getSelectedIndex()));
			}
			if (statusSelector.getSelectedIndex() > 0) {
				filter = filter.policyStatus(PolicyStatus.values()[statusSelector.getSelectedIndex() - 1]);
			}
			if (stateSelector.getSelectedIndex() > 0) {
				filter = filter.state(stateSelector.getItemAt(stateSelector.getSelectedIndex()));
			}
			if (premiumSelector.getSelectedIndex() > 0) {
				filter = filter.annualPremium(RangeFilters.premiumAmountRanges[premiumSelector.getSelectedIndex() - 1]);
			}
			if (ageSelector.getSelectedIndex() > 0) {
				filter = filter.ageRange(RangeFilters.ageRanges[ageSelector.getSelectedIndex() - 1]);
			}
			if (accidentSelector.getSelectedIndex() > 0) {
				filter = filter.numAccidentsRange(RangeFilters.numAccidentRanges[accidentSelector.getSelectedIndex() - 1]);
			}
			
			object.dataUpdated(filter.getPolicies());
		}
	}
	
	private JPanel genPanel(String label, JComboBox<String> combo) {
		combo.addItemListener(this);
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
	
	private static String[] getPremiumRangeTitles() {
		List<String> lst = Arrays.asList(RangeFilters.premiumAmountRanges).stream().map(item -> item.toString(true)).collect(Collectors.toList());
		lst.add(0, "-");
		return lst.toArray(new String[0]);
	}
	
	private static String[] getRangeTitles(Range[] rng) {
		List<String> lst = Arrays.asList(rng).stream().map(item -> item.toString()).collect(Collectors.toList());
		lst.add(0, "-");
		return lst.toArray(new String[0]);
	}
}
