package net.servletDatabase.translateAndRedactorNameTable;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.sun.java.accessibility.util.Translator;

public class CorrectClass {


//    static private String translate(String cityOrDatabase) {
//
//
//        Translate translate=TranslateOptions.getDefaultInstance().getService();
//
//
//        Translation translate1=translate.translate(cityOrDatabase, Translate.TranslateOption.sourceLanguage("en"),
//                Translate.TranslateOption.targetLanguage("ru"));
//
//
//        System.out.println( translate1.getTranslatedText());
//        return translate1.getTranslatedText();
//
//
//    }


    static public String doCorrectCityOrDatabaseName(String cityOrDatabase) {
        if (cityOrDatabase == null) {
            return null;
        }

        String s=cityOrDatabase;


        s=s.replaceAll(" ", "_");
        s=s.replaceAll("-", "_");
        s=s.replaceAll("\\+", "_");

        System.out.println( s.toLowerCase());
        return s.toLowerCase();
    }

}
