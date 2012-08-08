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

    public Object remove(String key) {
        return all.remove(key);
    }

    public Object add(String document) {
        String key = extractDocumentIdFunction.getFrom(document);
        return all.put(key, document);
    }

    public String get(String key) {
        return all.get(key);
    }

    public void doForAllDocuments(DocumentIterator documentIterator) {
        Set<String> docIds = all.keySet();
        for (String docId : docIds) {
            Object document = all.get(docId);
            documentIterator.iterate(docId, document);
        }
    }

    public List<String> getAll(List<String> documentIds) {
        List<String> documents = new ArrayList<>(documentIds.size());
        for (String documentId : documentIds)
            documents.add(get(documentId));
        return documents;
    }

    public int size() {
        return all.size();
    }
}