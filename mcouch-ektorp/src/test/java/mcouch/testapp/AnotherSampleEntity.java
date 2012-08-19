package mcouch.testapp;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type == 'AnotherSample'")
public class AnotherSampleEntity extends CouchDbDocument {
    @JsonProperty
    private String type = "AnotherSample";
}