package com.bliss.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/home/*")
public class ServletHome extends UtilHttpServlet {
	
	public void index(){
		
		
		this.displayView(null);
	}
	
	
}

