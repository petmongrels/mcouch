package mcouch.core.http.request.bulk;

import mcouch.core.http.request.body.BulkDeleteRequestDoc;
import mcouch.core.jackson.JSONSerializer;

public class BulkRequestItemFactory {
    public static BulkItemRequest create(String s) {
        if (s.contains("_deleted")) {
            BulkDeleteRequestDoc bulkDeleteRequestDoc = JSONSerializer.fromJson(s, BulkDeleteRequestDoc.class);
            return new BulkItemDeleteRequest(bulkDeleteRequestDoc);
        }
        return new BulkPostItemRequest(s);
    }
}