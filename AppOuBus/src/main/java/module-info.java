module com.dht.appoubus {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires java.sql;
    opens com.dht.appoubus to javafx.fxml;
    exports com.dht.appoubus;
    requires javafx.graphicsEmpty;
}
