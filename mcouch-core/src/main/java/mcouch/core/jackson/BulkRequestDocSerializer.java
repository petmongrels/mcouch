package mcouch.core.jackson;

import mcouch.core.http.request.body.BulkRequestDoc;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.JsonNodeDeserializer;

import java.io.IOException;

public class BulkRequestDocSerializer extends JsonDeserializer<BulkRequestDoc> {
    @Override
    public BulkRequestDoc deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = JsonNodeDeserializer.instance.deserialize(jp, ctxt);
        return new BulkRequestDoc(jsonNode.toString());
    }
}