package com.example.project_stage1.testClasses;

import com.example.project_stage1.objects.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTest {

    @Test
    void print_info() {
        Module module = new Module();
        module.setModuleName("Maths");
        module.setStudentId("R00196016");
        module.setStudentName("Al");
        module.setDob("30/11/2000");
        Assertions.assertEquals("ID: R00196016, Name: Al, DOB: 30/11/2000, Module: Maths", module.print_info());
    }
}