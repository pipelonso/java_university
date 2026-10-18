import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class NetworkManager {
        
    private final Scanner scanner;

    protected String[] equipos;

    int[][] conexiones = new int[5][5];
    boolean[] visitados = new boolean[5];

    Queue<Integer> cola = new LinkedList<>();

    public NetworkManager() {

        this.scanner = new Scanner(System.in);
        this.equipos = new String[]{"Computador", "Impresora", "Servidor", "Enrutador", "Portátil"};

        this.conexiones = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };

    }

    public static void main(String[] args) {
        
        NetworkManager NM = new NetworkManager();
        NM.run();

    }

    public void run() {
        
        int option = -1;

        do {
            System.out.println(getTittle());
            System.out.println("1. Mostrar equipos");
            System.out.println("2. Conectar dos equipos");
            System.out.println("3. Mostrar matriz de conexiones");
            System.out.println("4. Recorrer la red con BFS");
            System.out.println("5. Mostrar creador de la app");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            option = readIntInRange(1, 5, "Seleccione una opción (1-5): ");

            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1 -> {requestShowEquipments(true);}
                case 2 -> {requestConnectEquipments();}
                case 3 -> {requestShowConnectionMatrix();}   
                case 4 -> {requestShowWithBFS();}
                case 5 -> {showCreators();}
                case 6 -> {
                    System.out.println("Saliendo... Hasta luego! ⋆");
                }
                default -> {System.out.println("⊠ Opción inválida");}
            }

        }while (option != 6);

    }

    private void requestShowEquipments(boolean showTitle) {

        if (showTitle) {
            System.out.println("""
                    =======================================================
                                    Mostrar equipos
                    =======================================================
                    """);
        }

        System.out.println("╭┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");

        for (int i = 0; i < equipos.length; i++) {
            System.out.println("| " + (i + 1) + ". " + equipos[i]);
        }

        System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");

        if (showTitle) {
            pauseScreen();
        }

    }

    private void requestConnectEquipments() {
        
        System.out.println("""
                    =======================================================
                                    Conectar equipos
                    =======================================================
                    """);

        System.out.println("Equipos disponibles para conectar:");

        requestShowEquipments(false);

        try {

            System.out.print("Ingrese el número del primer equipo a conectar: ");
            int equipo1 = readIntInRange(1, equipos.length, "Ingrese el número del primer equipo a conectar: ") - 1;

            System.out.print("Ingrese el número del segundo equipo a conectar: ");
            int equipo2 = readIntInRange(1, equipos.length, "Ingrese el número del segundo equipo a conectar: ") - 1;

            if (equipo1 == equipo2) {
                System.out.println("⊠ No se puede conectar un equipo consigo mismo.");
                return;
            }

            if (conexiones[equipo1][equipo2] == 1) {
                System.out.println("⊠ Los equipos ya están conectados.");
                return;
            }

            conexiones[equipo1][equipo2] = 1;
            conexiones[equipo2][equipo1] = 1;

            System.out.println("✓ Conexión establecida entre " + equipos[equipo1] + " y " + equipos[equipo2] + ".");

            pauseScreen();

        } catch (Exception e) {
            System.out.println("⊠ Error al conectar los equipos: " + e.getMessage());
        }

    }

    private void requestShowConnectionMatrix() {
        
        System.out.println("""
                    =======================================================
                                Matriz de conexiones
                    =======================================================
                    """);

        System.out.print("  ");

        for (int i = 0; i < 5; i++) {
            System.out.print(" " + (i + 1));
        }

        for (int i = 0; i < 5; i++) {
            System.out.print("\n" + (i + 1) + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(" " + conexiones[i][j]);
            }
            System.out.print(" -> " + equipos[i]);
        }

        pauseScreen();

    }

    private void requestShowWithBFS() {

        System.out.println("""
                    =======================================================
                                Recorrido de la red con BFS
                    =======================================================
                    """);

        System.out.print("Ingrese el número del equipo desde el cual iniciar el recorrido: ");
        int startEquipo = readIntInRange(1, equipos.length, "Ingrese el número del equipo desde el cual iniciar el recorrido: ") - 1;

        bfs(startEquipo);

        pauseScreen();

    }
    
    private void bfs(int startEquipo) { 
        
        for (int i = 0; i < visitados.length; i++) {
            visitados[i] = false;
        }

        cola.clear();

        visitados[startEquipo] = true;
        cola.add(startEquipo);

        System.out.println("Recorrido BFS desde " + equipos[startEquipo] + ":");

        while (!cola.isEmpty()) {
            int equipoActual = cola.poll();
            System.out.print(" -> " + equipos[equipoActual]);

            for (int i = 0; i < conexiones.length; i++) {
                if (conexiones[equipoActual][i] == 1 && !visitados[i]) {
                    visitados[i] = true;
                    cola.add(i);
                }
            }
        }

        System.out.println(); // Salto de línea para mejorar la legibilidad en la salida de la consola
        System.out.println("Recorrido BFS completado.");

    }

    public String getTittle() {

        return """
            ========================================
                     RED SENCILLA DE EQUIPOS
            ========================================
                """;

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
                    System.out.println("⊠ Error: Debe ingresar un entero entre " + min + " y " + max + ".\n"); // mensaje de error para el usuario
                    scanner.nextLine(); // limpieza del buffer de entrada para evitar un bucle infinito en caso de error
                }
            }

            System.out.println(); // salto de línea para mejorar la legibilidad en la salida de la consola

            return option; // retorno del valor ingresado por el usuario, ya validado y dentro del rango permitido

    }

    public void showCreators() { // metodo que nos muestra como creadores de la app
            
        System.out.println("""
            =======================================================
                     Creador de la app / Estudiante
            =======================================================
            """);

        System.out.println("╭┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉|");
        System.out.println("|Andres Felipe Ibañez Cuta 100216824         |");
        System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉|");

        pauseScreen();

    }

    private void pauseScreen() { // Aqui el metodo para que el usuario pueda leer con mas calma
        System.out.println("\nPresione cualquier tecla para continuar...");
        scanner.nextLine();
        System.out.println("-----------------------------------\n");
        System.out.println(""); // Salto de línea para mejorar la legibilidad en la salida de la consola
    }

}