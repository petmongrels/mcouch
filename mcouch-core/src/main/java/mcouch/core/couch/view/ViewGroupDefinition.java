package mcouch.core.couch.view;

import mcouch.core.jackson.JSONSerializer;
import mcouch.core.rhino.DocumentFunctions;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewGroupDefinition {
    private String revision;
    @JsonProperty
    private Map<String, ViewDefinition> views = new HashMap<>();
    private String document;

    public static ViewGroupDefinition create(String document, DocumentFunctions documentFunctions) {
        ViewGroupDefinition viewGroupDefinition = JSONSerializer.fromJson(document, ViewGroupDefinition.class);
        viewGroupDefinition.revision = UUID.randomUUID().toString();
        viewGroupDefinition.document = documentFunctions.updateExistingDocument(document, viewGroupDefinition.revision);
        return viewGroupDefinition;
    }

    public String document() {
        return document;
    }

    public ViewDefinition getView(String name) {
        return views.get(name);
    }

    public String revision() {
        return revision;
    }
}