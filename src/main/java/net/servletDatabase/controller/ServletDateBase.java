package net.servletDatabase.controller;

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
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import static net.servletDatabase.doCorrectClass.CorrectClass.doCorrectCityOrDatabaseName;

public class ServletDateBase extends HttpServlet {
    private VacancyDao vacancyDao=new VacancyDaoMock();
    private final static String PAGE_ALL_VACANCY="allVacancy.jsp";
    private final static GoToAggregVacationAndCreateNewDatabase createDatabase=new GoToAggregVacationAndCreateNewDatabase();
    private static final Set<String> database_name=new ConcurrentSkipListSet<>() {{
        add("vacancy_table");
    }};


    private final static String PAGE_ERROR="error.jsp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String vacancy=doCorrectCityOrDatabaseName(request.getParameter("vacancy"));
        String city=doCorrectCityOrDatabaseName(request.getParameter("city"));
        String salary_min=request.getParameter("salary_min");
        String salary_max=request.getParameter("salary_max");
        String web_site=request.getParameter("web_site");

        switchWhatWeDo(vacancy, city, salary_min, salary_max, web_site, request, response);


    }

    private void switchWhatWeDo(String vacancy, String city, String salary_min, String salary_max, String web_site, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (database_name.contains(vacancy) && vacancy != null && web_site.equals("allWebSite")) {
            if (city.isEmpty()) {

                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectAllVacancy(vacancy));
//                    chooseVacancy=vacancyDao.selectAllVacancy(vacancy);
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);

                    return;
                } catch (DaoSystemException | NoSuchEntityException ignore) {
                }
            } else {
                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectVacanсyByCity(city, vacancy));
//                    chooseVacancy=vacancyDao.selectVacanсyByCity(city, vacancy);
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);
                    return;
                } catch (NoSuchEntityException | DaoSystemException ignore) {
                }

            }


        } else if (vacancy != null && database_name.contains(vacancy) && !web_site.equals("allWebSite")) {
            if (city.isEmpty()) {
                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectAllVacancyWithoutCity(web_site, vacancy));
//                    chooseVacancy=vacancyDao.selectAllVacancyWithoutCity(web_site, vacancy);
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);
                    return;

                } catch (DaoSystemException | NoSuchEntityException ignore) {

                }
            } else {
                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectVacancyCityAndWebSiteHave(web_site, vacancy, city));
//                    chooseVacancy=vacancyDao.selectVacancyCityAndWebSiteHave(web_site, vacancy, city);
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);

                    return;
                } catch (DaoSystemException | NoSuchEntityException ignore) {
//                  e.printStackTrace();
                }
            }

        } else if (vacancy != null && !database_name.contains(vacancy)) {


            createDatabase.aggregatorCreate(vacancy, city, vacancy, !database_name.contains(vacancy));
//            HttpSession session=request.getSession(true);
//            session.setAttribute(vacancy, city);
            database_name.add(vacancy);
            switchWhatWeDo(vacancy, city, salary_min, salary_max, web_site, request, response);
            return;
        }


        response.sendRedirect(PAGE_ERROR);
    }
}







