//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.tabs;

import com.example.project_stage1.controller.StudentRecordController;
import com.example.project_stage1.lists.ModuleList;
import com.example.project_stage1.lists.StudentList;
import com.example.project_stage1.objects.Module;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class Tab2 extends Tab { //Inherits Tab

    private StudentRecordController control;
    private StudentList students;
    private ModuleList modules;

    //create constructor
    public Tab2(StudentList students, ModuleList modules) {

        //import students and modules list so we don't have two lists with different data
        this.students = students;
        this.modules = modules;

        //creating labels, textfields

        Label student_selection_label = new Label("Select a student: ");
        //create a drop-down list using ComboBox
        ComboBox<String> student_combobox = new ComboBox<String>();
        TextField student_textfield = new TextField();
        Label module_name_label = new Label("name of module they have completed: ");
        TextField module_name_textfield = new TextField();
        Label grade_label = new Label("Grade they got in the module: ");
        TextField grade_textfield = new TextField();
        //creating a button
        Button submit_button = new Button("Submit");

        //creating Hboxes
        HBox hbox7 = new HBox();
        hbox7.setSpacing(20);
        hbox7.setPadding(new Insets(20));
        HBox hbox8 = new HBox();
        hbox8.setSpacing(20);
        hbox8.setPadding(new Insets(20));
        HBox hbox9 = new HBox();
        hbox9.setSpacing(20);
        hbox9.setPadding(new Insets(20));
        HBox hbox10 = new HBox();
        hbox10.setSpacing(20);
        hbox10.setPadding(new Insets(20));

        //adding items to the Hboxes
        hbox7.getChildren().addAll(student_selection_label,student_textfield);
        hbox8.getChildren().addAll(module_name_label,module_name_textfield);
        hbox9.getChildren().addAll(grade_label,grade_textfield);
        hbox10.getChildren().addAll(submit_button);

        //setting action when submit button is clicked
        submit_button.setOnAction( e->
                control.addStudentAndModule(student_textfield.getText(), module_name_textfield.getText(), Integer.parseInt(grade_textfield.getText()))
        );

        //creating a Vbox and adding all the Hboxes to it
        VBox root2 = new VBox();
        root2.getChildren().addAll(hbox7,hbox8,hbox9,hbox10);
        //naming the tab
        this.setText("Add Modules and Grades");

        //adding a black circle beside the tab name text
        this.setGraphic(new Circle(0, 0, 5));

        //setting background image
        Image image = new Image("file:mtu_logo.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root2.setBackground(new Background(backgroundImage));

        //setting action when this tab is selected
        this.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                // clear items so they not be printed more than once
                student_combobox.getItems().clear();
                for (int i = 0; i < students.getSize(); i++) {
                    student_combobox.getItems().add(students.getStudent(i).getStudent_id());
                }
            }
        });

        //disable the ability to close the tab
        this.setClosable(false);

        //add the Vbox to the tab
        this.setContent(root2);
    }
}
