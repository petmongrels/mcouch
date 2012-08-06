package mcouch.core.rhino;

import mcouch.core.couch.indexing.View;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

public class EmitFunction {
    private View view;
    private final static ObjectMapper mapper = new ObjectMapper();

    public static final String EMIT_FUNCTION = "function emit(one, two){if (two) return javaEmitFunction.emit(one, two); return javaEmitFunction.emit(one)}";

    public void emit(String docId) {
        assertIndexNotNull();
        view.addOrUpdate(docId, docId);
    }

    public void emit(String indexValue, String docId) {
        assertIndexNotNull();
        view.addOrUpdate(indexValue, docId);
    }

    public void emit(String[] indexValues, String docId) {
        assertIndexNotNull();
        view.addOrUpdate(toJson(indexValues).toString(), docId);
    }

    private void assertIndexNotNull() {
        if (view == null) {
            throw new AssertionError("No view set, which can be updated");
        }
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


    public void currentIndex(View view) {
        this.view = view;
    }
}