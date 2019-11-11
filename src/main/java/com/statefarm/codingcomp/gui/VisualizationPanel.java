package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.statefarm.codingcomp.model.Policy;
import com.statefarm.codingcomp.reader.Reader;

public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 521685048389218424L;
	
	private FiltersPanel controls;
	private DataPanel dataPanel;
	private ChartCreatorPanel creator;
	
	private List<Policy> allData;
	
	public VisualizationPanel() {
		super();
		try {
			allData = new Reader().read();
		} catch (Exception e2) {
			e2.printStackTrace();
			System.exit(1);
			return;
		}
		this.setLayout(new BorderLayout());
		dataPanel = new DataPanel();
		creator = new ChartCreatorPanel(allData);
		controls = new FiltersPanel(new DataReceiverMultiplexer(dataPanel, creator), allData);
		
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						GUIMain.switchToPane(new FileSelectPanel());
					}
				}.start();
			}
		});
		north.add(back, BorderLayout.WEST);
		north.add(controls, BorderLayout.CENTER);
		north.add(creator, BorderLayout.EAST);
		this.add(north, BorderLayout.NORTH);
		this.add(dataPanel, BorderLayout.CENTER);
		try {
			dataPanel.dataUpdated(new Reader().read());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
