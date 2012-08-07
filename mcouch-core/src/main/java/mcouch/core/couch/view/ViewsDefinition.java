package mcouch.core.couch.view;

import mcouch.core.jackson.JSONSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewsDefinition {
    private Map<String, ViewDefinition> views = new HashMap<>();
    private String document;

    public static ViewsDefinition create(String document) {
        ViewsDefinition viewsDefinition = JSONSerializer.fromJson(document, ViewsDefinition.class);
        viewsDefinition.document = document;
        return viewsDefinition;
    }

    public String document() {
        return document;
    }

    public ViewDefinition getView(String name) {
        return views.get(name);
    }
}