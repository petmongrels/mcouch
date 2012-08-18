package mcouch.core.couch.database;

import mcouch.core.couch.AllDocuments;
import mcouch.core.couch.indexing.*;
import mcouch.core.couch.indexing.query.IndexQuery;
import mcouch.core.couch.reducers.Reducer;
import mcouch.core.couch.view.ViewDefinition;
import mcouch.core.couch.view.ViewGroup;
import mcouch.core.couch.view.ViewGroupDefinition;
import mcouch.core.http.response.SuccessfulDocumentCreateResponse;
import mcouch.core.http.response.ViewCustomStructureResponse;
import mcouch.core.http.response.ViewDocumentResponse;
import mcouch.core.http.response.ViewDocumentsResponse;
import mcouch.core.jackson.JSONSerializer;
import mcouch.core.rhino.DocumentFunctions;
import mcouch.core.rhino.JavaScriptInterpreter;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

public class Database {
    private AllDocuments allDocuments;
    private Map<String, ViewGroup> viewGroups = new HashMap<>();
    private Indexes indexes;
    public static String ReducedResult = "{\"rows\":[{\"key\":null,\"value\":%s}]}";

    private String name;
    private JavaScriptInterpreter javaScriptInterpreter;
    private final DocumentFunctions documentFunctions;

    public Database(String name, MapFunctionInterpreter mapFunctionInterpreter, JavaScriptInterpreter javaScriptInterpreter) {
        this.name = name;
        this.javaScriptInterpreter = javaScriptInterpreter;
        indexes = new Indexes(mapFunctionInterpreter);
        allDocuments = new AllDocuments(javaScriptInterpreter);
        documentFunctions = new DocumentFunctions(javaScriptInterpreter);
    }

    public ViewGroup createViewGroup(String viewGroupName, String document) {
        ViewGroup viewGroup = new ViewGroup(viewGroupName, document, documentFunctions);
        viewGroups.put(viewGroupName, viewGroup);
        return viewGroup;
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
        return viewGroups.containsKey(viewGroupName);
    }

    public ViewGroup viewGroup(String name) {
        return viewGroups.get(name);
    }

    public String executeView(String viewGroupName, String viewName, IndexQuery indexQuery, boolean doReduce) {
        ViewGroup viewGroup = viewGroup(viewGroupName);
        ViewGroupDefinition viewGroupDefinition = viewGroup.definition();
        ViewDefinition viewDefinition = viewGroupDefinition.getView(viewName);
        View view = indexes.buildIndex(viewName, viewDefinition.map, allDocuments);

        NavigableMap<IndexKey, IndexEntry> map = indexQuery.execute(view);
        Reducer reducer = viewDefinition.reducer();
        if (doReduce && reducer != null) {
            return String.format(ReducedResult, reducer.reduce(map));
        } else {
            ViewDocumentsResponse viewDocumentsResponse = new ViewDocumentsResponse(allDocuments.size(), 0);
            for (IndexKey indexKey : map.keySet()) {
                IndexEntry indexEntry = map.get(indexKey);
                for (IndexValue indexValue : indexEntry.indexedValues()) {
                    if (indexValue.isDocId()) {
                        ViewDocumentResponse viewDocumentResponse = new ViewDocumentResponse(indexValue.getDocId(), indexKey.value(), indexValue.getDocId(), allDocuments.get(indexValue.getDocId()));
                        viewDocumentsResponse.add(viewDocumentResponse);
                    } else {
                        ViewCustomStructureResponse viewCustomStructureResponse = new ViewCustomStructureResponse(indexValue.getDocId(), indexKey.value(), javaScriptInterpreter.stringiFy(indexValue.getObject()));
                        viewDocumentsResponse.add(viewCustomStructureResponse);
                    }
                }
            }
            return JSONSerializer.toJson(viewDocumentsResponse);
        }
    }

    public SuccessfulDocumentCreateResponse addDocument(String document) {
        return allDocuments.add(document);
    }

    public String get(String documentId) {
        return allDocuments.get(documentId);
    }

    public String name() {
        return name;
    }

    public SuccessfulDocumentCreateResponse updateDocument(String document) {
        return allDocuments.update(document);
    }

    public void delete(String id) {
        allDocuments.remove(id);
    }
}