package com.example.desarrollonomina;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteFiles {
    
    /** 
     * @param nomina
     * @return boolean
     * @throws IOException
     */
    //GENERAR EL ARCHIVO DE NOMINA DE ACUERDO CON LOS CAMBIOS REALIZADOS EN LA IMPLEMENTACION.
    public static boolean generarArchivoNomina(Nomina nomina) throws IOException {
        //Creamos la lista de empleados.
        ArrayList<Empleado> listaEmpleadosNomina = nomina.getListaEmpleadosNomina();
        FileWriter archivo = null;
        PrintWriter escritor = null;
        //Intentamos abrir el archivo y escribir en el
        try {
            archivo = new FileWriter("empleados.txt");
            escritor = new PrintWriter(archivo);
            for (int i = 0; i < listaEmpleadosNomina.size(); i++) {
                //Lineas comunes entre todos los tipos de Empleados:
                escritor.println(listaEmpleadosNomina.get(i).getNombreEmpleado() + "%" + listaEmpleadosNomina.get(i).getDocIdentidad() + "%" + listaEmpleadosNomina.get(i).getCargo());

                //LINEAS INDEPENDIENTES DE CADA TIPO DE EMPLEADO:
                //Profesor
                //Si es profesor
                if (listaEmpleadosNomina.get(i) instanceof Profesor) {
                    //Transformamos el empleado a profesor y obtenemos el Escalafon.
                    escritor.println(((Profesor) listaEmpleadosNomina.get(i)).getEscalafon());
                    //Obtenemos las asignatura.
                    ArrayList<Asignatura> asignaturasProfesor = ((Profesor) listaEmpleadosNomina.get(i)).getListaAsignaturasDictadas();
                    //Escrbimos las asignaturas.
                    for (int j = 0; j < asignaturasProfesor.size(); j++) {
                        escritor.println(asignaturasProfesor.get(j).getNombreAsignatura() + "," + asignaturasProfesor.get(j).getNumeroHoras());
                    }
                }

                //Monitor
                //Si es monitor
                if (listaEmpleadosNomina.get(i) instanceof Monitor) {
                    ArrayList<Asignatura> asignaturasMonitor = ((Monitor) listaEmpleadosNomina.get(i)).getListaAsignaturasDictadas();
                    for (int j = 0; j < asignaturasMonitor.size(); j++) {
                        escritor.println(asignaturasMonitor.get(j).getNombreAsignatura() + "," + asignaturasMonitor.get(j).getNumeroHoras());
                    }
                }

                //Empleado
                //Si es empleado, se escriben los salarios minimos.
                if (listaEmpleadosNomina.get(i) instanceof Empleado && listaEmpleadosNomina.get(i) instanceof Profesor == false && listaEmpleadosNomina.get(i) instanceof Monitor == false) {
                    escritor.println(listaEmpleadosNomina.get(i).getSalariosMinimos());
                }

                //Linea de cierre
                escritor.println("#");
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            archivo.close();
        }
    }

    
    /** 
     * @param nomina
     * @return boolean
     * @throws IOException
     */
    //GENERAR EL REPORTE CON LOS SUELDOS A TRAVÉS DEL ARCHIVO DE REPORTE.TXT
    public static boolean generarReporte(Nomina nomina) throws IOException {
        FileWriter archivo = null;
        PrintWriter escritor = new PrintWriter("Reporte.txt");
        try {
            archivo = new FileWriter("Reporte.txt");
            escritor = new PrintWriter(archivo);
            ArrayList<Empleado> listaEmpleadosNomina = nomina.getListaEmpleadosNomina();
            ArrayList<Float> sueldosEmpleadosNomina = nomina.getSueldosEmpleadosNomina();
            for (int i = 0; i < listaEmpleadosNomina.size(); i++) {
                escritor.println(listaEmpleadosNomina.get(i).getNombreEmpleado() + " , " + listaEmpleadosNomina.get(i).getDocIdentidad() + " ,  \\$" + sueldosEmpleadosNomina.get(i));
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            archivo.close();
        }
    }
}