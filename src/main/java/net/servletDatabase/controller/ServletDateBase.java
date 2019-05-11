package net.servletDatabase.controller;

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

import static com.aggregator.view.DatabaseView.databaseNameAndCity;
import static net.servletDatabase.translateAndRedactorNameTable.CorrectClass.doCorrectCityOrDatabaseName;

public class ServletDateBase extends DependencyInjectionServlet {


    @Inject("vacancyDao")
    private VacancyDao vacancyDao;
    private final static String PAGE_ALL_VACANCY="allVacancy.jsp";
    private final static GoToAggregVacationAndCreateNewDatabase createDatabase=new GoToAggregVacationAndCreateNewDatabase();
//    private static final Set<String> databaseNameAndCity=new ConcurrentSkipListSet<>();


    private final static String PAGE_ERROR="error.jsp";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String vacancy=doCorrectCityOrDatabaseName(request.getParameter("vacancy"));
        boolean alreadyCreated = databaseNameAndCity.contains(vacancy);
        String city=doCorrectCityOrDatabaseName(request.getParameter("city"));
        String salary_min=request.getParameter("salary_min");
        String salary_max=request.getParameter("salary_max");
        String web_site=request.getParameter("web_site");


        HttpSession session=request.getSession(true);


        switchWhatWeDo(alreadyCreated, vacancy, city, salary_min, salary_max, web_site, request, response, session);


    }

    private void switchWhatWeDo(boolean alreadyCreated,  String vacancy, String city, String salary_min, String salary_max, String web_site, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        if (alreadyCreated & vacancy != null & web_site.equals("allWebSite")  ) {
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


        } else if (vacancy != null & alreadyCreated & !web_site.equals("allWebSite")) {
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

        } else if (vacancy != null & !alreadyCreated) {

//            if (session.isNew()) {


                createDatabase.aggregatorCreate(vacancy, city, vacancy, !databaseNameAndCity.contains(vacancy));

//                session.setAttribute("vacancy", vacancy);
//                session.setAttribute("city", city);

                databaseNameAndCity.add(vacancy);
                switchWhatWeDo(true, vacancy, city, salary_min, salary_max, web_site, request, response, session);
//            } else {
//                String alreadyExistVacancy=(String) session.getAttribute("vacancy");
//                String alreadyExistCity=(String) session.getAttribute("city");


//            }
            return;
        }


        response.sendRedirect(PAGE_ERROR);
    }
}







