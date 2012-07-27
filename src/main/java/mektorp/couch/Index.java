package mektorp.couch;

import mektorp.jackson.JSONSerializer;
import mektorp.rhino.MapFunctionInterpreter;

public class Index implements DocumentIterator {
    private String name;
    private MapFunctionInterpreter mapFunctionInterpreter;
    private String mapFunction;

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
}