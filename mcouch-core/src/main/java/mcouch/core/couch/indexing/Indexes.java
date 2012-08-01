package mcouch.core.couch.indexing;

import mcouch.core.couch.AllDocuments;
import mcouch.core.rhino.EmitFunction;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.ArrayList;
import java.util.List;

public class Indexes {
    private List<Index> indexList = new ArrayList<>();
    private MapFunctionInterpreter mapFunctionInterpreter;

    public Indexes(MapFunctionInterpreter mapFunctionInterpreter) {
        this.mapFunctionInterpreter = mapFunctionInterpreter;
    }

    public Index getOrCreate(String name, String mapFunction, EmitFunction emitFunction) {
        Index index = new Index(name, mapFunctionInterpreter, mapFunction, emitFunction);
        if (indexList.contains(index)) return index;
        indexList.add(index);
        return index;
    }

    public Index buildIndex(String name, String mapFunction, EmitFunction emitFunction, AllDocuments allDocuments) {
        Index index = getOrCreate(name, mapFunction, emitFunction);
        index.build(allDocuments);
        return index;
    }
}