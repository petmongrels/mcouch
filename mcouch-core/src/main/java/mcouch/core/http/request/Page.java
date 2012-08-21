package mcouch.core.http.request;

public class Page {
    private int limit;
    private int skip;

    public Page(int skip, int limit) {
        this.limit = limit;
        this.skip = skip;
    }

    public boolean fallsOutOf(int index) {
        return index < skip * limit || index >= (skip + 1) * limit;
    }
}