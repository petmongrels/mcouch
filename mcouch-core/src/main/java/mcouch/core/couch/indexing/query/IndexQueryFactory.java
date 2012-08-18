package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.http.request.CouchURI;
import mcouch.core.rhino.JavaScriptInterpreter;

public class IndexQueryFactory {
    public static IndexQuery create(CouchURI couchURI, JavaScriptInterpreter javaScriptInterpreter) {
        if (couchURI.getKey() != null) return new SimpleQuery(convert(javaScriptInterpreter, couchURI.getKey()));
        if (couchURI.getStartKey() != null)
            return new BetweenQuery(convert(javaScriptInterpreter, couchURI.getStartKey()), convert(javaScriptInterpreter, couchURI.getEndKey()));
        return new AllQuery();
    }

    private static String convert(JavaScriptInterpreter javaScriptInterpreter, String value) {
        Object jsObject = javaScriptInterpreter.interpret(String.format("(%s)", value));
        return IndexEntry.fromJSObject(jsObject);
    }
}