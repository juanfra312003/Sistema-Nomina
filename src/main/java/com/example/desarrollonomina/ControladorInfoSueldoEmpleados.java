package com.example.desarrollonomina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorInfoSueldoEmpleados {
    private Stage stage;
    private Nomina nominaEmpleados;

    
    /** 
     * @param nominaEmpleados
     */
    public void setNominaEmpleados(Nomina nominaEmpleados) {
        this.nominaEmpleados = nominaEmpleados;
    }

    
    /** 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField cargoEmpleado;

    @FXML
    private TextField nombreEmpleado;

    @FXML
    private TextField salarioEmpleado;

    
    /** 
     * @param cargoEmpleado
     */
    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado.setText(cargoEmpleado);
    }

    
    /** 
     * @param nombreEmpleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado.setText(nombreEmpleado);
    }

    
    /** 
     * @param salarioEmpleado
     */
    public void setSalarioEmpleado(String salarioEmpleado) {
        this.salarioEmpleado.setText(salarioEmpleado);
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void regresarMenu(ActionEvent event) throws IOException {
        //Declaramos el nuevo espacio.
        Stage stage = new Stage();
        //Cargamos el archivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuProgramaNomina.fxml"));
        //Asignamos la escena con el archivo fxml
        Scene scene = new Scene(fxmlLoader.load());
        //Agregamos el titulo a la ventana.
        stage.setTitle("Menu Nomina");
        //Agregamos la escena en el espacio.
        stage.setScene(scene);
        //Obtenemos el controlador del menu pricnpal, obteniendo los controles del interfaz.
        ControladorMenuPrincipal controladorMenuPrincipal = fxmlLoader.getController();
        //Asiganamos el espacio al controlador.
        controladorMenuPrincipal.setStage(stage);
        //Asignamos la nomina de empleados al controlador del menu principal.
        controladorMenuPrincipal.setNominaEmpleados(nominaEmpleados);
        //Mostramos el espacio al usuario.
        stage.show();
        //Cerramos el espacio del controlador actual.
        this.stage.close();
    }
}