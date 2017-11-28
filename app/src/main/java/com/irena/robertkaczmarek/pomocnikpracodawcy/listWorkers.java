package com.irena.robertkaczmarek.pomocnikpracodawcy;

/**
 * Created by robertkaczmarek on 08.09.2017.
 */

public class listWorkers {
    private long id;
    private   String surname = "surname";
    private boolean completed;
    public static final String history1 = "history1";
    public listWorkers(long id, String surname, boolean completed) {
        this.id = id;
        this.surname = surname;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
