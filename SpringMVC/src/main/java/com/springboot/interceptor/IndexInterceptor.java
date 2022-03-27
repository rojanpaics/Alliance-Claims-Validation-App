package com.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class IndexInterceptor extends HandlerInterceptorAdapter{

	public IndexInterceptor(){
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	
		boolean flag = true;
		
		if(session.getAttribute("loggedInUser") != null){
			if(request.getRequestURL().indexOf("index/welcome") > 0) {
				if(!session.getAttribute("userType").equals("admin")){
					response.sendRedirect(request.getContextPath() + "/login");
					flag = false;
				}
			}
		}else if(request.getRequestURL().indexOf("login") > 0){
			
		}
		else{
			response.sendRedirect(request.getContextPath() + "/login");
			return flag;
		}
		
		return true;
	}
	
	
}