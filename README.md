# Proyecto Final - Estructura de Datos

*Título de la Práctica:* Implementación de un Algoritmo para Encontrar la Ruta Óptima en un Laberinto Aplicando Programación Dinámica y Estructuras de Datos  
## Carrera: Computación  
*Asignatura:* Estructura de Datos – Segundo Interciclo  
*Número de Práctica:* 5  

---

## *Objetivo*

Desarrollar una aplicación que implemente un algoritmo para encontrar la ruta óptima desde un punto de inicio (A) hasta un punto de destino (B) en un laberinto. Para ello, se utilizarán conceptos avanzados de programación dinámica y estructuras de datos lineales y no lineales.

---
---

## *Actividades por desarrollar*

### *Comprensión de Algoritmos*
- Implementar algoritmos de búsqueda y optimización para encontrar rutas en grafos y laberintos.
- Comparar estrategias de búsqueda como BFS (Breadth-First Search), DFS (Depth-First Search) y A*.

### *Descripción del Problema*
- Representar el laberinto como una matriz de celdas transitables o no transitables.
- Definir los puntos de inicio (A) y destino (B).

### *Implementación del Algoritmo*
- Desarrollo de al menos cuatro métodos para encontrar la ruta:
  - Método recursivo simple
  - Método aplicando cache (programación dinámica)
  - BFS (Breadth-First Search)
  - DFS (Depth-First Search)
- Utilizar estructuras de datos adecuadas para almacenar rutas.
- Aplicar el patrón MVC para organizar el código.

### *Interfaz de Usuario*
- Diseño de una UI para ingresar laberintos y visualizar la ruta óptima.
- Permitir la configuración del tamaño del laberinto y las celdas transitables o no.
- Permitir la selección de puntos de inicio y destino desde la interfaz.

### *Versión Extra*
- Implementar una visualización de la mejor ruta en la UI.
- Comparar la eficiencia de cada algoritmo en términos de pasos y tiempo de ejecución.
- Mostrar los resultados en la UI e incluir capturas en el informe.

---

# *Proyecto Final - Estructura de Datos*


![Logo de la Universidad](https://github.com/Jona142004/Proyecto-FInal/blob/main/logo_ups.png?raw=true)


- *Universidad:* Universidad Politécnica Salesiana
- *Carrera:* Ingeniería en Ciencias de la Computación
- *Asignatura:* Estructura de Datos
- *Estudiantes:* Esteban Hernandez (jhernandezv3@est.ups.edu.ec)
                    Sebastian Zurita(tzuritaa@est.ups.edu.ec)
                        Emanuel León(eleonj2@est.ups.edu.ec)
- *Docente:* Ing. Pablo Torres

---

## *Descripción del Problema*
Dado un laberinto representado como una matriz de celdas, donde algunas son transitables y otras no, se busca encontrar la ruta óptima que conecta un punto de inicio (A) con un punto de destino (B). El problema o desafío radica en diseñar un algoritmo que, utilizando estructuras de datos apropiadas y técnicas de búsqueda óptima, sea capaz de hallar el camino más eficiente en términos de distancia o costo, y a su vez también aplicar lo aprendido en cuanto interfaz gráfica, para diseñar algo atractivo e intuitivo para el usuario.
---

## *Propuesta de Solución*

### *Marco Teórico*
Para encontrar la ruta óptima en un laberinto, es fundamental conocer los algoritmos y estructuras de datos utilizados en la búsqueda de caminos dentro de grafos. A continuación, se presentan diferentes enfoques y técnicas relevantes.
- *Programación Dinámica:* Es una técnica de optimización que permite reducir el tiempo de ejecución al descomponer un problema en subproblemas más pequeños y almacenar sus soluciones intermedias. En la búsqueda de rutas, esta técnica puede ayudar a evitar cálculos repetitivos y mejorar la eficiencia del algoritmo.
- *BFS (Breadth-First Search):* Es un método de búsqueda en anchura que explora el grafo nivel por nivel, garantizando la obtención del camino más corto en grafos no ponderados. Se basa en una estructura de datos tipo cola, lo que permite expandir primero los nodos más cercanos al punto de partida antes de explorar niveles más profundos.
- *DFS (Depth-First Search):* Es un método de búsqueda que permite reducir el tiempo de ejecución al descomponer un problema en subproblemas más pequeños y almacenar sus soluciones intermedias. En la búsqueda de rutas, esta técnica puede ayudar a evitar cálculos repetitivos y mejorar la eficiencia del algoritmo.
- *Búsqueda Recursiva:* Es una variante del DFS en la que las llamadas a la función se realizan de manera recursiva en lugar de utilizar una pila explícita. Su implementación es sencilla y directa, pero puede ser ineficiente en laberintos grandes debido al riesgo de desbordamiento de la pila si el espacio de búsqueda es muy profundo.

### *Descripción de la Solución*
- *Descripción de la propuesta de solución*
Para encontrar la ruta óptima en el laberinto, se utilizarán algoritmos de búsqueda en grafos. Si se requiere garantizar el camino más corto en un laberinto no ponderado, se aplicará BFS (Breadth-First Search), ya que explora nivel por nivel y asegura la solución óptima. Para recorrer completamente el laberinto y analizar todas las posibles rutas, se empleará DFS (Depth-First Search), que permite una exploración profunda antes de retroceder.
Cada algoritmo será evaluado en términos de su eficiencia mediante el uso de System.nanoTime(), que permite medir el tiempo de ejecución de cada recorrido y comparar cuál es el más rápido en función del tamaño del laberinto y la complejidad de la ruta.
Además, se implementará una búsqueda recursiva, que simplifica la estructura del código al utilizar llamadas recursivas en lugar de una pila explícita. Esta solución será modular y adaptable según la estructura del laberinto, optimizando el rendimiento con almacenamiento de nodos visitados para evitar exploraciones redundantes.
- *Herramientas*
- *Lenguaje de Programación:* Java
- *Interfaz gráfica:* Implementada con JFrame en Java
- *Patrón de Diseño:* Modelo-Vista-Controlador
- *Estructuras de Datos Utilizadas:*
  - *Matriz:* Para representar el laberinto.
  - *Listas (ArrayList, LinkedList):* Para almacenar rutas exploradas y gestionar la secuencia de nodos visitados.
  - *Colas (Queue - LinkedList):*  Utilizada en BFS para manejar la exploración por niveles.
  - *Pilas (LinkedList como pila):* Aplicada en DFS para recorrer el laberinto en profundidad.
  - *Conjuntos (Set - LinkedHashSet)* Para evitar revisitar celdas y mejorar la eficiencia.
  - *Mapas (HashMap):* Para almacenar relaciones entre nodos y sus rutas previas.
  - *Medición de Tiempo (System.nanoTime()):* Para registrar y comparar el tiempo de ejecución de los algoritmos, permitiendo analizar la eficiencia de cada uno en distintos laberintos.

### *Criterio de los Integrantes*
- *Esteban Hernandez*  Implementación de métodos y validaciones  
- *Emanuel León* Desarrollo de interfaz gráfica
- *Sebastian Zurita* Documentación y correción de errores

### *Capturas de Pantalla*
Imágenes de la interfaz implementada  
    *Interfaz Principal*  
    ![Interfaz Principal](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235610254.png?raw=true)
    *Interfaz de la Matriz*  
    ![Interfaz de la Matriz](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235622223.png?raw=true)
    *Poniendo los obstaculos*  
    ![Poniendo los obstaculos](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235630881.png?raw=true)
    *Recorrido con tiempo BFS*  
    ![Recorrido con tiempo BFS](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235636393.png?raw=true)
    *Recorrido con tiempo DFS*  
    ![Recorrido con tiempo DFS](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235641889.png?raw=true)
    *Recorrido con tiempo DP*  
    ![Recorrido con tiempo DP](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235648722.png?raw=true)
    *Recorrido con tiempo Recursivo*  
    ![Recorrido con tiempo Recursivo](https://github.com/Esteban12f/icc-est-u2-recorridosProyecto/blob/main/imagen_2025-02-18_235655290.png?raw=true)
---

## *Conclusiones*
En conclusión la mejor opción para encontrar el camino más corto en un laberinto, entre recursiva, BFS y DFS, es BFS. Esto se debe a que BFS garantiza encontrar el camino más corto en laberintos no ponderados, ya que explora el grafo por niveles, asegurando que primero se expanden los nodos más cercanos al inicio antes de explorar nodos más lejanos. Por otro lado, DFS, aunque útil para explorar exhaustivamente todas las rutas posibles, no asegura que se encuentre el camino más corto, ya que puede llegar rápidamente a un camino largo antes de explorar otras rutas más cortas. En cuánto a programación dinámica ayudó en ciertos casos a optimizar la búsqueda, especialmente al almacenar resultados intermedios y evitar cálculos redundantes. Además, la implementación del patrón MVC permitió una clara separación entre la lógica de búsqueda, la gestión de datos y la interfaz gráfica, facilitando el desarrollo y mantenimiento del sistema.
---

## *Consideraciones*
- Explorar otros algoritmos, como A* o Dijkstra, para mejorar la búsqueda de rutas óptimas, especialmente en laberintos ponderados o con restricciones adicionales, lo que permitiría aplicar una búsqueda más adaptada a diferentes tipos de escenarios.
- Consideración de obstáculos móviles en el laberinto, lo que agregarían complejidad al problema y podría abrir nuevas aplicaciones para la solución en escenarios reales como la robótica.
- Implementar pruebas automatizadas que verifiquen la validez de los algoritmos, garantizando que los resultados sean correctos en una variedad de casos, incluyendo laberintos de diferentes tamaños y complejidades..
- Mejorar la gestión de memoria al optimizar el uso de estructuras de datos, lo cual es especialmente importante cuando se manejan laberintos grandes o con muchas rutas posibles. Esto podría incluir la liberación de memoria innecesaria o el uso de estructuras de datos más eficientes.
- Aplicar compatibilidad multiplataforma, asegurando que el proyecto pueda ejecutarse en diferentes sistemas operativos sin necesidad de modificaciones significativas en el código.
- Ampliación de la interfaz gráfica para incluir funcionalidades como guardar los resultados de la ejecución, modificar el laberinto en tiempo real o configurar parámetros de los algoritmos, como el tamaño de la matriz o el tipo de algoritmo a utilizar.

---

### *Versión del Proyecto*
- *Versión:* 1.0.0
- *Mensaje de versión:* "Proyecto Final – Estructura de Datos"
- *Repositorio:* [https://github.com/Esteban12f/icc-est-u2-recorridosProyecto]

---
