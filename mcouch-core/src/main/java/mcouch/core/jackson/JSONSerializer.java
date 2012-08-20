package mcouch.core.jackson;

import mcouch.core.http.MCouchException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JSONSerializer {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object objectToSerialize) {
        StringWriter stringWriter = new StringWriter();
        try {
            mapper.writeValue(stringWriter, objectToSerialize);
            stringWriter.close();
        } catch (IOException e) {
            throw new MCouchException(e);
        }
        return stringWriter.toString();
    }

    public static <T> T fromJson(String jsonString, Class<T> rootClass) {
        try {
            return mapper.readValue(jsonString, rootClass);
        } catch (IOException e) {
            throw new MCouchException(String.format("Cannot deserialize from: %s to class of type: %s", jsonString, rootClass.getSimpleName()), e);
        }
    }
}