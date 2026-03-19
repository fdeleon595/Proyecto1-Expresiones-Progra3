public class Nodo {
    String valor; // Almacena el operador, el número o la variable
    Nodo izquierdo;
    Nodo derecho;

    // Constructor para inicializar el nodo
    public Nodo(String valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Método auxiliar para saber si el nodo es una hoja (operando)
    public boolean esHoja() {
        return (this.izquierdo == null && this.derecho == null);
    }
}
