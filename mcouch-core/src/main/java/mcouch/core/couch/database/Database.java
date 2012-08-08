package mcouch.core.couch.database;

import mcouch.core.couch.AllDocuments;
import mcouch.core.couch.indexing.Indexes;
import mcouch.core.couch.indexing.View;
import mcouch.core.couch.view.ViewDefinition;
import mcouch.core.couch.view.ViewGroup;
import mcouch.core.couch.view.ViewsDefinition;
import mcouch.core.rhino.JavaScriptInterpreter;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private AllDocuments allDocuments;
    private List<ViewGroup> viewGroups = new ArrayList<>();
    private Indexes indexes;
    private static String QueryResult = "{\"total_rows\":{0},\"offset\":0,\"rows\":[";

    private String name;

    public Database(String name, MapFunctionInterpreter mapFunctionInterpreter, JavaScriptInterpreter javaScriptInterpreter) {
        this.name = name;
        indexes = new Indexes(mapFunctionInterpreter);
        allDocuments = new AllDocuments(javaScriptInterpreter);
    }

    public void createViewGroup(String viewGroupName, String document) {
        viewGroups.add(new ViewGroup(viewGroupName, document));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Database database = (Database) o;
        return name.equals(database.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Database{documentCount=%d, viewGroupsCount=%d, name='%s'}", allDocuments.size(), viewGroups.size(), name);
    }

    public boolean containsViewGroup(String viewGroupName) {
        return viewGroups.contains(new ViewGroup(viewGroupName, "{}"));
    }

    public ViewGroup viewGroup(String name) {
        return viewGroups.get(viewGroups.indexOf(new ViewGroup(name, null)));
    }

    public String executeView(String viewGroupName, String viewName) {
        ViewGroup viewGroup = viewGroup(viewGroupName);
        ViewsDefinition viewsDefinition = viewGroup.definition();
        ViewDefinition viewDefinition = viewsDefinition.getView(viewName);
        View view = indexes.buildIndex(viewName, viewDefinition.mapFunction(), allDocuments);
        List<String> matchingDocuments = allDocuments.getAll(view.all());

        StringBuilder stringBuilder = new StringBuilder(45 + matchingDocuments.size() * 100);
        stringBuilder.append(String.format(QueryResult, matchingDocuments.size()));
        for (String matchingDocument : matchingDocuments)
            stringBuilder.append(matchingDocument).append(",");
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    public void addDocument(String document) {
        allDocuments.add(document);
    }
}