package com.example.desarrollonomina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorGeneralSueldos {
    private Stage stage;

    @FXML
    private TextField nombreEmpleado;

    private Nomina nominaEmpleados;
    
    /** 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /** 
     * @param nominaEmpleados
     */
    public void setNominaEmpleados(Nomina nominaEmpleados) {
        this.nominaEmpleados = nominaEmpleados;
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void consultar(ActionEvent event) throws IOException {
        //Obtenemos el nombre del empleado.
        String nombre = nombreEmpleado.getText();
        //Si el nombre es mayor a verificaremos si el nombre existe en la nomina.
        if (nombre.length() > 0) {
            //Obtenemos el empleado por medio de la nomina.
            Empleado empleado = nominaEmpleados.existeEmpleado(nombre);
            //Si logramos consrguir el empleado, se imprimira la información de este mismo.
            if (empleado != null) {
                //Creamos el espacio
                Stage stage = new Stage();
                //Obtenemos el archivo FXML
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("informacionSalarioEmpleados.fxml"));
                //Cargamos el archivo en la escena.
                Scene scene = new Scene(fxmlLoader.load());
                //Colocamos nombre a la ventana.
                stage.setTitle("Informacion Resultado de la Consulta: ");
                //Añadimos la escena al espacio.
                stage.setScene(scene);
                //Obtenemos la información del controlador
                ControladorInfoSueldoEmpleados controladorConsulta = fxmlLoader.getController();
                //Cargamos la información del empleado.
                controladorConsulta.setCargoEmpleado(empleado.getCargo());
                controladorConsulta.setNombreEmpleado(empleado.getNombreEmpleado());
                controladorConsulta.setSalarioEmpleado(String.valueOf(empleado.calcularSalario()));
                //Cargamos la nomina de los empleados.
                controladorConsulta.setNominaEmpleados(nominaEmpleados);
                //Cargamos el espacio en el controlador.
                controladorConsulta.setStage(stage);
                //Mostramos el espacio.
                stage.show();
                //Cerramos el espacio.
                this.stage.close();
            } else {
                // De lo contrario mostramos un mensaje de error al obtener el empleado.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Empleado no existente");
                alert.setContentText("El nombre del empleado ingresado no corresponde a ninguno de los registrados en la planta de nomina.");
                alert.show();
                nombreEmpleado.setText("");
            }
        }
        else{
            //En caso de que el usurio no haya colocado un nombre mostraremos el error.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Datos no encontrados");
            alert.setContentText("No has ingresado un nombre de empleado para realizar la busqueda.");
            alert.show();
        }
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void regresar(ActionEvent event) throws IOException {
        //Creamos el espacio
        Stage stage = new Stage();
        //Obtenemos el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuProgramaNomina.fxml"));
        //Cargamos el archivo en la escena.
        Scene scene = new Scene(fxmlLoader.load());
        //Colocamos nombre a la ventana.
        stage.setTitle("Menu Nomina");
        //Añadimos la escena al espacio.
        stage.setScene(scene);
        //Obtenemos la información del controlador
        ControladorMenuPrincipal controladorMenuPrincipal = fxmlLoader.getController();
        //Cargamos el espacio en el controlador.
        controladorMenuPrincipal.setStage(stage);
        //Cargamos la nomina de los empleados.
        controladorMenuPrincipal.setNominaEmpleados(nominaEmpleados);
        //Mostramos el espacio.
        stage.show();
        //Cerramos el espacio.
        this.stage.close();
    }
}