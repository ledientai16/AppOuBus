module com.dht.appoubus {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dht.appoubus to javafx.fxml;
    exports com.dht.appoubus;
}
