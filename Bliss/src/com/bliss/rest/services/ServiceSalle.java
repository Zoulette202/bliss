package com.bliss.rest.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.bliss.dao.SalleDAO;
import com.bliss.metier.Salle;

@Stateless
@LocalBean
@Provider
@Path("/salles")
public class ServiceSalle {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Salle> getAllMachines(){
		return SalleDAO.list();
	}
}
