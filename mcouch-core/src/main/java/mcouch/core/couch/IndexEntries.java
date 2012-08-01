package mcouch.core.couch;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IndexEntries {
    private Collection<IndexEntry> indexEntries;

    public IndexEntries(Collection<IndexEntry> indexEntries) {
        this.indexEntries = indexEntries;
    }

    public List<String> documentIds() {
        ArrayList<String> documentIds = new ArrayList<>();
        for (IndexEntry indexEntry : indexEntries) {
            documentIds.addAll(indexEntry.documentIds());
        }
        return documentIds;
    }
}