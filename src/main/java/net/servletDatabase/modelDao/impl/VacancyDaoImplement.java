package net.servletDatabase.modelDao.impl;

import com.aggregator.vo.Vacancy;
import net.servletDatabase.modelDao.VacancyDao;
import net.servletDatabase.modelDao.exception.DaoSystemException;
import net.servletDatabase.modelDao.exception.NoSuchEntityException;
import net.servletDatabase.modelDao.impl.utils.VacancyMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class VacancyDaoImplement implements VacancyDao {



    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;




    @Override
     public boolean checkingExistDatabase(String database){
        String query = "SHOW TABLES LIKE \'" + database + "\'";
        List<Map<String, Object>> maps=jdbcTemplate.queryForList(query);
        System.out.println(maps);


        return !maps.isEmpty();
    }

    @Override

    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }


//    private static ResultSet connectToDatabase(String query) throws DaoSystemException {
//        Properties properties=new Properties();
//
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try (FileInputStream inputStream=new FileInputStream("/Users/macuser/Desktop/projects/webPlusDatabase/src/main/resources/connectToDataBase.properties")) {
//            properties.load(( inputStream ));
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Connection connection=DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
//            //!!!!!!!!!! не закрыт!!!!!!!! это какая-то хуйня!!!!!
//
//
//            PreparedStatement preparedStatement=connection.prepareStatement(query);
//            System.out.println("connection is successfull");
//
//
//            return preparedStatement.executeQuery();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        throw new DaoSystemException("Not connect to database");
//    }

    //именно тут я буду ходить в базу данных

    @Override
    public List<Vacancy> selectAllVacancy(String vacancy) throws DaoSystemException, NoSuchEntityException {
        String query="select * from " + vacancy;

        return getInformationByVacationAdnAddToList(query);


    }

    private List<Vacancy> getInformationByVacationAdnAddToList(String query) throws NoSuchEntityException {

        List<Vacancy> list=jdbcTemplate.query(query, new VacancyMapper());


        if (list.isEmpty()) throw new NoSuchEntityException("Not found vacancy");

        return list;
    }

    @Override
    public List<Vacancy> selectVacanсyByCity(String city, String vacancy) throws NoSuchEntityException {
        String query="select * from " + vacancy + " where city =\'" + city + "\'";

        return getInformationByVacationAdnAddToList(query);

    }

    @Override
    public List<Vacancy> selectVacancyBySalary(Integer min, Integer max) throws DaoSystemException, NoSuchEntityException {

        String query="select * from vacancy_table where salary >" + min + " and salary <" + min;
        return getInformationByVacationAdnAddToList(query);
    }

    @Override
    public List<Vacancy> selectAllVacancyWithoutCity(String webSite, String vacancy) throws DaoSystemException, NoSuchEntityException {
        if (!webSite.equals("allWebSite")) {
            return getInformationByVacationAdnAddToList("select * from " + vacancy + " where url like " + "\'%" + webSite + "%\'");
        } else {
            return getInformationByVacationAdnAddToList("select * from " + vacancy);
        }
//        throw new NoSuchEntityException("Current vacancy are missed");

    }

    @Override
    public List<Vacancy> selectVacancyCityAndWebSiteHave(String webSite, String vacancy, String city) throws DaoSystemException, NoSuchEntityException {
        return getInformationByVacationAdnAddToList("select * from " + vacancy + " where url like " + "\'%" + webSite + "%\' and city =" + "\'" + city + "\'");

    }



}
