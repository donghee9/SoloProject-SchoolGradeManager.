package com.example.schoolman.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Student extends Person {
    String name;
    String email;
    String phoneNumber;
    String studentId;
    Department department;


    public Student(String name, String email, String phoneNumber, Department department) {
        super(name, email, phoneNumber);
        this.name = name;
        this.email = email;
        this.phoneNumber = formatPhoneNumber(phoneNumber);
        this.department = department;
    }

    private String formatPhoneNumber(String rawNumber) {
        return rawNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
    }

    public static Student of(String name, String email, String phoneNumber, Department department) {
        return new Student(name, email, phoneNumber, department);
    }

}


