package net.servletDatabase.model_dao;


import com.aggregator.vo.Vacancy;
import net.servletDatabase.model_dao.exception.DaoSystemException;
import net.servletDatabase.model_dao.exception.NoSuchEntityException;

import java.util.List;

//CRUD operation
// create, read, update, delete
// работа с бизнес сущностью по отношению к базе данных
// методы будут с сигнатурой, которые нужны java!!!
public interface VacancyDao {

    public List<Vacancy> selectAllVacancy(String vacancy) throws DaoSystemException, NoSuchEntityException;

     List <Vacancy> selectVacanсyByCity(String city, String vacancy) throws NoSuchEntityException, DaoSystemException;

     List<Vacancy> selectVacancyBySalary(Integer min, Integer max) throws DaoSystemException, NoSuchEntityException;

     List<Vacancy> selectAllVacancyWithoutCity(String webSite, String vacancy) throws DaoSystemException, NoSuchEntityException;

List<Vacancy> selectVacancyCityAndWebSiteHave(String webSite, String vacancy, String city) throws DaoSystemException, NoSuchEntityException;


}
