package com.example.anilturan.sozlukapp.model;

public class SpinnerModel {

    public String LangCode;
    public String Language;

    public SpinnerModel(String langCode,String language){
        this.LangCode=langCode;
        this.Language=language;
    }
    public String getLangCode() {
        return LangCode;
    }

    public void setLangCode(String langCode) {
        LangCode = langCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
