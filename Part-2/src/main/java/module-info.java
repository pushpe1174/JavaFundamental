module com.part2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.part2 to javafx.fxml;
    exports com.part2;
}