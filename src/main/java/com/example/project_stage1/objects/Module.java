//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.objects;

/**
 * @class Module class that has module name
 */
public class Module {
    private String name;
    private String studentId;
    private String studentName;
    private String dob;

    //creating setters and getters
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentId(String student_Id) {
        this.studentId = student_Id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getModuleName() {
        return name;
    }

    public void setModuleName(String name) {
        this.name = name;
    }

    /**
     * @return info about the module
     */
    //method to print all student info and all modules they have completed
    public String print_info() {
        return "ID: " + getStudentId() + ", Name: " + getStudentName() + ", DOB: " + getDob() + ", Module: " + getModuleName();
    }

}
