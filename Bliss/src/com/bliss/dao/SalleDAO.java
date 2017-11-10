package com.bliss.dao;

import java.util.List;

import com.bliss.db.HibernateUtil;
import com.bliss.metier.Machine;
import com.bliss.metier.Salle;

public class SalleDAO {
	public static void save(Salle u){
		HibernateUtil.getSession().saveOrUpdate(u);
		HibernateUtil.getSession().getTransaction().commit();
	}
		
	public static Salle getSalleByName(String name){
		try{
		return (Salle) HibernateUtil.getSession()
			.createQuery("from Salle where name=?")
			.setParameter(0, name)
			.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	public static List<Salle> list(){
		return (List<Salle>) HibernateUtil.getSession().createQuery("from Salle").getResultList();
		
	}
	
}