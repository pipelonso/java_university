> Estructura de datos (CARMEN RUBIO 24082026_C2_202633) Unidad de Aprendizaje I

> cod: 1261169

# Actividad 2 - Proyecto: sistema de Estructuras Lineales

### Resultado de aprendizaje:

Aplicar estructuras de datos lineales para almacenar, organizar y consultar información mediante el desarrollo de un prototipo empresarial en Java.

Saberes cognitivos: están relacionados con los conceptos y procesos que debe conocer y comprender el estudiante.

Conceptos fundamentales de arreglos, ArrayList, pilas y colas.
Operaciones de inserción, búsqueda, consulta, eliminación y recorrido.
Aplicación de estructuras lineales en situaciones empresariales.
Selección de la estructura apropiada según la información que debe administrarse.
Saberes procedimentales: corresponden a las acciones que debe realizar el estudiante durante el desarrollo de la actividad.

Desarrollar un sistema empresarial mediante estructuras de datos lineales.
Registrar empleados, equipos y solicitudes de soporte.
Utilizar una cola para organizar las solicitudes pendientes.
Utilizar una pila para guardar las solicitudes solucionadas.
Validar los datos ingresados mediante ciclos y manejo de excepciones.
Probar el funcionamiento del programa mediante la consola.
Saberes actitudinales: corresponden a las actitudes que debe demostrar el estudiante durante el desarrollo del prototipo.

Responsabilidad en el desarrollo y entrega del programa.
Organización y claridad en la escritura del código.
Precisión en el registro y consulta de la información.
Disposición para identificar y corregir errores.
Uso responsable de la información empresarial.
Descripción de la actividad

Estimado estudiante, cordial saludo.

En esta actividad deberá desarrollar un prototipo denominado Sistema de Gestión de Soporte Técnico Empresarial. El programa permitirá registrar empleados, equipos tecnológicos y solicitudes de soporte, además de organizar los casos pendientes y conservar un historial de los servicios solucionados.

Cada estudiante o grupo deberá crear un nombre original para la empresa. Este nombre debe aparecer en el encabezado del programa cada vez que se muestre el menú principal en la consola.

Para desarrollar el prototipo se deben utilizar arreglos, ArrayList, colas y pilas. Cada estructura debe participar en una operación funcional del sistema.

Todo el programa debe desarrollarse en Java, dentro de una sola clase y mediante entradas y salidas por consola. La entrega corresponde únicamente a un archivo con extensión .java.

Condiciones técnicas obligatorias

- El programa debe desarrollarse únicamente en Java.
- Todo el código debe estar contenido dentro de una sola clase.
- El programa debe funcionar mediante entradas y salidas por consola.
- El menú debe implementarse mediante switch-case.
- El menú debe repetirse mediante un ciclo while.
- Se debe utilizar Scanner para capturar los datos.
- Se debe utilizar try-catch para controlar entradas numéricas incorrectas.
- Cada línea del código debe estar comentada para explicar su función.
- La entrega debe contener únicamente un archivo con extensión .java.
- El nombre del archivo debe coincidir con el nombre de la clase.
- No se permiten interfaces gráficas, páginas web ni bases de datos.
- No se aceptará el proyecto en otro lenguaje de programación.
- No se deben entregar documentos, informes, presentaciones ni archivos PDF.

## Estructuras lineales obligatorias


|Estructura	|Aplicación dentro del prototipo|
|---|---|
| Arreglo |	Almacenar las áreas de la empresa, categorías de los problemas o niveles de     prioridad.|
| ArrayList |	Almacenar la información de los empleados y equipos registrados.|
| Cola |	Organizar las solicitudes pendientes según su orden de llegada.|
| Pila |	Almacenar las solicitudes atendidas y solucionadas.|


> No será suficiente declarar las estructuras. Cada una debe utilizarse correctamente dentro de las operaciones del programa.

# Información que debe administrar el sistema

### 1. Datos de los empleados

- Código del empleado.
- Nombre completo.
- Área de trabajo.
- Correo electrónico.

### 2. Datos de los equipos

- Código del equipo.
- Tipo de equipo.
- Marca.
- Código del empleado responsable.
- Estado del equipo.

### 3. Datos de las solicitudes

- Código de la solicitud.
- Código del empleado.
- Código del equipo.
- Descripción del problema.
- Nivel de prioridad.
- Estado de la solicitud.


### Estados de las solicitudes

- Pendiente.
- En atención.
- Solucionada.


### Funcionamiento esperado

- El usuario registra los empleados de la empresa.
- El usuario registra los equipos y los asigna a empleados existentes.
- El sistema permite crear solicitudes relacionadas con empleados y equipos registrados.
- Cada solicitud se agrega a la cola de casos pendientes.
- Al atender una solicitud, se retira el primer caso de la cola.
- La solicitud atendida cambia su estado a “Solucionada”.
- La solicitud solucionada se guarda en la pila.


##### Menú mínimo del prototipo

```

========================================================
                 NOMBRE DE LA EMPRESA
         SISTEMA DE SOPORTE TÉCNICO EMPRESARIAL
========================================================

1. Registrar empleado
2. Registrar equipo
3. Crear solicitud
4. Consultar registros
5. Atender siguiente solicitud
6. Mostrar solicitudes pendientes
7. Mostrar solicitudes solucionadas
8. Salir
                

```

El nombre mostrado en el encabezado debe ser creado por el estudiante o grupo. No se debe dejar el texto “Nombre de la empresa” como nombre definitivo.

La opción “Consultar registros” permitirá buscar empleados, equipos y solicitudes mediante sus respectivos códigos. También permitirá mostrar los empleados y equipos registrados.

### Validaciones obligatorias

- Impedir códigos de empleados duplicados.
- Impedir códigos de equipos duplicados.
- Impedir códigos de solicitudes duplicados.
- Evitar campos vacíos mediante ciclos while.
- Verificar que el empleado exista antes de asignarle un equipo.
- Verificar que el empleado y el equipo existan antes de crear una solicitud.
- Impedir la atención de solicitudes cuando la cola esté vacía.
- Controlar entradas numéricas incorrectas mediante try-catch.
- Mostrar mensajes claros cuando las estructuras estén vacías.
- Evitar que el programa termine cuando el usuario ingrese un dato incorrecto.
- Instrucciones para el desarrollo

-- 

- Cree un nombre original para la empresa.
- Declare las estructuras lineales dentro de la única clase.
- Implemente el registro de empleados y equipos.
- Implemente la creación y consulta de solicitudes.
- Utilice una cola para organizar los casos pendientes.
- Utilice una pila para almacenar los casos solucionados.
- Construya el menú mediante switch-case.
- Mantenga activo el menú mediante un ciclo while.
- Incluya todas las validaciones solicitadas.
- Comente cada línea del código explicando su función.
- Pruebe todas las opciones antes de realizar la entrega.
- Esta actividad permitirá aplicar las estructuras lineales en una situación empresarial real, reconociendo la utilidad de las listas, arreglos, colas y pilas para organizar y consultar información.

¡Mucho éxito en el desarrollo del Sistema de Soporte Técnico Empresarial!

La actividad puede desarrollarse de manera individual o mediante trabajo colaborativo, según las indicaciones del docente.