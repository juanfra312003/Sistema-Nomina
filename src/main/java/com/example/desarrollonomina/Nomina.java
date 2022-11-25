package com.example.desarrollonomina;
import java.util.*;

public class Nomina {
    //Declaracion de atributos.
    private ArrayList<Empleado> listaEmpleadosNomina = new ArrayList<>();
    private ArrayList<Float> sueldosEmpleadosNomina = new ArrayList<>();

    //METODO CONSTRUCTOR
    public Nomina (ArrayList<Empleado> listaEmpleadosNomina, ArrayList<Float> sueldosEmpleadosNomina){
        this.listaEmpleadosNomina = listaEmpleadosNomina;
        this.sueldosEmpleadosNomina = sueldosEmpleadosNomina;
    }

    //METODOS GET/SET
    /** 
     * @return ArrayList<Empleado>
     */
    public ArrayList<Empleado> getListaEmpleadosNomina() {
        return listaEmpleadosNomina;
    }

    
    /** 
     * @return ArrayList<Float>
     */
    public ArrayList<Float> getSueldosEmpleadosNomina() {
        return sueldosEmpleadosNomina;
    }

    
    /** 
     * @param sueldosEmpleadosNomina
     */
    public void setSueldosEmpleadosNomina(ArrayList<Float> sueldosEmpleadosNomina) {
        this.sueldosEmpleadosNomina = sueldosEmpleadosNomina;
    }

    
    /** 
     * @param listaEmpleadosNomina
     */
    public void setListaEmpleadosNomina(ArrayList<Empleado> listaEmpleadosNomina) {
        this.listaEmpleadosNomina = listaEmpleadosNomina;
    }

    
    /** 
     * @param nombreProfesor
     * @param nombreAsignatura
     * @param horasAsignatura
     * @return boolean
     */
    //METODOS PARA ANADIR UNA ASIGNATURA A UN PROFESOR.
    public boolean anadirAsignaturaProfesor(String nombreProfesor, String nombreAsignatura, int horasAsignatura) {
        Profesor profesorBusqueda = buscarProfesor(nombreProfesor);
        if (profesorBusqueda != null) {
            profesorBusqueda.anadirClaseProfesor(nombreAsignatura, horasAsignatura);
            return true;
        }
        return false;
        //SI NO, CASO DE ERROR, EXCEPCION Y DEMOSTRADO EN LA INTERFAZ GRAFICA REALIZADA EN JAVAFX
    }

    
    /** 
     * @param nombreMonitor
     * @param nombreAsignatura
     * @param horasAsignatura
     * @return boolean
     */
    public boolean anadirAsignaturaMonitor(String nombreMonitor, String nombreAsignatura, int horasAsignatura) {
        Monitor monitorBusqueda = buscarMonitor(nombreMonitor);
        if (monitorBusqueda != null) {
            monitorBusqueda.anadirClaseMonitor(nombreAsignatura, horasAsignatura);
            return true;
        }
        return false;
        //SI NO, CASO DE ERROR, EXCEPCION Y DEMOSTRADO EN LA INTERFAZ GRAFICA REALIZADA EN JAVAFX
    }

    
    /** 
     * @param nombreProfesor
     * @return Profesor
     */
    //METODO DE BUSQUEDA
    public Profesor buscarProfesor(String nombreProfesor) {
        ArrayList<Empleado> listaEmpleados = this.getListaEmpleadosNomina();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (nombreProfesor.equalsIgnoreCase(listaEmpleados.get(i).getNombreEmpleado()) && listaEmpleados.get(i) instanceof Profesor) {
                return (Profesor) listaEmpleados.get(i);
            }
        }
        return null;
    }

    
    /** 
     * @param nombreMonitor
     * @return Monitor
     */
    public Monitor buscarMonitor(String nombreMonitor) {
        ArrayList<Empleado> listaEmpleados = this.getListaEmpleadosNomina();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (nombreMonitor.equalsIgnoreCase(listaEmpleados.get(i).getNombreEmpleado()) && listaEmpleados.get(i) instanceof Monitor) {
                return (Monitor) listaEmpleados.get(i);
            }
        }
        return null;
    }

    public void realizarElCalculoSalariosTodosLosEmpleados() {
        //Obtenemos los salarios por medio de cada los metodos de cada empleado.
        ArrayList<Empleado> listaEmpleadosNomina = this.getListaEmpleadosNomina();
        ArrayList<Float> listaSueldosNomina = this.getSueldosEmpleadosNomina();
        
        for (int i = 0; i < listaEmpleadosNomina.size(); i++) {
            Float salarioEmpleado = (listaEmpleadosNomina.get(i).calcularSalario());
            listaSueldosNomina.add(salarioEmpleado);
        }
    }

    
    /** 
     * @param nombreEmpleado
     * @return Empleado
     */
    public Empleado existeEmpleado (String nombreEmpleado){
        //Obtenemos el empleado buscado.
        ArrayList<Empleado> listaEmpleados = this.getListaEmpleadosNomina();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (nombreEmpleado.equalsIgnoreCase(listaEmpleados.get(i).getNombreEmpleado())) {
                return listaEmpleados.get(i);
            }
        }
        return null;
    }
}