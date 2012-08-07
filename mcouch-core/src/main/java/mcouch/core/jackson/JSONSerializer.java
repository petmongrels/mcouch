package mcouch.core.jackson;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JSONSerializer {
    public static String toJson(Object objectToSerialize) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        try {
            mapper.writeValue(stringWriter, objectToSerialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }

    public static <T> T fromJson(String jsonString, Class<T> rootClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, rootClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}