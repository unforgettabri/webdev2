package com.example;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Roster roster = new Roster();

        roster.addStudent(new Student("Wendy", 17, "BSIT"));
        roster.addStudent(new Student("Lowe", 18, "BSCS"));
        roster.addStudent(new Student("Janiyah", 19, "BSIT"));
        roster.addStudent(new Student("David", 20, "BSBA"));
        roster.addStudent(new Student("Jhelly", 16, "BSED"));
        roster.addStudent(new Student("AGH-ron", 21, "BSIT"));

        System.out.println("=== STUDENT ROSTER ===");

        for (Student student : roster.getStudents()) {
            System.out.println(student);
        }

        System.out.println("\n=== EXCEPTION HANDLING ===");

        try {
            Student invalidStudent = new Student("Invalid Student", -5, "BSIT");
            roster.addStudent(invalidStudent);
        } catch (InvalidAgeException e) {
            System.out.println("Caught invalid age: " + e.getMessage());
            System.out.println("Program continues running.");
        }

        System.out.println("\n=== ROSTER REPORT ===");

        List<String> qualifiedStudents = roster.getStudents()
                .stream()
                .filter(student -> student.getAge() >= 18)
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println("Students aged 18 or older: " + qualifiedStudents);
        System.out.println("Number of qualified students: " + qualifiedStudents.size());
    }
}