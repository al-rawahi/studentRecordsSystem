//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.tabs;

import com.example.project_stage1.controller.StudentRecordController;
import com.example.project_stage1.lists.ModuleList;
import com.example.project_stage1.lists.StudentList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Tab3 extends Tab { //Inherits Tab

    private StudentRecordController control;
    private StudentList students;
    private ModuleList modules;

    //create constructor
    public Tab3(StudentRecordController controller, StudentList students, ModuleList modules) {

        control = controller;
        //import students and modules list so we don't have two lists with different data
        this.students = students;
        this.modules = modules;

        Label student_selection_label2 = new Label("Select a student: ");
        ComboBox<String> student_combobox2 = new ComboBox<String>();

        for (int i = 0; i < students.getSize(); i++) {
            student_combobox2.getItems().add(students.getStudent(i).getName());
        }

        //creating Hboxes
        HBox hbox11 = new HBox();
        hbox11.setSpacing(20);
        hbox11.setPadding(new Insets(20));
        HBox hbox12 = new HBox();
        hbox12.setSpacing(20);
        hbox12.setPadding(new Insets(20));
        HBox hbox13 = new HBox();
        hbox13.setSpacing(20);
        hbox13.setPadding(new Insets(20));

        //create show button, ordering buttons and textarea for output
        Button show_button = new Button("Show");
        Button order_by_grade_button = new Button("Order by grade");
        Button order_by_module_button = new Button("Order by module");
        Button order_by_grade_then_module_button = new Button("Order by grade then module");

        TextArea student_module_details_textarea = new TextArea();
        student_module_details_textarea.setPadding(new Insets(20));
        student_module_details_textarea.setPrefWidth(800);
        student_module_details_textarea.setPrefHeight(400);

        //add items to the Hboxes
        hbox11.getChildren().addAll(student_selection_label2,student_combobox2);
        hbox12.getChildren().addAll(show_button,order_by_grade_button,order_by_module_button,order_by_grade_then_module_button);
        hbox13.getChildren().addAll(student_module_details_textarea);

        //setting action when show button is clicked
        show_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //String students = control.ListAllStudentsWithModules();
                //student_module_details_textarea.setText(students);
                //change the font style
                student_module_details_textarea.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
                //clear all data in the textarea before processing
                student_module_details_textarea.setText("");
                String printing = control.processStudentAndModuleDetails(students, modules,student_combobox2);
                student_module_details_textarea.setText(printing);
            }
        });

        //setting action when order by grade button is clicked
        order_by_grade_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                student_module_details_textarea.setText("");
                String printing = control.sortByGrade(students,student_combobox2);
                student_module_details_textarea.setText(printing);
            }
        });

        //setting action when order by module button is clicked
        order_by_module_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                student_module_details_textarea.setText("");
                String printing = control.sortByModule(students,student_combobox2);
                student_module_details_textarea.setText(printing);
            }
        });

        //setting action when "order by grade then module" button is clicked
        order_by_grade_then_module_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                student_module_details_textarea.setText("");
                String printing = control.sortByGradeThenModule(students,student_combobox2);
                student_module_details_textarea.setText(printing);
            }
        });

        //create Vbox and add the Hboxes to it
        VBox root3 = new VBox();
        root3.getChildren().addAll(hbox11,hbox12,hbox13);
        //name the tab
        this.setText("View Student Records");

        //adding a black circle beside the tab name text
        this.setGraphic(new Circle(0, 0, 5));

        //setting background image
        Image image = new Image("file:mtu_logo.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root3.setBackground(new Background(backgroundImage));

        //action when this tab is selected
        this.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                // clear items so they not be printed more than once
                student_combobox2.getItems().clear();
                for (int i = 0; i < students.getSize(); i++) {
                    student_combobox2.getItems().add(students.getStudent(i).getName());
                }
            }
        });

        //disable the ability to close the tab
        this.setClosable(false);

        //add the Vbox to the tab
        this.setContent(root3);

    }
}
