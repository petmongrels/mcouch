package mektorp.repository;

import mektorp.rhino.EmitFunction;
import mektorp.rhino.MapFunctionInterpreter;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<RepositoryMethod> repositoryMethods = new ArrayList<>();
    private EmitFunction emitFunction;

    public Repository(EmitFunction emitFunction) {
        this.emitFunction = emitFunction;
    }

    public RepositoryMethod repositoryMethod(StackTraceElement[] stackTrace) {
        RepositoryMethod repositoryMethod = RepositoryMethod.fromStackTrace(stackTrace);
        int index = repositoryMethods.indexOf(repositoryMethod);
        if (index != -1) return repositoryMethods.get(index);

        repositoryMethod = RepositoryMethod.withMapFunction(repositoryMethod, emitFunction);
        repositoryMethods.add(repositoryMethod);
        return repositoryMethod;
    }
}