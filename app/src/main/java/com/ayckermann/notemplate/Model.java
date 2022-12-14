package com.ayckermann.notemplate;

public class Model {
    public int id;
    public String judul, content;

    public String template;

    public Model(int id, String judul, String content, String template) {
        this.id = id;
        this.judul = judul;
        this.content = content;
        this.template = template;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

class Note extends Model{

    public Note(int id, String judul, String content, String template) {
        super(id, judul, content, template);
    }
}

class Todo extends Model{
    public boolean check;

    public Todo(int id, String judul, String content, String template, boolean check) {
        super(id, judul, content, template);
        this.check = check;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
