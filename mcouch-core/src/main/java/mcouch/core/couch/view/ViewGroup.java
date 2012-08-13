package mcouch.core.couch.view;

import mcouch.core.rhino.DocumentFunctions;

public class ViewGroup {
    private final String name;
    private ViewGroupDefinition viewGroupDefinition;

    public ViewGroup(String name, String document, DocumentFunctions documentFunctions) {
        this.name = name;
        viewGroupDefinition = ViewGroupDefinition.create(document, documentFunctions);
    }

    public ViewGroupDefinition definition() {
        return viewGroupDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewGroup viewGroup = (ViewGroup) o;
        return name.equals(viewGroup.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String id() {
        return "_design/" + name;
    }
}