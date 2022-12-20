package com.ayckermann.notemplate.Model;

public class Todo {

    public int id;
    public boolean check;
    public String text;

    public Todo(int id, boolean check, String text) {
        this.id = id;
        this.check = check;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return
                ", check=" + check +
                ", text='" + text + '\'' +
                '}';
    }
}


