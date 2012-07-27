package mektorp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private List<RepositoryMethod> repositoryMethods = new ArrayList<>();

    public RepositoryMethod repositoryMethod(StackTraceElement[] stackTrace) {
        RepositoryMethod repositoryMethod = RepositoryMethod.fromStackTrace(stackTrace);
        if (repositoryMethods.contains(repositoryMethod)) return repositoryMethod;

        repositoryMethod = RepositoryMethod.withMapFunction(repositoryMethod);
        repositoryMethods.add(repositoryMethod);
        return repositoryMethod;
    }
}