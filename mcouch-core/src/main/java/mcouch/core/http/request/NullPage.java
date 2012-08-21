package mcouch.core.http.request;

public class NullPage extends Page {
    public static Page Instance = new NullPage();

    private NullPage() {
        super(0, 0);
    }

    @Override
    public boolean fallsOutOf(int index) {
        return false;
    }
}