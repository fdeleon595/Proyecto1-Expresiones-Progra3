import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class LienzoArbol extends JPanel {
    private ArbolExpresion arbol;

    public LienzoArbol(ArbolExpresion arbol) {
        this.arbol = arbol;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Validamos que el árbol exista y tenga al menos la raíz
        if (arbol != null && arbol.raiz != null) {
            // Empezamos a dibujar desde el centro superior de la ventana
            // (ancho / 2, coordenada Y inicial, espacio horizontal inicial)
            dibujarNodo(g, arbol.raiz, getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int espacioX) {
        if (nodo == null) return;

        int radio = 15;
        int diametro = radio * 2;
        int separacionVertical = 50; // Espacio hacia abajo entre cada nivel del árbol

        // 1. Dibujar línea al hijo izquierdo (si existe)
        if (nodo.izquierdo != null) {
            g.drawLine(x, y + radio, x - espacioX, y + separacionVertical);
            // Llamada recursiva para el subárbol izquierdo
            dibujarNodo(g, nodo.izquierdo, x - espacioX, y + separacionVertical, espacioX / 2);
        }

        // 2. Dibujar línea al hijo derecho (si existe)
        if (nodo.derecho != null) {
            g.drawLine(x, y + radio, x + espacioX, y + separacionVertical);
            // Llamada recursiva para el subárbol derecho
            dibujarNodo(g, nodo.derecho, x + espacioX, y + separacionVertical, espacioX / 2);
        }

        // 3. Dibujar el círculo del nodo actual
        g.setColor(Color.WHITE);
        g.fillOval(x - radio, y, diametro, diametro);
        g.setColor(Color.BLACK);
        g.drawOval(x - radio, y, diametro, diametro);

        // 4. Dibujar el texto (el operador o la variable) dentro del círculo
        // Restamos unos píxeles en X y sumamos en Y para intentar centrar el texto
        g.drawString(nodo.valor, x - 5, y + 18);
    }
}