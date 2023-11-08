package com.example.schoolman;

import com.example.schoolman.Controller.StaffController;
import com.example.schoolman.Model.Department;
import com.example.schoolman.Model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SchoolManApplication {

    private final StaffController staffController;

    public SchoolManApplication(StaffController staffController) {
        this.staffController = staffController;
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolManApplication.class, args);

    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("Enter a number (1: enrollStudent, 2: modifyStudentDetails, 3: removeStudent, 4: listStudents, 5: Exit)");
                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":

                            System.out.println("Enter student name:");
                            String name = scanner.nextLine();
                            System.out.println("Enter student email:");
                            String email = scanner.nextLine();
                            System.out.println("Enter student phone number:");
                            String phoneNumber = scanner.nextLine();
                            System.out.println("Enter student department (e.g. COMPUTER_SCIENCE, BUSINESS):");
                            String departmentStr = scanner.nextLine();
                            Department department = Department.valueOf(departmentStr.toUpperCase());
                            Student student = new Student(name, email, phoneNumber, department);
                            System.out.println(staffController.enrollStudent(student));
                            break;
                        case "2":
                            // Modify student details
                            System.out.println("Enter student ID:");
                            String studentId = scanner.nextLine();
                            System.out.println("Enter student name:");
                            String studentName = scanner.nextLine();
                            System.out.println("Enter new phone number:");
                            String newPhoneNumber = scanner.nextLine();
                            System.out.println(staffController.modifyStudentDetails(studentId, studentName, newPhoneNumber));
                            break;
                        case "3":
                            System.out.println("Enter student ID:");
                            String studentIdForDelete = scanner.nextLine().trim();

                            System.out.println("Enter student name:");
                            String studentNameForDelete = scanner.nextLine().trim();

                            String removalResult = String.valueOf(staffController.removeStudent(studentIdForDelete, studentNameForDelete));
                            System.out.println(removalResult);
                            break;

                        case "4":
                            ResponseEntity<List<Student>> response = staffController.listStudents();
                            if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
                                List<Student> students = response.getBody();
                                students.forEach(System.out::println);
                            } else {
                                System.out.println("No students found.");
                            }
                            break;
                        case "5":
                            System.out.println("Exiting program.");
                            return;
                        default:
                            System.out.println("Invalid input, please try again.");
                    }
                }
            }
        };
    }
}
