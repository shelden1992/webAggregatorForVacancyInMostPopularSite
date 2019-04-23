package net.servletDatabase.controller;

import com.aggregator.vo.Vacancy;
import net.servletDatabase.dao.VacancyDao;
import net.servletDatabase.dao.exception.DaoSystemException;
import net.servletDatabase.dao.exception.NoSuchEntityException;
import net.servletDatabase.dao.impl.VacancyDaoMock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class ServletDateBase extends HttpServlet {
    private VacancyDao vacancyDao=new VacancyDaoMock();
    private final static String PAGE_ALL_VACANCY="allVacancy.jsp";
    private final static String PAGE_BY_SALARY="allVacancy.jsp";
    private final static String PAGE_BY_CITY="allVacancy.jsp";
    private static List<Vacancy> chooseVacancy;

    public static List<Vacancy> getChooseVacancy() {
        return chooseVacancy;
    }

    public static void setChooseVacancy(List<Vacancy> chooseVacancy) {
        ServletDateBase.chooseVacancy=chooseVacancy;
    }

    private final static String PAGE_ERROR="error.jsp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vacancy=request.getParameter("vacancy");
        String city=request.getParameter("city");
        String salary_min=request.getParameter("salary_min");
        String salary_max=request.getParameter("salary_max");


            try {
                chooseVacancy=vacancyDao.selectAllVacancy();
                System.out.println(chooseVacancy);

                request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);//
                return;

            } catch (DaoSystemException e) {
            }

//            try {
//                vacancyDao.selectVacanсyByCity(city);
//            } catch (NoSuchEntityException e) {
//            } catch (DaoSystemException e) {
//                e.printStackTrace();
//            }
}
//        response.sendRedirect(PAGE_ERROR);


    }





