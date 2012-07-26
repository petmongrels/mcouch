package mektorp.repository;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Repositories {
    private List<RepositoryMethod> repositoryMethods = new ArrayList<RepositoryMethod>();
    private Map<RepositoryMethod, Object> compiledScripts = new HashMap<RepositoryMethod, Object>();

    public void ensureViewIsDefined(StackTraceElement[] stackTrace) {
        RepositoryMethod repositoryMethod = RepositoryMethod.fromStackTrace(stackTrace);
        if (repositoryMethods.contains(repositoryMethod)) return;

        String function = repositoryMethod.mapFunction();

//        compiledScripts.put(repositoryMethod, compiledScript);

        repositoryMethods.add(repositoryMethod);
    }
}