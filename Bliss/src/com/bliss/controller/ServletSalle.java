package com.bliss.controller;

import javax.servlet.annotation.WebServlet;

import com.bliss.dao.SalleDAO;
import com.bliss.metier.Salle;

     
@WebServlet(urlPatterns="/salle/*")
public class ServletSalle extends UtilHttpServlet {
	private static final long serialVersionUID = 1L;
 	     
	public void create(){     	     
		if(this.req.getMethod().equals("POST")) {
			Salle s = new Salle(); 
			s.setName(this.getParam("name")); 
			SalleDAO.save(s); 			
			this.displayView(null);	  
		} else 
		    this.displayView(null);
			}
	

	}