import java.util.ArrayList;

public class ListaThunderbolt {
    public ArrayList<Thunderbolt> thunderbolts;

    public ListaThunderbolt() {
        thunderbolts = new ArrayList<>();
    }

    // Agregar un Thunderbolt a la lista
    public boolean agregarThunderbolt(Thunderbolt nuevo) {
        for (Thunderbolt t : thunderbolts) {
            if (t.getCodigo() == nuevo.getCodigo()) {
                return false; // Código ya existe
            }
        }
        thunderbolts.add(0, nuevo); // Agregar al inicio
        return true; // Éxito
    }

    // Buscar un Thunderbolt por código
    public Thunderbolt buscarThunderbolt(int codigo) {
        for (Thunderbolt t : thunderbolts) {
            if (t.getCodigo() == codigo) {
                return t; // Retornar el Thunderbolt encontrado
            }
        }
        return null; // No encontrado
    }



    // Filtrar Thunderbolts que no tienen una habilidad específica
    public ArrayList<Thunderbolt> filtrarPorHabilidad(String habilidad) {
        ArrayList<Thunderbolt> filtrados = new ArrayList<>();
        for (Thunderbolt t : thunderbolts) {
            if (!t.getHabilidadPrincipal().equals(habilidad)) {
                filtrados.add(t);
            }
        }
        return filtrados;
    }

    // Ordenar la lista usando el método de burbuja basado en el nivel de redención
    public void ordenarPorBurbuja(ArrayList<Thunderbolt> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getNivelRedencion() > lista.get(j + 1).getNivelRedencion()) {
                    // Intercambiar
                    Thunderbolt temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }
    }

    // Contar misiones por habilidad de forma recursiva
    public int contarMisionesPorHabilidad(String habilidad, int index) {
        if (index >= thunderbolts.size()) {
            return 0; // Caso base: hemos llegado al final de la lista
        }

        int count = thunderbolts.get(index).getHabilidadPrincipal().equals(habilidad) ? 1 : 0;

        // Llamada recursiva para el siguiente Thunderbolt
        return count + contarMisionesPorHabilidad(habilidad, index + 1);
    }

    // Obtener la lista de Thunderbolts
    public ArrayList<Thunderbolt> getThunderbolts() {
        return thunderbolts;
    }
}