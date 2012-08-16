package mcouch.core.http.request.body;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class BulkRequest {
    @JsonProperty
    public List<EnclosedRequest> docs;
}