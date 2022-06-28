package com.youhost.webcore;

import com.youhost.common.DefaultLogableObject;

public class TopMenuMapping extends DefaultLogableObject {
	private int depth1;
	private int depth2;
	public TopMenuMapping(int depth1, int depth2){
		this.depth1 = depth1;
		this.depth2 = depth2;
	}
	public int getDepth1() {
		return depth1;
	}
	public int getDepth2() {
		return depth2;
	}
}
