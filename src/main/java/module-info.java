module com.majorproject.supplychain {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.majorproject.supplychain to javafx.fxml;
    exports com.majorproject.supplychain;
}