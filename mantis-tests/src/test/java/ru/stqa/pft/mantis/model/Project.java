package ru.stqa.pft.mantis.model;

public class Project {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Project withId(int id) { //set
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project withName(String name) { //set
        this.name = name;
        return this;
    }
}
