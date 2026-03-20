# Proyecto 1: Evaluador de Expresiones Matemáticas
**Curso:** Programación III
**Integrantes:** Fernando Monroy y Jonathan Flores

---

## 📖 Manual de Usuario

### Introducción
Esta aplicación es un evaluador avanzado de expresiones matemáticas. Su propósito es procesar ecuaciones ingresadas por el usuario, manejar variables dinámicas y resolver la expresión utilizando estructuras de datos. Además de proveer el resultado numérico, muestra los recorridos del árbol matemático y genera una representación gráfica de la ecuación.

### Instrucciones de Uso
1. **Inicio del programa:** Al ejecutar la aplicación, se abrirá una consola pidiendo: `Ingrese la expresión matemática:`.
2. **Ingreso de la ecuación:** Escriba su expresión. Operadores soportados: sumas (`+`), restas (`-`), multiplicaciones (`*`), divisiones (`/`), potencias (`^`), raíces (`√`) y paréntesis `()`.
3. **Asignación de variables:** Si su ecuación contiene letras (ej. `a+b`), el programa pausará y le pedirá el valor numérico de cada letra (ej. `a = ? :`). **Importante:** Ingrese únicamente valores numéricos.
4. **Resultados (Consola):** El programa imprimirá:
   * Recorrido Preorden.
   * Recorrido Inorden.
   * Recorrido Postorden (Notación Polaca o Postfija).
   * Resultado final numérico.
5. **Visualización Gráfica:** Se desplegará una ventana emergente ("Árbol de Expresión - Proyecto 1") dibujando el árbol binario generado.

---

## ⚙️ Manual Técnico

### Descripción General del Sistema
El proyecto está desarrollado en Java estándar (JDK). Se fundamenta en Estructuras de Datos dinámicas, específicamente Pilas (`Stacks`) y Árboles Binarios. El algoritmo principal se basa en la conversión de notación infija a postfija (algoritmo Shunting Yard) para la evaluación y construcción del Árbol de Expresión.

### Estructura y Lógica de Clases
El código está modularizado en 6 clases principales:

* **`Main.java`:** Orquesta el flujo de ejecución. Usa `Scanner` para entrada, un `HashMap` para variables y valores numéricos, y lanza la interfaz gráfica.
* **`Analizador.java`:** Responsable del análisis léxico. Valida que los símbolos matemáticos pertenezcan estrictamente a los permitidos.
* **`ConversorPostfija.java`:** Transforma tokens infijos a notación postfija. Utiliza un `Stack<String>` para manejar operadores y evalúa la precedencia (potencia/raíz > multiplicación/división > suma/resta).
* **`Nodo.java`:** Define la estructura del árbol binario. Contiene el valor (operador u operando) y los punteros (`izquierdo` y `derecho`).
* **`ArbolExpresion.java`:** Construye el árbol usando un `Stack<Nodo>`. Implementa métodos recursivos para imprimir los recorridos y utiliza un `Stack<Double>` para resolver aritméticamente la expresión.
* **`LienzoArbol.java`:** Extiende de `JPanel` para gráficos bidimensionales. Usa recursividad para dibujar nodos (`drawOval`) y líneas conectoras (`drawLine`), ajustando dinámicamente las coordenadas.