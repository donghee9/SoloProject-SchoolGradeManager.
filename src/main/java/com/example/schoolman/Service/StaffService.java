package com.example.schoolman.Service;

import com.example.schoolman.Model.Department;
import com.example.schoolman.Model.Student;

import java.util.List;

public interface StaffService {

    //학적부 등록
    String insertStudent(Student student , Department department);

    List<Student> getStudentList();

    //학적부 수정
    void updateStudent(String studentId, String name, String newPhoneNumber);
    //학적부 삭제
    String deleteStudent(String studentId, String name, String phone);

}

