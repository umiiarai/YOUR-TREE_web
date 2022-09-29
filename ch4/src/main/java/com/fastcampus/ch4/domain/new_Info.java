package com.fastcampus.ch4.domain;

import java.util.Date;
import java.util.Objects;

public class new_Info {
    private String name;
    private int old;
    private String gender;

    public new_Info(){}
    public new_Info(String name, int old, String gender) {
    	this.name = name;
    	this.old = old;
    	this.gender = gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, old, gender);
    }

    @Override
    public String toString() {
        return "new_Info{" +
                "name='" + name + '\'' +
                ", old='" + old + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}