package com.example.desarrollonomina;

public interface salariosMinimosEscalafon{
    // Atributos
    // Definición de valores estaticos que representan el escalafón del tipo de profesor.
    public static int CATEDRA = 1;
    public static int INSTRUCTOR = 2;
    public static int ASISTENTE = 3;
    public static int ASOCIADO = 4;
    public static int TITULAR = 4;
    
    /** 
     * @param escalafonProfesor
     * @return int
     */
    // Declaración de metodo para calcular los salarios minimos.
    public int retornarCantidadSalariosMinimos(String escalafonProfesor);
}
