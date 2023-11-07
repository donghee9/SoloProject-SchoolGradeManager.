package com.example.schoolman;

import com.example.schoolman.Controller.StaffController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SchoolManApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManApplication.class, args);


}
    @Bean
    public CommandLineRunner run(StaffController controller) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter a number (1: enrollStudent, 2: updateStudentDetails, 3: deleteStudent, 4: studentList, 5: Exit)");
                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println(controller.enrollStudent());
                        break;
                    case "2":
                        System.out.println(controller.updateStudentDetails());
                        break;
                    case "3":
                        System.out.println(controller.deleteStudent());
                        break;
                    case "4":
                        System.out.println(controller.studentList());
                        break;
                    case "5":
                        System.out.println("Exiting program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid input, please try again.");
                }
            }
        };
    }
}