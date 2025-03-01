package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.statefarm.codingcomp.DataFilter;
import com.statefarm.codingcomp.model.Policy;

public class DataPanel extends JPanel implements DataReceiver {
	private static final long serialVersionUID = 8399167358797477523L;
	
	private JTable table;
	private JLabel recordLabel;
	
	private static final Object[] HEADERS = new String[]{"Policy Type", "Policy Status", "State", "Annual Premium", "Age", "Accidents"};
	
	DataPanel() {
		this.setLayout(new BorderLayout());
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(false);
		table.setShowGrid(true);
		table.setAutoCreateRowSorter(true);
		table.setModel(new PolicyDataModel(HEADERS, 0));
		
		recordLabel = new JLabel();
		
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		south.add(recordLabel, BorderLayout.EAST);
		this.add(south, BorderLayout.SOUTH);
	}

	@Override
	public void dataUpdated(List<Policy> policies, DataFilter filter) {
		Object[][] data = new Object[policies.size()][HEADERS.length];
		int i = 0;
		for (Policy policy : policies) {
			data[i] = new Object[] {
				policy.getPolicyType(),
				policy.getPolicyStatus().getUserFriendlyName(),
				policy.getState(),
				policy.getAnnualPremium(),
				policy.getAge(),
				policy.getNumberOfAccidents()
			};
			i++;
		}
		TableModel model = new PolicyDataModel(data, HEADERS);
		table.setModel(model);
		recordLabel.setText("Data Entries: " + data.length + "   ");
	}
	
	private class PolicyDataModel extends DefaultTableModel {
		private static final long serialVersionUID = 2459270881757581627L;

		PolicyDataModel(Object[] columnNames, int numRows) {
			super(columnNames, numRows);
		}
		
		PolicyDataModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}
		
		@Override
		public Class<?> getColumnClass(int column) {
			switch (column) {
			case 0:
			case 1:
			case 2:
				return String.class;
			case 3:
				return Double.class;
			case 4:
			case 5:
				return Integer.class;
			}
			return null;
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}
}
