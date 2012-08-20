package mcouch.core.http.request.bulk;

import mcouch.core.couch.database.Database;
import mcouch.core.http.response.EnclosedResponseForBulkRequest;

public class BulkPostItemRequest implements BulkItemRequest {
    private String json;

    public BulkPostItemRequest(String json) {
        this.json = json;
    }

    @Override
    public EnclosedResponseForBulkRequest execute(Database database) {
        database.addOrUpdate(json);
        return null;
    }
}