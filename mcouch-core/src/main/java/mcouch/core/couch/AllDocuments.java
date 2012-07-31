package mcouch.core.couch;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AllDocuments {
    private Map<String, Object> all = new HashMap<>();

    public Object remove(Object key) {
        return all.remove(key);
    }

    public Object add(String key, Object value) {
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

    public void add(Object doc) {
        UUID uuid = UUID.randomUUID();
        add(uuid.toString(), doc);
    }

}