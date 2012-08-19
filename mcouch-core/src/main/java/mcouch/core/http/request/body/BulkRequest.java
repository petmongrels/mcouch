package mcouch.core.http.request.body;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkRequest {
    @JsonProperty
    public List<BulkRequestDoc> docs;
}