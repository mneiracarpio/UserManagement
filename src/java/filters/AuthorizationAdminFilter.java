/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco
 */
public class AuthorizationAdminFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String role = httpRequest.getSession().getAttribute("role").toString();
        if (role != null && role.equals("admin")) {
            chain.doFilter(request, response);
            return;
        }
        String myMessage = "This page is only for Users. Try to login again";
        request.setAttribute("message", myMessage );
        httpRequest.getSession().setAttribute("message", myMessage);
        httpResponse.sendRedirect("login");
        //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
    }
}
