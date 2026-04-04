package com.inaction.pro1;

public class Person implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private java.lang.String id;
    private java.lang.String name;
    private java.lang.Integer age;

    public Person() {
    }

    public java.lang.String getId() {
        return this.id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.Integer getAge() {
        return this.age;
    }

    public void setAge(java.lang.Integer age) {
        this.age = age;
    }

    public Person(java.lang.String id, java.lang.String name, java.lang.Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
