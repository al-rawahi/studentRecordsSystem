//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.controller;

import com.example.project_stage1.objects.Student;
import com.example.project_stage1.tabs.Tab3;
import com.example.project_stage1.lists.ModuleList;
import com.example.project_stage1.lists.StudentList;
import com.example.project_stage1.tabs.Tab2;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * controls the whole system of student records
 */
public class StudentRecordController {

    /////////
    private static final String URL = "jdbc:derby:studentRecordDB;create=true";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    private PreparedStatement addStudentStatement = null;
    private PreparedStatement removeStudentStatement = null;
    private PreparedStatement selectAllStudents = null;
    private PreparedStatement editStudentStatement = null;

    private PreparedStatement addStudentWithModuleStatement = null;
    private PreparedStatement removeStudentWithModuleStatement = null;
    private PreparedStatement selectAllStudentsWithModulesStatement = null;
    private PreparedStatement editStudentGradeStatement = null;

    private Statement statement = null;
    private ResultSet resultSet= null;
    private Statement statement2 = null;
    private ResultSet resultSet2= null;
    //////////


    /**
     * initialise the StudentRecordController
     */
    public StudentRecordController(){
        // constructor
        try {

            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );

            // check if the database contains a studentRecordsTable
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            resultSet = databaseMetadata.getTables(null, null, "StudentRecordsTable2", null);
            if ( !resultSet.next() ){
                statement = connection.createStatement();
                //statement.execute("CREATE TABLE StudentRecordsTable2 (StudentID VARCHAR(12), Name VARCHAR(12), Dob VARCHAR(12))");
            }

            /////////////

            resultSet2 = databaseMetadata.getTables(null, null, "StudentsModulesTable2", null);
            if ( !resultSet2.next() ){
                statement2 = connection.createStatement();
                //statement2.execute("CREATE TABLE StudentsModulesTable (StudentID VARCHAR(12), Module VARCHAR(12), Grade INT )");
            }

            /*
            addStudentWithModuleStatement = connection.prepareStatement("INSERT INTO StudentsModulesTable2 " +
                    "( StudentID, Module, Grade) VALUES ( ?, ?, ?) ");

            removeStudentWithModuleStatement = connection.prepareStatement("DELETE FROM StudentsModulesTable2 " +
                    "WHERE StudentID = ? ");

            selectAllStudentsWithModulesStatement = connection.prepareStatement("SELECT * FROM StudentsModulesTable2");

            editStudentGradeStatement = connection.prepareStatement("UPDATE StudentsModulesTable2 SET Grade = ? " +
                    "WHERE StudentID = ? ");

             */



//////////////
            addStudentStatement = connection.prepareStatement("INSERT INTO StudentRecordsTable2 " +
                    "( StudentID, Name, Dob) " + "VALUES ( ?, ?, ?)");

            removeStudentStatement = connection.prepareStatement("DELETE FROM StudentRecordsTable2 " +
                    "WHERE StudentID = ? AND Name = ? AND Dob = ?");

            selectAllStudents = connection.prepareStatement( "SELECT * FROM StudentRecordsTable2 " );

            editStudentStatement = connection.prepareStatement("UPDATE StudentRecordsTable2 SET StudentID = ?," +
                    " Name = ?, Dob = ? WHERE StudentID = ? ");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * @param Sid Student ID
     * @param Sname Student Name
     * @param Sdob Student DOB
     * @return student added to the DB
     */
    public int addStudentToList(String Sid, String Sname, String Sdob) {
        int result = 0;
        try {
            addStudentStatement.setString(1, Sid);
            addStudentStatement.setString(2, Sname);
            addStudentStatement.setString(3, Sdob);

            result = addStudentStatement.executeUpdate();
        }
        catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    /**
     * @param Sid student ID
     * @param Module Module name
     * @param Grade grade in that module
     * @return student with module and grade added to the DB
     */

    public int addStudentAndModule(String Sid, String Module, int Grade) {
        int result = 0;
        try {
            addStudentWithModuleStatement.setString(1, Sid);
            addStudentWithModuleStatement.setString(2, Module);
            addStudentWithModuleStatement.setInt(3, Grade);

            result = addStudentWithModuleStatement.executeUpdate();
        }
        catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }



    /**
     * @param Sid student ID
     * @param Sname student name
     * @param Sdob student DOB
     * @return student removed from the DB
     */
    public int removeStudentFromList(String Sid,String Sname, String Sdob) {
        int result = 0;
        try {
            removeStudentStatement.setString(1, Sid);
            removeStudentStatement.setString(2, Sname);
            removeStudentStatement.setString(3, Sdob);

            result = removeStudentStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    /**
     * @param Sid student ID
     * @return student with their module removed from the DB
     */

    public int removeStudentWithModule(String Sid) {
        int result = 0;
        try {
            removeStudentWithModuleStatement.setString(1, Sid);

            result = removeStudentWithModuleStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }



    public String ListAllStudents() {
        List<Student> studentsList = getAllStudents();
        String allStudents ="\0";
        for (int i = 0; i < studentsList.size(); i++) {
            allStudents = allStudents + studentsList.get(i).print_info() + "\n";
        }
        return allStudents;
    }

    public List<Student> getAllStudents() {
        List<Student> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllStudents.executeQuery();
            results = new ArrayList<Student>();

            while (resultSet.next()) {
                results.add(new Student(
                        resultSet.getString("Name"),
                        resultSet.getString("StudentID"),
                        resultSet.getString("Dob")
                        )
                );
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }

    /**
     * @param Sid student ID
     * @param Sname Student name
     * @param Sdob student DOB
     * @param Sid_toUpdate the student ID that need to be updated
     * @return student info updated
     */
    public int updateStudentInfo(String Sid, String Sname, String Sdob, String Sid_toUpdate) {
        int result = 0;
        try {
            editStudentStatement.setString(1, Sid);
            editStudentStatement.setString(2, Sname);
            editStudentStatement.setString(3, Sdob);
            editStudentStatement.setString(4, Sid_toUpdate);

            result = editStudentStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }

    /**
     * @param Grade student grade
     * @param Sid_toUpdate student ID
     * @return
     */

    public int updateStudentGrade(int Grade, String Sid_toUpdate) {
        int result = 0;
        try {
            editStudentGradeStatement.setInt(1, Grade);
            editStudentGradeStatement.setString(2, Sid_toUpdate);

            result = editStudentGradeStatement.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }


    private Tab2 modules;
    private Tab3 printing;


    //method to process student and module details for a specific student
    //used in Tab3
    public String processStudentAndModuleDetails(StudentList students, ModuleList modules, ComboBox<String> combobox) {
        String details = "";
        for (int i = 0; i < students.getSize(); i++) {
            if (students.getStudent(i).getName() == combobox.getSelectionModel().getSelectedItem()) {
                details = students.getStudent(i).print_modules_info();
            }
        }
        return details;
    }

    public String sortByGrade(StudentList students, ComboBox<String> combobox) {
        String details = "";
        for (int i = 0; i < students.getSize(); i++) {
            if (students.getStudent(i).getName() == combobox.getSelectionModel().getSelectedItem()) {
                details = students.getStudent(i).print_info_by_grade();
            }
        }
        return details;
    }

    public String sortByModule(StudentList students, ComboBox<String> combobox) {
        String details = "";
        for (int i = 0; i < students.getSize(); i++) {
            if (students.getStudent(i).getName() == combobox.getSelectionModel().getSelectedItem()) {
                details = students.getStudent(i).print_info_by_module();
            }
        }
        return details;
    }

    public String sortByGradeThenModule(StudentList students, ComboBox<String> combobox) {
        String details = "";
        for (int i = 0; i < students.getSize(); i++) {
            if (students.getStudent(i).getName() == combobox.getSelectionModel().getSelectedItem()) {
                details = students.getStudent(i).print_info_by_grade_then_module();
            }
        }
        return details;
    }

    public void close() {
        try {
            connection.close();
        }
        catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
        }
    }

}