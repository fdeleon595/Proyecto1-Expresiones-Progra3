import java.util.Stack;
import java.util.List;

public class ArbolExpresion {
    public Nodo raiz;

    public ArbolExpresion() {
        this.raiz = null;
    }

    public void construirArbol(List<String> postfija) {
        Stack<Nodo> pilaNodos = new Stack<>();

        for (String token : postfija) {
            // Si es un número o variable, se convierte en un nodo hoja y va a la pila
            if (token.matches("[a-zA-Z0-9.]+")) {
                pilaNodos.push(new Nodo(token));
            } else {
                // Si es un operador, sacamos dos nodos para que sean sus hijos
                Nodo nodoOperador = new Nodo(token);
                // ¡Importante! El primero que sale de la pila es el hijo DERECHO
                nodoOperador.derecho = pilaNodos.pop();
                nodoOperador.izquierdo = pilaNodos.pop();
                
                // Metemos el subárbol resultante de vuelta a la pila
                pilaNodos.push(nodoOperador);
            }
        }
        // El último nodo que queda en la pila es la raíz de todo el árbol
        this.raiz = pilaNodos.pop();
    }
 // Recorrido Preorden: Raíz -> Izquierdo -> Derecho
    public void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preorden(nodo.izquierdo);
            preorden(nodo.derecho);
        }
    }

    // Recorrido Inorden: Izquierdo -> Raíz -> Derecho
    public void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecho);
        }
    }

    // Recorrido Postorden: Izquierdo -> Derecho -> Raíz
    public void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.izquierdo);
            postorden(nodo.derecho);
            System.out.print(nodo.valor + " ");
        }
    }
    public double evaluarPostfija(List<String> postfijaNumerica) {
        Stack<Double> pilaEvaluacion = new Stack<>();

        for (String token : postfijaNumerica) {
            // Si es un número, lo apilamos
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pilaEvaluacion.push(Double.parseDouble(token));
            } else {
                // Si es operador, desapilamos y operamos
                double valorDerecho = pilaEvaluacion.pop();
                double valorIzquierdo = pilaEvaluacion.pop();
                double resultado = 0;

                switch (token) {
                    case "+": resultado = valorIzquierdo + valorDerecho; break;
                    case "-": resultado = valorIzquierdo - valorDerecho; break;
                    case "*": resultado = valorIzquierdo * valorDerecho; break;
                    case "/": resultado = valorIzquierdo / valorDerecho; break;
                    case "^": resultado = Math.pow(valorIzquierdo, valorDerecho); break;
                    // Para la raíz, podrías usar un formato específico, ej. valorIzquierdo √ valorDerecho
                }
                pilaEvaluacion.push(resultado);
            }
        }
        return pilaEvaluacion.pop(); // El resultado final
    }
}