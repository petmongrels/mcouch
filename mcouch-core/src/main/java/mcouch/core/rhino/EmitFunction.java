package mcouch.core.rhino;

import mcouch.core.couch.indexing.Index;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

public class EmitFunction {
    private Index index;
    private final static ObjectMapper mapper = new ObjectMapper();

    public static final String EMIT_FUNCTION = "function emit(one, two){return javaEmitFunction.emit(one, two);}";

    public void emit(String indexValue, String docId) {
        if (index == null) {
            throw new AssertionError("Not index set, which can be updated");
        }
        index.addOrUpdate(indexValue, docId);
    }

    public void emit(String[] indexValues, String docId) {
        if (index == null) {
            throw new AssertionError("Not index set, which can be updated");
        }
        index.addOrUpdate(toJson(indexValues).toString(), docId);
    }

    private JsonNode toJson(String[] strings) {
        ArrayNode key = mapper.createArrayNode();
        for (Object component : strings) {
            if (component == new Object[0]) {
                key.addObject();
            } else {
                key.addPOJO(component);
            }
        }
        return key;
    }


    public void currentIndex(Index index) {
        this.index = index;
    }
}