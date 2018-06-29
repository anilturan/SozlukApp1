package com.example.anilturan.sozlukapp.model;

import java.util.List;

public class Meaning {

    private Phrase phrase;
    private List<Phrase> meanings;
    private long meaningId;
    private List<Long> authors;

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public List<Phrase> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Phrase> meanings) {
        this.meanings = meanings;
    }

    public long getMeaningId() {
        return meaningId;
    }

    public void setMeaningId(long meaningId) {
        this.meaningId = meaningId;
    }

    public List<Long> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Long> authors) {
        this.authors = authors;
    }
}
