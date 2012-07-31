package mcouch.ektorp.repository;

import mcouch.core.couch.AllDocuments;
import mcouch.core.couch.indexing.Index;
import mcouch.core.couch.indexing.Indexes;
import mcouch.core.couch.indexing.query.IndexQuery;
import mcouch.ektorp.IndexQueryFactory;
import mcouch.core.rhino.EmitFunction;
import org.ektorp.ViewQuery;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RepositoryMethod {
    private String className;
    private String methodName;
    private String mapFunction;
    private EmitFunction emitFunction;

    public static RepositoryMethod fromStackTrace(StackTraceElement[] stackTrace) {
        StackTraceElement e = stackTrace[2];
        String className = e.getClassName();
        String methodName = e.getMethodName();
        return new RepositoryMethod(className, methodName);
    }

    public static RepositoryMethod withMapFunction(RepositoryMethod repositoryMethod, EmitFunction emitFunction) {
        RepositoryMethod copy = new RepositoryMethod(repositoryMethod.className, repositoryMethod.methodName);
        copy.emitFunction = emitFunction;
        copy.readMapFunction();
        return copy;
    }

    public RepositoryMethod(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    private void readMapFunction() {
        try {
            Class<?> repositoryClass = Class.forName(className);
            Method method = null;
            Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(repositoryClass);
            for (Method declaredMethod : allDeclaredMethods) {
                if (declaredMethod.getName().equals(methodName)) method = declaredMethod;
            }
            View annotation = method.getAnnotation(View.class);
            if (annotation != null)
                mapFunction = MapFunction.fromViewAnnotation(annotation);
            else if (method.getAnnotation(GenerateView.class) != null) {
                mapFunction = MapFunction.fromGenerateView(method);
            }
        } catch (ClassNotFoundException e) {
            throw new AssertionError(String.format("Cannot find class: %s", className), e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryMethod that = (RepositoryMethod) o;

        return !(className != null ? !className.equals(that.className) : that.className != null) && !(methodName != null ? !methodName.equals(that.methodName) : that.methodName != null);
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        return result;
    }

    public String mapFunction() {
        return mapFunction;
    }

    public <T> List<T> execute(ViewQuery query, Class<T> type, AllDocuments allDocuments, Indexes indexes) {
        Index index = indexes.getOrCreate(query.getViewName(), mapFunction);
        emitFunction.currentIndex(index);
        index.build(allDocuments);
        List<String> documentIds = list(query, index);
        List<T> documents = new ArrayList<>(documentIds.size());
        for (String documentId : documentIds)
            documents.add((T) allDocuments.get(documentId));
        return documents;
    }

    private List<String> list(ViewQuery query, Index index) {
        IndexQuery indexQuery = IndexQueryFactory.create(query, index);
        return indexQuery.execute();
    }
}