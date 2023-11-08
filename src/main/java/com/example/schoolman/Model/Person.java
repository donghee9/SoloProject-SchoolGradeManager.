package com.example.schoolman.Model;

//질문: 슈퍼클래스는 private으로 만들고 보호해도 되나요?
public class Person {
    private String name;
    private String email;
    private String phoneNumber;


    protected Person(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public static Person createPerson(String name, String email, String phoneNumber) {
        return new Person(name, email, phoneNumber);

    }

}
