import java.util.ArrayList;
import java.util.List;

public class Spiderverse {
    private Nodo inicio;

    public Spiderverse() {
        this.inicio = null;
    }

    public boolean agregarHeroe(SpiderverseHero hero) {
        if (buscarPorCodigo(hero.getCodigo()) != null) {
            return false;
        }
        Nodo nuevo = new Nodo(hero);
        nuevo.siguiente = inicio;
        inicio = nuevo;
        return true;
    }

    public SpiderverseHero buscarPorCodigo(int codigo) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.dato.getCodigo() == codigo) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Spiderverse filtrarPorPoder(String poder) {
        Spiderverse nuevaLista = new Spiderverse();
        Nodo actual = inicio;
        while (actual != null) {
            if (!actual.dato.getPoderEspecial().equals(poder)) {
                nuevaLista.agregarHeroe(actual.dato);
            }
            actual = actual.siguiente;
        }
        return nuevaLista;
    }

    public List<SpiderverseHero> obtenerTodos(){
        List<SpiderverseHero> heroes = new ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            heroes.add(actual.dato);
            actual = actual.siguiente;
        }
        return heroes;
    }



    public void ordenarPorNivel() {
        if (inicio == null || inicio.siguiente == null) return;

        boolean inter;
        do {
            inter = false;
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                if (actual.dato.getNivelExperiencia() > actual.siguiente.dato.getNivelExperiencia()) {
                    SpiderverseHero temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    inter = true;
                }
                actual = actual.siguiente;
            }
        } while (inter);
    }

    public int contarPorUniverso(String universo) {
        return contarPorUniversoRec(inicio, universo);
    }

    private int contarPorUniversoRec(Nodo nodo, String universo) {
        if (nodo == null) return 0;
        return (nodo.dato.getUniverso().equals(universo) ? 1 : 0) +
                contarPorUniversoRec(nodo.siguiente, universo);
    }

}
