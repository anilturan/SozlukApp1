package com.example.anilturan.sozlukapp.model;

import java.util.ArrayList;
import java.util.List;

public class GlosbeResponse {

    private List<Meaning> tuc=new ArrayList<>();
    private String result;

    public List<Meaning> getTuc() {
        return tuc;
    }

    public void setTuc(List<Meaning> tuc) {
        this.tuc = tuc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
