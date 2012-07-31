package mcouch.core.couch;

public interface DocumentIterator {
    void iterate(String id, Object document);
}
