package mcouch.core.couch;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AllDocuments {
    private Map<String, Object> all = new HashMap<>();

    public Object remove(Object key) {
        return all.remove(key);
    }

    public Object put(String key, Object value) {
        return all.put(key, value);
    }

    public Object get(Object key) {
        return all.get(key);
    }

    public boolean containsKey(Object key) {
        return all.containsKey(key);
    }

    public void doForAllDocuments(DocumentIterator documentIterator) {
        Set<String> docIds = all.keySet();
        for (String docId : docIds) {
            Object document = all.get(docId);
            documentIterator.iterate(docId, document);
        }
    }
}