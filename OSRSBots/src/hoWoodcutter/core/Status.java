package hoWoodcutter.core;

import hoWoodcutter.util.States;

public class Status {

	private static States status;
	
	public Status() {
		status = null;
	}
	
	public static void setStatus(States state) {
		status = state;
	}
	
	public static States getStatus() {
		return status;
	}
	
}
