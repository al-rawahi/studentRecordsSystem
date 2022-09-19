package com.example.project_stage1.testClasses;

import com.example.project_stage1.objects.Module;
import com.example.project_stage1.objects.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;


class StudentTest {

    @BeforeEach
    void setUp() {
        Student student = new Student("Al","R00196016","30/11/2000");
        HashMap<Module, Integer> modules = new HashMap<Module, Integer>();
    }

    @Test
    void addModule() {}

    @Test
    void print_modules_info() {
        Student student = new Student("Al","R00196016","30/11/2000");
        Module module = new Module();
        module.setModuleName("Maths");
        try {
            student.addModule(module,90);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("Name: Al, ID: R00196016, DOB: 30/11/2000, Module: Maths, Grade: 90" + "\n", student.print_modules_info());
    }

    @Test
    void print_info_by_grade() {
        Student student = new Student("Al","R00196016","30/11/2000");
        Module module1 = new Module();
        module1.setModuleName("Maths");
        Module module2 = new Module();
        module2.setModuleName("OOP");
        try {
            student.addModule(module1,90);
            student.addModule(module2,92);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("""
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: OOP, Grade: 92
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: Maths, Grade: 90
                """, student.print_info_by_grade());
    }

    @Test
    void print_info_by_module() {
        Student student = new Student("Al","R00196016","30/11/2000");
        Module module1 = new Module();
        module1.setModuleName("Maths");
        Module module2 = new Module();
        module2.setModuleName("OOP");
        try {
            student.addModule(module1,90);
            student.addModule(module2,92);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("""
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: Maths, Grade: 90
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: OOP, Grade: 92
                """, student.print_info_by_module());
    }

    @Test
    void print_info_by_grade_then_module() {
        Student student = new Student("Al","R00196016","30/11/2000");
        Module module1 = new Module();
        module1.setModuleName("Maths");
        Module module2 = new Module();
        module2.setModuleName("OOP");
        Module module3 = new Module();
        module3.setModuleName("Economy");
        try {
            student.addModule(module1,90);
            student.addModule(module2,92);
            student.addModule(module3,92);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("""
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: Economy, Grade: 92
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: OOP, Grade: 92
                Name: Al, ID: R00196016, DOB: 30/11/2000, Module: Maths, Grade: 90
                """, student.print_info_by_grade_then_module());
    }
}