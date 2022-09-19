package com.example.project_stage1.testClasses;

import com.example.project_stage1.lists.StudentList;
import com.example.project_stage1.objects.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    @Test
    void getStudent() {
        Student student1 = new Student();
        Student student2 = new Student();
        StudentList students = new StudentList();
        students.addStudent(student1);
        students.addStudent(student2);
        Assertions.assertEquals(student1, students.getStudent(0));
        Assertions.assertEquals(student2, students.getStudent(1));
    }

    @Test
    void getSize() {
        Student student1 = new Student();
        Student student2 = new Student();
        StudentList students = new StudentList();
        students.addStudent(student1);
        students.addStudent(student2);
        Assertions.assertEquals(2, students.getSize());
    }

    @Test
    void addStudent() {
        Student student1 = new Student();
        StudentList students = new StudentList();
        students.addStudent(student1);
        Assertions.assertSame(student1, students.getStudent(0));
    }

    @Test
    void removeStudent() {
        Student student1 = new Student();
        StudentList students = new StudentList();
        students.addStudent(student1);
        students.removeStudent(0);
        Assertions.assertNull(students.getStudent(0));

    }
}