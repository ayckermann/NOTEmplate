package com.ayckermann.notemplate;

public class Model {

    public String judul, content;

    public String template;

    public Model( String judul, String content, String template) {

        this.judul = judul;
        this.content = content;
        this.template = template;
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}



