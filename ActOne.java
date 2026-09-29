import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

// Desarrollado en openjdk 21.0.12.1 2026-08-18
// Autor: Andres Felipe Ibañez Cuta - 100216824
// Autor: Julio Cesar González Hernández - 100219419

/**
 * 
 * 2026-09-28
 * Notas de desarrollo: Para este desarrollo se hicieron llamadas
 * por google meet mientras uno de nosotros escribía el codigo 
 * y los dos dabamos ideas de como implementarlo.
 * 
 * Como entorno de desarrollo se utilizó Visual Studio Code por su
 * facilidad para el manejo de git.
 * 
 * El proyecto fue desarrollado desde linux usando openjdk-21
 * que viene incluido por defecto en la distrubución MX linux
 * 
 * Puede encontrar el historial de commits en el repositorio de github:
 * https://github.com/pipelonso/java_university
 * 
 * Los caracteres especiales fueron tomados del sitio:
 * https://www.adslzone.net/como-se-hace/internet/simbolos-copiar-pegar/
 * 
 */


class Main {
    /**
     * Se opto por usar una clase principal para iniciar la aplicación
     * esto con el fin de mantener un orden de ejecución ya que es considerado
     * buena practica tener un punto de entrada único para la ejecución de la aplicación.
     */
    public static void main(String[] args) {
        Application app = new Application(); // arranque de aplicación
        app.run(); // ejecución del bucle principal de la aplicación
    }
}

class Equipment { // clase usada como modelo de datos para el registro de equipos

    public String code;
    public String type;
    public String employeeCode;
    public String state;
    public String brand;

    public Equipment() {}

}

class Employee { // clase usada como modelo de datos para el registro de empleados

    public String name;
    public String email;
    public String area;
    public String code;

    public Employee() {}

}

class Request { // clase usada como modelo de datos para el registro de solicitudes

    public String code;
    public String employeeCode;
    public String equipmentCode;
    public String priority;
    public String description;
    public String state;

    public Request() {}

}

class Application { // Aplicación principal que contiene la lógica de negocio y el flujo de ejecución del programa
    
    public String[] requestStates; // declaración de los estados de las solicitudes - vacía por defecto, se llenará en el constructor
    public String[] companyAreas; // declaración de las áreas de la empresa - vacía por defecto, se llenará en el constructor
    public boolean onMainLoop;
    public ArrayList<Employee> employees = new ArrayList<>(); // declaración de la lista de empleados
    public ArrayList<Equipment> equipment = new ArrayList<>(); // declaración de la lista de equipos

    private final Queue<Request> pendingRequest = new ArrayDeque<>(); // declaración de la cola de solicitudes pendientes

    private final Deque<Request> completeRequest = new ArrayDeque<>(); // declaración de la pila de solicitudes completadas

    private final Scanner scanner; // declaración del objeto Scanner para leer la entrada del usuario de forma global

    private boolean isTestDataGenerated = false; // Esta variable evalua si la data de prueba ya fue generada para evitar duplicados

    public Application() {
        this.scanner = new Scanner(System.in); // instancia del scanner para leer la entrada del usuario
        this.onMainLoop = true; // variable que controla el bucle principal de ejecución de la aplicación

        this.requestStates = new String[] { // inicialización de los estados de las solicitudes
            "Pendiente",
            "En atención",
            "Solucionada"
        };

        this.companyAreas = new String[] { // inicialización de las áreas de la empresa
            "Soporte",
            "Ventas",
            "Administración",
            "Recursos Humanos",
            "Infraestructura",
            "Desarrollo",
            "Marketing",
            "Diseño",
            "Finanzas",
            "Legal",
            "Logística",
            "Calidad",
            "Seguridad",
            "Atención al cliente"
        };
    }

    public void run() { // metodo de arranque de la aplicación, contiene el bucle principal de ejecución
        while (onMainLoop) {
            showMenu(); // funcion que muestra el menú principal de la aplicación
            int option = readIntInRange(1, 10, "Seleccione una opción válida (1-10): "); // seleccion de opción del menú principal - basado en rango
            processMenuOption(option); // Esto procesa la entrada del usuario
        }
        
        this.scanner.close();
    }

    public int readIntInRange(int min, int max, String prompt) { // metodo para leer un entero dentro de un rango específico, con manejo de errores y validación
        int option = -1;
        boolean isValidOption = false; // variable confirma estado de si la opción ingresada es válida

        while (!isValidOption) {
            System.out.print(prompt); // muestra del texto enviado como parámetro para solicitar la entrada del usuario
            try {
                option = scanner.nextInt(); // petición de entrada del usuario numerica

                if (option >= min && option <= max) { // validación de rango
                    isValidOption = true; // esto dice que lo que puso el usuario esta bien 
                } else {
                    throw new IllegalArgumentException("Número fuera del rango permitido."); // Fallo - Error - Explosion de excepción - Se lanza una excepción para indicar que el número ingresado está fuera del rango permitido
                }

            } catch (Exception e) { // captura de excepción en caso de fallo - por ejemplo cuando ingresan una letra en vez de un numero
                System.out.println("Error: Debe ingresar un entero entre " + min + " y " + max + ".\n"); // mensaje de error para el usuario
                scanner.nextLine(); // limpieza del buffer de entrada para evitar un bucle infinito en caso de error
            }
        }

        return option; // retorno del valor ingresado por el usuario, ya validado y dentro del rango permitido

    }

    /**
     * Ya que se habpia requerido que se usara un switch para el menú principal, 
     * se implementó este método que recibe la opción seleccionada por el usuario 
     * y ejecuta la acción correspondiente.     
     * 
     * Se busco la forma de hacerlo mas compato y 
     * legible usando la sintaxis de switch moderna 
     * de Java (->) en lugar de los tradicionales case y break que
     * me parecen mas dificiles de leer.
     *  
     */
    private void processMenuOption(int option) { // metodo que procesa la opción seleccionada por el usuario en el menú principal
        switch (option) {
            case 1 -> {requestRegisterEmployee();}
            case 2 -> {requestRegisterEquipment();}
            case 3 -> {requestCreateRequest();}
            case 4 -> {requestShowRecords();}
            case 5 -> {requestAttendNextRequest();}
            case 6 -> {viewPendingRequests();}
            case 7 -> {requestCompleteRequests();}
            case 8 -> {generateTestData();}
            case 9 -> {showCreators();}
            case 10 -> {
                System.out.println("Saliendo del sistema...");
                this.onMainLoop = false;
            }
            default -> System.out.println("Opción no implementada."); // en teoria esto nunca debería aparecer debido a las validaciones anteriores
        }
    }

    public void showMenu() { // Método que muestra el menú principal de la aplicación
        System.out.println(makeTitle());
        System.out.println("1. Registrar empleado");
        System.out.println("2. Registrar equipo");
        System.out.println("3. Crear solicitud");
        System.out.println("4. Consultar registros");
        System.out.println("5. Atender siguiente solicitud");
        System.out.println("6. Mostrar solicitudes pendientes");
        System.out.println("7. Mostrar solicitudes solucionadas");
        System.out.println("8. Generar datos de prueba");
        System.out.println("9. Mostrar creadores de la app");
        System.out.println("10. Salir");
    }

    public void showCreators() { // metodo que nos muestra como creadores de la app - propuesto por Julio
        
        System.out.println("""
            =======================================================
                     Creadores de la app / Estudiantes
            =======================================================
            """);

        System.out.println("╭┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉|");
        System.out.println("|Andres Felipe Ibañez Cuta 100216824         |");
        System.out.println("|Julio cesar González Hernández 100219419    |");
        System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉|");

        scanner.nextLine();
        pauseScreen();

    }

    public void generateTestData() { // Este metodo la va a ayudar a generar data de pruebas

        if (isTestDataGenerated) { // validación global que verifica si los datos de prueba ya fueron generados
            System.out.println("Los datos de prueba ya han sido generados.");
            pauseScreen(); // pantalla de pausa para que el usuario pueda leer el mensaje antes de continuar
            return;
        }

        // Generar empleados de prueba
        Employee emp1 = new Employee();
        emp1.name = "Juan Pérez";
        emp1.email = "juanperez@gmail.com";
        emp1.area = "Soporte";
        emp1.code = "EMP001";
        employees.add(emp1);
        
        Employee emp2 = new Employee();
        emp2.name = "Andres Ibañez";
        emp2.email = "andresibañez@gmail.com";
        emp2.area = "Soporte";
        emp2.code = "EMP002";
        employees.add(emp2);

        Employee emp3 = new Employee();
        emp3.name = "Julio Gonzalez";
        emp3.email = "juliogonzalez@gmail.com";
        emp3.area = "Soporte";
        emp3.code = "EMP003";
        employees.add(emp3);

        // Generar equipos de prueba

        Equipment eq1 = new Equipment();
        eq1.code = "EQ001";
        eq1.type = "Laptop";
        eq1.state = "En buen estado";
        eq1.brand = "Dell";
        eq1.employeeCode = "EMP001";
        equipment.add(eq1); 

        Equipment eq2 = new Equipment();
        eq2.code = "EQ002";
        eq2.type = "Monitor";
        eq2.state = "En buen estado";
        eq2.brand = "Samsung";
        eq2.employeeCode = "EMP002";
        equipment.add(eq2);

        Equipment eq3 = new Equipment();
        eq3.code = "EQ003";
        eq3.type = "Teclado";
        eq3.state = "En buen estado";
        eq3.brand = "Logitech";
        eq3.employeeCode = "EMP003";    
        equipment.add(eq3);

        isTestDataGenerated = true;
        System.out.println("Datos de prueba generados exitosamente.");

        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine(); // limpiar entrada
        scanner.nextLine(); // esperar a que el usuario presione Enter
        System.out.println("-----------------------------------\n");


    }

    public String requestCompleteRequests() { // Este metodo muestra las solicitudes ya completadas
        System.out.println("""
            =======================================================
                     SOLICITUDES SOLUCIONADAS (PILA - LIFO)
            =======================================================
            """);

        if (completeRequest.isEmpty()) {
            System.out.println("Aún no se ha solucionado ninguna solicitud.");
            pauseScreen();
            return "";
        }

        int index = 1;

        for (Request request : completeRequest) { // bucle que muestra las solicitudes - equivalente a un foreach
            System.out.println("╭┉( Histórico #" + index + " )");
            System.out.println("| Código de Solicitud: " + request.code);
            System.out.println("| Código de Empleado:  " + request.employeeCode);
            System.out.println("| Código de Equipo:    " + request.equipmentCode);
            System.out.println("| Prioridad:           " + request.priority);
            System.out.println("| Estado:              " + request.state);
            System.out.println("| Descripción:         " + request.description);
            System.out.println("╰┉");
            index++;
        }

        System.out.println("Total de solicitudes solucionadas: " + completeRequest.size()); // mensaje que muestra el total de solicitudes solucionadas
        pauseScreen(); // espera para que el usuario pueda leer
        return "";
    }

    public boolean requestAttendNextRequest() { // metodo para poder atender una solicitud de usuario
        System.out.println("""
            =======================================================
                        ATENDER SIGUIENTE SOLICITUD
            =======================================================
            """);

        Request requestToAttend = pendingRequest.poll();

        if (requestToAttend == null) { // validación de solicitudes en cola
            System.out.println("No hay solicitudes pendientes en la cola.");
            pauseScreen();
            scanner.nextLine(); // limpiar entrada
            return false; // Este retorno solo sirve para evitar que el proceso continue 
        }

        System.out.println("Procesando la siguiente solicitud en cola...");
        System.out.println("╭┉ Código: " + requestToAttend.code);
        System.out.println("| Empleado: " + requestToAttend.employeeCode);
        System.out.println("| Equipo: " + requestToAttend.equipmentCode);
        System.out.println("| Prioridad: " + requestToAttend.priority);
        System.out.println("| Descripción: " + requestToAttend.description);
        System.out.println("╰┉ Estado previo: " + requestToAttend.state);

        requestToAttend.state = requestStates[2];  // esto cambia la solicitud a completada

        completeRequest.push(requestToAttend); // aquí se añade la solicitud ya completada

        System.out.println("\n¡Solicitud atendida y marcada como 'Solucionada' correctamente!");
        System.out.println("La solicitud fue enviada al registro histórico (Pila de completadas).");
        pauseScreen(); // espera de usuario
        scanner.nextLine(); // limpiar entrada
        return true;
    }

    /**
     * Este metodo realmente esta pensando para mostrar los datos ya definidos al iniciar
     */
    public void requestShowRecords() { // Esto despliega otro menu para mirar los registros del sistema
        
        System.out.println("""
        ========================================================
                          REGISTROS DEL SISTEMA
        ========================================================
        """);

        System.out.println("1) Mostrar empleados registrados");
        System.out.println("2) Mostrar equipos registrados");
        System.out.println("3) Mostrar areas de la empresa");

        int response = readIntInRange(1, 3, "Seleccione una opción válida (1-3): "); // obtenee input de usuario de 1 a 3

        switch (response) {
            case 1 -> {
                showEmployees();
            }
            case 2 -> {
                showEquipment();
            }
            case 3 -> {
                showCompanyAreas();
            }
            default -> {
                System.out.println("Opción no implementada.");
            }
        }


    }

    public void showEquipment() { // esto muestra los equipos registrados 

        System.out.println("""
            
            ==============================================
            =           Equipos registrados:             =
            ==============================================

        """);

        for (int i = 0; i < equipment.size(); i++) {
            Equipment eq = equipment.get(i);
            System.out.println("╭┉( " + (i + 1) + " )");
            System.out.println("| Código: " +  eq.code + " \n| Tipo: " + eq.type + " \n| Estado: " + eq.state + " \n| Marca: " + eq.brand + " \n| Código de empleado asignado: " + eq.employeeCode);
            System.out.println("╰┉");
        }

        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    public void showEmployees() { // Esto muestra los empleados registrados
        
         System.out.println("""
            
            ==============================================
            =           Empleados registrados:           =
            ==============================================

        """);

        for (int i = 0; i < employees.size(); i++) { // Estructura comun en bucles <declaración> <condición> <incremento>
            Employee employee = employees.get(i); // obtención de objeto usando indice
            System.out.println("╭┉( " + (i + 1) + " )");
            System.out.println("| Nombre: " +  employee.name + " \n| Correo: " + employee.email + " \n| Área: " + employee.area + " \n| Código: " + employee.code);
            System.out.println("╰┉");
        }

        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    public void showCompanyAreas() {

        System.out.println("""
            
            ==============================================
            =           Áreas de la empresa:             =
            ==============================================

        """);

        for (int i = 0; i < companyAreas.length; i++) { // Esto imprime las areas de la empresa, es mas simple por que solo son strings[]
            System.out.println((i + 1) + ". " + companyAreas[i]);
        }

        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    public String makeTitle() { // Este metodo muestra el titulo. Se llama así por (Julio - Andres) = (Jul - An)
        return """
        ========================================================
                          JULAN SOFTWARE
               SISTEMA DE SOPORTE TÉCNICO EMPRESARIAL
        ========================================================
        """;
    }

    public void requestCreateRequest() {  // Este metodo crea solicitudes siempre que halla empleados

    System.out.println("""
            =======================================================
                          CREACIÓN DE SOLICITUDES
            =======================================================
            """);

        if (employees.isEmpty()) { // Si no hay empleados no pueden haber solicitudes 
            System.out.println("Error: No se pueden crear solicitudes sin empleados registrados.");
            pauseScreen();
            scanner.nextLine(); // limpiar entrada
            return;
        }

        scanner.nextLine(); // Limpiar el buffer de entrada

        Request request = new Request();

        boolean isCodeValid = false;
        while (!isCodeValid) {
            System.out.print("Ingrese el código de la solicitud: ");
            String code = scanner.nextLine().trim();

            if (code.isEmpty()) {
                System.out.println("El código no puede estar vacío.");
                continue;
            }

            // Esto busca si ya hay peticiones completas y pendientes
            // Se busco un recurso en internet para buscar como filtrar de forma efectiva
            // y mas rapida posible
            boolean existsInPending = pendingRequest.stream().anyMatch(r -> r.code.equalsIgnoreCase(code));
            boolean existsInComplete = completeRequest.stream().anyMatch(r -> r.code.equalsIgnoreCase(code));

            // En el requerimiento se especificó que fuera validada el codigo de las solicitudes

            if (existsInPending || existsInComplete) {
                System.out.println("El código de la solicitud ya está registrado. Intente con otro.");
            } else {
                request.code = code;
                isCodeValid = true;
            }
        }

        boolean isEmployeeFound = false;
        while (!isEmployeeFound) {
            System.out.print("Ingrese el código del empleado que realiza la solicitud: ");
            String empCode = scanner.nextLine().trim();

            boolean exists = employees.stream().anyMatch(e -> e.code.equalsIgnoreCase(empCode)); // esto verifica que el empleado existe antes de asignar una solicitud
            if (exists) {
                request.employeeCode = empCode;
                isEmployeeFound = true;
            } else {
                System.out.println("Empleado no encontrado. Verifique los registros.");
            }
        }

        // Se hizo que el quipo fuera opcional porque de alguna manera tiene sentido ya que no todas las solicitudes pudieron haber pasado en un equipo
        System.out.print("Ingrese el código del equipo involucrado (Opcional, presione Enter para omitir): ");
        String eqCode = scanner.nextLine().trim();
        if (!eqCode.isEmpty()) {
            boolean exists = equipment.stream().anyMatch(e -> e.code.equalsIgnoreCase(eqCode));
            request.equipmentCode = exists ? eqCode : "N/A"; // Esto de aquí es un operador ternario, los uso mucho en php y python
            if (!exists) {
                System.out.println("Equipo no encontrado. Se registrará como 'N/A'.");
            }
        } else {
            request.equipmentCode = "N/A";
        }

        System.out.println("\nSeleccione el nivel de prioridad:");
        System.out.println("1. Alta\n2. Media\n3. Baja");
        int priorityOpt = readIntInRange(1, 3, "Seleccione la prioridad (1-3): ");
        request.priority = switch (priorityOpt) { // esto de aquí sigue la misma estructura del menu pero al ser un string lo romará como valor de retorno
            case 1 -> "Alta";
            case 2 -> "Media";
            default -> "Baja";
        };

        scanner.nextLine();

        System.out.print("Ingrese la descripción del problema: ");
        request.description = scanner.nextLine().trim();
        request.state = requestStates[0]; // "Pendiente" Esto asigna la solicitud como pendiente de realizar

        pendingRequest.offer(request);

        System.out.println("\nSolicitud agregada exitosamente a la cola de atención.");
        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    private void pauseScreen() { // Aqui el metodo para que el usuario pueda leer con mas calma
        System.out.println("\nPresione cualquier tecla para continuar...");
        scanner.nextLine();
        System.out.println("-----------------------------------\n");
    }

    public void viewPendingRequests() { // este metodo muestra las solicitudes en cola
        
        System.out.println("""
            =======================================================
                        SOLICITUDES PENDIENTES (COLA)
            =======================================================
            """);

        if (pendingRequest.isEmpty()) { // verificaciones de si la lista esta vacía
            System.out.println("No hay solicitudes pendientes por atender.");
            pauseScreen();
            return;
        }

        int index = 1;
        for (Request request : pendingRequest) { // esto es un equivalente a unn foreach para mostrar las solicitudes
            System.out.println("╭┉( Turno #" + index + " )");
            System.out.println("| Código de Solicitud: " + request.code);
            System.out.println("| Código de Empleado:  " + request.employeeCode);
            System.out.println("| Código de Equipo:    " + request.equipmentCode);
            System.out.println("| Prioridad:           " + request.priority);
            System.out.println("| Estado:              " + request.state);
            System.out.println("| Descripción:         " + request.description);
            System.out.println("╰┉");
            index++;
        }

        System.out.println("Total de solicitudes en espera: " + pendingRequest.size());
        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    public boolean requestRegisterEmployee() {

        System.out.println("""
            =======================================================
                          REGISTRO DE EMPLEADOS
            =======================================================

            """);

        scanner.nextLine(); // limpiar entrada

        Employee newEmployee = new Employee();

        System.out.print("Ingrese el nombre del empleado: ");

        String name = scanner.nextLine();
        newEmployee.name = name;

        System.out.print("Ingrese el correo del empleado: ");
        String email = scanner.nextLine();
        newEmployee.email = email;

        /*
            Esta parte es genial porque muestra el menu de forma dinamica
        */

        System.out.println("""
        -> Seleccionar area
        ╭───────────────────────────────
        """);

        for (int i = 0; i < companyAreas.length; i++) {
            System.out.println("├ " + (i + 1) + ". " + companyAreas[i]);
        }

        System.out.println("""  
        ╰───────────────────────────────
        """);

        int areaIndex = readIntInRange(1, companyAreas.length, "Seleccione un área válida (1-" + companyAreas.length + "): ") - 1;
        String area = companyAreas[areaIndex];

        newEmployee.area = area;

        boolean isCodeValid = false;
        String code = "";

        scanner.nextLine(); // limpiar entrada

        while (!isCodeValid) {

            System.out.print("Ingrese el código del empleado: ");
            
            code = scanner.nextLine();

            boolean codeExists = false;

            for (Employee employee : employees) { // este bucle es para verificar si un usuario ya existe
                if (employee.code.equals(code)) {
                    System.out.println("El código ya está en uso. Por favor, ingrese un código diferente.");
                    codeExists = true;
                    break;
                }
            }

            if (!codeExists) {
                newEmployee.code = code;
                isCodeValid = true;
            }

        }

        employees.add(newEmployee);

        System.out.println("Empleado registrado exitosamente.");
        pauseScreen();
        scanner.nextLine(); // limpiar entrada
        return false;
    }

    public boolean requestRegisterEquipment() { // metodo para registrar equipos


        System.out.println("""
            =======================================================
                          REGISTRO DE EQUIPOS
            =======================================================

            """);

            String code = "";
            boolean isCodeValid = false;

            while (!isCodeValid) {

                System.out.println("Ingrese el código del equipo: ");
                code = scanner.nextLine();

                boolean codeExists = false;

                for (Equipment equipment : equipment) {
                    if (equipment.code.equals(code)) {
                        System.out.println("El código ya está en uso. Por favor, ingrese un código diferente.");
                        codeExists = true;
                        break;
                    }
                }

                if (!codeExists) {
                    isCodeValid = true;
                }

            }

            System.out.println("Ingrese el tipo de equipo: ");
            String type = scanner.nextLine();

            System.out.println("Describa el estado del equipo: ");
            String state = scanner.nextLine();

            System.out.println("Ingrese la marca del equipo: ");
            String brand = scanner.nextLine();

            Equipment newEquipment = new Equipment();
            newEquipment.code = code;
            newEquipment.type = type;
            newEquipment.state = state;
            newEquipment.brand = brand;

            if (employees.size() == 0) {
                System.out.println("No hay empleados registrados. Por favor, registre un empleado antes de asignar un equipo.");
                return false;
            }

            boolean isEmployeeFound = false;

            while (!isEmployeeFound) {

                /*
                    Este bucle es genial porque puedes buscar un empleado.

                    Julio fue el que propuso la idea, porque era molesto tener que memorizar
                    los codigos de los empleados, entonces es mas facil saber una parte del codigo
                    en vez de saber el codigo completo
                */

                System.out.println("Digite un codigo de empleado para buscar: ");
                String employeeCode = scanner.nextLine();

                ArrayList<Employee> employeesFound = new ArrayList<>();

                for (Employee employee : employees) {
                    if (employee.code.contains(employeeCode)) {
                        employeesFound.add(employee);
                    }
                }

                if (employeesFound.size() > 0) {
                    System.out.println("Empleados encontrados:");

                    int idx = 0;

                    for (Employee employee : employeesFound) { // este bucle muestra los empleados despues de encontrarlos
                        idx++;
                        System.out.println("╭┉( " + idx + " )┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");
                        System.out.println("Código: " + employee.code + "\nNombre: " + employee.name + "\nÁrea: " + employee.area);
                        System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉\n");
                    }
                    
                    // seccion de empleado pero tomando como limite el numero de empleados registrados de forma dinamica
                    int selectedEmployeeIndex = readIntInRange(1, employeesFound.size(), "Seleccione un empleado válido (1-" + employeesFound.size() + "): ") - 1;
                    // Aquí se asigna el codigo seleccionado
                    newEquipment.employeeCode = employeesFound.get(selectedEmployeeIndex).code;
                    isEmployeeFound = true;

                } else {
                    System.out.println("No se encontraron empleados con ese código. Intente nuevamente.");
                }

            }

            equipment.add(newEquipment); // Aquí se añade el equipo a la lista de equipos registrados

            System.out.println("Equipo registrado exitosamente.");
            pauseScreen();
            scanner.nextLine(); // limpiar entrada
        return true;
    }

}