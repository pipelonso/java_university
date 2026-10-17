## Tarea: representación de una red sencilla mediante 

<strong>Resultado de aprendizaje:</strong> implementar un grafo no dirigido mediante una matriz de adyacencia para representar y recorrer las conexiones entre equipos, utilizando Java en una sola clase.

<strong>Saberes cognitivos:</strong> reconocer los vértices y las aristas de un grafo, comprender una matriz de adyacencia e identificar el funcionamiento del recorrido en anchura BFS.

<strong>Saberes procedimentales:</strong> conectar equipos, consultar sus conexiones y recorrer la red utilizando una cola y un arreglo de visitados.

<strong>Saberes actitudinales:</strong> organizar el código, explicar su funcionamiento y corregir los errores encontrados durante las pruebas.

# Descripción de la actividad

Estimado estudiante, cordial saludo.

Desarrolle un programa denominado <strong>Red Sencilla de Equipos</strong>. El programa permitirá crear conexiones entre cinco equipos, mostrar la matriz de conexiones y recorrer la red desde un equipo seleccionado.

Los cinco equipos estarán definidos desde el inicio en un arreglo. No será necesario registrarlos. Cada equipo representa un <strong>vértice</strong> y cada conexión representa una <strong> arista </strong>.

Se utilizará un <strong> grafo no dirigido y no ponderado </strong>: las conexiones funcionan en ambos sentidos y no tienen costos ni distancias.

# Equipos de la red

Guarde los siguientes nombres en un arreglo unidimensional de tipo String[]:

| Número mostrado al usuario | Posición en el arreglo | Equipo |
|:---------------------------|------------------------|--------:|
|1	|0	|Computador|
|2	|1	|Impresora|
|3	|2	|Servidor|
|4	|3	|Enrutador|
|5	|4	|Portátil|

<strong> Recuerde: </strong> el usuario selecciona números del 1 al 5, pero las posiciones del arreglo van del 0 al 4. Después de validar el número, reste 1 para obtener la posición correspondiente.


# Estructuras que debe utilizar



|Estructura	| Función |
|-----------|---------|
|String[] equipos	| Guardar los nombres de los cinco equipos.|
|int[][] conexiones	| Representar el grafo mediante una matriz de adyacencia de 5 filas y 5 columnas.|
|boolean[] visitados	|Identificar los equipos que ya se descubrieron durante el recorrido BFS para evitar repeticiones.|
|Queue<Integer> con LinkedList<Integer>	|Guardar las posiciones de los equipos pendientes de procesar durante BFS.|


# Representación del grafo

La matriz comienza con todos sus valores en cero, porque inicialmente no existen conexiones.

- 0: no existe conexión directa entre los dos equipos.
- 1: existe una conexión directa entre los dos equipos.


Como el grafo es no dirigido, cada conexión debe registrarse en ambos sentidos. Por ejemplo, para conectar el Computador con la Impresora, se asigna el valor 1 en conexiones[0][1] y conexiones[1][0].

No se permiten conexiones de un equipo consigo mismo. Por esta razón, la diagonal principal de la matriz debe permanecer en cero.

## Menú mínimo del prototipo

```

========================================
        RED SENCILLA DE EQUIPOS
========================================

1. Mostrar equipos
2. Conectar dos equipos
3. Mostrar matriz de conexiones
4. Recorrer la red con BFS
5. Salir

Seleccione una opción:


```


## Funcionamiento de cada opción

### 1. Mostrar equipos

Recorrer el arreglo y mostrar los cinco equipos, numerados del 1 al 5.

### 2. Conectar dos equipos

Mostrar los equipos disponibles y solicitar el número del primer equipo y el del segundo. Validar las selecciones y guardar la conexión en ambos sentidos dentro de la matriz.

Si los equipos ya están conectados, informar al usuario. Si elige el mismo equipo dos veces, rechazar la operación.

### 3. Mostrar matriz de conexiones

Mostrar la matriz completa utilizando dos ciclos. Incluir los números del 1 al 5 como encabezados de las filas y las columnas, y mostrar la lista de equipos para facilitar su interpretación.

Si todavía no se han creado conexiones, la matriz se mostrará con todos sus valores en cero.

### 4. Recorrer la red con BFS

Solicitar el número del equipo inicial y realizar un recorrido en anchura. Mostrar los nombres de los equipos en el orden en que se procesan.

1. Crear una cola vacía y un arreglo de visitados con todos sus valores en false para cada recorrido.
2. Marcar el equipo inicial como visitado y agregar su posición a la cola.
Mientras la cola tenga elementos, retirar la primera posición y mostrar el nombre del equipo correspondiente.
3. Revisar la fila de ese equipo en la matriz, desde la posición 0 hasta la 4.
4. Por cada vecino conectado que no esté visitado, marcarlo como visitado y agregarlo a la cola.
5. Finalizar cuando la cola quede vacía.


<strong> Importante: </strong> el recorrido solo mostrará los equipos que puedan alcanzarse desde el equipo inicial, mediante conexiones directas o a través de otros equipos. Si el equipo inicial está aislado, solo se mostrará ese equipo.

### 5. Salir

Mostrar un mensaje de despedida y finalizar el programa.


# Condiciones técnicas obligatorias

- Desarrollar el programa en Java, con entradas y salidas por consola.
- Escribir todo el programa en una sola clase, sin clases adicionales ni clases internas.
- Se permite crear métodos static dentro de la misma clase para organizar las operaciones.
- Utilizar Scanner para leer los datos.
- Implementar el menú con switch-case y repetirlo mediante un ciclo while.
- Representar el grafo mediante una matriz de adyacencia.
- Implementar BFS mediante una cola y un arreglo de visitados.
- Utilizar try-catch para controlar las entradas numéricas incorrectas.
- Comentar los bloques principales del código para explicar su función.


# Validaciones básicas

- Aceptar únicamente opciones del menú del 1 al 5.
- Aceptar únicamente números de equipos del 1 al 5.
- Impedir conexiones de un equipo consigo mismo.
- Informar cuando una conexión ya exista.
- Validar el equipo inicial antes de comenzar el recorrido.
- Evitar que un equipo se agregue varias veces a la cola durante un mismo recorrido.
- Reiniciar la cola y los visitados cada vez que se ejecute BFS.
- Mostrar un mensaje ante una entrada incorrecta y permitir que el programa continúe.

# Prueba de funcionamiento
Cree las siguientes conexiones:


| Primer equipo	| Segundo equipo |
|---------------|----------------|
|1. Computador	|2. Impresora    |
|1. Computador  |3. Servidor     |
|3. Servidor	|4. Enrutador    |

1. Muestre la matriz y verifique que cada conexión aparezca en ambos sentidos.
2. Ejecute BFS desde el equipo 1. Al revisar los vecinos de menor a mayor posición, el resultado debe ser: <strong> Computador, Impresora, Servidor, Enrutador </strong>.
3. Compruebe que el Portátil no aparece en ese recorrido, porque no tiene conexiones.
4. Ejecute BFS desde el equipo 5. Debe aparecer únicamente <strong> Portátil </strong>.
5. Conecte el equipo 4 con el 5 y repita BFS desde el equipo 1. Ahora deben aparecer los cinco equipos.
6. Intente conectar un equipo consigo mismo, repetir una conexión e ingresar un número fuera del rango. El programa debe mostrar los mensajes correspondientes.
7. Ingrese una letra donde se solicita un número. El programa debe controlar el error y continuar.


# Alcance de la actividad

El trabajo comprende cinco equipos definidos desde el inicio, creación de conexiones, visualización de la matriz y un recorrido BFS. No se solicitan registro de incidentes, direcciones IP, eliminación de conexiones, recorrido DFS, rutas mínimas ni conexiones con pesos.

No se requieren bases de datos, archivos ni interfaces gráficas. Las conexiones se conservarán únicamente mientras el programa esté en ejecución.

# Entregable


Entregue únicamente un archivo .java, por ejemplo RedEquipos.java. El nombre del archivo debe coincidir con el nombre de la clase.

El archivo debe contener el programa completo, compilar y ejecutarse correctamente. No se solicitan informes, capturas, videos, presentaciones, archivos PDF ni carpetas comprimidas.

> La actividad puede desarrollarse de manera individual o en grupo, según las indicaciones del docente.

# Criterios de evaluación
- Representación correcta del grafo mediante la matriz.
- Creación de conexiones en ambos sentidos.
- Visualización clara de los equipos y la matriz.
- Funcionamiento de BFS sin repetir equipos.
- Control de entradas incorrectas.
- Organización y comentarios del código en una sola clase.
- Tiempo de dedicación estimado
- 6 horas: 1 hora de repaso y organización, 3 horas de programación y 2 horas de pruebas, comentarios y correcciones.

Recurso de apoyo: Consultar la bibliografía de la unidad .

> ¡Mucho éxito en el desarrollo de su programa!

