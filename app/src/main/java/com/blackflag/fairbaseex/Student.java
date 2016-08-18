package com.blackflag.fairbaseex;

/**
 * Created by BlackFlag on 8/17/2016.
 */
public class Student {
    String name;
    Double  cgpa;

    public Student() {
    }

    public Double getCgpa() {

        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Student(String name, Double cgpa) {

        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
