package mcouch.core.couch.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViewDefinition {
    @JsonProperty
    private String map;

    public String mapFunction() {
        return map;
    }
}