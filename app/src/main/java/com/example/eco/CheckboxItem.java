package com.example.eco;

import java.io.Serializable;

public class CheckboxItem implements Serializable{

    private String ToDo;

    private boolean active;

    public  CheckboxItem(String ToDo) {
        this.ToDo = ToDo;
        this.active = true;
    }

    public CheckboxItem(String ToDo, boolean active) {
        this.ToDo = ToDo;
        this.active = active;
    }

    public String getToDo() {
        return ToDo;
    }

    public void setToDo(String toDo) {
        ToDo = toDo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.ToDo;
    }
}
