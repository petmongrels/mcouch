package mcouch.core.couch.database;

import mcouch.core.rhino.JavaScriptInterpreter;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Databases {
    private List<Database> items = new ArrayList<>();
    private final MapFunctionInterpreter mapFunctionInterpreter;
    private JavaScriptInterpreter javaScriptInterpreter;

    public Databases(Database... databases) {
        javaScriptInterpreter = new JavaScriptInterpreter();
        mapFunctionInterpreter = new MapFunctionInterpreter(javaScriptInterpreter);
        items.addAll(Arrays.asList(databases));
    }

    public boolean contains(String databaseName) {
        return items.contains(new Database(databaseName, mapFunctionInterpreter, javaScriptInterpreter));
    }

    public Database getDatabase(String databaseName) {
        return items.get(items.indexOf(new Database(databaseName, mapFunctionInterpreter, javaScriptInterpreter)));
    }

    public void create(String databaseName) {
        items.add(new Database(databaseName, mapFunctionInterpreter, javaScriptInterpreter));
    }
}