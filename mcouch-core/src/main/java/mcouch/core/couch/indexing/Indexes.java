package mcouch.core.couch.indexing;

import mcouch.core.couch.AllDocuments;
import mcouch.core.rhino.EmitFunction;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.ArrayList;
import java.util.List;

public class Indexes {
    private List<View> viewList = new ArrayList<>();
    private MapFunctionInterpreter mapFunctionInterpreter;

    public Indexes(MapFunctionInterpreter mapFunctionInterpreter) {
        this.mapFunctionInterpreter = mapFunctionInterpreter;
    }

    public View getOrCreate(String name, String mapFunction, EmitFunction emitFunction) {
        View view = new View(name, mapFunctionInterpreter, mapFunction, emitFunction);
        if (viewList.contains(view)) return view;
        viewList.add(view);
        return view;
    }

    public View buildIndex(String name, String mapFunction, EmitFunction emitFunction, AllDocuments allDocuments) {
        View view = getOrCreate(name, mapFunction, emitFunction);
        view.build(allDocuments);
        return view;
    }
}