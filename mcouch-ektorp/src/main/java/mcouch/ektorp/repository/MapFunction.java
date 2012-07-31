package mcouch.ektorp.repository;

import org.ektorp.support.View;

import java.lang.reflect.Method;

public class MapFunction {
    public static String TemplateForMapBasedOnSingleField = "function(doc) {emit(doc.%s, doc._id);}";

    public static String fromViewAnnotation(View view) {
        return view.map();
    }

    public static String fromGenerateView(Method method) {
        return fromGenerateView(method.getName());
    }

    public static String fromGenerateView(String methodName) {
        String fieldName = methodName.replace("findBy", "");
        String fieldNamesFirstChar = fieldName.substring(0, 1);
        String fieldNameWithoutFirstChar = fieldName.substring(1);
        return String.format(TemplateForMapBasedOnSingleField, fieldNamesFirstChar.toLowerCase() + fieldNameWithoutFirstChar);
    }
}