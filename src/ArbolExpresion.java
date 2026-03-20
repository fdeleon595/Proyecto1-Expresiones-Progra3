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
                
                nodoOperador.derecho = pilaNodos.pop();
                nodoOperador.izquierdo = pilaNodos.pop();
                
                // Metemos el subárbol resultante de vuelta a la pila
                pilaNodos.push(nodoOperador);
            }
        }
        this.raiz = pilaNodos.pop();
    }
 // Recorrido Preorden
    public void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preorden(nodo.izquierdo);
            preorden(nodo.derecho);
        }
    }

    // Recorrido Inorden
    public void inorden(Nodo nodo) {
        if (nodo != null) {
            inorden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecho);
        }
    }

    // Recorrido Postorden
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
                    
                }
                pilaEvaluacion.push(resultado);
            }
        }
        return pilaEvaluacion.pop(); // El resultado final
    }
}