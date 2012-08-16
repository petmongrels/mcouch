package mcouch.core.http.request.body;

import mcouch.core.http.MCouchException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.io.IOException;
import java.io.StringWriter;

public class CouchRequestBody {
    private String submittedJSON;

    public CouchRequestBody(HttpEntityEnclosingRequestBase request) {
        HttpEntity requestEntity = request.getEntity();
        if (requestEntity == null) return;

        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(requestEntity.getContent(), writer);
            submittedJSON = writer.toString();
        } catch (IOException e) {
            throw new MCouchException(e);
        }
    }

    public String submittedJSON() {
        return submittedJSON;
    }
}