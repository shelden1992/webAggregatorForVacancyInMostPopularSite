package net.servletDatabase.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DatabaseFilter extends AbstractDatabaseFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        String method=request.getMethod();
        String remoteAddr=request.getRemoteAddr();
        String queryString=request.getQueryString();
        String protocol=request.getProtocol();
        System.out.printf(">> DatabaseFilter method - %s, remoteAddress - %s, queryString - %s, protocol - %s",
                method, remoteAddr,queryString,protocol);
//response.sendRedirect("/webPlusDatabase_war_exploded/");
        try {
            chain.doFilter(request,response);



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
