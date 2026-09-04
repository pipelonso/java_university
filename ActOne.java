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


class Application {
    
    public String[] requestStates;
    public String[] companyAreas;
    public boolean onMainLoop;
    public ArrayList<Employee> employees = new ArrayList<>();
    public ArrayList<Equipment> equipment = new ArrayList<>();

    private final Queue<String> pendingRequest = new ArrayDeque<>();

    private final Deque<String> completeRequest = new ArrayDeque<>();

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
            System.out.println("Presione cualquier tecla para continuar...");
            scanner.nextLine(); // limpiar entrada
            scanner.nextLine(); // esperar a que el usuario presione Enter
            System.out.println("-----------------------------------\n");
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
        System.out.println("Función para consultar solicitudes aún no implementada.");
        return "";
    }

    public boolean requestAttendNextRequest() {
        System.out.println("Función para atender solicitudes aún no implementada.");
        return false;
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

        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine(); // limpiar entrada
        scanner.nextLine(); // esperar a que el usuario presione Enter
        System.out.println("-----------------------------------\n");

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

        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine(); // limpiar entrada
        scanner.nextLine(); // esperar a que el usuario presione Enter
        System.out.println("-----------------------------------\n");

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

        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine(); // limpiar entrada
        scanner.nextLine(); // esperar a que el usuario presione Enter
        System.out.println("-----------------------------------\n");

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
        System.out.println("Función para crear una solicitud aún no implementada.");
    }

    public void viewPendingRequests() {
        System.out.println("Función para consultar solicitudes aún no implementada.");
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
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine(); // limpiar entrada
        scanner.nextLine(); // esperar a que el usuario presione Enter
        System.out.println("-----------------------------------\n");

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
            System.out.println("Presione cualquier tecla para continuar...");
            scanner.nextLine(); // limpiar entrada
            scanner.nextLine(); // esperar a que el usuario presione Enter
            System.out.println("-----------------------------------\n");

        return true;
    }

}