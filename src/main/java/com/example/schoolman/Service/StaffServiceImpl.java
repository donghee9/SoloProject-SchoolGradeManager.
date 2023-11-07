package com.example.schoolman.Service;

import com.example.schoolman.Model.Department;
import com.example.schoolman.Model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StaffServiceImpl implements StaffService{
    List<Student> studentList= new ArrayList<>();
    //map에 두개의 인자가 들어가는 이유
    Map<String,Integer> departmentNumbers =new HashMap<>();
    Map<Department,Integer> studentIdCounters =new HashMap<>();


    public StaffServiceImpl(){
        departmentNumbers.put("Science", 1);
        departmentNumbers.put("Commerce", 3);
        departmentNumbers.put("Arts", 5);

        for(Department department :Department.values()){
            studentIdCounters.put(department,1);
        }
        initializeListStudent();

    }
    private void initializeListStudent() {
        studentList.add(createStudent("seo", "seo@example.com", "010-9888-1111", Department.COMPUTER_SCIENCE));
        studentList.add(createStudent("kim", "kim@example.com", "010-1234-5678", Department.BUSINESS));
        studentList.add(createStudent("park", "park@example.com", "010-8765-4321", Department.NURSING));
        studentList.add(createStudent("lee", "lee@example.com", "010-5566-7788", Department.ART));
        studentList.add(createStudent("choi", "choi@example.com", "010-6677-8899", Department.MATHEMATICS));
    }

    //Person 변수를 Person name 으로 설정했더니 인자값으로 받지를 못함
      private Student createStudent(String name, String email, String phone, Department department){
        int departmentCounter = studentIdCounters.get(department);
        String fixDepart= departmentNumbers.get(department.getCategory()).toString();
        String studentId = "2023" + fixDepart + String.format("%02d", departmentCounter);
        studentIdCounters.put(department, departmentCounter + 1);

        Student newStudent =new Student(name,email,phone,department);

        newStudent.setStudentId(studentId);
        return newStudent;
    }


    @Override
    public String insertStudent(Student student, Department department) {
      int count= studentIdCounters.get(department);
      String departPrefix = String.format("%04d", departmentNumbers.get(department.getCategory()));
      String studentId= LocalDate.now().getYear()+departPrefix+count;
      student.setStudentId(studentId);
      studentIdCounters.put(department,count+1);

      student.setDepartment(department);
      studentList.add(student);
      String name=student.getName();
      String email=student.getEmail();
      return String.format("Name: %s, Email: %s, Student ID: %s", student.getName(), student.getEmail(), studentId);
    }

    @Override
    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public void updateStudent(String studentId, String name, String newPhoneNumber) {
        for(Student student :studentList){
            if(student.getStudentId().equals(studentId)&&student.getName().equals(name)){
                student.setPhoneNumber(newPhoneNumber);
                break;
            }
        }

    }

    @Override
    public String deleteStudent(String studentId, String name, String rawPhoneNumber) {
        String formattedPhoneNumber = formatPhoneNumber(rawPhoneNumber);
        studentList.removeIf(student ->
                student.getStudentId().equals(studentId) &&
                        student.getName().equals(name) &&
                        student.getPhoneNumber().equals(formattedPhoneNumber)
        );
        return formattedPhoneNumber;
    }
    private String formatPhoneNumber(String rawNumber) {
        return rawNumber.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
    }
}
