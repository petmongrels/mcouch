package mcouch.core.http.request;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.util.List;
import java.util.StringTokenizer;

public class CouchURI {
    private String databaseName;
    private String viewGroup;
    private String viewName;
    private String key;
    private boolean bulkPost;
    private String documentId;

    public CouchURI(URI uri, String method) {
        String uriPath = uri.getPath();
        StringTokenizer pathTokenizer = new StringTokenizer(uriPath, "/");
        if (pathTokenizer.countTokens() >= 1)
            databaseName = pathTokenizer.nextToken();
        if (method.equals("GET") && pathTokenizer.countTokens() == 1) {
            documentId = pathTokenizer.nextToken();
            return;
        }

        if (method.equals("POST") && pathTokenizer.countTokens() == 1 && pathTokenizer.nextToken().equals("_bulk_docs")) {
            bulkPost = true;
            return;
        }

        if (pathTokenizer.countTokens() >= 2 && pathTokenizer.nextToken().equals("_design"))
            viewGroup = pathTokenizer.nextToken();

        if (pathTokenizer.countTokens() >= 2 && pathTokenizer.nextToken().equals("_view"))
            viewName = pathTokenizer.nextToken();

        List<NameValuePair> queryParams = URLEncodedUtils.parse(uri, "UTF-8");
        for (NameValuePair nameValuePair : queryParams) {
            switch (nameValuePair.getName()) {
                case "key":
                    String value = nameValuePair.getValue();
                    key = value.substring(1, value.length() - 1);
            }
        }
    }

    public String databaseName() {
        return databaseName;
    }

    public String viewGroup() {
        return viewGroup;
    }

    public String viewName() {
        return viewName;
    }

    public String key() {
        return key;
    }

    public boolean isGetViewDocRequest() {
        return databaseName != null && viewGroup != null && viewName == null;
    }

    public boolean isExecuteViewRequest() {
        return databaseName != null && viewGroup != null && viewName != null;
    }

    public boolean isBulkDocsRequest() {
        return databaseName != null && bulkPost;
    }

    public boolean isBulkPost() {
        return bulkPost;
    }

    public boolean isGetDocRequest() {
        return documentId != null;
    }

    public String documentId() {
        return documentId;
    }
}