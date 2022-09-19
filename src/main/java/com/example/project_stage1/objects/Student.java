//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.objects;


import com.example.project_stage1.sorting.SortByGrade;
import com.example.project_stage1.sorting.SortByGradeThenModule;
import com.example.project_stage1.sorting.SortByModuleName;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    private String name;
    private String student_id;
    private String dob;
    /////
    private HashMap<Module, Integer> modules;
    private SortByGrade modules_by_grade;
    private SortByModuleName modules_by_modules;
    private SortByGradeThenModule modules_by_grades_then_modules;
    private TreeMap<Module, Integer> sorted_by_grade;

    //creating constructors
    public Student() {}

    public Student(String name, String student_id, String dob)
    {
        modules = new HashMap<Module, Integer> ();
        this.name = name;
        this.student_id = student_id;
        this.dob = dob;
    }

    public void addModule(Module module, Integer grade)  throws Exception {
        modules.put(module, grade);
    }

    public ArrayList<Module> getModules() throws Exception {
        ArrayList<Module> listModules = new ArrayList<Module>();
        for(Module moduleName: modules.keySet()) {
            listModules.add(moduleName);
        }
        return listModules;
    }

    public ArrayList<Integer> getGrades() throws Exception {
        ArrayList<Integer> listGrades = new ArrayList<Integer>();
        for(Integer grade: modules.values()) {
            listGrades.add(grade);
        }
        return listGrades;
    }

    //creating setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    //method to print all student info
    public String print_modules_info() {
        String info = "";
        for (Module i : modules.keySet()) {
            info += "Name: " + getName() + ", ID: " + getStudent_id() + ", DOB: " + getDob() + ", Module: " + i.getModuleName() + ", Grade: " + modules.get(i) + "\n";
        }
        return info;
    }

    public String print_info_by_grade() {
        String info = "";
        modules_by_grade = new SortByGrade(modules);
        TreeMap<Module, Integer> sorted_map = new TreeMap<Module, Integer>(modules_by_grade);

        for (Map.Entry<Module, Integer> i : modules.entrySet()) {
            sorted_map.put(i.getKey(),i.getValue());
        }

        for (Module i : sorted_map.keySet()) {
            info += "Name: " + getName() + ", ID: " + getStudent_id() + ", DOB: " + getDob() + ", Module: " + i.getModuleName() + ", Grade: " + modules.get(i) + "\n";
        }
        return info;
    }

    public String print_info_by_module() {
        String info = "";
        modules_by_modules = new SortByModuleName(modules);
        TreeMap<Module, Integer> sorted_map = new TreeMap<Module, Integer>(modules_by_modules);

        for (Map.Entry<Module, Integer> i : modules.entrySet()) {
            sorted_map.put(i.getKey(),i.getValue());
        }

        for (Module i : sorted_map.keySet()) {
            info += "Name: " + getName() + ", ID: " + getStudent_id() + ", DOB: " + getDob() + ", Module: " + i.getModuleName() + ", Grade: " + modules.get(i) + "\n";
        }
        return info;
    }

    public String print_info_by_grade_then_module() {
        String info = "";
        modules_by_grades_then_modules = new SortByGradeThenModule(modules);
        TreeMap<Module, Integer> sorted_map = new TreeMap<Module, Integer>(modules_by_grades_then_modules);

        for (Map.Entry<Module, Integer> i : modules.entrySet()) {
            sorted_map.put(i.getKey(),i.getValue());
        }

        for (Module i : sorted_map.keySet()) {
            info += "Name: " + getName() + ", ID: " + getStudent_id() + ", DOB: " + getDob() + ", Module: " + i.getModuleName() + ", Grade: " + modules.get(i) + "\n";
        }
        return info;
    }

    public String print_info() {
        return "Name: " + getName() + ", ID: " + getStudent_id() + ", DOB: " + getDob();
    }

}
