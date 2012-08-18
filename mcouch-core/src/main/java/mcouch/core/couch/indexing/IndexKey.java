package mcouch.core.couch.indexing;

public class IndexKey {
    private String indexedValue;

    public IndexKey(String indexedValue) {
        this.indexedValue = indexedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexKey that = (IndexKey) o;

        return indexedValue.equals(that.indexedValue);

    }

    @Override
    public int hashCode() {
        return indexedValue.hashCode();
    }

    public String value() {
        return indexedValue;
    }

    @Override
    public String toString() {
        return "value=" + indexedValue;
    }
}