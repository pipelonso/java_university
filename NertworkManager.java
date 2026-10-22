import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class NetworkManager {
        

    // Desarrollado en openjdk 21.0.12.1 2026-08-18
    // Autor: Andres Felipe Ibañez Cuta - 100216824
    
    /**
     *
     * 
     * De igual manera omo entorno de desarrollo se utilizó Visual Studio Code por su
     * facilidad para el manejo de git.
     * 
     * El proyecto fue desarrollado desde linux usando openjdk-21
     * que viene incluido por defecto en la distrubución MX linux
     * 
     * Puede encontrar el historial de commits en el repositorio de github:
     * https://github.com/pipelonso/java_university/commits/main/
     * 
     * Los caracteres especiales fueron tomados del sitio:
     * https://www.adslzone.net/como-se-hace/internet/simbolos-copiar-pegar/
     * 
     */ 

    private final Scanner scanner; // Objeto Scanner utilizado para leer la entrada del usuario desde la consola

    protected String[] equipos; // Arreglo de equipos que representa los nombres de los equipos en la red

    int[][] conexiones = new int[5][5]; // Matriz de conexiones que representa las conexiones entre los equipos en la red
    boolean[] visitados = new boolean[5]; // Arreglo que indica si un equipo ha sido visitado durante el recorrido BFS

    Queue<Integer> cola = new LinkedList<>(); // Cola utilizada para el recorrido BFS, donde se almacenan los equipos a visitar

    public NetworkManager() { // Constructor de la clase NetworkManager que inicializa los atributos de la clase

        this.scanner = new Scanner(System.in);
        this.equipos = new String[]{"Computador", "Impresora", "Servidor", "Enrutador", "Portátil"}; // Arreglo de equipos inicializado con nombres de ejemplo

        this.conexiones = new int[][]{ // Matriz de conexiones inicializada con ceros, indicando que no hay conexiones entre los equipos al inicio
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

    public void run() { // metodo principal que ejecuta el programa y muestra el menú de opciones al usuario
        
        int option = -1;

        do {
            System.out.println(getTittle()); // Se llama al método getTittle para mostrar el título de la aplicación en la consola
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

    private void requestShowEquipments(boolean showTitle) { // Metodo que recibe llamado para mostrar los equipos disponibles en la red

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
            pauseScreen(); // Se llama al método pauseScreen para pausar la ejecución y permitir que el usuario lea la información antes de continuar
        }

    }

    private void requestConnectEquipments() { // Metodo que recibe llamado para conectar equipos
        
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

            if (equipo1 == equipo2) { // Se verifica si el usuario intenta conectar un equipo consigo mismo
                System.out.println("⊠ No se puede conectar un equipo consigo mismo.");
                return;
            }

            if (conexiones[equipo1][equipo2] == 1) { // Se verifica si ya existe una conexión entre los equipos en la matriz de conexiones
                System.out.println("⊠ Los equipos ya están conectados.");
                return;
            }

            conexiones[equipo1][equipo2] = 1; // Se establece la conexión entre los equipos en la matriz de conexiones
            conexiones[equipo2][equipo1] = 1; // Se establece la conexión entre los equipos en la matriz de conexiones (simétrica)

            System.out.println("✓ Conexión establecida entre " + equipos[equipo1] + " y " + equipos[equipo2] + ".");

            pauseScreen();

        } catch (Exception e) {
            System.out.println("⊠ Error al conectar los equipos: " + e.getMessage());
        }

    }

    private void requestShowConnectionMatrix() { // Metodo que recibe llamado para mostrar la matriz de conexiones
        
        System.out.println("""
                    =======================================================
                                Matriz de conexiones
                    =======================================================
                    """);

        System.out.print("  ");

        for (int i = 0; i < 5; i++) { // Se recorre la matriz de conexiones para mostrar los encabezados de columna en la consola
            System.out.print(" " + (i + 1));
        }

        for (int i = 0; i < 5; i++) { // Se recorre la matriz de conexiones para mostrarla en la consola
            System.out.print("\n" + (i + 1) + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(" " + conexiones[i][j]);
            }
            System.out.print(" -> " + equipos[i]);
        }

        pauseScreen();

    }

    private void requestShowWithBFS() { // Metodo que recibe llamado para ejecutar bfs

        System.out.println("""
                    =======================================================
                                Recorrido de la red con BFS
                    =======================================================
                    """);

        System.out.print("Ingrese el número del equipo desde el cual iniciar el recorrido: ");
        int startEquipo = readIntInRange(1, equipos.length, "Ingrese el número del equipo desde el cual iniciar el recorrido: ") - 1;

        bfs(startEquipo); // Se llama al método bfs con el equipo de inicio proporcionado por el usuario

        pauseScreen();

    }
    
    private void bfs(int startEquipo) { 
        
        for (int i = 0; i < visitados.length; i++) { // Se inicializa el arreglo de visitados para marcar todos los equipos como no visitados antes de iniciar el recorrido BFS
            visitados[i] = false;
        }

        cola.clear(); // Se limpia la cola antes de iniciar el recorrido BFS

        visitados[startEquipo] = true;
        cola.add(startEquipo);

        System.out.println("Recorrido BFS desde " + equipos[startEquipo] + ":");

        while (!cola.isEmpty()) { // Mientras la cola no esté vacía, se procesan los equipos conectados
            int equipoActual = cola.poll();
            System.out.print(" -> " + equipos[equipoActual]); // Se imprime el equipo actual en el recorrido

            for (int i = 0; i < conexiones.length; i++) { // Se recorre la matriz de conexiones para encontrar los equipos conectados al equipo actual
                if (conexiones[equipoActual][i] == 1 && !visitados[i]) { // Si hay una conexión y el equipo no ha sido visitado, se marca como visitado y se agrega a la cola
                    visitados[i] = true;
                    cola.add(i);
                }
            }
        }

        System.out.println(); // Salto de línea para mejorar la legibilidad en la salida de la consola
        System.out.println("Recorrido BFS completado."); // Mensaje indicando que el recorrido BFS ha finalizado

    }

    public String getTittle() { // metodo que nos muestra el titulo de la app

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