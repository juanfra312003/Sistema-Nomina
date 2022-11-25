package com.example.desarrollonomina;

public class Empleado {
    //Atributos
    private String nombreEmpleado;
    private int docIdentidad;
    private float salariosMinimos;
    private String dependencia;
    private String cargo;

    //Implementacion del metodo Constructor:
    public Empleado(String nombreEmpleado, int docIdentidad, float salariosMinimos, String dependencia, String cargo) {
        this.nombreEmpleado = nombreEmpleado;
        this.docIdentidad = docIdentidad;
        this.salariosMinimos = salariosMinimos;
        this.dependencia = dependencia;
        this.cargo = cargo;
    }

    
    /** 
     * @param nombreEmpleado
     */
    //Implementacion de metodos Get/Set
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    
    /** 
     * @param dependencia
     */
    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    
    /** 
     * @param salariosMinimos
     */
    public void setSalariosMinimos(float salariosMinimos) {
        this.salariosMinimos = salariosMinimos;
    }

    
    /** 
     * @param docIdentidad
     */
    public void setDocIdentidad(int docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    
    /** 
     * @return String
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    
    /** 
     * @return int
     */
    public int getDocIdentidad() {
        return docIdentidad;
    }

    
    /** 
     * @return String
     */
    public String getDependencia() {
        return dependencia;
    }

    
    /** 
     * @return double
     */
    public double getSalariosMinimos() {
        return salariosMinimos;
    }

    
    /** 
     * @return String
     */
    public String getCargo() {
        return cargo;
    }

    
    /** 
     * @param cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
    /** 
     * @return float
     * Implementacion del metodo calcular salario para los empleados. (Generalización Posterior con Profesor y Monitor
     */
    public float calcularSalario() {
        //Se toman los salarios minimos, para ser multiplicados por los valores estaticos de empleados "1000000", luego son multiplicados por los descuentos de ley.
        return (float) ((this.getSalariosMinimos() * valoresEstaticos.SALARIOMINIMOEMPLEADOS) * 0.88);
    }
}