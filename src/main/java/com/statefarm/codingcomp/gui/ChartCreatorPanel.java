package com.statefarm.codingcomp.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

public class ChartCreatorPanel extends JPanel {
	private static final long serialVersionUID = 7124665277931954713L;

	ChartCreatorPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Create Chart"));
		
		//TODO
		this.setLayout(new BorderLayout());
		this.add(Box.createHorizontalStrut(200), BorderLayout.CENTER);
	}
}
