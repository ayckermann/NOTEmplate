package com.ayckermann.notemplate.Model;

public class HeadTodo {
    public int id;
    public String judul;

    public HeadTodo(int id, String judul) {
        this.id = id;
        this.judul = judul;
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
}
