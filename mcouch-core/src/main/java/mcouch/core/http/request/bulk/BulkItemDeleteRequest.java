package mcouch.core.http.request.bulk;

import mcouch.core.couch.database.Database;
import mcouch.core.http.request.body.BulkDeleteRequestDoc;
import mcouch.core.http.response.EnclosedResponseForBulkRequest;

public class BulkItemDeleteRequest implements BulkItemRequest {
    private BulkDeleteRequestDoc requestDoc;

    public BulkItemDeleteRequest(BulkDeleteRequestDoc requestDoc) {
        this.requestDoc = requestDoc;
    }

    @Override
    public EnclosedResponseForBulkRequest execute(Database database) {
        database.delete(requestDoc._id);
        return new EnclosedResponseForBulkRequest(requestDoc._id, requestDoc._rev);
    }
}