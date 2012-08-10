package mcouch.core.couch;

import mcouch.core.http.response.SuccessfulDocumentCreateResponse;
import mcouch.core.rhino.DocumentFunctions;
import mcouch.core.rhino.JavaScriptInterpreter;

import java.util.*;

public class AllDocuments {
    private Map<String, String> all = new HashMap<>();
    private final DocumentFunctions documentFunctions;

    public AllDocuments(JavaScriptInterpreter javaScriptInterpreter) {
        documentFunctions = new DocumentFunctions(javaScriptInterpreter);
    }

    public Object remove(String key) {
        return all.remove(key);
    }

    public SuccessfulDocumentCreateResponse add(String document) {
        String id = UUID.randomUUID().toString();
        String rev = UUID.randomUUID().toString();
        String updatedDoc = documentFunctions.updateNewDocument(document, id, rev);
        SuccessfulDocumentCreateResponse response = new SuccessfulDocumentCreateResponse(id, rev);
        all.put(id, updatedDoc);
        return response;
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