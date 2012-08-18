package mcouch.testapp;

import org.codehaus.jackson.annotate.JsonProperty;

public class SampleEntityPart {
    @JsonProperty
    public String a;
    @JsonProperty
    public String type;
}