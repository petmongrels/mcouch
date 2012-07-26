package mektorp.repository;

import org.ektorp.support.View;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class RepositoryMethod {
    private String className;
    private String methodName;

    public static RepositoryMethod fromStackTrace(StackTraceElement[] stackTrace) {
        StackTraceElement e = stackTrace[2];
        String className = e.getClassName();
        String methodName = e.getMethodName();
        return new RepositoryMethod(className, methodName);
    }

    public RepositoryMethod(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String mapFunction() {
        try {
            Class<?> repositoryClass = Class.forName(className);
            Method method = null;
            Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(repositoryClass);
            for (Method declaredMethod : allDeclaredMethods) {
                if (declaredMethod.getName().equals(methodName)) method = declaredMethod;
            }
            View annotation = method.getAnnotation(View.class);
            return annotation.map();
        } catch (ClassNotFoundException e) {
            throw new AssertionError(String.format("Cannot find class: %s", className), e);
        }
    }
}