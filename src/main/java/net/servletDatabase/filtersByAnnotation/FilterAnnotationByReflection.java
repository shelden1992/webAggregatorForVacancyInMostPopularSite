package net.servletDatabase.filtersByAnnotation;

import net.servletDatabase.controller.DependencyInjectionServlet;
import net.servletDatabase.inject.Inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterAnnotationByReflection {
    public static List<Field> getAllField(Class<? extends DependencyInjectionServlet> startClass, Class<DependencyInjectionServlet> finishClass) {

        List<Field> result=new ArrayList();

        Class<?> courentClass=startClass;

        while (courentClass != finishClass) {
            result.addAll(Arrays.asList(startClass.getDeclaredFields()));
            courentClass=courentClass.getSuperclass();

        }
        return result;
    }

    public static List<Field> getAllFieldWithAnnotationInject(List<Field> list) {
        List<Field> result=new ArrayList<>();

        for (Field fields : list
        ) {
            Inject annotation=fields.getAnnotation(Inject.class);
            if (annotation != null) {
                result.add(fields);
            }


        }

        return result;

    }
}
