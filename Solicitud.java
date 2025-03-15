// Clase Caso (PQRS)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// Clase Caso (PQRS)
class Solicitud {
    private static int contador = 0;
    private int id;
    private Cliente cliente;
    private Departamento departamento;
    private Empleado empleado;
    private String estado;

    public Solicitud(Cliente cliente, Departamento departamento, Empleado empleado) {
        this.id = ++contador;
        this.cliente = cliente;
        this.departamento = departamento;
        this.empleado = empleado;
        this.estado = "Recibido"; // Estado inicial
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "Solicitud ID: " + id + ", Cliente: " + cliente.getNombre() + ", Departamento: " + departamento.getNombre() + ", Empleado: " + empleado.getNombre() + ", Estado: " + estado;
    }
}