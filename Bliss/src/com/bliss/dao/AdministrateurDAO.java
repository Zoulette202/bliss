package com.bliss.dao;

import java.util.List;

import com.bliss.db.HibernateUtil;
import com.bliss.metier.Administrateur;

public class AdministrateurDAO {
	public static void save(Administrateur t){
		HibernateUtil.getSession().saveOrUpdate(t);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	public static Administrateur getAdminByMailPassword(String mail, String password){
		try{
		return (Administrateur) HibernateUtil.getSession()
			.createQuery("from Administrateur where mail=? and password=?")
			.setParameter(0, mail)
			.setParameter(1, password)
			.getSingleResult();
		}catch(Exception e){
			return null;
		}
	}
	
	public static List<Administrateur> list(){
		return (List<Administrateur>) HibernateUtil.getSession().createQuery("from Administrateur").getResultList();
		
	}
	
	public static Administrateur getAdministrateurById(int id) {
		return  (Administrateur) HibernateUtil.getSession()
				.createQuery("from Administrateur where id=?").setParameter(0, id).getSingleResult();
	}
}