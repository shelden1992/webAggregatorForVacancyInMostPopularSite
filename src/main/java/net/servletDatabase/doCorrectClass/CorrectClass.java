package net.servletDatabase.doCorrectClass;

public class CorrectClass {

    static public String doCorrectCityOrDatabaseName(String cityOrDatabase) {
        if (cityOrDatabase == null) {
            return null;
        }
        String cityAndDatabase=cityOrDatabase;


        if
        (cityOrDatabase.matches(" ")) {
            cityAndDatabase=cityOrDatabase.replaceAll(" ", "_");
        }
        if (cityAndDatabase.matches("-")) {
            cityAndDatabase=cityAndDatabase.replaceAll("-", "_");
        }
        if (cityAndDatabase.matches("\\+")) {
            cityAndDatabase=cityAndDatabase.replaceAll("\\+", "_");
        }


        return cityAndDatabase.toLowerCase();
    }
}
