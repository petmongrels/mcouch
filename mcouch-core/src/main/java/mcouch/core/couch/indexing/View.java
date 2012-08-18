package mcouch.core.couch.indexing;

import mcouch.core.couch.AllDocuments;
import mcouch.core.couch.DocumentIterator;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.NavigableMap;
import java.util.TreeMap;

public class View implements DocumentIterator {
    private String name;
    private MapFunctionInterpreter mapFunctionInterpreter;
    private String mapFunction;
    private TreeMap<IndexKey, IndexEntry> treeMap = new TreeMap<>(new IndexKeyComparator());

    public View(String name, MapFunctionInterpreter mapFunctionInterpreter, String mapFunction) {
        this.name = name;
        this.mapFunctionInterpreter = mapFunctionInterpreter;
        this.mapFunction = mapFunction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        View view = (View) o;

        return name.equals(view.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public void iterate(String id, String document) {
        mapFunctionInterpreter.interpret(mapFunction, id, document);
    }

    public void build(AllDocuments allDocuments) {
        mapFunctionInterpreter.emitOn(this);
        allDocuments.doForAllDocuments(this);
    }

    public void addOrUpdate(String indexValue, Object object, String docId) {
        IndexKey indexKey = IndexKeyFactory.create(indexValue);
        IndexEntry indexEntry = treeMap.get(indexKey);
        if (indexEntry == null) treeMap.put(indexKey, new IndexEntry(object, docId));
        else
            indexEntry.append(object, docId);
    }

    public NavigableMap<IndexKey, IndexEntry> get(IndexKey indexKey) {
        return itemsBetween(indexKey, indexKey);
    }

    public NavigableMap<IndexKey, IndexEntry> itemsBetween(IndexKey startKey, IndexKey endKey) {
        return treeMap.subMap(startKey, true, endKey, true);
    }

    public NavigableMap<IndexKey, IndexEntry> all() {
        return treeMap;
    }
}