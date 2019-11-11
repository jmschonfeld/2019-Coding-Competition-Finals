package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.statefarm.codingcomp.reader.Reader;

public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 521685048389218424L;
	
	private FiltersPanel controls;
	private DataPanel dataPanel;
	
	public VisualizationPanel() {
		super();
		this.setLayout(new BorderLayout());
		dataPanel = new DataPanel();
		controls = new FiltersPanel(dataPanel);
		
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
		this.add(north, BorderLayout.NORTH);
		this.add(dataPanel, BorderLayout.CENTER);
		try {
			dataPanel.dataUpdated(new Reader().read());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
