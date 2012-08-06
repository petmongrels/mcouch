package mcouch.core.couch.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Databases {
    private List<Database> items = new ArrayList<Database>();

    public Databases(Database... databases) {
        items.addAll(Arrays.asList(databases));
    }

    public boolean contains(String databaseName) {
        return items.contains(new Database(databaseName));
    }

    public Database getDatabase(String databaseName) {
        return items.get(items.indexOf(new Database(databaseName)));
    }
}