package com.bliss.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.bliss.db.HibernateUtil;

public class FilterHibernate implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
			arg2.doFilter(arg0, arg1);
			try{
				if(HibernateUtil.getSession().getTransaction().isActive())
					HibernateUtil.getSession().getTransaction().commit();
			} catch (Exception e){
				HibernateUtil.getSession().getTransaction().rollback();
				e.printStackTrace();
			}finally {
				try {
					if(HibernateUtil.getSession().isOpen())
						HibernateUtil.getSession().close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
