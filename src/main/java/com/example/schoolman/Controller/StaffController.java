// StaffController.java
package com.example.schoolman.Controller;

import com.example.schoolman.Model.Student;
import com.example.schoolman.Service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public ResponseEntity<String> enrollStudent(@RequestBody Student student) {
        String response = staffService.insertStudent(student, student.getDepartment());
        String message = "학생등록 완료";
        return ResponseEntity.ok(response + " " + message);
    }

    @GetMapping
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = staffService.getStudentList();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping
    public ResponseEntity<String> removeStudent(@RequestBody String studentIdForDelete, String studentNameForDelete) {
        staffService.deleteStudent(studentIdForDelete, studentNameForDelete);
        return ResponseEntity.ok("삭제되었습니다.");
    }

    @PutMapping
    public ResponseEntity<String> modifyStudentDetails(@RequestBody String studentId, String studentName, @RequestParam String newPhoneNumber) {
        staffService.putStudent(studentId, studentName, newPhoneNumber);
        return ResponseEntity.ok("학생의 전화번호가 업데이트 되었습니다: " + studentId);
    }
}

