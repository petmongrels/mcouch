package mcouch.core.couch.indexing;

import mcouch.core.couch.AllDocuments;
import mcouch.core.couch.DocumentIterator;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.NavigableMap;
import java.util.TreeMap;

public class Index implements DocumentIterator {
    private String name;
    private MapFunctionInterpreter mapFunctionInterpreter;
    private String mapFunction;
    private TreeMap<IndexKey, IndexEntry> treeMap = new TreeMap<>(new IndexKeyComparator());

    public Index(String name, MapFunctionInterpreter mapFunctionInterpreter, String mapFunction) {
        this.name = name;
        this.mapFunctionInterpreter = mapFunctionInterpreter;
        this.mapFunction = mapFunction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Index index = (Index) o;

        return name.equals(index.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public void iterate(String id, Object document) {
        mapFunctionInterpreter.interpret(mapFunction, document);
    }

    public void build(AllDocuments allDocuments) {
        allDocuments.doForAllDocuments(this);
    }

    public void addOrUpdate(String indexValue, String docId) {
        IndexKey indexKey = new IndexKey(indexValue);
        IndexEntry indexEntry = treeMap.get(indexKey);
        if (indexEntry == null) treeMap.put(indexKey, new IndexEntry(docId));
        else
            indexEntry.append(docId);
    }

    public IndexEntry get(IndexKey indexKey) {
        return treeMap.get(indexKey);
    }

    public NavigableMap<IndexKey, IndexEntry> itemsBetween(IndexKey startKey, IndexKey endKey) {
        return treeMap.subMap(startKey, true, endKey, true);
    }
}