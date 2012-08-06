package mcouch.core.couch.database;

import mcouch.core.couch.AllDocuments;

public class Database {
    private AllDocuments allDocuments = new AllDocuments();
    private ViewGroup viewGroup;

    private String name;

    public Database(String name) {
        this.name = name;
    }

    public void createViewGroup(String viewGroupName, String document) {
        viewGroup = new ViewGroup(viewGroupName, document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Database database = (Database) o;
        return name.equals(database.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Database{documentCount=%d, indexCount=%d, name='%s'}", allDocuments.size(), viewGroup == null ? 0 : viewGroup.size(), name);
    }

    public boolean containsViewGroup(String viewGroupName) {
        return viewGroup != null && viewGroup.name().equals(viewGroupName);
    }
}