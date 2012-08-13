package mcouch.testapp;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type == 'Sample'")
public class SampleEntity extends CouchDbDocument {
    private String type = "Sample";
    private String a;

    public SampleEntity() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public SampleEntity(String a) {
        this.a = a;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}