package net.servletDatabase.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class AddAtributesToSessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        HttpSession session=httpServletRequest.getSession();

        Enumeration<String> initParameterNames=filterConfig.getInitParameterNames();

        while (initParameterNames.hasMoreElements()) {
            String s=initParameterNames.nextElement();
            String values=filterConfig.getInitParameter(s);
            session.setAttribute(s, values);
        }
        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println(">> AddAtributesToSessionFilter destroy");

    }

    private FilterConfig filterConfig;


}
