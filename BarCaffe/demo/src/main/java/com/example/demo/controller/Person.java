package com.example.demo.controller;

public class Person{
    String emri;
    Integer mosha;

    public Person(String e,Integer m){
        emri=e; mosha=m;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public Integer getMosha() {
        return mosha;
    }

    public void setMosha(Integer mosha) {
        this.mosha = mosha;
    }
}