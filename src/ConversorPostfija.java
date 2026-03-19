import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class ConversorPostfija {

    public List<String> convertir(List<String> tokens) {
        List<String> salida = new ArrayList<>();
        Stack<String> pilaOperadores = new Stack<>();

        for (String token : tokens) {
            // Regla 1: Si es un operando (número o variable), va directo a la salida
            if (esOperando(token)) {
                salida.add(token);
            } 
            // Regla 2: Si es un paréntesis de apertura, va a la pila
            else if (token.equals("(")) {
                pilaOperadores.push(token);
            } 
            // Regla 3: Si es un paréntesis de cierre, vaciar la pila hasta el '('
            else if (token.equals(")")) {
                while (!pilaOperadores.isEmpty() && !pilaOperadores.peek().equals("(")) {
                    salida.add(pilaOperadores.pop());
                }
                if (!pilaOperadores.isEmpty()) pilaOperadores.pop(); // Sacar el '('
            } 
            // Regla 4: Es un operador aritmético
            else {
                while (!pilaOperadores.isEmpty() && precedencia(pilaOperadores.peek()) >= precedencia(token)) {
                    salida.add(pilaOperadores.pop());
                }
                pilaOperadores.push(token);
            }
        }

        // Vaciar cualquier operador restante en la pila
        while (!pilaOperadores.isEmpty()) {
            salida.add(pilaOperadores.pop());
        }

        return salida;
    }

    private int precedencia(String operador) {
        if (operador.equals("^") || operador.equals("√")) return 3;
        if (operador.equals("*") || operador.equals("/")) return 2;
        if (operador.equals("+") || operador.equals("-")) return 1;
        return 0; // Para los paréntesis
    }

    private boolean esOperando(String token) {
        // Si no es operador ni paréntesis, asumimos que es número o variable
        return token.matches("[a-zA-Z0-9.]+"); 
    }
}