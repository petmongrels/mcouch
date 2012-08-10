package mcouch.core.couch.view;

import mcouch.core.couch.documents.CouchDocument;
import mcouch.core.jackson.JSONSerializer;
import mcouch.core.rhino.DocumentFunctions;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewsDefinition {
    private Map<String, ViewDefinition> views = new HashMap<>();
    private CouchDocument document;

    public static ViewsDefinition create(String document, DocumentFunctions documentFunctions) {
        ViewsDefinition viewsDefinition = JSONSerializer.fromJson(document, ViewsDefinition.class);
        viewsDefinition.document = new CouchDocument(document, documentFunctions);
        return viewsDefinition;
    }

    public String document() {
        return document.toString();
    }

    public ViewDefinition getView(String name) {
        return views.get(name);
    }
}