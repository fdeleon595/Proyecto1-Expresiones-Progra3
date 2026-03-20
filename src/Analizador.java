import java.util.ArrayList;
import java.util.List;

public class Analizador {

    public List<String> tokenizar(String expresion) {
        List<String> tokens = new ArrayList<>();
        StringBuilder acumulador = new StringBuilder();

        expresion = expresion.replace(" ", "");

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            // Validar si es una letra o un numero
            if (Character.isLetterOrDigit(c)) {
                acumulador.append(c);
            } else {
                if (acumulador.length() > 0) {
                    tokens.add(acumulador.toString());
                    acumulador.setLength(0);
                }

                // Validamos que el caracter sea un operador permitido o paréntesis
                if (esOperadorValido(c) || c == '(' || c == ')') {
                    tokens.add(String.valueOf(c));
                } else {
                    // Aquí puedes manejar la excepción o error de caracter inválido
                    System.out.println("Error: Caracter no permitido '" + c + "'");
                }
            }
        }
        
        // Agregar el último operando si quedó en el acumulador
        if (acumulador.length() > 0) {
            tokens.add(acumulador.toString());
        }

        return tokens;
    }

    private boolean esOperadorValido(char c) {
        // Validamos sumas, restas, multiplicaciones, divisiones, potencias y raíces
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '√';
    }
}