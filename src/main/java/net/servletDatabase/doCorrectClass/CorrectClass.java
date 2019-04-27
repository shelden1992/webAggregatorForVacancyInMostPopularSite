package net.servletDatabase.doCorrectClass;

public class CorrectClass {


        static public String doCorrectCityOrDatabaseName(String cityOrDatabase) {
            if (cityOrDatabase == null) {
                return null;
            }


            String s=cityOrDatabase;

            s=s.replaceAll(" ", "_");
            s=s.replaceAll("-", "_");
            s=s.replaceAll("\\+", "_");


            return s.toLowerCase();
        }

}
