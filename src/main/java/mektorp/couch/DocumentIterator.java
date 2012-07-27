package mektorp.couch;

public interface DocumentIterator {
    void iterate(String id, Object document);
}
