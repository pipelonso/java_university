# Tarea: registro sencillo de productos mediante un árbol

> Resultado de aprendizaje: implementar un árbol binario de búsqueda en Java para registrar, buscar y mostrar productos, con todo el programa en una sola clase.

> Saberes cognitivos: identificar la raíz, los nodos y los hijos de un árbol binario de búsqueda, y comprender las operaciones de inserción, búsqueda y recorrido inorden.

> Saberes procedimentales: representar un árbol mediante arreglos paralelos, insertar productos según su código, buscar un producto y mostrar los registros ordenados.

> Saberes actitudinales: escribir código organizado, explicar su funcionamiento y corregir los errores encontrados durante las pruebas.


## Descripción de la actividad

Estimado estudiante, cordial saludo.


Desarrolle un programa denominado Registro de Productos con Árbol Binario. El programa permitirá registrar productos, buscarlos por su código y mostrarlos ordenados de menor a mayor código.


Para cada producto solo deberá solicitar un código entero positivo y un nombre. Por ejemplo: código 50, nombre Cuaderno.


Se utilizará un solo árbol binario de búsqueda. Los productos con códigos menores se ubicarán a la izquierda y los productos con códigos mayores a la derecha. No se permitirán códigos repetidos.


### Representación del árbol en una sola clase
El árbol se representará mediante cuatro arreglos unidimensionales paralelos, con capacidad para 20 productos. Una misma posición en los cuatro arreglos representa un nodo.


| Elemento	| Función|
| :----------- |-------------:|
| int[] codigos |	Guardar el código de cada producto. |
String[] nombres	| Guardar el nombre de cada producto.
| int[]  izquierdo |	Guardar la posición del hijo izquierdo de cada nodo. |
|int[] derecho	| Guardar la posición del hijo derecho de cada nodo.|
|int raiz	| Guardar la posición del nodo raíz. Su valor inicial será -1 porque el árbol está vacío.|
|int cantidad	| Contar los productos registrados e indicar la siguiente posición libre. Inicia en 0.|


> Importante: los arreglos izquierdo y derecho guardan posiciones, no códigos de productos. El valor -1 indica que no existe un hijo. Al crear cada nodo, sus dos hijos deben iniciar en -1.

> No necesita crear una clase Nodo ni clases adicionales. Puede utilizar métodos static dentro de la misma clase para insertar, buscar y recorrer el árbol. Los arreglos y las variables compartidas pueden declararse como atributos static.



```
========================================
         REGISTRO DE PRODUCTOS
       ÁRBOL BINARIO DE BÚSQUEDA
========================================

1. Registrar producto
2. Buscar producto por código
3. Mostrar productos ordenados
4. Salir

Seleccione una opción:

```

## Funcionamiento de las opciones

1. Registrar producto

Solicitar un código entero positivo y el nombre del producto. Verificar que el código no exista y que haya espacio disponible. Insertar el producto respetando el orden del árbol.

- El primer producto registrado será la raíz.
- Si el código es menor que el del nodo actual, continuar por el hijo izquierdo.
- Si el código es mayor, continuar por el hijo derecho.
- Al encontrar un enlace con valor -1, colocar allí la posición del nuevo nodo.
- Aumentar el contador únicamente cuando el registro se complete correctamente.

2. Buscar producto por código

Solicitar el código y buscarlo desde la raíz. Comparar el código solicitado con el del nodo actual para decidir si continúa por la izquierda o por la derecha. Si lo encuentra, mostrar su código y nombre. Si llega a una posición -1, informar que el producto no existe.

3. Mostrar productos ordenados

Realizar un recorrido <strong> inorden </strong>: primero visitar el hijo izquierdo, después mostrar el nodo actual y finalmente visitar el hijo derecho. Así se mostrarán los productos de menor a mayor código.

Implemente este recorrido mediante un método recursivo dentro de la misma clase. El método debe detenerse cuando reciba la posición -1. Para iniciar el recorrido, debe recibir la posición de la raíz.

4. Salir

Mostrar un mensaje de despedida y finalizar el programa.

## Condiciones técnicas obligatorias

- Desarrollar el programa en Java, dentro de una sola clase, sin clases adicionales ni clases internas.
- Utilizar Scanner para capturar los datos.
- Implementar el menú mediante switch-case y repetirlo mediante un ciclo while.
- Representar el árbol con los cuatro arreglos indicados.
- Realizar la inserción y la búsqueda siguiendo los enlaces izquierdo y derecho del árbol.
- Implementar únicamente el recorrido inorden, mediante recursividad.
- Controlar entradas numéricas incorrectas mediante try-catch.
- Comentar los bloques principales del programa para explicar su función.

<strong> Guardar productos en los arreglos y ordenarlos con un método de ordenamiento no reemplaza la construcción del árbol. </strong> Debe utilizar la raíz y los enlaces de los hijos para insertar, buscar y recorrer.

## Validaciones básicas

- Aceptar únicamente códigos enteros mayores que cero.
- Impedir códigos duplicados.
- No aceptar nombres vacíos o compuestos solo por espacios.
- Impedir nuevos registros cuando se alcancen 20 productos.
- Mostrar un mensaje si se intenta buscar o recorrer un árbol vacío.
- nformar cuando un código buscado no exista.
- Controlar opciones fuera del menú y entradas no numéricas, sin finalizar el programa por error.


## Prueba de funcionamiento

Registre los siguientes productos en este orden:

| Orden de registro |	Código|	Producto|
|-------------------|---------|---------| 
|1	|50|	Cuaderno|
|2	|30|	|Lápiz|
|3	|70|	Carpeta|
|4	|40|	Borrador|


El código 50 debe quedar en la raíz; el 30, como hijo izquierdo de 50; el 70, como hijo derecho de 50; y el 40, como hijo derecho de 30.

1. Busque el código 30. Debe aparecer el producto Lápiz.
2. Busque el código 90. Debe aparecer un mensaje indicando que no existe.
3. Muestre los productos ordenados. Los códigos deben aparecer en este orden: 30, 40, 50, 70, cada uno acompañado de su nombre.
4. Intente registrar nuevamente el código 50. El programa debe rechazarlo.
5. Pruebe un código negativo, un nombre vacío y una letra donde se solicita un número. El programa debe mostrar un mensaje y continuar.

## Alcance de la actividad

El programa incluye un árbol, dos datos por producto y cuatro opciones de menú. No se solicitan eliminación de nodos, balanceo, recorridos preorden o postorden, gestión de pedidos, listas, pilas ni colas. Tampoco se requieren archivos, bases de datos o interfaces gráficas.

La información se conservará únicamente mientras el programa esté en ejecución.

## Entregable

Entregue únicamente un archivo .java, por ejemplo RegistroProductos.java. El nombre del archivo debe coincidir con el de la clase.

El archivo debe contener el programa completo, compilar y ejecutarse correctamente. No se solicitan informes, videos, capturas, presentaciones, archivos PDF ni carpetas comprimidas.

La actividad puede desarrollarse de manera individual o en grupo, según las indicaciones del docente.


## Criterios de evaluación
- Representación correcta del árbol mediante arreglos.
- Inserción de productos respetando el orden del árbol.
- Búsqueda mediante los enlaces izquierdo y derecho.
- Recorrido inorden con resultados ordenados.
- Funcionamiento del menú y las validaciones.
- Organización del código en una sola clase.


Tiempo de dedicación estimado

6 horas: 1 hora de repaso y organización, 3 horas de programación y 2 horas de pruebas, comentarios y correcciones.

