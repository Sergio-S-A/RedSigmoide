# Red Neuronal Sigmoide para Clasificación Binaria

Implementación en **Java** de una red neuronal simple con función de activación **sigmoide**, entrenada con **backpropagation** para resolver un problema de clasificación binaria:  
> Determinar si un número es **mayor que otro**.

---

## Descripción del Proyecto
Este proyecto construye desde cero una red neuronal artificial con tres neuronas:
- **Neurona A y Neurona B** → reciben los datos de entrada.  
- **Neurona C (neurona de salida)** → combina los resultados de A y B para producir la clasificación final.  

El entrenamiento se realiza mediante:
- **Función de activación sigmoide**
- **Error cuadrático medio**
- **Regla delta (gradiente descendente)**  

La arquitectura está diseñada usando **Programación Orientada a Objetos (POO)** con clases modulares para la neurona y el proceso de entrenamiento.

---

## Estructura del Código
- **`NeuronaSigmoide.java`**  
  Clase que implementa una neurona sigmoide individual.  
  - Inicialización de pesos y sesgo.  
  - Función sigmoide.  
  - Cálculo de salida, error y gradientes.  

- **`Main.java`**  
  Contiene el conjunto de entrenamiento, el bucle de **backpropagation**, y la evaluación de la red.  
  - Entrena con pares de números `(x1, x2)` y un objetivo binario (`1 si x1 > x2, 0 en caso contrario`).  
  - Ajusta pesos y sesgos en cada época.  
  - Muestra la precisión final alcanzada.  

---

## Ejecución
Compilar y ejecutar desde consola:

```bash
javac NeuronaSigmoide.java Main.java
java Main
