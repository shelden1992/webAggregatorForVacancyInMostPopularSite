package net.servletDatabase.dao.impl;

import com.aggregator.vo.Vacancy;
import net.servletDatabase.dao.VacancyDao;
import net.servletDatabase.dao.exception.DaoSystemException;
import net.servletDatabase.dao.exception.NoSuchEntityException;

import java.util.List;

public class VacancyDaoMock implements VacancyDao {
    //именно тут я буду ходить в базу данных

    @Override
    public List<Vacancy> selectAllVacancy() throws DaoSystemException {
        return null;
    }

    @Override
    public List<Vacancy> selectVacanсyByCity(String city) throws NoSuchEntityException {
        return null;
    }
}
