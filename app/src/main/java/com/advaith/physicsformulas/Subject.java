package com.advaith.physicsformulas;

import java.util.ArrayList;

public class Subject {
    private ArrayList<Formula> array;
    private String subject;


    public Subject(ArrayList<Formula> array, String subject) {
        this.array = array;
        this.subject = subject;
    }


    public ArrayList<Formula> getArray() {
        return array;
    }

    public void setArray(ArrayList<Formula> array) {
        this.array = array;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
