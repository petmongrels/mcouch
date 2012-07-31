package mcouch.ektorp.sample;

public class TestIndexEntry {
    private String value;
    private String docId;

    public TestIndexEntry(String value, String docId) {
        this.value = value;
        this.docId = docId;
    }

    public String getValue() {
        return value;
    }

    public String getDocId() {
        return docId;
    }
}