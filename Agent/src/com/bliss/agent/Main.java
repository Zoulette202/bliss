package com.bliss.agent;

import com.bliss.agent.CreateJson;

public class Main {
	
	  public Main() {
	  
	      try {
	    	  while(true) {
	    		 CreateJson createJson = new CreateJson(); 
	    	  	 createJson.sendInfo();
	    	  	 Thread.sleep(6000);
	    	  }
	      }
	      catch (InterruptedException ex) {}
	    
	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();

	}
}
