package com.example.schoolman.Controller;

import com.example.schoolman.Model.Department;
import com.example.schoolman.Model.Student;
import com.example.schoolman.Service.StaffService;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class StaffController {

    StaffService staffService;

    StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    public String enrollStudent() {
        Student student = new Student("서경원", "dong0@naver.com", "01011112222",Department.BUSINESS);
        String response = staffService.insertStudent(student, Department.valueOf("BUSINESS"));
        String message = "학생등록 완료";
        return response + message;
    }

    public List studentList() {
        return staffService.getStudentList();
    }

    public String deleteStudent() {
        String studentName = "seo";
        String studentId = "20231001";
        String phoneNumber = "01098881111";

        return staffService.deleteStudent(studentId, studentName, phoneNumber);
    }

    public String updateStudentDetails() {
        String studentId = "202300011";
        String name = "서경원";
        String newPhoneNumber = "01011112222";

        staffService.updateStudent(studentId, name, newPhoneNumber);
        return "학생의 전화번호가 업데이트 되었습니다: " + studentId;
    }
}





