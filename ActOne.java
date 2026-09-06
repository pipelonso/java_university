import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}

class Equipment {

    public String code;
    public String type;
    public String employeeCode;
    public String state;
    public String brand;

    public Equipment() {}

}

class Employee {

    public String name;
    public String email;
    public String area;
    public String code;

    public Employee() {}

}

class Request {

    public String code;
    public String employeeCode;
    public String equipmentCode;
    public String priority;
    public String description;
    public String state;

    public Request() {}

}

class Application {
    
    public String[] requestStates;
    public String[] companyAreas;
    public boolean onMainLoop;
    public ArrayList<Employee> employees = new ArrayList<>();
    public ArrayList<Equipment> equipment = new ArrayList<>();

    private final Queue<Request> pendingRequest = new ArrayDeque<>();

    private final Deque<Request> completeRequest = new ArrayDeque<>();

    private final Scanner scanner;

    private boolean isTestDataGenerated = false;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.onMainLoop = true;

        this.requestStates = new String[] {
            "Pendiente",
            "En atención",
            "Solucionada"
        };

        this.companyAreas = new String[] {
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

    public void run() {
        while (onMainLoop) {
            showMenu();
            int option = readIntInRange(1, 9, "Seleccione una opción válida (1-9): ");
            processMenuOption(option);
        }
        
        this.scanner.close();
    }

    public int readIntInRange(int min, int max, String prompt) {
        int option = -1;
        boolean isValidOption = false;

        while (!isValidOption) {
            System.out.print(prompt);
            try {
                option = scanner.nextInt();

                if (option >= min && option <= max) {
                    isValidOption = true;
                } else {
                    throw new IllegalArgumentException("Número fuera del rango permitido.");
                }

            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un entero entre " + min + " y " + max + ".\n");
                scanner.nextLine();
            }
        }

        return option;

    }

    private void processMenuOption(int option) {
        switch (option) {
            case 1 -> {requestRegisterEmployee();}
            case 2 -> {requestRegisterEquipment();}
            case 3 -> {requestCreateRequest();}
            case 4 -> {requestShowRecords();}
            case 5 -> {requestAttendNextRequest();}
            case 6 -> {viewPendingRequests();}
            case 7 -> {requestCompleteRequests();}
            case 8 -> {generateTestData();}
            case 9 -> {
                System.out.println("Saliendo del sistema...");
                this.onMainLoop = false;
            }
            default -> System.out.println("Opción no implementada.");
        }
    }

    public void generateTestData() {

        if (isTestDataGenerated) {
            System.out.println("Los datos de prueba ya han sido generados.");
            pauseScreen();
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

    public void showMenu() {
        System.out.println(makeTitle());
        System.out.println("1. Registrar empleado");
        System.out.println("2. Registrar equipo");
        System.out.println("3. Crear solicitud");
        System.out.println("4. Consultar registros");
        System.out.println("5. Atender siguiente solicitud");
        System.out.println("6. Mostrar solicitudes pendientes");
        System.out.println("7. Mostrar solicitudes solucionadas");
        System.out.println("8. Generar datos de prueba");
        System.out.println("9. Salir");
    }

    public String requestCompleteRequests() {
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

        for (Request request : completeRequest) {
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

        System.out.println("Total de solicitudes solucionadas: " + completeRequest.size());
        pauseScreen();
        return "";
    }

    public boolean requestAttendNextRequest() {
        System.out.println("""
            =======================================================
                        ATENDER SIGUIENTE SOLICITUD
            =======================================================
            """);

        Request requestToAttend = pendingRequest.poll();

        if (requestToAttend == null) {
            System.out.println("No hay solicitudes pendientes en la cola.");
            pauseScreen();
            scanner.nextLine(); // limpiar entrada
            return false;
        }

        System.out.println("Procesando la siguiente solicitud en cola...");
        System.out.println("╭┉ Código: " + requestToAttend.code);
        System.out.println("| Empleado: " + requestToAttend.employeeCode);
        System.out.println("| Equipo: " + requestToAttend.equipmentCode);
        System.out.println("| Prioridad: " + requestToAttend.priority);
        System.out.println("| Descripción: " + requestToAttend.description);
        System.out.println("╰┉ Estado previo: " + requestToAttend.state);

        requestToAttend.state = requestStates[2]; 

        completeRequest.push(requestToAttend);

        System.out.println("\n¡Solicitud atendida y marcada como 'Solucionada' correctamente!");
        System.out.println("La solicitud fue enviada al registro histórico (Pila de completadas).");
        pauseScreen();
        scanner.nextLine(); // limpiar entrada
        return true;
    }

    public void requestShowRecords() {
        
        System.out.println("""
        ========================================================
                          REGISTROS DEL SISTEMA
        ========================================================
        """);

        System.out.println("1) Mostrar empleados registrados");
        System.out.println("2) Mostrar equipos registrados");
        System.out.println("3) Mostrar areas de la empresa");

        int response = readIntInRange(1, 3, "Seleccione una opción válida (1-3): ");

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

    public void showEquipment() {

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

    public void showEmployees() {
        
         System.out.println("""
            
            ==============================================
            =           Empleados registrados:           =
            ==============================================

        """);

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
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

        for (int i = 0; i < companyAreas.length; i++) {
            System.out.println((i + 1) + ". " + companyAreas[i]);
        }

        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    public String makeTitle() {
        return """
        ========================================================
                          JULAN SOFTWARE
               SISTEMA DE SOPORTE TÉCNICO EMPRESARIAL
        ========================================================
        """;
    }

    public void requestCreateRequest() {

    System.out.println("""
            =======================================================
                          CREACIÓN DE SOLICITUDES
            =======================================================
            """);

        if (employees.isEmpty()) {
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

            boolean existsInPending = pendingRequest.stream().anyMatch(r -> r.code.equalsIgnoreCase(code));
            boolean existsInComplete = completeRequest.stream().anyMatch(r -> r.code.equalsIgnoreCase(code));

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

            boolean exists = employees.stream().anyMatch(e -> e.code.equalsIgnoreCase(empCode));
            if (exists) {
                request.employeeCode = empCode;
                isEmployeeFound = true;
            } else {
                System.out.println("Empleado no encontrado. Verifique los registros.");
            }
        }

        System.out.print("Ingrese el código del equipo involucrado (Opcional, presione Enter para omitir): ");
        String eqCode = scanner.nextLine().trim();
        if (!eqCode.isEmpty()) {
            boolean exists = equipment.stream().anyMatch(e -> e.code.equalsIgnoreCase(eqCode));
            request.equipmentCode = exists ? eqCode : "N/A";
            if (!exists) {
                System.out.println("Equipo no encontrado. Se registrará como 'N/A'.");
            }
        } else {
            request.equipmentCode = "N/A";
        }

        System.out.println("\nSeleccione el nivel de prioridad:");
        System.out.println("1. Alta\n2. Media\n3. Baja");
        int priorityOpt = readIntInRange(1, 3, "Seleccione la prioridad (1-3): ");
        request.priority = switch (priorityOpt) {
            case 1 -> "Alta";
            case 2 -> "Media";
            default -> "Baja";
        };

        scanner.nextLine();

        System.out.print("Ingrese la descripción del problema: ");
        request.description = scanner.nextLine().trim();
        request.state = requestStates[0]; // "Pendiente"

        pendingRequest.offer(request);

        System.out.println("\nSolicitud agregada exitosamente a la cola de atención.");
        pauseScreen();
        scanner.nextLine(); // limpiar entrada

    }

    private void pauseScreen() {
        System.out.println("\nPresione cualquier tecla para continuar...");
        scanner.nextLine();
        System.out.println("-----------------------------------\n");
    }

    public void viewPendingRequests() {
        
        System.out.println("""
            =======================================================
                        SOLICITUDES PENDIENTES (COLA)
            =======================================================
            """);

        if (pendingRequest.isEmpty()) {
            System.out.println("No hay solicitudes pendientes por atender.");
            pauseScreen();
            return;
        }

        int index = 1;
        for (Request request : pendingRequest) {
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

            for (Employee employee : employees) {
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

    public boolean requestRegisterEquipment() {


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

                    for (Employee employee : employeesFound) {
                        idx++;
                        System.out.println("╭┉( " + idx + " )┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");
                        System.out.println("Código: " + employee.code + "\nNombre: " + employee.name + "\nÁrea: " + employee.area);
                        System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉\n");
                    }
                    
                    int selectedEmployeeIndex = readIntInRange(1, employeesFound.size(), "Seleccione un empleado válido (1-" + employeesFound.size() + "): ") - 1;
                    newEquipment.employeeCode = employeesFound.get(selectedEmployeeIndex).code;
                    isEmployeeFound = true;

                } else {
                    System.out.println("No se encontraron empleados con ese código. Intente nuevamente.");
                }

            }

            equipment.add(newEquipment);

            System.out.println("Equipo registrado exitosamente.");
            pauseScreen();
            scanner.nextLine(); // limpiar entrada
        return true;
    }

}