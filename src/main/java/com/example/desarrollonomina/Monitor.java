package com.example.desarrollonomina;
import java.util.ArrayList;

public class Monitor extends Empleado {
    //Declaracion de atributos propios:
    private ArrayList<Asignatura> listaAsignaturasDictadas;

    //Metodo constructor
    public Monitor(String nombreEmpleado, int docIdentidad, float salariosMinimos, String dependencia, String cargo, ArrayList<Asignatura> listaAsignaturasDictadas) {
        super(nombreEmpleado, docIdentidad, salariosMinimos, dependencia, cargo);
        this.listaAsignaturasDictadas = listaAsignaturasDictadas;
    }

    
    /** 
     * @param listaAsignaturasDictadas
     */
    //Metodos de tipo Get/Set
    public void setListaAsignaturasDictadas(ArrayList<Asignatura> listaAsignaturasDictadas) {
        this.listaAsignaturasDictadas = listaAsignaturasDictadas;
    }

    
    /** 
     * @return ArrayList<Asignatura>
     */
    public ArrayList<Asignatura> getListaAsignaturasDictadas() {
        return listaAsignaturasDictadas;
    }

    
    /** 
     * @return float
     */
    //METODOS PARA EL FUNCIONAMIENTO DEL PROGRAMA DE ACUERDO CON EL MODELO DE NEGOCIO.
    //Metodo para realizar el calculo del salario de un monitor:
    public float calcularSalario() {
        return obtenerTodasLasHorasTrabajadas() * valoresEstaticos.VALORPORHORAMONITORIA;
    }

    
    /** 
     * @return int
     */
    //Metodo para la obtencion de todas las horas trabajadas por un monitor
    public int obtenerTodasLasHorasTrabajadas() {
        int horasTrabajadas = 0;
        ArrayList<Asignatura> listaAsignaturas = this.getListaAsignaturasDictadas();
        for (int i = 0; i < listaAsignaturas.size(); i++) {
            horasTrabajadas += listaAsignaturas.get(i).getNumeroHoras();
        }
        return horasTrabajadas;
    }

    
    /** 
     * @param nombreClase
     * @param numHoras
     */
    //Metodo para añadir clase en las que debe participar el monitor
    public void anadirClaseMonitor(String nombreClase, int numHoras) {
        //Obtención de la lista de asignaturas que dicta.
        ArrayList<Asignatura> listaAsignaturas = this.getListaAsignaturasDictadas();
        //Creación de la asginatura que dictará.
        Asignatura asignaturaAnadir = new Asignatura(numHoras, nombreClase);
        //Adición de la lista de asignaturas.
        listaAsignaturas.add(asignaturaAnadir);
    }
}