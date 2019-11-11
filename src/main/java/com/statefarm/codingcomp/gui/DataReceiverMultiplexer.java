package com.statefarm.codingcomp.gui;

import java.util.List;

import com.statefarm.codingcomp.model.Policy;

public class DataReceiverMultiplexer implements DataReceiver {
	
	private DataReceiver[] receivers;
	
	public DataReceiverMultiplexer(DataReceiver... dataReceivers) {
		receivers = dataReceivers;
	}

	@Override
	public void dataUpdated(List<Policy> policies) {
		for (DataReceiver rec : receivers) {
			rec.dataUpdated(policies);
		}
	}

}
