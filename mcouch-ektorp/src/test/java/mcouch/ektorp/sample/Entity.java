package mcouch.ektorp.sample;

import org.ektorp.support.CouchDbDocument;

public class Entity extends CouchDbDocument {
    private String firstString;
    private String secondString;

    public String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }
}