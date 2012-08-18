package mcouch.core.couch.indexing;

public class NullIndexKey extends IndexKey {
    public static NullIndexKey Instance = new NullIndexKey();

    private NullIndexKey() {
        super(null);
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof NullIndexKey;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}