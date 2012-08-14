package mcouch.core.couch.view;

import mcouch.core.couch.reducers.Reducer;
import mcouch.core.couch.reducers.ReducerFactory;
import org.codehaus.jackson.annotate.JsonProperty;

public class ViewDefinition {
    @JsonProperty
    public String map;
    @JsonProperty
    private String reduce;

    public Reducer reducer() {
        return ReducerFactory.create(reduce);
    }
}