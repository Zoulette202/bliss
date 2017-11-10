package com.bliss.rest.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.jtransfo.JTransfo;

import com.bliss.dao.AdministrateurDAO;
import com.bliss.dao.MachineDAO;
import com.bliss.metier.Administrateur;
import com.bliss.metier.Machine;

@Stateless
@LocalBean
@Provider
@Path("/machines")
public class ServiceMachine {
	
	JTransfo transformateur;
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Machine> getAllMachines(){
		return MachineDAO.list();
	}
	
	@GET
	@Path("infoPc")
	@Consumes(MediaType.APPLICATION_JSON)
	public void getInfoPc(Json object){
		Machine m = (Machine) transformateur.convert(object);
		if(m != null) {
			
		}
		MachineDAO.save(m);
	}
	
	
	
}
