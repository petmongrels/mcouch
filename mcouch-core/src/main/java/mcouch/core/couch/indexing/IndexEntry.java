package mcouch.core.couch.indexing;

import org.mozilla.javascript.NativeArray;

import java.util.ArrayList;
import java.util.List;

public class IndexEntry {
    private IndexValue object;
    private List<IndexValue> similarEntries;

    public IndexEntry(Object object, String docId) {
        this.object = new IndexValue(object, docId);
    }

    public void append(Object object, String docId) {
        if (similarEntries == null) similarEntries = new ArrayList<>(3);
        similarEntries.add(new IndexValue(object, docId));
    }

    public List<IndexValue> indexedValues() {
        ArrayList<IndexValue> indexedValues = new ArrayList<>(1);
        indexedValues.add(object);
        if (similarEntries != null)
            indexedValues.addAll(similarEntries);
        return indexedValues;
    }

    public static String fromJSObject(Object obj) {
        if (obj instanceof NativeArray) {
            StringBuilder stringBuilder = new StringBuilder();
            NativeArray nativeArray = (NativeArray) obj;
            int size = nativeArray.size();
            for (int i = 0; i < size; i++) {
                Object arrayElement = nativeArray.get(i);
                if (arrayElement != null)
                    stringBuilder.append(arrayElement);
                if (i != size - 1)
                    stringBuilder.append(",");
            }
            return stringBuilder.toString();
        } else {
            return obj == null ? null : obj.toString();
        }
    }
}