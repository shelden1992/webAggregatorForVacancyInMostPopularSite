package net.servletDatabase.dao;


import com.aggregator.vo.Vacancy;
import net.servletDatabase.dao.exception.DaoSystemException;
import net.servletDatabase.dao.exception.NoSuchEntityException;

import java.util.List;

//CRUD operation
// create, read, update, delete
// работа с бизнес сущностью по отношению к базе данных
// методы будут с сигнатурой, которые нужны java!!!
public interface VacancyDao {

    public List<Vacancy> selectAllVacancy() throws DaoSystemException;

     List <Vacancy> selectVacanсyByCity(String city) throws NoSuchEntityException, DaoSystemException;

     List<Vacancy> selectVacancyBySalary(Integer min, Integer max) throws DaoSystemException, NoSuchEntityException;


     List<Vacancy> allVacancyAllCity () throws DaoSystemException,NoSuchEntityException;




}
