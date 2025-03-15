
// Clase SistemaPQRS
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// Clase SistemaPQRS
public class PQRSApp {
    private List<Solicitud> solicitudes;
    private List<Departamento> departamentos;
    private Empleado empleadoAutenticado;

    public PQRSApp() {
        solicitudes = new ArrayList<>();
        departamentos = new ArrayList<>();
        inicializarDatos(); // Inicializa departamentos y empleados
    }

    private void inicializarDatos() {
        Departamento dep1 = new Departamento("Atención al Cliente");
        dep1.agregarEmpleado(new Empleado("Juan Pérez", "EMP001"));
        dep1.agregarEmpleado(new Empleado("María Gómez", "EMP002"));

        Departamento dep2 = new Departamento("Soporte Técnico");
        dep2.agregarEmpleado(new Empleado("Carlos Ruiz", "EMP003"));
        dep2.agregarEmpleado(new Empleado("Ana Torres", "EMP004"));

        Departamento dep3 = new Departamento("Quejas, Reclamos y Sugerencias");
        dep3.agregarEmpleado(new Empleado("Pedro López", "EMP005"));
        dep3.agregarEmpleado(new Empleado("Laura Martínez", "EMP006"));

        departamentos.add(dep1);
        departamentos.add(dep2);
        departamentos.add(dep3);
    }

    public void crearSolicitud(Cliente cliente, Departamento departamento, Empleado empleado) {
        Solicitud nuevaSolicitud = new Solicitud(cliente, departamento, empleado);
        solicitudes.add(nuevaSolicitud);
        System.out.println("Solicitud creada: " + nuevaSolicitud);
    }

    public void consultarSolicitud(int id) {
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getId() == id) {
                System.out.println("Solicitud encontrada: " + solicitud);
                return;
            }
        }
        System.out.println("Solicitud no encontrada.");
    }

    public void actualizarEstadoSolicitud(int id, String nuevoEstado) {
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getId() == id) {
                solicitud.actualizarEstado(nuevoEstado);
                System.out.println("Estado actualizado a: " + nuevoEstado);
                return;
            }
        }
        System.out.println("Solicitud no encontrada.");
    }

    public void autenticarEmpleado() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su ID: ");
        String id = scanner.nextLine();
        this.empleadoAutenticado = new Empleado(nombre, id);
        System.out.println("Empleado autenticado: " + nombre);
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Crear Solicitud");
            System.out.println("2. Consultar Solicitud");
            System.out.println("3. Entrar con perfil de Empleado");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Ingrese ID del cliente: ");
                    String idCliente = scanner.nextLine();

                    // Mostrar departamentos disponibles
                    System.out.println("Seleccione un departamento:");
                    for (int i = 0; i < departamentos.size(); i++) {
                        System.out.println((i + 1) + ". " + departamentos.get(i).getNombre());
                    }
                    int indiceDepartamento = scanner.nextInt() - 1;
                    Departamento departamentoSeleccionado = departamentos.get(indiceDepartamento);

                    // Mostrar empleados del departamento seleccionado
                    System.out.println("Seleccione un empleado:");
                    List<Empleado> empleadosDelDepartamento = departamentoSeleccionado.getEmpleados();
                    for (int i = 0; i < empleadosDelDepartamento.size(); i++) {
                        System.out.println((i + 1) + ". " + empleadosDelDepartamento.get(i).getNombre());
                    }
                    int indiceEmpleado = scanner.nextInt() - 1;
                    Empleado empleadoSeleccionado = empleadosDelDepartamento.get(indiceEmpleado);

                    Cliente cliente = new Cliente(nombreCliente, idCliente);
                    crearSolicitud(cliente, departamentoSeleccionado, empleadoSeleccionado);
                    break;
                case 2:
                    System.out.print("Ingrese el ID de la solicitud: ");
                    int idSolicitud = scanner.nextInt();
                    consultarSolicitud(idSolicitud);
                    break;
                case 3:
                    autenticarEmpleado();
                    boolean empleadoAuth = true;
                    while (empleadoAuth) {
                        System.out.println("1. Actualizar Estado de Solicitud");
                        System.out.println("2. Salir del perfil de Empleado");
                        System.out.print("Seleccione una opción: ");
                        int opcionEmpleado = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        switch (opcionEmpleado) {
                            case 1:
                                System.out.print("Ingrese el ID de la solicitud: ");
                                int idActualizar = scanner.nextInt();
                                System.out.println("Seleccione el nuevo estado:");
                                System.out.println("1. Finalizado");
                                int estadoSeleccionado = scanner.nextInt();
                                String nuevoEstado = (estadoSeleccionado == 1) ? "Finalizado" : "Recibido"; // Solo se
                                                                                                            // permite
                                                                                                            // cambiar a
                                                                                                            // "Finalizado"
                                actualizarEstadoSolicitud(idActualizar, nuevoEstado);
                                break;
                            case 2:
                                empleadoAuth = false;
                                break;
                            default:
                                System.out.println("Opción inválida. Intente de nuevo.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}