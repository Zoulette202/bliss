package com.bliss.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static final SessionFactory sessionFact; 
		static {
			final StandardServiceRegistry registry = 
					new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml")
						.build();
			
			Metadata meta = new MetadataSources(registry)
					.getMetadataBuilder().build(); 
			
			sessionFact = meta.getSessionFactoryBuilder().build(); 
		}
		
	public static SessionFactory getSessionFactory() {
		return sessionFact; 
	}
	
	public static final Session getSession() {
		Session session = getSessionFactory().getCurrentSession(); 
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		
		return session; 
	}
}