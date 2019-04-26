package net.servletDatabase.controller;

import com.aggregator.Controller;
import com.aggregator.model.*;
import com.aggregator.view.DatabaseView;
import com.aggregator.view.View;
import com.aggregator.vo.Vacancy;
import net.servletDatabase.model.GoToAggregVacationAndCreateNewDatabase;
import net.servletDatabase.model.VacancyDao;
import net.servletDatabase.model.exception.DaoSystemException;
import net.servletDatabase.model.exception.NoSuchEntityException;
import net.servletDatabase.model.impl.VacancyDaoMock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class ServletDateBase extends HttpServlet {
    private VacancyDao vacancyDao=new VacancyDaoMock();
    private final static String PAGE_ALL_VACANCY="allVacancy.jsp";
    private static List<Vacancy> chooseVacancy;
    private final static GoToAggregVacationAndCreateNewDatabase createDatabase=new GoToAggregVacationAndCreateNewDatabase();
private static final Set<String> database_name= new ConcurrentSkipListSet<>(){{add("vacancy_table");}};

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
        String web_site =request.getParameter("web_site");

        if(vacancy!=null&& database_name.contains(vacancy) ){
            try {
                chooseVacancy = vacancyDao.selectAllVacancyWithoutCity(web_site, vacancy);
                request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);


            } catch (DaoSystemException e) {
            } catch (NoSuchEntityException e) {
            }
        }


//        try {
//            chooseVacancy=vacancyDao.selectAllVacancy();
//
//            request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);//
//            return;
//
//        } catch (DaoSystemException e) {
//        }

        response.sendRedirect(PAGE_ERROR);
    }


}





