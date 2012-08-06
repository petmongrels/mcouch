package mcouch.core.couch.database;

public class ViewGroup {
    private final String name;
    private final String document;

    public ViewGroup(String name, String document) {
        this.name = name;
        this.document = document;
    }

    public int size() {
        return 0;
    }

    public String name() {
        return name;
    }
}