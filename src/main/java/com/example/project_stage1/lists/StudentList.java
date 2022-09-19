//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.lists;

import com.example.project_stage1.objects.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentList implements Serializable {

    //create students arraylist
    private ArrayList<Student> students;

    //create constructor
    public StudentList (){
        students = new ArrayList <Student> ();
    }

    //create getters
    public ArrayList<Student> getList (){
        return students;
    }

    public Student getStudent(int i)
    {
        if ((i>-1) && (i < students.size()))
            return students.get(i);
        return null;
    }

    public int getSize (){
        return students.size();
    }

    /**
     * @param student adding student to the arraylist
     */
    //creating a method to add a student to the students list
    public void addStudent(Student student)
    {
        students.add(student);
    }

    /**
     * @param i removing student from the arraylist
     */
    //creating a method to remove a student from the students list
    public void removeStudent(int i)
    {
        if ((i>-1) && (i < students.size())) {
            students.remove(i);
        }
    }

}
