public class ListaAvengers {
    public Avenger cabeza;
    public int tamano;

    public ListaAvengers() {
        cabeza = null;
        tamano = 0;
    }

    // Método para agregar un Avenger a la lista
    public void agregarAvenger(Avenger avenger) {
        if (cabeza == null) {
            cabeza = avenger;
        } else {
            Avenger actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = avenger;
        }
        tamano++;
    }

    // Método para modificar un Avenger en la lista por ID
    public boolean modificarAvenger(String id, String nombre, String mision, int nivelPeligrosidad, double pagoMensual) {
        Avenger actual = cabeza;
        while (actual != null) {
            if (actual.id.equals(id)) {
                // Modificar el Avenger encontrado
                actual.nombre = nombre;
                actual.mision = mision;
                actual.nivelPeligrosidad = nivelPeligrosidad;
                actual.pagoMensual = pagoMensual;
                actual.aporteFondoHeroes = actual.calcularAporteFondoHeroes();
                actual.impuestoGobierno = actual.calcularImpuestoGobierno();
                return true;
            }
            actual = actual.siguiente;
        }
        return false; // Si no se encuentra el Avenger con el ID
    }

    // Método para listar todos los Avengers de la lista
    public String listarAvengers() {
        StringBuilder lista = new StringBuilder();
        Avenger actual = cabeza;
        while (actual != null) {
            lista.append("ID: ").append(actual.id)
                    .append(", Nombre: ").append(actual.nombre)
                    .append(", Misión: ").append(actual.mision)
                    .append(", Peligrosidad: ").append(actual.nivelPeligrosidad)
                    .append(", Pago Mensual: ").append(actual.pagoMensual)
                    .append(", Pago Anual: ").append(actual.pagoAnual)
                    .append(", Aporte al Fondo de Héroes: ").append(actual.aporteFondoHeroes)
                    .append(", Impuesto: ").append(actual.impuestoGobierno)
                    .append("\n");
            actual = actual.siguiente;
        }
        return lista.toString();
    }
}