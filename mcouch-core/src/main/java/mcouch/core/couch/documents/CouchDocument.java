package mcouch.core.couch.documents;

import mcouch.core.rhino.DocumentFunctions;

import java.util.UUID;

public class CouchDocument {
    private String json;

    public CouchDocument(String json, DocumentFunctions documentFunctions) {
        this.json = documentFunctions.updateExistingDocument(json);
    }

    @Override
    public String toString() {
        return json;
    }
}