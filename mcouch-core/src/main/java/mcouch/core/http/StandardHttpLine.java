package mcouch.core.http;

import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicStatusLine;

public class StandardHttpLine {
    private static final ProtocolVersion http = new ProtocolVersion("http", 1, 1);
    public static BasicStatusLine NOT_FOUND = new BasicStatusLine(http, HttpStatus.SC_NOT_FOUND, null);
    public static BasicStatusLine OK = new BasicStatusLine(http, HttpStatus.SC_OK, null);
}