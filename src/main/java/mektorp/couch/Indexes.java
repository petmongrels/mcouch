package mektorp.couch;

import mektorp.rhino.MapFunctionInterpreter;

import java.util.ArrayList;
import java.util.List;

public class Indexes {
    private List<Index> indexList = new ArrayList<>();
    private MapFunctionInterpreter mapFunctionInterpreter;

    public Indexes(MapFunctionInterpreter mapFunctionInterpreter) {
        this.mapFunctionInterpreter = mapFunctionInterpreter;
    }

    public Index getOrCreate(String name, String mapFunction) {
        Index index = new Index(name, mapFunctionInterpreter, mapFunction);
        if (indexList.contains(index)) return index;
        indexList.add(index);
        return index;
    }
}