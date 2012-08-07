package mcouch.core.couch.view;

public class ViewGroup {
    private final String name;
    private ViewsDefinition viewsDefinition;

    public ViewGroup(String name, String document) {
        this.name = name;
        viewsDefinition = ViewsDefinition.create(document);
    }

    public ViewsDefinition definition() {
        return viewsDefinition;
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
}