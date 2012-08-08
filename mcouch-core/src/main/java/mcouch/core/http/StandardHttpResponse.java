package mcouch.core.http;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;

public class StandardHttpResponse {
    public static BasicHttpResponse NOT_FOUND = new BasicHttpResponse(StandardHttpLine.NOT_FOUND);
    public static BasicHttpResponse OK = new BasicHttpResponse(StandardHttpLine.OK);

    public static BasicHttpResponse okWith(String str) {
        BasicHttpResponse response = new BasicHttpResponse(StandardHttpLine.OK);
        response.setEntity(new StringEntity(str, ContentType.APPLICATION_JSON));
        return response;
    }
}