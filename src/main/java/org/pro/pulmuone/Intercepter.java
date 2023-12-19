package org.pro.pulmuone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class Intercepter implements HandlerInterceptor {

//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//	String path = request.getRequestURI();
//	System.out.println(path);
//    if (path.contains("/cart")) {
//      	response.sendRedirect("/cart");
//        return false;
//    }else{
//        return true;
//    }
//	
//  }

}
