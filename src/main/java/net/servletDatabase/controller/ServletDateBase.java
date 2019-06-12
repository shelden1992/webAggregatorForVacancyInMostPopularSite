package net.servletDatabase.controller;

import com.aggregator.vo.Vacancy;
import net.servletDatabase.inject.Inject;
import net.servletDatabase.modelDao.GoToAggregVacationAndCreateNewDatabase;
import net.servletDatabase.modelDao.VacancyDao;
import net.servletDatabase.modelDao.exception.DaoSystemException;
import net.servletDatabase.modelDao.exception.NoSuchEntityException;
import net.servletDatabase.modelDao.impl.VacancyDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

import static net.servletDatabase.translateAndRedactorNameTable.CorrectClass.doCorrectCityOrDatabaseName;

public class ServletDateBase extends DependencyInjectionServlet {


    @Inject("vacancyDao")
    private VacancyDao vacancyDao;
    private final static String PAGE_ALL_VACANCY="allVacancy.jsp";


    private final static GoToAggregVacationAndCreateNewDatabase createDatabase=new GoToAggregVacationAndCreateNewDatabase();


    private final static String PAGE_ERROR="error.jsp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String vacancy=doCorrectCityOrDatabaseName(request.getParameter("vacancy"));
        String city=doCorrectCityOrDatabaseName(request.getParameter("city"));

        boolean alreadyCreated=false;
        boolean cityNotAdd=false;
        try {
            alreadyCreated=vacancyDao.checkingExistDatabase(vacancy);
            if (alreadyCreated) {

                List<Vacancy> vacancies=vacancyDao.selectVacanсyByCity(city, vacancy);

            }


        } catch (DaoSystemException | NoSuchEntityException e) {
            cityNotAdd=true;
        }
        String salary_min=request.getParameter("salary_min");
        String salary_max=request.getParameter("salary_max");
        String web_site=request.getParameter("web_site");


        HttpSession session=request.getSession(true);


        switchWhatWeDo(alreadyCreated, cityNotAdd, vacancy, city, salary_min, salary_max, web_site, request, response, session);


    }

    private void switchWhatWeDo(boolean alreadyCreated, boolean cityNotAdd, String vacancy, String city, String salary_min, String salary_max, String web_site, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        if (alreadyCreated & !cityNotAdd & vacancy != null & web_site.equals("allWebSite")) {  // если уже есть база/ город добавлен /вакансия есть
            if (city.isEmpty()) {

                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectAllVacancy(vacancy));
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);

                    return;
                } catch (DaoSystemException | NoSuchEntityException ignore) {
                }
            } else {
                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectVacanсyByCity(city, vacancy));
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);
                    return;
                } catch (NoSuchEntityException | DaoSystemException ignore) {
                }

            }


        } else if (vacancy != null & alreadyCreated & !cityNotAdd & !web_site.equals("allWebSite")) {
            if (city.isEmpty()) {
                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectAllVacancyWithoutCity(web_site, vacancy));
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);
                    return;

                } catch (DaoSystemException | NoSuchEntityException ignore) {

                }
            } else {
                try {
                    request.setAttribute("chooseVacancy", vacancyDao.selectVacancyCityAndWebSiteHave(web_site, vacancy, city));
                    request.getRequestDispatcher(PAGE_ALL_VACANCY).forward(request, response);

                    return;
                } catch (DaoSystemException | NoSuchEntityException ignore) {
                }
            }

        } else if (vacancy != null & alreadyCreated & cityNotAdd) {  //если вакансия существует, а город не добавлен в базу данных
            createDatabase.aggregatorCreate(vacancy, city, vacancy, false);
            switchWhatWeDo(true, false, vacancy, city, salary_min, salary_max, web_site, request, response, session);


            return;

        } else if (vacancy != null & !alreadyCreated) {  // если нет базы данных

//            if (session.isNew()) {


            createDatabase.aggregatorCreate(vacancy, city, vacancy, true);

            switchWhatWeDo(true, false, vacancy, city, salary_min, salary_max, web_site, request, response, session);


            return;
        }


        response.sendRedirect(PAGE_ERROR);
    }


}







