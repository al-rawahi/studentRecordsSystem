//Name: Al-Warith Al-Rawahi
//Student No.: R00196016
//Group: SDH2A

package com.example.project_stage1.main;

import com.example.project_stage1.controller.StudentRecordController;
import com.example.project_stage1.tabs.Tab3;
import com.example.project_stage1.lists.ModuleList;
import com.example.project_stage1.lists.StudentList;
import com.example.project_stage1.tabs.Tab1;
import com.example.project_stage1.tabs.Tab2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;

public class StudentRecordSystem extends Application {

    private Stage window;
    private static Button quitButton;
    private static StudentRecordController control = new StudentRecordController();

    StudentList students = new StudentList();
    ModuleList modules = new ModuleList();

    @Override
    public void start(Stage stage) throws IOException {
        //changing the stage name and title
        window = stage;
        stage.setTitle("MTU Student Record System");

        //creating new tabs
        //tab2
        Tab2 tab2 = new Tab2(students, modules);
        //control = new StudentRecordController(tab2);
        //tab3
        Tab3 tab3 = new Tab3(control, students, modules);
        //control = new StudentRecordController(tab3);
        //tab1
        Tab1 studentstab = new Tab1(control, students);
        //control = new StudentRecordController();

        //adding all the tabs to a TabPane
        TabPane tp = new TabPane();
        tp.getTabs().add(studentstab.newTab());
        tp.getTabs().add(tab2);
        tp.getTabs().add(tab3);

        quitButton = new Button("Quit program");
        quitButton.setOnAction(e -> studentstab.quit());

        //creating a VBox and adding the TabPane to it
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(tp, quitButton);

        //creating a scene and adding it to the stage
        Scene scene = new Scene(layout, 900, 900);
        window.setScene(scene);
        window.show();

        //setting stage icon
        stage.getIcons().add(new Image("file:mtu_logo.jpg"));
    }

    public static void main(String[] args) {
        launch(args);
    }

}