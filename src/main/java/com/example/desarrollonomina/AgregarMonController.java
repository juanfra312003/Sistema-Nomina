package com.example.desarrollonomina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AgregarMonController {

    @FXML
    private TextField ingresarCantidadHoras;
    private Nomina nominaEmpleados;
    private Stage stage;
    
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
    private TextField ingresarNombre;

    @FXML
    private TextField ingresarNombreAsignatura;

    
    /** 
     * @param event
     */
    @FXML
    void registrarUsuario(ActionEvent event) {
        //Verificamos si se es posble crear el usuario mediante el texto ingresado (.getText())
        if (posible(ingresarNombre.getText(), ingresarNombreAsignatura.getText(), ingresarCantidadHoras.getText())) {
            //Verificamos si se trata de un número el texto ingresado.
            if (numHorasisDigit(ingresarCantidadHoras.getText())) {
                //Toma los valores.
                String nombre = ingresarNombre.getText();
                int numeroHoras = Integer.parseInt(ingresarCantidadHoras.getText());
                String nombreAsignatura = ingresarNombreAsignatura.getText();

                //Si es posible añadir la asignatura, mostrará el mensaje.
                if (nominaEmpleados.anadirAsignaturaMonitor(nombre, nombreAsignatura, numeroHoras)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Monitor Registrado");
                    alert.setTitle("Registro Exitoso");
                    alert.setContentText("Se ha registrado la asignatura: " + nombreAsignatura + " al monitor " + nombre + " de manera exitosa.");
                    alert.show();
                } else {
                    //En caso de que no sea posible crear el empleado se procedera a mostrar el mensaje de error.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Monitor Inexistente");
                    alert.setTitle("Error en la operacion");
                    alert.setContentText("El nombre ingresado no corresponde al registrado por parte de ningun empleado registrado como monitor.");
                    alert.show();
                }
                //Vaciar los TextField
                manejarValoresEspacios();
            }
            else{
                //En caso de que no sea un entero no se podrá crear.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Formato no valido.");
                alert.setContentText("No has ingresado un dato de tipo numerico en el campo de horas de trabajo.");
                alert.show();
            }
        }
        else{
            //En caso de que no sea un entero no se podrá crear.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Credenciales Incompletas");
            alert.setContentText("No has ingresado todas las credenciales para realizar el registro de una asignatura para un Monitor.");
            alert.show();
        }
    }


    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void regresarAlMenu(ActionEvent event) throws IOException {
        //Creamos el espacio para colocar la nueva escena.
        Stage stage = new Stage();
        //Cargamos el archivo que tiene como se veria el interfaz.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menuProgramaNomina.fxml"));
        //Cargamos el archivo en la escena.
        Scene scene = new Scene(fxmlLoader.load());
        //Le colocamos el titulo a la ventana.
        stage.setTitle("Menu Nomina");
        //Cargamos la escena en el espacio.
        stage.setScene(scene);
        //Creamos el controlador del menu principal y adquirimos el controlador.
        ControladorMenuPrincipal controladorMenuPrincipal = fxmlLoader.getController();
        //Colocamos el stage en el controlador.
        controladorMenuPrincipal.setStage(stage);
        //Cargamos la nominaEmpleados en el controlador.
        controladorMenuPrincipal.setNominaEmpleados(nominaEmpleados);
        //Mostramos el interfaz al usuario.
        stage.show();
        //Cerramos el Stage.
        this.stage.close();
    }

    public void manejarValoresEspacios (){
        //Vaciamos los campos donde se ingresa la información.
        ingresarNombre.setText("");
        ingresarNombreAsignatura.setText("");
        ingresarCantidadHoras.setText("");
    }

    
    /** 
     * @param nombre
     * @param nombreAsignatura
     * @param cantHoras
     * @return boolean
     */
    public boolean posible (String nombre, String nombreAsignatura, String cantHoras){
        //Verificar si es posible crear el usuario, tomando el cuenta la longitud de su string.
        if (nombre.length() == 0)
            return false;
        if (nombreAsignatura.length() == 0)
            return false;
        if (cantHoras.length() == 0)
            return false;

        return true;
    }

    
    /** 
     * @param cantHoras
     * @return boolean
     */
    public boolean numHorasisDigit (String cantHoras){
        // Verificar si se tiene un numero o no.
        boolean isNumeric = cantHoras.matches(("[+-]?\\d*(\\.\\d+)?"));
        if (isNumeric)
            return true;
        return false;
    }
}