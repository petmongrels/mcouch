package mektorp.couch.indexing;

import mektorp.couch.AllDocuments;
import mektorp.couch.DocumentIterator;
import mektorp.couch.indexing.query.IndexQuery;
import mektorp.couch.indexing.query.IndexQueryFactory;
import mektorp.rhino.MapFunctionInterpreter;
import org.ektorp.ViewQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public List<String> list(ViewQuery query) {
        IndexQuery indexQuery = IndexQueryFactory.create(treeMap, query);
        return indexQuery.execute();
    }
}