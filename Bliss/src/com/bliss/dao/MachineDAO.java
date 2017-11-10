package com.bliss.dao;


import java.util.List;

import com.bliss.db.HibernateUtil;
import com.bliss.metier.Machine;

public class MachineDAO {
	public static void save(Machine m){
		HibernateUtil.getSession().saveOrUpdate(m);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	
	
	public static Machine getMachineByNameIp(String name, String ip){
		try{
		return (Machine) HibernateUtil.getSession()
			.createQuery("from Machine where name=? and ip=?")
			.setParameter(0, name)
			.setParameter(1, ip)
			.getSingleResult();
		}catch(Exception e){
			return null;
		}
	} 
	
	public static List<Machine> list(){
		return (List<Machine>) HibernateUtil.getSession().createQuery("from Machine").getResultList();
		
	}

}