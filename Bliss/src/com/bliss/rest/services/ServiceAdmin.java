package com.bliss.rest.services;

import java.util.List;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.bliss.dao.AdministrateurDAO;
import com.bliss.metier.Administrateur;

@Stateless
@LocalBean
@Provider
@Path("/admins")
public class ServiceAdmin {
	
	
	@Path("/sayHello")
	@Produces(MediaType.TEXT_HTML)
	public String getHelloMsg() {
		return "<h1>Hello Word Tu as r�ussi � mettre en place le web service rest!</h1>" ;
	}
	
	@GET
	@Path("/getAdminById/{id}")
	@Produces("text/plain")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAdminById(@PathParam("id") int id) {
		return "L'admin choisi est "+ AdministrateurDAO.getAdministrateurById(id).getFirstname();
	}
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Administrateur> getAllAdmin(){
		return AdministrateurDAO.list();
	}
	
	
	/*@POST
	@Path("/newAdmin/{lastname}?{firstname}?{mail}?{password}")
	public void createAdmin(@PathParam("lastname") String lastname, @PathParam("firstname") String firstname,@PathParam("mail")  String mail,@PathParam("password")  String password){
		Administrateur a = new Administrateur();
		a.setLastname(lastname);
		a.setFirstname(firstname);
		a.setMail(mail);
		a.setPassword(password);
		AdministrateurDAO.save(a);
	}*/
}
