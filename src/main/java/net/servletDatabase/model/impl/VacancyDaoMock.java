package net.servletDatabase.model.impl;

import com.aggregator.vo.Vacancy;
import net.servletDatabase.model.VacancyDao;
import net.servletDatabase.model.exception.DaoSystemException;
import net.servletDatabase.model.exception.NoSuchEntityException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VacancyDaoMock implements VacancyDao {

    private static ResultSet connectToDatabase(String query) throws DaoSystemException {
        Properties properties=new Properties();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (FileInputStream inputStream=new FileInputStream("/Users/macuser/Desktop/projects/webPlusDatabase/src/main/resources/connect.properties")) {
            properties.load(( inputStream ));


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Connection connection=DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
            //!!!!!!!!!! не закрыт!!!!!!!!


            PreparedStatement preparedStatement=connection.prepareStatement(query);
            System.out.println("connection is successfull");


            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DaoSystemException("Not connect to database");
    }

    //именно тут я буду ходить в базу данных

    @Override
    public List<Vacancy> selectAllVacancy() throws DaoSystemException, NoSuchEntityException {
        String query="select * from vacancy_table";

        return getInformationByVacationAdnAddToList(query);


    }

    private List<Vacancy> getInformationByVacationAdnAddToList(String query) throws NoSuchEntityException {
        List<Vacancy> list=new ArrayList<>();
        try (ResultSet resultSet=connectToDatabase(query)) {

            while (resultSet.next()) {

                Vacancy vacancy=new Vacancy();

                resultSet.getString("id");

                vacancy.setUrl(resultSet.getString("url"));

                vacancy.setTitle(resultSet.getString("title"));

                vacancy.setCity(resultSet.getString("city"));

                vacancy.setCompanyName(resultSet.getString("company_name"));

                vacancy.setSalary(resultSet.getString("salary"));
                list.add(vacancy);


            }


        } catch (SQLException e) {
            e.printStackTrace();


        } catch (DaoSystemException e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) throw new NoSuchEntityException("Not found vacancy");

        return list;
    }

    @Override
    public List<Vacancy> selectVacanсyByCity(String city) throws NoSuchEntityException {
        String query="select * from vacancy_table where city = \'" + doCorrectCityOrDatabaseName(city) + "\'";

        return getInformationByVacationAdnAddToList(query);

    }

    @Override
    public List<Vacancy> selectVacancyBySalary(Integer min, Integer max) throws DaoSystemException, NoSuchEntityException {

        String query="select * from vacancy_table where salary >" + min + " and salary <" + min;
        return getInformationByVacationAdnAddToList(query);
    }

    @Override
    public List<Vacancy> selectAllVacancyWithoutCity(String webSite, String nameDatabase) throws DaoSystemException, NoSuchEntityException {
        if (!webSite.equals("allWebSite")) {
            return getInformationByVacationAdnAddToList("select * from " + doCorrectCityOrDatabaseName(nameDatabase) + " where url like " + "\'%" + webSite + "%\'");
        } else if(webSite.equals("allWebSite")){
        return getInformationByVacationAdnAddToList("select * from " + doCorrectCityOrDatabaseName(nameDatabase));}
        throw  new NoSuchEntityException("Current vacancy are missed");

    }



    private String doCorrectCityOrDatabaseName(String cityOrDatabase) {
        String correctCityOrDatabase=cityOrDatabase;
        if
        (cityOrDatabase.matches(" ")) {
            correctCityOrDatabase=correctCityOrDatabase.replaceAll(" ", "_");
        }
        if (correctCityOrDatabase.matches("-")) {
            correctCityOrDatabase=correctCityOrDatabase.replaceAll("-", "_");
        }


        return correctCityOrDatabase.toLowerCase();
    }
}
