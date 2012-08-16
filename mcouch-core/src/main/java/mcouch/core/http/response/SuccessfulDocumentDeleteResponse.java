package mcouch.core.http.response;

import org.codehaus.jackson.annotate.JsonProperty;

public class SuccessfulDocumentDeleteResponse {
    @JsonProperty
    public boolean ok = true;
    @JsonProperty
    public String rev;

    public SuccessfulDocumentDeleteResponse() {
    }

    public SuccessfulDocumentDeleteResponse(String rev) {
        this.rev = rev;
    }
}