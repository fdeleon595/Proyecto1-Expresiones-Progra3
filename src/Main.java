import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ingresar la expresión matemática
        System.out.println("Ingrese la expresión matemática:");
        String expresion = scanner.nextLine();
        
        // Organizar la expresión 
        Analizador analizador = new Analizador();
        List<String> tokens = analizador.tokenizar(expresion);
        
        // Detectar variables y pedir valores al usuario
        Map<String, String> valoresVariables = new HashMap<>();
        for (String token : tokens) {
            // Si el token es una letra, es una variable
            if (token.matches("[a-zA-Z]+")) {
                if (!valoresVariables.containsKey(token)) {
                    System.out.print(token + " = ? : ");
                    String valor = scanner.nextLine();
                    valoresVariables.put(token, valor);
                }
            }
        }
        
        // Crear una lista paralela reemplazando las letras por los números ingresados
        List<String> tokensNumericos = new ArrayList<>();
        for (String token : tokens) {
            if (valoresVariables.containsKey(token)) {
                tokensNumericos.add(valoresVariables.get(token));
            } else {
                tokensNumericos.add(token);
            }
        }

        // Convertir a Notación Postfija
        ConversorPostfija conversor = new ConversorPostfija();
        List<String> postfija = conversor.convertir(tokens); 
        List<String> postfijaNumerica = conversor.convertir(tokensNumericos); 
        
        // Construir el árbol de expresión
        ArbolExpresion arbol = new ArbolExpresion();
        arbol.construirArbol(postfija); 
        
        // Mostrar los recorridos requeridos
        System.out.println("\n--- Resultados ---");
        System.out.print("1. Recorrido Preorden: "); 
        arbol.preorden(arbol.raiz); 
        System.out.println();
        
        System.out.print("2. Recorrido Inorden: "); 
        arbol.inorden(arbol.raiz); 
        System.out.println();
        
        System.out.print("3. Recorrido Postorden (Notación Polaca): "); 
        arbol.postorden(arbol.raiz); 
        System.out.println();
        
        // Evaluar la expresión y mostrar el resultado
        double resultado = arbol.evaluarPostfija(postfijaNumerica);
        System.out.println("\nResultado de la evaluación: " + resultado);
        
     // Interfaz Gráfica
        // Crear la ventana principal
        javax.swing.JFrame ventana = new javax.swing.JFrame("Árbol de Expresión - Proyecto 1");
        ventana.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 400); // Ancho y alto de la ventana
        
        // Agregar nuestro lienzo con el árbol ya construido
        LienzoArbol lienzo = new LienzoArbol(arbol);
        ventana.add(lienzo);
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
        scanner.close();
    }
}