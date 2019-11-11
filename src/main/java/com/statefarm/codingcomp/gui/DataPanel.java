package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.statefarm.codingcomp.model.Policy;

public class DataPanel extends JPanel implements DataReceiver {
	private static final long serialVersionUID = 8399167358797477523L;
	
	private JTable table;
	
	private static final String[] HEADERS = new String[]{"Policy Type", "Policy Status", "Policy State", "Annual Premium", "Age", "Accidents"};
	
	DataPanel() {
		this.setLayout(new BorderLayout());
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(false);
		table.setShowGrid(true);
		table.setModel(new PolicyDataModel(HEADERS, 0));
		
		this.add(new JScrollPane(table), BorderLayout.CENTER);
	}

	@Override
	public void dataUpdated(List<Policy> policies) {
		String[][] data = new String[policies.size()][HEADERS.length];
		int i = 0;
		for (Policy policy : policies) {
			data[i] = new String[] {
				policy.getPolicyType(),
				policy.getPolicyStatus().name(),
				policy.getState(),
				policy.getAnnualPremium() + "",
				policy.getAge() + "",
				policy.getNumberOfAccidents() + ""
			};
			i++;
		}
		TableModel model = new PolicyDataModel(data, HEADERS);
		table.setModel(model);
	}
	
	private class PolicyDataModel extends DefaultTableModel {
		private static final long serialVersionUID = 2459270881757581627L;

		PolicyDataModel(String[] columnNames, int numRows) {
			super(columnNames, numRows);
		}
		
		PolicyDataModel(String[][] data, String[] columnNames) {
			super(data, columnNames);
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}
}
