package mcouch.testapp;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type == 'AnotherSample'")
public class AnotherSampleEntity extends CouchDbDocument {
    private String type = "AnotherSample";
}