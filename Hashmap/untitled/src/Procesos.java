import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Procesos {
    private HashMap<String, ArrayList<Double>> estudiantes = new HashMap<>();

    public void inicio() {
        registrarEstudiantes();
        mostrarNotasYPromedios();
        mostrarResultadosFinales();
    }

    public void registrarEstudiantes() {
        int activadorEstudiantes = Integer.parseInt(JOptionPane.showInputDialog(
                "¿Desea averiguar el promedio de algún estudiante?\n1. Sí\n2. No"));

        while (activadorEstudiantes == 1) {
            String nombre = JOptionPane.showInputDialog("Nombre del estudiante:");
            ArrayList<Double> notas = new ArrayList<>();

            int solicitadorNotas = 1;

            for (int i = 0; i < 3; i++) {
                double nota;
                do {
                    nota = Double.parseDouble(JOptionPane.showInputDialog(
                            "Ingrese la nota " + solicitadorNotas + " para " + nombre + " (entre 0 y 5)"));
                    if (nota < 0 || nota > 5) {
                        JOptionPane.showMessageDialog(null, "Nota inválida. Debe estar entre 0 y 5.");
                    }
                } while (nota < 0 || nota > 5);

                notas.add(nota);
                solicitadorNotas++;
            }

            estudiantes.put(nombre, notas);

            activadorEstudiantes = Integer.parseInt(JOptionPane.showInputDialog(
                    "¿Desea ingresar otro estudiante?\n1. Sí\n2. No"));
        }
    }

    public void mostrarNotasYPromedios() {
        for (String nombre : estudiantes.keySet()) {
            ArrayList<Double> notas = estudiantes.get(nombre);

            System.out.print("Estudiante: " + nombre + " - Notas: ");
            for (double nota : notas) {
                System.out.print(nota + " " + "\n");
            }

        }
    }

    public void mostrarResultadosFinales() {
        int totalEstudiantes = estudiantes.size();
        int totalNotasIngresadas = 0;
        int estudiantesGanan = 0;
        int estudiantesPierden = 0;
        int estudiantesRecuperan = 0;
        int estudiantesPierdenSinRecuperacion = 0;
        double sumaNotasFinales = 0;

        StringBuilder listado = new StringBuilder("Listado de estudiantes y sus notas finales:\n");

        for (String nombre : estudiantes.keySet()) {
            ArrayList<Double> notas = estudiantes.get(nombre);
            double promedio = calcularPromedio(notas);

            totalNotasIngresadas += notas.size();
            sumaNotasFinales += promedio;

            if (promedio >= 3.5) {
                estudiantesGanan++;
            } else {
                estudiantesPierden++;
                if (promedio > 2) {
                    estudiantesRecuperan++;
                } else {
                    estudiantesPierdenSinRecuperacion++;
                }
            }

            listado.append(nombre).append(": ").append(String.format("%.2f", promedio)).append("\n");
        }

        double promedioNotasFinales = totalEstudiantes > 0 ? sumaNotasFinales / totalEstudiantes : 0;

        System.out.println("\n\n"+"----------------------"+"\n"+
                "Total estudiantes validados: " + totalEstudiantes + "\n" +
                        "Total notas ingresadas: " + totalNotasIngresadas + "\n" +
                        "Estudiantes que ganan: " + estudiantesGanan + "\n" +
                        "Estudiantes que pierden: " + estudiantesPierden + "\n" +
                        "Estudiantes que pueden recuperar: " + estudiantesRecuperan + "\n" +
                        "Estudiantes que pierden sin recuperación: " + estudiantesPierdenSinRecuperacion + "\n" +
                        "Promedio general de notas finales: " + (promedioNotasFinales) + "\n\n"+"----------------------"+"\n");

        System.out.println(listado.toString());
    }

    public double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return notas.size() > 0 ? suma / notas.size() : 0;
    }
}

