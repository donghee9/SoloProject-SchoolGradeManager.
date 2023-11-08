package com.example.schoolman.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.schoolman.Model.Department;
import com.example.schoolman.Model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaffServiceImpl implements StaffService {
    private static final String YEAR = "2023";
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffServiceImpl.class);
    List<Student> studentList = new ArrayList<>();
    Map<String, Integer> departmentNumbers = new HashMap<>();
    Map<Department, Integer> studentIdCounters = new HashMap<>();


    public StaffServiceImpl() {
        departmentNumbers.put("Science", 1);
        departmentNumbers.put("Commerce", 3);
        departmentNumbers.put("Arts", 5);

        for (Department department : Department.values()) {
            studentIdCounters.put(department, 1);
        }
        initializeListStudent();

    }


    private void initializeListStudent() {

        studentList.add(createStudent(new Student("seo", "seo@example.com", "010-9888-1111", Department.COMPUTER_SCIENCE)));
        studentList.add(createStudent(new Student("kim", "kim@example.com", "010-1234-5678", Department.BUSINESS)));
        studentList.add(createStudent(new Student("park", "park@example.com", "010-8765-4321", Department.NURSING)));
        studentList.add(createStudent(new Student("lee", "lee@example.com", "010-5566-7788", Department.ART)));
        studentList.add(createStudent(new Student("choi", "choi@example.com", "010-6677-8899", Department.MATHEMATICS)));
    }


    private Student createStudent(Student student) {

        int departmentCounter = studentIdCounters.get(student.getDepartment());
        String fixDepart = departmentNumbers.get(student.getDepartment().getCategory()).toString();
        String studentId = YEAR + fixDepart + String.format("%02d", departmentCounter);
        studentIdCounters.put(student.getDepartment(), departmentCounter + 1);
        student.setStudentId(studentId);
        return student;
    }


    @Override
    public String insertStudent(Student student, Department department) {
        LOGGER.info("Inserting new student into department: {}", department);
        int count = studentIdCounters.get(department);
        String departPrefix = String.format("%04d", departmentNumbers.get(department.getCategory()));
        String studentId = YEAR + departPrefix + count;
        student.setStudentId(studentId);
        studentIdCounters.put(department, count + 1);
        student.setDepartment(department);
        studentList.add(student);
        String result = String.format("Name: %s, Email: %s, Student ID: %s", student.getName(), student.getEmail(), studentId);
        LOGGER.info("New student inserted: {}", result);
        return result;
    }

    @Override
    public List<Student> getStudentList() {
        LOGGER.info("Retrieving student list. Current list size: {}", studentList.size());
        return new ArrayList<>(studentList);
    }

    @Override
    public void putStudent(String studentId, String studentName, String newPhoneNumber) {
        LOGGER.info("Updating phone number for student ID: {}, Name: {}", studentId, studentName);
        boolean studentFound = false;
        for (Student studentInList : studentList) {
            if (Helper.isIdAndNamed(studentId, studentName, studentInList)) {
                studentInList.setPhoneNumber(newPhoneNumber);
                studentFound = true;
                LOGGER.info("Phone number updated for student ID: {}, Name: {}", studentId, studentName);
                break;
            }
        }
        if (!studentFound) {
            LOGGER.warn("No student found to update phone number for ID: {}, Name: {}", studentId, studentName);
        }
    }

    public void deleteStudent(String studentIdForDelete, String studentNameForDelete) {
        Student toRemove = null;
        for (Student student : studentList) {
            if (Helper.isIdAndNamed(studentIdForDelete, studentNameForDelete, student)) {
                toRemove = student;
                LOGGER.info("Deleting student with ID: {} and Name: {}", studentIdForDelete, studentNameForDelete);
                break;
            }
        }
        if (toRemove != null) {
            studentList.remove(toRemove);
            LOGGER.info("Student removed successfully");
        } else {
            LOGGER.info("No student found with ID: {} and Name: {}", studentIdForDelete, studentNameForDelete);
        }
    }
}


