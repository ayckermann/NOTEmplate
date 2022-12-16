package com.ayckermann.notemplate;

public class Todo {
    public boolean check;
    public String text;

    public Todo(boolean check, String text) {
        this.check = check;
        this.text = text;
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
}
