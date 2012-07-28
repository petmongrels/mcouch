package mektorp.couch;

import java.util.ArrayList;
import java.util.List;

public class IndexEntry {
    private String firstDocumentId;
    private List<String> similarEntries;

    public IndexEntry(String firstDocumentId) {
        this.firstDocumentId = firstDocumentId;
    }

    public void append(String docId) {
        if (similarEntries == null) similarEntries = new ArrayList<>(3);
        similarEntries.add(docId);
    }

    public List<String> documentIds() {
        ArrayList<String> docIds = new ArrayList<>(1);
        docIds.add(firstDocumentId);
        if (similarEntries != null)
            docIds.addAll(similarEntries);
        return docIds;
    }
}