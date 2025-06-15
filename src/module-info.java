module GRANJAFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.media; // ¡Este es el módulo necesario para Media/MediaPlayer!

    exports modelo;
    exports controlador;  // Si otros módulos necesitan acceder a los controladores
    
    opens controlador to javafx.fxml;
    opens modelo to javafx.base; // Para propiedades observables
}