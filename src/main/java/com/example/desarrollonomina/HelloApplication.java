package com.example.desarrollonomina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //Inicializar los valores que permitirán la creacion de un objeto de la clase nomina.
        ArrayList<Float> salariosEmpleados = new ArrayList<>();
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();

        //Realizar la creacion de la nomina que corresponde a la ejecucion del programa
        Nomina nominaEmpleados = new Nomina(listaEmpleados, salariosEmpleados);

        //Realizar la carga de la informacion proveniente del archivo de texto.
        if (ReadFiles.realizarCargaInfoArchivo(nominaEmpleados)) {
            //Realizar la inicializacion de la escena
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuProgramaNomina.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Menu Nomina");
            stage.setScene(scene);
            ControladorMenuPrincipal controladorMenuPrincipal = fxmlLoader.getController();
            controladorMenuPrincipal.setStage(stage);
            controladorMenuPrincipal.setNominaEmpleados(nominaEmpleados);
            stage.show();
        }
        //En caso tal de que se presente errores con el archivo, desde la persistencia se muestra el mensaje de error.
    }

    public static void main(String[] args) {
        launch();
    }
}