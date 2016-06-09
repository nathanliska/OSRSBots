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
		
		/*
		 * FIND ALL STATUSES HERE.
		 * 
		 * IN NODE VALIDATION:
		 * -------------------
		 * return (Status.getStatus == States.CHOP) - For chop, others will look the same.
		 */
		
		return status;
	}
	
}
