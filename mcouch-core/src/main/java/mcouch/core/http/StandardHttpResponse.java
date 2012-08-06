package mcouch.core.http;

import org.apache.http.message.BasicHttpResponse;

public class StandardHttpResponse {
    public static BasicHttpResponse NOT_FOUND = new BasicHttpResponse(StandardHttpLine.NOT_FOUND);
    public static BasicHttpResponse OK = new BasicHttpResponse(StandardHttpLine.OK);
}