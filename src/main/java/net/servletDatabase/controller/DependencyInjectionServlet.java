package net.servletDatabase.controller;

import net.servletDatabase.filtersByAnnotation.FilterAnnotationByReflection;
import net.servletDatabase.inject.Inject;
import net.servletDatabase.modelDao.impl.VacancyDaoImplement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;

public class DependencyInjectionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        try {
            ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationConfiguration.xml");


            List<Field> fields=FilterAnnotationByReflection.getAllField(this.getClass(), DependencyInjectionServlet.class);

            List<Field> alreadyFiltersFields=FilterAnnotationByReflection.getAllFieldWithAnnotationInject(fields);


            for (Field fl : alreadyFiltersFields) {
                fl.setAccessible(true); //если поле !!!!финальное или приватное!!!
                Inject annotation=fl.getAnnotation(Inject.class);
                System.out.println("Find method marked by @Inject " + fl);

                String beanName=annotation.value();
                //так-как анотация у нас одним полем, то только его мы и берем!
                // а вообще спокойно можно брать разные поля
                System.out.println("Bean name " + beanName);

                VacancyDaoImplement vacancyDaoImplement=(VacancyDaoImplement) applicationContext.getBean(beanName);

                if (vacancyDaoImplement == null) {

                    throw new ServletException("Bean == null, must be " + beanName);

                }
                try {
                    fl.set(this, vacancyDaoImplement);  // c помощью рефлексии назначаем поле бином, вытянутый из ApplicationContext;
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }






    }

}
