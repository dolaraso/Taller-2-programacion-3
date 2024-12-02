public class Thunderbolt {
    public int codigo;
    public String nombre;
    public String habilidadPrincipal;
    public int nivelRedencion;
    public String misionAsignada;

    public Thunderbolt(int codigo, String nombre, String habilidadPrincipal, int nivelRedencion, String misionAsignada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.habilidadPrincipal = habilidadPrincipal;
        this.nivelRedencion = nivelRedencion;
        this.misionAsignada = misionAsignada;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabilidadPrincipal() {
        return habilidadPrincipal;
    }

    public int getNivelRedencion() {
        return nivelRedencion;
    }

    public String getMisionAsignada() {
        return misionAsignada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHabilidadPrincipal(String habilidadPrincipal) {
        this.habilidadPrincipal = habilidadPrincipal;
    }

    public void setNivelRedencion(int nivelRedencion) {
        this.nivelRedencion = nivelRedencion;
    }

    public void setMisionAsignada(String misionAsignada) {
        this.misionAsignada = misionAsignada;
    }
}