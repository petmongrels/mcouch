package mcouch.core.couch.indexing.query;

import mcouch.core.http.request.CouchURI;

public class IndexQueryFactory {
    public static IndexQuery create(CouchURI couchURI) {
        if (couchURI.getKey() != null) return new SimpleQuery(couchURI.getKey());
        if (couchURI.getStartKey() != null) return new BetweenQuery(couchURI.getStartKey(), couchURI.getEndKey());
        return new AllQuery();
    }
}