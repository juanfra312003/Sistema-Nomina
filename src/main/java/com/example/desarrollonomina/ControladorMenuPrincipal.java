package com.example.desarrollonomina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class ControladorMenuPrincipal implements Initializable {

    private Stage stage;
    private Nomina nominaEmpleados;

    
    /** 
     * @return Nomina
     */
    public Nomina getNominaEmpleados() {
        return nominaEmpleados;
    }

    
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
    private ComboBox<String> comboBoxAcciones;

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void agregarAsignaturaMonitor(ActionEvent event) throws IOException {
        //Realizar la inicializacion de la escena.
        //Obtenemos el espacio
        Stage stage = new Stage();
        //Cargamos el archivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("agregarAsignaturaMonitor.fxml"));
        //Obtenemos una escena donde agregaremos el archivo FXML.
        Scene scene = new Scene(fxmlLoader.load());
        //Colocamos el titulo a la ventana.
        stage.setTitle("Agregar Asignatura Monitor");
        //Asignamos la escena al espacio.
        stage.setScene(scene);
        //Obtenemos el controlador del archivo FXML.
        AgregarMonController controladorAsignaturaMonitor = fxmlLoader.getController();
        //Asignamos el espacio en el controlador.
        controladorAsignaturaMonitor.setStage(stage);
        //Asignamos la nomina al controlador del monitor.
        controladorAsignaturaMonitor.setNominaEmpleados(nominaEmpleados);
        //Mostramos el espacio.
        stage.show();
        //Cerramos el espacio actual.
        this.stage.close();
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void agregarAsignaturaProfesor(ActionEvent event) throws IOException {
        //Realizar la inicializacion de la escena.
        //Obtenemos el espacio
        Stage stage = new Stage();
        //Cargamos el archivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("agregarAsignaturaProfesor.fxml"));
        //Obtenemos una escena donde agregaremos el archivo FXML.
        Scene scene = new Scene(fxmlLoader.load());
        //Colocamos el titulo a la ventana.
        stage.setTitle("Agregar Asignatura Profesor");
        //Asignamos la escena al espacio.
        stage.setScene(scene);
        //Obtenemos el controlador del archivo FXML.
        AgregarController controladorAsignaturaProfesor = fxmlLoader.getController();
        //Asignamos el espacio en el controlador.
        controladorAsignaturaProfesor.setStage(stage);
        //Asignamos la nomina al controlador del profesor.
        controladorAsignaturaProfesor.setNominaEmpleados(nominaEmpleados);
        //Mostramos el espacio.
        stage.show();
        //Cerramos el espacio actual.
        this.stage.close();
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void calcularSalarioEmpleado(ActionEvent event) throws IOException {
        //Obtenemos el espacio
        Stage stage = new Stage();
        //Cargamos el archivo FXML.
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("consultaSalarioEmpleados.fxml"));
        //Obtenemos una escena donde agregaremos el archivo FXML.
        Scene scene = new Scene(fxmlLoader.load());
        //Colocamos el titulo a la ventana.
        stage.setTitle("Agregar Asignatura Profesor");
        //Asignamos la escena al espacio.
        stage.setScene(scene);
        //Obtenemos el controlador del archivo FXML.
        ControladorGeneralSueldos controladorGeneralSueldos = fxmlLoader.getController();
        //Asignamos el espacio en el controlador.
        controladorGeneralSueldos.setStage(stage);
        //asignamos la nomina al controlador.
        controladorGeneralSueldos.setNominaEmpleados(nominaEmpleados);
        //Mostramos el espacio
        stage.show();
        //Cerramos el espacio actual.
        this.stage.close();
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void generarReporteDeNomina(ActionEvent event) throws IOException {
        //Realizamos el caluclo de la nomina de los empleados.
        nominaEmpleados.realizarElCalculoSalariosTodosLosEmpleados();
        //Si podemos escribir el reporte mostrará un mensaje indicando de que fue exitoso.
        if (WriteFiles.generarReporte(this.getNominaEmpleados())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Reporte Generado");
            alert.setTitle("Exito en el proceso");
            alert.setContentText("El archivo de reporte con los salarios de los empleados ha sido generado exitosamente.");
            alert.show();
        }
        else{
            //Mostramos un mensaje de error en el caso de no haberse podido escribir el reporte.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Proceso no realizado");
            alert.setTitle("Error");
            alert.setContentText("El archivo de reporte con los salarios de los empleados no se generó.");
            alert.show();
        }
    }

    
    /** 
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Agregamos las acciones que se pueden hacer con el comboBox
        comboBoxAcciones.getItems().add("Agregar Asignatura Profesor");
        comboBoxAcciones.getItems().add("Agregar Asignatura Monitor");
        comboBoxAcciones.getItems().add("Calcular Sueldo Empleado");
        comboBoxAcciones.getItems().add("Generar Reporte de Nomina");
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void seleccionOpcionComboBox(ActionEvent event) throws IOException {
        //Valida la selección
        String seleccion = comboBoxAcciones.getSelectionModel().getSelectedItem();
        if (seleccion.equalsIgnoreCase("Agregar Asignatura Profesor")) {
            agregarAsignaturaProfesor(event);
        }
        if (seleccion.equalsIgnoreCase("Calcular Sueldo Empleado")) {
            calcularSalarioEmpleado(event);
        }
        if (seleccion.equalsIgnoreCase("Generar Reporte de Nomina")) {
            generarReporteDeNomina(event);
        }
        if (seleccion.equalsIgnoreCase("Agregar Asignatura Monitor")) {
            agregarAsignaturaMonitor(event);
        }
    }

    
    /** 
     * @param event
     * @throws IOException
     */
    @FXML
    void salirPrograma(ActionEvent event) throws IOException {
        //Guardamos los los archivos de la nomina.
        if (WriteFiles.generarArchivoNomina(nominaEmpleados)){
            //Mandamos mensaje de exito
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Archivo de Planta Generado");
            alert.setTitle("Exito en el proceso");
            alert.setContentText("El archivo de reporte con la informacion de la planta de empleados ha sido generado exitosamente.");
            alert.show();
        }
        else{
            //Mandamos error en caso de que no se pueda escribir.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Proceso no realizado");
            alert.setTitle("Error");
            alert.setContentText("El archivo de la informacion de planta correspondiente a los empleados registrados no fue generado de manera exitosa.");
            alert.show();
        }
    }
}