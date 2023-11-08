package com.example.schoolman.Model;

import com.example.schoolman.Service.StaffServiceImpl;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class StudentTest {

    private StaffServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new StaffServiceImpl();
    }

    @org.junit.jupiter.api.Test
    void testInsertStudent() {
        Department department = Department.COMPUTER_SCIENCE;
        Student newStudent = new Student("test", "test@example.com", "010-0000-0000", department);
        String result = service.insertStudent(newStudent, department);

        assertTrue(result.contains(newStudent.getName()));
        assertTrue(result.contains(newStudent.getEmail()));
        assertTrue(result.contains("2023"));
    }

    @org.junit.jupiter.api.Test
    void testGetStudentList() {
        List<Student> students = service.getStudentList();
        assertFalse(students.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testPutStudent() {
        String studentId = "2023101";
        String studentName = "seo";
        String newPhoneNumber = "010-9888-1111";
        service.putStudent(studentId, studentName, newPhoneNumber);

        List<Student> students = service.getStudentList();
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.getName().equals(studentName)) {
                assertEquals(newPhoneNumber, student.getPhoneNumber());
                return;
            }
        }
        fail("Student with given ID and name not found or phone number not updated.");
    }

    @org.junit.jupiter.api.Test
    void testDeleteStudent() {
        String studentId = "20230101";
        String studentName = "seo";
        service.deleteStudent(studentId, studentName);

        List<Student> students = service.getStudentList();
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.getName().equals(studentName)) {
                fail("Student was not deleted.");
            }
        }

    }
}