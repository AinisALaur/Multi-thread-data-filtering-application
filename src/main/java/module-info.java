module com.example.datathreadingapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datathreadingapplication to javafx.fxml;
    exports com.example.datathreadingapplication;
    exports com.example.datathreadingapplication.Controllers;
    opens com.example.datathreadingapplication.Controllers to javafx.fxml;
    exports com.example.datathreadingapplication.Applications;
    opens com.example.datathreadingapplication.Applications to javafx.fxml;
}