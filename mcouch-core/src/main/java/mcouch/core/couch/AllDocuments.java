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
            String document = all.get(docId);
            documentIterator.iterate(docId, document);
        }
    }

    public Map<String, String> getAll(List<String> documentIds) {
        Map<String, String> documents = new HashMap<>(documentIds.size());
        for (String documentId : documentIds)
            documents.put(documentId, get(documentId));
        return documents;
    }

    public int size() {
        return all.size();
    }

    public SuccessfulDocumentCreateResponse update(String document) {
        String documentId = documentId(document);
        return update(documentId, document);
    }

    private SuccessfulDocumentCreateResponse update(String documentId, String document) {
        String newRevision = UUID.randomUUID().toString();
        String updatedDocument = documentFunctions.updateExistingDocument(document, newRevision);
        all.put(documentId, updatedDocument);
        return new SuccessfulDocumentCreateResponse(documentId, newRevision);
    }

    private String documentId(String document) {
        return documentFunctions.getDocumentId(document);
    }

    public void addOrUpdate(String document) {
        String documentId = documentId(document);
        if (documentId == null || !all.containsKey(documentId))
            add(document);
        else
            update(documentId, document);
    }
}