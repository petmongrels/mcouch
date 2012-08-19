package mcouch.core.http.request.body;

import mcouch.core.jackson.BulkRequestDocSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BulkRequestDocSerializer.class)
public class BulkRequestDoc {
    public String json;

    public BulkRequestDoc(String json) {
        this.json = json;
    }

    public BulkRequestDoc() {
    }
}