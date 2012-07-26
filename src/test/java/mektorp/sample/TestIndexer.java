package mektorp.sample;

import mektorp.rhino.Indexer;

public class TestIndexer implements Indexer {
    private TestIndexEntry testIndexEntry;

    @Override
    public void index(String value, String docId) {
        testIndexEntry = new TestIndexEntry(value, docId);
    }

    public TestIndexEntry indexEntry() {
        return testIndexEntry;
    }
}