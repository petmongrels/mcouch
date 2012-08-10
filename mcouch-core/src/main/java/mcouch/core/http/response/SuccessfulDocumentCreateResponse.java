package mcouch.core.http.response;

public class SuccessfulDocumentCreateResponse {
    private boolean ok;
    private String id;
    private String rev;
    private String document;

    public SuccessfulDocumentCreateResponse(String id, String rev) {
        this.ok = true;
        this.id = id;
        this.rev = rev;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }
}