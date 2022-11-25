package com.example.desarrollonomina;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

//CLASE DESTINADA PARA REALIZAR LA LECTURA DEL FICHERO EMPLEADOS.TXT
public class ReadFiles {
    
    /** 
     * @param nomina
     * @throws FileNotFoundException
     */
    //METODOS RELACIONADOS CON LA CARGA DE ARCHIVOS:
    public static boolean realizarCargaInfoArchivo(Nomina nomina){
        //Obtenemos los empleados
        ArrayList<Empleado> listaEmpleadosNomina = nomina.getListaEmpleadosNomina();
        //Creamos un arraylist de cadenas para la lectura.
        ArrayList<String> cadenasInfoEmpleadoLista = new ArrayList<String>();
        //Buscamos el archivo.
        File archivoInformacionEmpleados = new File("empleados.txt");
        //Realizamos una lista multidimensional de los empleados.
        List<ArrayList<String>> listaEmpleadosMultidimensional = new ArrayList<>();

        //Intentamos leer el archivo.
        try {
            //Con ayuda de scanner hacemos la lectura
            Scanner scannerArchivo = new Scanner(archivoInformacionEmpleados);
            //Mientras que el scanner pueda leer se repetira el proceso.
            while (scannerArchivo.hasNext()) {
                //Tokenizamos las particiones del empleado.
                StringTokenizer particionesCadenaEmpleado = new StringTokenizer(scannerArchivo.next(), ",#%", true);
                //Mientras que tenga tokens guardaremos la cadena de ellos en las informacion de los empleados.
                while (particionesCadenaEmpleado.hasMoreTokens()) {
                    String cadena = particionesCadenaEmpleado.nextToken();
                    //Guardar todos los tokens en el ArrayList
                    cadenasInfoEmpleadoLista.add(cadena);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            // En caso de no poderse abrir el archivo lanzamos el error.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Archivo No Existente");
            alert.setContentText("Error en la apertura del archivo  " + fileNotFoundException.getMessage());
            alert.show();
            return false;
        }

        //Creamos una lista temporal de strings
        ArrayList<String> listaTemporal = new ArrayList<String>();
        //Hacemos el bucle hasta el maximo de inofrmacion de los empleados.
        for (int i = 0; i < cadenasInfoEmpleadoLista.size(); i++) {
            //Guardamos los valores de la informacion de las cadenas en la lista temporal.
            listaTemporal.add(cadenasInfoEmpleadoLista.get(i));
            //Si las cadenas son iguales a # guardaremos la información.
            if (cadenasInfoEmpleadoLista.get(i).equals("#")) {
                listaEmpleadosMultidimensional.add(listaTemporal);
                //Limpiamos la lista temporal.
                listaTemporal = new ArrayList<String>();
            }
        }

        //Eliminar los %, # y ,
        for (int i = 0; i < listaEmpleadosMultidimensional.size(); i++) {
            //Obtenemos los valores de la listaEmpleados multidimensional.
            ArrayList<String> listaIteracion = listaEmpleadosMultidimensional.get(i);
            //Abrimos otro for donde podemos verrificar los valores de los strings
            for (int j = 0; j < listaIteracion.size(); j++) {
                if (listaIteracion.get(j).equals("%") || listaIteracion.get(j).equals("#") || listaIteracion.get(j).equals(",")) {
                    listaIteracion.remove(j);
                }
            }
        }

        //Recorremos la lista de empleados multidimensional
        for (int i = 0; i < listaEmpleadosMultidimensional.size(); i++) {
            //obtenemos los valores de la lista en esa posición
            ArrayList<String> listaIteracion = listaEmpleadosMultidimensional.get(i);
            //Realizamos el registro.
            realizarSeparacionRegistrosCadaTipoEmpleado(listaIteracion, listaEmpleadosNomina);
        }
        
        return true;
    }

    
    /** 
     * @param infoEmpleado
     * @param listaEmpleadosNomina
     */
    private static void realizarSeparacionRegistrosCadaTipoEmpleado(ArrayList<String> infoEmpleado, ArrayList<Empleado> listaEmpleadosNomina) {
        //Realizar la separacion y posterior eliminacion.
        String nombre = infoEmpleado.get(0) + " " + infoEmpleado.get(1);
        int cedula = Integer.parseInt(infoEmpleado.get(2));
        String cargo = infoEmpleado.get(3);

        //Realizar la adicion del tipo de Empleado a la nomina de acuerdo con la carga del archivo:
        //Empleado
        if (cargo.equalsIgnoreCase("Empleado")) {
            float cantidadSalariosMinimos = Float.parseFloat(infoEmpleado.get(4));
            //Añadir el empleado al ArrayList de la nomina.
            Empleado empleadoNuevo = new Empleado(nombre, cedula, cantidadSalariosMinimos, "No hay por el momento", cargo);
            listaEmpleadosNomina.add(empleadoNuevo);
        }


        //Profesor
        if (cargo.equalsIgnoreCase("Profesor")) {
            String escalafon = infoEmpleado.get(4);
            ArrayList<Asignatura> listaAsignaturasProfe = new ArrayList<Asignatura>();

            //Guardar los valores numericos en el siguiente ArrayList de Enteros.
            ArrayList<Integer> listaEnteros = new ArrayList<Integer>();
            ArrayList<String> nombreAsignaturasIndice = new ArrayList<String>();

            for (int i = 5; i < infoEmpleado.size(); i++) {
                boolean isNumeric = infoEmpleado.get(i).matches(("[+-]?\\d*(\\.\\d+)?"));
                if (isNumeric) {
                    int cantidadHoras = Integer.parseInt(infoEmpleado.get(i));
                    listaEnteros.add(cantidadHoras);
                }
            }

            String nombreAsignatura = new String();
            for (int i = 5; i < infoEmpleado.size(); i++) {
                boolean isNumeric = infoEmpleado.get(i).matches(("[+-]?\\d*(\\.\\d+)?"));
                if (isNumeric) {
                    nombreAsignaturasIndice.add(nombreAsignatura);
                    nombreAsignatura = new String();
                } else {
                    nombreAsignatura += infoEmpleado.get(i) + " ";
                }
            }

            for (int i = 0; i < nombreAsignaturasIndice.size(); i++) {
                String nombreMateria = nombreAsignaturasIndice.get(i);
                int numHoras = listaEnteros.get(i);
                Asignatura asignaturaNueva = new Asignatura(numHoras, nombreMateria);
                listaAsignaturasProfe.add(asignaturaNueva);
            }

            Profesor profesorAnadir = new Profesor(nombre, cedula, 0, "No hay por el momento", cargo, listaAsignaturasProfe, escalafon);
            listaEmpleadosNomina.add(profesorAnadir);
        }

        if (cargo.equalsIgnoreCase("Monitor")) {
            ArrayList<Asignatura> listaAsignaturasMonitor = new ArrayList<Asignatura>();

            //Guardar los valores numericos en el siguiente ArrayList de Enteros.
            ArrayList<Integer> listaEnteros = new ArrayList<>();
            ArrayList<String> nombreAsignaturasIndice = new ArrayList<String>();

            for (int i = 4; i < infoEmpleado.size(); i++) {
                boolean isNumeric = infoEmpleado.get(i).matches(("[+-]?\\d*(\\.\\d+)?"));
                if (isNumeric) {
                    int cantidadHoras = Integer.parseInt(infoEmpleado.get(i));
                    listaEnteros.add(cantidadHoras);
                }
            }

            String nombreAsignatura = new String();
            for (int i = 4; i < infoEmpleado.size(); i++) {
                boolean isNumeric = infoEmpleado.get(i).matches(("[+-]?\\d*(\\.\\d+)?"));
                if (isNumeric) {
                    nombreAsignaturasIndice.add(nombreAsignatura);
                    nombreAsignatura = new String();
                } else {
                    nombreAsignatura += infoEmpleado.get(i) + " ";
                }
            }

            for (int i = 0; i < nombreAsignaturasIndice.size(); i++) {
                String nombreMateria = nombreAsignaturasIndice.get(i);
                int numHoras = listaEnteros.get(i);
                Asignatura asignaturaNueva = new Asignatura(numHoras, nombreMateria);
                listaAsignaturasMonitor.add(asignaturaNueva);
            }
            Monitor monitorAnadir = new Monitor(nombre, cedula, 0, "No hay por el momento", cargo, listaAsignaturasMonitor);
            listaEmpleadosNomina.add(monitorAnadir);
        }
    }
}
