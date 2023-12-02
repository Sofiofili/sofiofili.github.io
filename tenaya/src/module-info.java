module tenaya.suomi.tampere.logistiikka {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens tenaya.suomi.tampere.logistiikka.logistiikka to javafx.fxml;
    exports tenaya.suomi.tampere.logistiikka.logistiikka;
}
