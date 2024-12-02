public class Avenger {
    public String id;
    public String nombre;
    public String mision;
    public int nivelPeligrosidad;
    public double pagoMensual;
    public double pagoAnual;
    public double aporteFondoHeroes;
    public double impuestoGobierno;

    public Avenger siguiente;

    public Avenger(String id, String nombre, String mision, int nivelPeligrosidad, double pagoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.mision = mision;
        this.nivelPeligrosidad = nivelPeligrosidad;
        this.pagoMensual = pagoMensual;
        this.aporteFondoHeroes = calcularAporteFondoHeroes();
        this.impuestoGobierno = calcularImpuestoGobierno();
        this.siguiente = null;
    }

    // Método para calcular el aporte al fondo de héroes (8% del pago mensual)
    public double calcularAporteFondoHeroes() {
        return 0.08 * pagoMensual;
    }

    // Método para calcular el impuesto gubernamental basado en el pago anual
    public double calcularImpuestoGobierno() {
        this.pagoAnual = this.pagoMensual * 12;  // Guardamos el pago anual
        if (pagoAnual <= 50000) {
            return 0;
        } else if (pagoAnual <= 100000) {
            return (pagoAnual * 0.10 ); // 10% si el pago anual es entre 50,001 y 100,000
        } else if (pagoAnual <= 200000) {
            return (pagoAnual * 0.20 ); // 20% si el pago anual es entre 100,001 y 200,000
        } else {
            return (pagoAnual * 0.30 ); // 30% si el pago anual es mayor a 200,000
        }
    }
}
