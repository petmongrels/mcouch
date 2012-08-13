package mcouch.core.couch.database;

import mcouch.core.rhino.JavaScriptInterpreter;
import mcouch.core.rhino.MapFunctionInterpreter;

import java.util.HashMap;
import java.util.Map;

public class Databases {
    private Map<String, Database> items = new HashMap<>();
    private final MapFunctionInterpreter mapFunctionInterpreter;
    private JavaScriptInterpreter javaScriptInterpreter;

    public Databases(Database... databases) {
        javaScriptInterpreter = new JavaScriptInterpreter();
        mapFunctionInterpreter = new MapFunctionInterpreter(javaScriptInterpreter);
        for (Database database : databases)
            items.put(database.name(), database);
    }

    public boolean contains(String databaseName) {
        return items.containsKey(databaseName.toLowerCase());
    }

    public Database getDatabase(String databaseName) {
        return items.get(databaseName.toLowerCase());
    }

    public void create(String databaseName) {
        items.put(databaseName.toLowerCase(), new Database(databaseName, mapFunctionInterpreter, javaScriptInterpreter));
    }
}