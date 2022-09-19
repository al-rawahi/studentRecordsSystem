//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.tabs;

import com.example.project_stage1.controller.StudentRecordController;
import com.example.project_stage1.lists.StudentList;
import com.example.project_stage1.objects.Student;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;

public class Tab1 implements Serializable{
    private StudentRecordController control;
    private StudentList students;
    private Tab thisTab;

    public Tab1(StudentRecordController controller, StudentList students) {

        this.students = students;
        control = controller;

        //Creating labels and textfields
        Label name_label = new Label("Enter name: ");
        TextField name_textField = new TextField();
        Label studentID_label = new Label("Enter Student ID: ");
        TextField studentID_textField = new TextField();
        Label DOB_label = new Label("Enter Date of Birth: ");
        TextField DOB_textField = new TextField();

        Label student_id_to_update_label = new Label("Enter StudentID that you want to update their info: ");
        TextField student_id_to_update_textfield = new TextField();
        Label update_id_label = new Label("Enter StudentID to update: ");
        TextField update_id_textfield = new TextField();
        Label update_name_label = new Label("Enter Name to update: ");
        TextField update_name_textfield = new TextField();
        Label update_dob_label = new Label("Enter DOB to update: ");
        TextField update_dob_textfield = new TextField();

        //creating Add, Remove, Edit and List buttons
        Button add_button = new Button("Add");
        Button remove_button = new Button("Remove");
        Button edit_button = new Button("Update");
        Button list_button = new Button("List");

        //creating a textarea to show students in application
        TextArea output_textField = new TextArea();
        output_textField.setPadding(new Insets(20));
        output_textField.setPrefWidth(800);
        output_textField.setPrefHeight(400);

        //changing the font style
        output_textField.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        //disabling the ability to edit the textfield by user
        output_textField.setEditable(false);

        //creating Load, Save and Exit buttons
        Button load_button = new Button("Load");
        Button save_button = new Button("Save");
        Button exit_button = new Button("Exit");
        Button dummy_button = new Button("Create Dummy People");

        //creating six Hboxes to organise labels,buttons and textfields and setting their spacing and padding
        HBox hbox1 = new HBox();
        hbox1.setSpacing(20);
        hbox1.setPadding(new Insets(20));
        HBox hbox2 = new HBox();
        hbox2.setSpacing(20);
        hbox2.setPadding(new Insets(20));
        HBox hbox3 = new HBox();
        hbox3.setSpacing(20);
        hbox3.setPadding(new Insets(20));
        HBox hbox4 = new HBox();
        hbox4.setSpacing(20);
        hbox4.setPadding(new Insets(20));
        HBox hbox5 = new HBox();
        hbox5.setSpacing(20);
        hbox5.setPadding(new Insets(20));
        HBox hbox6 = new HBox();
        hbox6.setSpacing(20);
        hbox6.setPadding(new Insets(20));
        HBox hbox66 = new HBox();
        hbox66.setSpacing(20);
        hbox66.setPadding(new Insets(20));
        HBox hbox7 = new HBox();
        hbox7.setSpacing(20);
        hbox7.setPadding(new Insets(20));
        HBox hbox8 = new HBox();
        hbox8.setSpacing(20);
        hbox8.setPadding(new Insets(20));

        //Adding elements to the Hboxes
        hbox1.getChildren().addAll(name_label, name_textField);
        hbox2.getChildren().addAll(studentID_label, studentID_textField);
        hbox3.getChildren().addAll(DOB_label, DOB_textField);
        hbox4.getChildren().addAll(add_button, remove_button, list_button);
        hbox5.getChildren().addAll(output_textField);
        hbox6.getChildren().addAll(student_id_to_update_label, student_id_to_update_textfield, update_id_label, update_id_textfield);
        hbox66.getChildren().addAll(update_name_label, update_name_textfield, update_dob_label, update_dob_textfield);
        hbox7.getChildren().addAll(edit_button, load_button, save_button, exit_button);
        hbox8.getChildren().add(dummy_button);

        //setting events for buttons when being clicked
        add_button.setOnAction( e->
                control.addStudentToList(studentID_textField.getText(), name_textField.getText(), DOB_textField.getText())
        );

        remove_button.setOnAction( e->
            control.removeStudentFromList(studentID_textField.getText(), name_textField.getText(), DOB_textField.getText())
        );

        edit_button.setOnAction( e->
                control.updateStudentInfo(update_id_textfield.getText(), update_name_textfield.getText(),
                        update_dob_textfield.getText(), student_id_to_update_textfield.getText())
        );

        list_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String students = control.ListAllStudents();
                output_textField.setText(students);
            }
        });

        save_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileOutputStream fileOut = null;
                try {
                    fileOut = new FileOutputStream("studentsDataFile.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(fileOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < students.getSize(); i++) {
                    try {
                        assert out != null;
                        out.writeObject(students.getStudent(i).print_info() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    assert out != null;
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert fileOut != null;
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        load_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileInputStream fileIn = null;
                try {
                    fileIn = new FileInputStream("studentsDataFile.txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ObjectInputStream in = null;
                try {
                    in = new ObjectInputStream(fileIn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String s = null;
                try {
                    assert in != null;
                    s = (String) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert fileIn != null;
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                output_textField.setText(s);
            }
        });

        exit_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage popupwindow = new Stage();
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("Saving..");
                Label label1 = new Label("Do you want to save before exiting?");
                Button button1 = new Button("save and exit");
                Button button2 = new Button("exit without saving");

                //if button1 clicked: saving data to the text file and then exiting
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        FileWriter writer = null;
                        try {
                            writer = new FileWriter("studentsDataFile.txt", true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //writer.println(output_textField.getText());
                        for (int i = 0; i < students.getSize(); i++) {
                            try {
                                writer.write(students.getStudent(i).print_info());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                });

                //if button2 clicked: exiting without saving data to the text file
                button2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });

                VBox layout = new VBox(10);
                layout.getChildren().addAll(label1, button1, button2);
                layout.setAlignment(Pos.CENTER);
                Scene scene1 = new Scene(layout, 300, 250);
                popupwindow.setScene(scene1);
                popupwindow.showAndWait();
            }
        });


        dummy_button.setOnAction( e-> {
            while (true){
                Student student = new Student();
                students.addStudent(student);
            }
        });


        VBox root = new VBox();

        //setting background image
        Image image = new Image("file:mtu_logo.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));


        root.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6,hbox66, hbox7, hbox8);

        thisTab = new Tab();
        thisTab.setText("Add or update a Student");

        //adding a black circle beside the tab name text
        thisTab.setGraphic(new Circle(0, 0, 5));

        thisTab.setClosable(false);
        thisTab.setContent(root);

    }
    public Tab newTab() {
        return thisTab;
    }

    public void quit() {
        System.exit(0);
    }
}
