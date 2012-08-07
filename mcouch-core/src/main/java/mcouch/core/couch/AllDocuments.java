package mcouch.core.couch;

import mcouch.core.rhino.ExtractDocumentIdFunction;
import mcouch.core.rhino.JavaScriptInterpreter;

import java.util.*;

public class AllDocuments {
    private Map<String, String> all = new HashMap<>();
    private final ExtractDocumentIdFunction extractDocumentIdFunction;

    public AllDocuments(JavaScriptInterpreter javaScriptInterpreter) {
        extractDocumentIdFunction = new ExtractDocumentIdFunction(javaScriptInterpreter);
    }

    public Object remove(Object key) {
        return all.remove(key);
    }

    public Object add(String document) {
        String key = extractDocumentIdFunction.getFrom(document);
        return all.put(key, document);
    }

    public Object get(Object key) {
        return all.get(key);
    }

    public boolean containsKey(Object key) {
        return all.containsKey(key);
    }

    public void doForAllDocuments(DocumentIterator documentIterator) {
        Set<String> docIds = all.keySet();
        for (String docId : docIds) {
            Object document = all.get(docId);
            documentIterator.iterate(docId, document);
        }
    }

    public <T> List<T> getAll(List<String> documentIds) {
        List<T> documents = new ArrayList<>(documentIds.size());
        for (String documentId : documentIds)
            documents.add((T) get(documentId));
        return documents;
    }

    public int size() {
        return all.size();
    }
}