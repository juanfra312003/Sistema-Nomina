package com.example.desarrollonomina;
import java.util.ArrayList;

public class Profesor extends Empleado implements salariosMinimosEscalafon {
    //AtributosS
    private ArrayList<Asignatura> listaAsignaturasDictadas;
    private String escalafon;

    //Metodo Constructor
    public Profesor(String nombreEmpleado, int docIdentidad, float salariosMinimos, String dependencia, String cargo, ArrayList<Asignatura> listaAsignaturasDictadas, String escalafon) {
        super(nombreEmpleado, docIdentidad, salariosMinimos, dependencia, cargo);
        this.listaAsignaturasDictadas = listaAsignaturasDictadas;
        this.escalafon = escalafon;
    }

    
    /** 
     * @param listaAsignaturasDictadas
     */
    //Implementacion de Metodos Get /Set:
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
     * @param escalafon
     */
    public void setEscalafon(String escalafon) {
        this.escalafon = escalafon;
    }

    
    /** 
     * @return String
     */
    public String getEscalafon() {
        return escalafon;
    }

    
    /** 
     * @return float
     */
    //Implementacion de Metodos relacionados con el Modelo de Negocio
    public float calcularSalario() {
        //En primer plano realizar la asignacion de la cantidad de salarios a través del metodo implementado proveniente de la inteface
        this.setSalariosMinimos(retornarCantidadSalariosMinimos(this.getEscalafon()));

        //Realizar el calculo respectivamente con base a lo implementado en la clase Empleado, donde tambien se tienen en cuenta
        // los descuentos de nomina, se multiplica por la cantidad de horas totales.
        return (float) (super.calcularSalario() + ((valoresEstaticos.VALORPORHORAPROFESOR * this.obtenerTodasLasHorasTrabajadas()) * 0.88));
    }

    
    /**  
     * @param escalafonProfesor
     * @return int
     */
    //Determinamos el escalafón del profesor.
    public int retornarCantidadSalariosMinimos(String escalafonProfesor) {
        if (escalafonProfesor.equalsIgnoreCase("Catedra"))
            return salariosMinimosEscalafon.CATEDRA;
        if (escalafonProfesor.equalsIgnoreCase("Instructor"))
            return salariosMinimosEscalafon.INSTRUCTOR;
        if (escalafonProfesor.equalsIgnoreCase("Asistente"))
            return salariosMinimosEscalafon.ASISTENTE;
        if (escalafonProfesor.equalsIgnoreCase("Asociado"))
            return salariosMinimosEscalafon.ASOCIADO;
        if (escalafonProfesor.equalsIgnoreCase("Titular"))
            return salariosMinimosEscalafon.TITULAR;
        //Retornar 0 en caso tal de que no se encuentren coincidencias.
        return 0;
    }

    
    /** 
     * @return int
     */
    public int obtenerTodasLasHorasTrabajadas() {
        //Declaración del contador que nos ayudará a sumar las horas totales de las asignaturas.
        int horasTrabajadas = 0;
        //Lista de asignatureas que tomará las listas que dicta el profesor.
        ArrayList<Asignatura> listaAsignaturas = this.getListaAsignaturasDictadas();
        //Iniciamos bucle donde estaremos sumando las horas totales de las asignaturas.
        for (int i = 0; i < listaAsignaturas.size(); i++)
            horasTrabajadas += listaAsignaturas.get(i).getNumeroHoras();
        return horasTrabajadas; //Finalmente retornamos el valor que total de la suma.
    }

    
    /** 
     * @param nombreClase
     * @param numHoras
     */
    public void anadirClaseProfesor(String nombreClase, int numHoras) {  
        //Declaracion de listaAsignaturas el cual tomará la lista de las asignaturas dictadas.      
        ArrayList<Asignatura> listaAsignaturas = this.getListaAsignaturasDictadas();
        //Declaración de la asginatura que será añadida.
        Asignatura asignaturaAnadir = new Asignatura(numHoras, nombreClase);
        //Añade las asignatura.
        listaAsignaturas.add(asignaturaAnadir);
    }
}