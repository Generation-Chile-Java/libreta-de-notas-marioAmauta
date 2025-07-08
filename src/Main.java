import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibretaDeNotas libretaDeNotas = new LibretaDeNotas();

        Scanner scanner = new Scanner(System.in);

        libretaDeNotas.ingresarCantidadDeAlumnosYNotasPorAlumno(scanner);
        libretaDeNotas.ingresarNombreDeEstudianteYSusNotas(scanner);
        libretaDeNotas.evaluarCalificaciones();

        int opcionDelUsuario = 0;

        do {
            System.out.println("Elije un opcion");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante.");
            System.out.println("2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.");
            System.out.println("3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.");
            System.out.println("0. Salir del Menú.");

            opcionDelUsuario = scanner.nextInt();

            switch (opcionDelUsuario) {
                case 1: {
                    libretaDeNotas.mostrarPromedioDeNotasDeTodosLosEstudiantes();

                    break;
                }
                case 2: {
                    Map<String, Object> datos = libretaDeNotas.solicitarNombreYNotaDelAlumno(scanner);

                    libretaDeNotas.mostrarSiLaNotaEsAprobadoONoAprobado((String) datos.get("nombreDelAlumno"),(double) datos.get("notaIngresada"));

                    break;
                }
                case 3: {
                    Map<String, Object> datos = libretaDeNotas.solicitarNombreYNotaDelAlumno(scanner);

                    libretaDeNotas.mostrarSiLaNotaEstaPorSobreOPorDebajoDelPromedioDelCurso((String) datos.get("nombreDelAlumno"),(double) datos.get("notaIngresada"));

                    break;
                }

            }
        } while (opcionDelUsuario != 0);

        scanner.close();

        System.out.println("Saliendo...");
    }
}