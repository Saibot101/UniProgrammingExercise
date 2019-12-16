package model;

import java.io.Serializable;
import java.time.LocalDate;


public class Employee implements Serializable {
    private String name, familyname,password,position;
    private LocalDate entryDate;

    public Employee(String name, String familyname, String password, String position, String date) {
        this.name = name;
        this.familyname = familyname;
        this.password = password;
        this.position = position;
        this.entryDate = LocalDate.parse(date);
    }

    public Employee(String name, String familyname, String password, LocalDate date){
        this.name = name;
        this.familyname = familyname;
        this.password = password;
        this.entryDate = date;
    }

    public Employee(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getDate() {
        return entryDate;
    }

    public void setDate(LocalDate date) {
        this.entryDate = date;
    }
}
