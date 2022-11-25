package com.example.desarrollonomina;

public class Asignatura {
    //Atributos propios de la Clase:
    private int numeroHoras;
    private String nombreAsignatura;

    
    /** 
     * @param nombreAsignatura
     */
    //Metodos Getters and Setters.
    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    
    /** 
     * @param numeroHoras
     */
    public void setNumeroHoras(int numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    
    /** 
     * @return String
     */
    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    
    /** 
     * @return int
     */
    public int getNumeroHoras() {
        return numeroHoras;
    }

    //Metodo constructor
    public Asignatura(int numHoras, String nombreAsignatura) {
        this.numeroHoras = numHoras;
        this.nombreAsignatura = nombreAsignatura;
    }
}