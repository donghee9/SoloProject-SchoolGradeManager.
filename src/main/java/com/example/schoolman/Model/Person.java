package com.example.schoolman.Model;
//질문: 슈퍼클래스는 private으로 만들고 보호해도 되나요?
public class Person {
    private String name;
    private  String email;
    private  String phoneNumber;


    private Person(String name ,String email ,String phoneNumber){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }


    //static을 붙여야하는 이유를 모르겠습니다 어차피 public 을 붙여서 공유가 다 되는거 아닌가요?
    public static Person createPerson(String name,String email,String phoneNumber){
        return new Person(name,email,phoneNumber);

    }

}
