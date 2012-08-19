package mcouch.core.http.request.bulk;

import mcouch.core.couch.database.Database;
import mcouch.core.http.response.EnclosedResponseForBulkRequest;

public interface BulkItemRequest {
    EnclosedResponseForBulkRequest execute(Database database);
}