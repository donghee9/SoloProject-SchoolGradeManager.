package com.example.schoolman.Service;

import com.example.schoolman.Model.Student;

public class Helper {
    public static boolean isIdAndNamed(String studentId,String studentName, Student student) {
        return studentId.equals(student.getStudentId())
                && studentName.equals(student.getName());
    }
}
