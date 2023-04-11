package com.advaith.physicsformulas;

import java.util.ArrayList;

public class Course {
    private ArrayList<Subject> array;
    private String course;

    public Course(ArrayList<Subject> array, String course) {
        this.array = array;
        this.course = course;
    }

    public ArrayList<Subject> getArray() {
        return array;
    }

    public void setArray(ArrayList<Subject> array) {
        this.array = array;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
