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
    private boolean bulkDocs;
    private String documentId;
    private boolean includeDocs;
    private String key;
    private String startKey;
    private String endKey;
    private boolean reduce = true;
    private boolean allDocs;
    private String rev;

    public CouchURI(URI uri) {
        String uriPath = uri.getPath();
        StringTokenizer pathTokenizer = new StringTokenizer(uriPath, "/");
        if (pathTokenizer.countTokens() >= 1)
            databaseName = pathTokenizer.nextToken();

        if (pathTokenizer.countTokens() == 1) {
            String nextToken = pathTokenizer.nextToken();
            bulkDocs = nextToken.equals("_bulk_docs");
            allDocs = nextToken.equals("_all_docs");
            documentId = (bulkDocs || allDocs) ? null : nextToken;
            return;
        }

        if (pathTokenizer.countTokens() >= 2 && pathTokenizer.nextToken().equals("_design"))
            viewGroup = pathTokenizer.nextToken();

        if (pathTokenizer.countTokens() >= 2 && pathTokenizer.nextToken().equals("_view"))
            viewName = pathTokenizer.nextToken();

        List<NameValuePair> queryParams = URLEncodedUtils.parse(uri, "UTF-8");
        for (NameValuePair nameValuePair : queryParams) {
            String value = nameValuePair.getValue();
            switch (nameValuePair.getName()) {
                case "key":
                    key = removeQuotes(value);
                    break;
                case "include_docs":
                    includeDocs = Boolean.parseBoolean(value);
                    break;
                case "startkey":
                    startKey = removeQuotes(value);
                    break;
                case "endkey":
                    endKey = removeQuotes(value);
                    break;
                case "reduce":
                    reduce = Boolean.parseBoolean(value);
                    break;
                case "rev":
                    rev = removeQuotes(value);
                    break;
            }
        }
    }

    private String removeQuotes(String value) {
        return value.replace("\"", "").replace("[", "").replace("]", "");
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

    public boolean isGetViewDocRequest() {
        return databaseName != null && viewGroup != null && viewName == null;
    }

    public boolean isExecuteViewRequest() {
        return databaseName != null && viewGroup != null && viewName != null;
    }

    public boolean isBulkDocsRequest() {
        return databaseName != null && bulkDocs;
    }

    public boolean isBulkDocs() {
        return bulkDocs;
    }

    public boolean isDocRequest() {
        return documentId != null;
    }

    public String documentId() {
        return documentId;
    }

    public String getKey() {
        return key;
    }

    public String getStartKey() {
        return startKey;
    }

    public String getEndKey() {
        return endKey;
    }

    public boolean isReduce() {
        return reduce;
    }

    public boolean isAllDocsRequest() {
        return allDocs;
    }

    public String getRev() {
        return rev;
    }
}