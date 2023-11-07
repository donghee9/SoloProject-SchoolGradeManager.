package com.example.schoolman.Model;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Student {
    String name;
    String email;
    String phoneNumber;
    String studentId;
    Department department;
//Person 을 슈퍼클래스로 상속 받고 싶었는데 서비스에서 인자 값으로 받지를 못해서 못했습니다,,,
    public Student(String name, String email, String phoneNumber, Department department){
        //생성자 값을 Department department Or String department?
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.department=department;
    }

     public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
     }

     public void setDepartment(Department department){
        this.department=department;
     }
     public void setStudentId(String studentId){
        this.studentId=studentId;
     }
}
