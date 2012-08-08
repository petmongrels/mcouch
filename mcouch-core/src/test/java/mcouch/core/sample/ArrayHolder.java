package mcouch.core.sample;

import java.util.ArrayList;
import java.util.List;

public class ArrayHolder {
    private List<String> a = new ArrayList<>();

    public List<String> getA() {
        return a;
    }

    public void setA(List<String> a) {
        this.a = a;
    }
}