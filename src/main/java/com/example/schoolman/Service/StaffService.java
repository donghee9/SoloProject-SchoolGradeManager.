package com.example.schoolman.Service;

import com.example.schoolman.Model.Department;
import com.example.schoolman.Model.Student;

import java.util.List;

public interface StaffService {

    //학적부 등록
    String insertStudent(Student student , Department department);

    List<Student> getStudentList();

    //학적부 수정
    void putStudent(String studentId, String studentName,String newPhoneNumber);
    //학적부 삭제
   void deleteStudent(String studentIdForDelete,String studentNameForDelete);

}

