package com.bliss.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilHttpServlet extends HttpServlet {

	protected String action = "";
	protected HttpServletRequest req = null;
	protected HttpServletResponse resp = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.action = req.getPathInfo();
		if (this.action == null || this.action.equals("/")){
			this.action = "/index";
		}
		this.req = req;
		this.resp = resp;
		try {
			this.getClass().getMethod(action.substring(1).toLowerCase()).invoke(this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.action = req.getPathInfo();
		this.req = req;
		this.resp = resp;		
		try {
			this.getClass().getMethod(action.substring(1).toLowerCase()).invoke(this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void displayView(Object model){
		String viewName = this.action.substring(1);
		
		String	controller = this.getClass().getSimpleName();
			controller = controller.substring(controller.lastIndexOf("Servlet")+7).toLowerCase();	
		 try {
			final String dir = "/WEB-INF/views/" + controller + "/" + viewName + ".jsp";
				req.setAttribute("model", model);
			req.getRequestDispatcher(dir).forward(this.req, this.resp);
		} catch (ServletException e) {
			try {
				this.resp.sendError(405,"La vue " + viewName + " est introuvable");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	protected String getParam(final String name){		
		return this.req.getParameter(name) != null ? this.req.getParameter(name).toString() : " ";
	}
	
	protected Integer getParamAsInt(final String name){
		final String param = getParam(name);
		try{
			return Integer.parseInt(param);
		} catch (Exception e){
			return null;
		}		
	}
	
	protected Float getParamAsFloat(final String name){
		final String param = getParam(name);
		try{
			return Float.parseFloat(param);
		} catch (Exception e){
			return null;
		}		
	}
	
	protected void redirect (String url){
		url = req.getContextPath() + url ;
		try {
			resp.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
