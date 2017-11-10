package com.bliss.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import com.bliss.dao.AdministrateurDAO;
import com.bliss.metier.Administrateur;

@WebServlet("/auth/*")
public class ServletAuthentification extends UtilHttpServlet {
	private static final long serialVersionUID = 1L; 

	public void login() {
		if(this.req.getMethod().equals("POST")) {
			String mail = getParam("inputMail");
			String password = getParam("inputPassword");
			Administrateur a = AdministrateurDAO.getAdminByMailPassword(mail, password );
			if(a == null) {
				this.displayView(null);
				return;
			}else {
				this.req.getSession().setAttribute("ADMINISTRATEUR", a);
				String redirect = (String)req.getSession().getAttribute("REDIRECT");
				if(redirect != null) {
					try {
						this.resp.sendRedirect(redirect);
						return;
					}catch(IOException e) {
						e.printStackTrace();
					}
				}else { 
					redirect("/home");
				}
			}
		}
		this.displayView(null);
	}
	
	public void logout() {
		this.req.getSession().removeAttribute("ADMINISTRATEUR");
		redirect("/home");
	}

}