package com.statefarm.codingcomp.gui;

import java.util.List;

import com.statefarm.codingcomp.model.Policy;

public interface DataReceiver {
	public void dataUpdated(List<Policy> policies);
}
