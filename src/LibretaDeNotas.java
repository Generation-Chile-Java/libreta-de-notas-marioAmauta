import java.text.MessageFormat;
import java.util.*;

public class LibretaDeNotas {
    HashMap<String, ArrayList<Double>> estudiantes;

    HashMap<String, HashMap<String, Double>> calificacionesPorEstudiante;

    int cantidadDeAlumnos;

    int notasPorAlumno;

    double notaMinimaParaAprobar = 4;

    public LibretaDeNotas() {
        this.estudiantes = new HashMap<>();
        this.calificacionesPorEstudiante = new HashMap<>();
    }

    void ingresarCantidadDeAlumnosYNotasPorAlumno(Scanner scanner) {
        System.out.println("Ingrese la cantidad de alumnos");
        cantidadDeAlumnos = scanner.nextInt();

        while (cantidadDeAlumnos <= 0) {
            System.out.println("El numero de alumnos debe ser igual o mayor a 1");
            System.out.println("Ingrese una cantidad de alumnos valida");
            cantidadDeAlumnos = scanner.nextInt();
        }

        System.out.println("Ingrese la cantidad de notas por alumno");
        notasPorAlumno = scanner.nextInt();
    }

    void ingresarNombreDeEstudianteYSusNotas(Scanner scanner) {
        for (int i = 0; i < cantidadDeAlumnos; i++) {
            System.out.println("Ingresa el nombre del estudiante");
            String nombreDeEstudiante = scanner.next();

            ArrayList<Double> notasDelAlumno = new ArrayList<>();

             for (int j = 0; j < notasPorAlumno; j++) {
                 System.out.println(MessageFormat.format("Ingresa la nota numero {0} de {1} del alumno {2}", j + 1, notasPorAlumno, nombreDeEstudiante));
                 double notaIngresada = obtenerNota(scanner);
                 notasDelAlumno.add(notaIngresada);
             }

            estudiantes.put(nombreDeEstudiante, notasDelAlumno);
        }
    }

    double obtenerNota(Scanner scanner) {
        double notaIngresada = scanner.nextDouble();

        while (!esUnaNotaValida(notaIngresada)) {
            System.out.println("Ingresa una nota valida");

            notaIngresada = scanner.nextDouble();
        }

        return notaIngresada;
    }

    void mostrarPromedioDeNotasDeTodosLosEstudiantes() {
        System.out.println("Promedio de notas de los alumnos");

        for (Map.Entry<String, HashMap<String, Double>> alumno : calificacionesPorEstudiante.entrySet()) {
            String nombreDeAlumno = alumno.getKey();
            HashMap<String, Double> calificaciones = alumno.getValue();

            System.out.println(MessageFormat.format("{0} {1}", nombreDeAlumno, calificaciones));
        }
    }

    void mostrarSiLaNotaEsAprobadoONoAprobado(String nombreDelEstudiante, double nota) {
        if (nota >= notaMinimaParaAprobar) {
            System.out.println(MessageFormat.format("La nota {0} del estudiante {1} es aprobatoria", nota, nombreDelEstudiante));
        } else {
            System.out.println(MessageFormat.format("La nota {0} del estudiante {1} no es aprobatoria", nota, nombreDelEstudiante));
        }
    }

    void mostrarSiLaNotaEstaPorSobreOPorDebajoDelPromedioDelCurso(String nombreDelEstudiante, double nota) {
        ArrayList<Double> notasPromedioDeCadaAlumno = new ArrayList<>();

        for (HashMap<String, Double> alumno : calificacionesPorEstudiante.values()) {
            notasPromedioDeCadaAlumno.add(alumno.get("promedioDeNotas"));
        }

        double promedioGeneralDelCurso = obtenerPromedio(notasPromedioDeCadaAlumno);

        if (nota > promedioGeneralDelCurso) {
            System.out.println(MessageFormat.format("La nota {0} del alumno {1} esta por sobre el promedio del curso {2}", nota, nombreDelEstudiante, promedioGeneralDelCurso) );
        } else {
            System.out.println(MessageFormat.format("La nota {0} del alumno {1} no esta por sobre el promedio del curso {2}", nota, nombreDelEstudiante, promedioGeneralDelCurso));
        }
    }

    Map<String, Object> solicitarNombreYNotaDelAlumno(Scanner scanner) {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Ingresa el nombre del Alumno");
        String nombreDelAlumno = scanner.next();
        data.put("nombreDelAlumno", nombreDelAlumno);

        System.out.println("Ingresa la nota");
        double notaIngresada = obtenerNota(scanner);

        data.put("notaIngresada", notaIngresada);

        return data;
    }

    boolean esUnaNotaValida(double nota) {
        double notaMinimaValida = 1;
        double notaMaximaValida = 7;

        if (nota < notaMinimaValida || nota > notaMaximaValida) {
            System.out.println(MessageFormat.format("La nota ingresada debe estar dentro del rango permitido que es nota minima {0} y nota maxima {1}", notaMinimaValida, notaMaximaValida));

            return false;
        } else {
            return true;
        }
    }

    double obtenerPromedio(ArrayList<Double> notas) {
        double sumaDeTodasLasNotas = 0;

        for (double nota : notas) {
            sumaDeTodasLasNotas += nota;
        }

        return sumaDeTodasLasNotas / notas.size();
    }

    void evaluarCalificaciones() {
        for (Map.Entry<String, ArrayList<Double>> alumno : estudiantes.entrySet()) {
            ArrayList<Double> notasDelAlumno =  alumno.getValue();

            String alumnoKey = alumno.getKey();

            Collections.sort(notasDelAlumno);

            HashMap<String, Double> calificaciones = new HashMap<>();

            calificaciones.put("promedioDeNotas", this.obtenerPromedio(notasDelAlumno));
            calificaciones.put("notaMaxima", notasDelAlumno.getLast());
            calificaciones.put("notaMinima", notasDelAlumno.getFirst());

            calificacionesPorEstudiante.put(alumnoKey, calificaciones);
        }
    }
}