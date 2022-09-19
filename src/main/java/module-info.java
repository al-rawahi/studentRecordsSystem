module com.example.project_stage1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires application.myalertbox;
    requires java.sql;
    requires org.junit.jupiter.api;

    opens com.example.project_stage1 to javafx.fxml;
    //exports com.example.project_stage1;
    exports com.example.project_stage1.objects;
    opens com.example.project_stage1.objects to javafx.fxml;
    exports com.example.project_stage1.lists;
    opens com.example.project_stage1.lists to javafx.fxml;
    exports com.example.project_stage1.sorting;
    opens com.example.project_stage1.sorting to javafx.fxml;
    exports com.example.project_stage1.tabs;
    opens com.example.project_stage1.tabs to javafx.fxml;
    exports com.example.project_stage1.main;
    opens com.example.project_stage1.main to javafx.fxml;
    exports com.example.project_stage1.controller;
    opens com.example.project_stage1.controller to javafx.fxml;

}