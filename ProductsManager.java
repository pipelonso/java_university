import java.util.Scanner;

public class ProductsManager {
    
    private final Scanner scanner;
    
    private static final int CAPACIDAD = 20; // Capacidad máxima del árbol binario de búsqueda
    private int[] codigos = new int[CAPACIDAD]; // Arreglo para almacenar los códigos de los productos
    private String[] nombres = new String[CAPACIDAD]; // Arreglo para almacenar los nombres de los productos
    private int[] izquierdo = new int[CAPACIDAD]; // Arreglo para almacenar los índices de los hijos izquierdos de cada nodo
    private int[] derecho = new int[CAPACIDAD]; // Arreglo para almacenar los índices de los hijos derechos de cada nodo
    private int raiz = -1; // Índice de la raíz del árbol binario de búsqueda, -1 indica que el árbol está vacío
    private int cantidad = 0; // Cantidad de nodos actualmente en el árbol binario de búsqueda

    // Desarrollado en openjdk 21.0.12.1 2026-08-18
    // Autor: Andres Felipe Ibañez Cuta - 100216824
    // Autor: Julio Cesar González Hernández - 100219419

    /**
     * Notas de desarrollo:
     * Igual que la actividad anterior para esta actividad se hizo llamada en google meet mientras 
     * averiguabamos como relacionar las listas de enteros con los nodos de un arbol, al cabo de un rato
     * descubrimos como hacerlo al representar los arreglos izquierdo y derecho como indicadores de los hijos de cada nodo, 
     * y el arreglo de codigos y nombres como los datos de cada nodo.
     * 
     * De igual manera omo entorno de desarrollo se utilizó Visual Studio Code por su
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

    public ProductsManager() {
        this.scanner = new Scanner(System.in);
        for (int i = 0; i < CAPACIDAD; i++) {
            izquierdo[i] = -1;
            derecho[i] = -1;
        }
    }

    public static void main(String[] args) { // Método principal para ejecutar la aplicación
    
        ProductsManager PM = new ProductsManager(); // Crea una instancia de ProductsManager
        PM.menu(); // Llama al método menu() para iniciar la interacción con el usuario

    }

    public String getTittle() {

        return """
                
            ========================================
                    REGISTRO DE PRODUCTOS
                ÁRBOL BINARIO DE BÚSQUEDA
            ========================================

                """;

    }

    public void menu() {
        int opcion;
        do {
            System.out.println(getTittle());
            System.out.println("1. Insertar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Mostrar productos ordenados");
            System.out.println("4. Mostrar creadores");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = readIntInRange(1, 5, "Seleccione una opción (1-5): ");

            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {requestRegisterProduct();}
                case 2 -> {requestSearchProduct();}
                case 3 -> {requestShowProducts();}   
                case 4 -> {showCreators();}
                case 5 -> {
                    System.out.println("Saliendo... Hasta luego! ⋆");
                }
                default -> {System.out.println("⊠ Opción inválida");}
            }
        } while (opcion != 5);
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

    public void requestRegisterProduct() {
        System.out.print("⊶ Ingrese el código del producto (entero positivo): ");
        try {
            int codigo = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (codigo <= 0) {
                System.out.println("⊠ Error: El código debe ser un número entero mayor a cero.");
                pauseScreen();
                return;
            }

            System.out.print("⊶ Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();

            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("⊠ Error: El nombre del producto no puede estar vacío.");
                pauseScreen();
                return;
            }

            insertar(codigo, nombre); // Inserta el producto en el árbol binario de búsqueda

        } catch (Exception e) {
            System.out.println("⊠ Error: Debe ingresar un número entero válido.");
            scanner.nextLine(); // Limpiar el buffer
        }
        pauseScreen(); // Una única pausa al finalizar la petición
    }

    public void requestSearchProduct() {
        System.out.print("⊶ Ingrese el código del producto a buscar: ");
        try {
            int codigoBuscado = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer por si acaso
            buscar(codigoBuscado); // Busca el producto en el árbol binario de búsqueda
        } catch (Exception e) {
            System.out.println("⊠ Error: Debe ingresar un número entero válido.");
            scanner.nextLine(); // Limpiar el buffer
        }
        pauseScreen(); // Una única pausa al finalizar
    }

    public void requestShowProducts() { // Muestra los productos ordenados en orden ascendente
        System.out.println("╭┉┉┉┉┉┉┉┉┉┉(Productos ordenados)┉┉┉┉┉┉┉┉");

        inorden(raiz);

        System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");
        pauseScreen();
    }

    private void pauseScreen() { // Aqui el metodo para que el usuario pueda leer con mas calma
        System.out.println("\nPresione cualquier tecla para continuar...");
        scanner.nextLine();
        System.out.println("-----------------------------------\n");
        System.out.println(""); // Salto de línea para mejorar la legibilidad en la salida de la consola
    }

    public void insertar(int nuevoCodigo, String nuevoNombre) {
        if (cantidad >= CAPACIDAD) {
            System.out.println("Árbol lleno");
            return;
        }

        if (raiz == -1) { // Si el árbol está vacío, se inserta el primer nodo como raíz
            raiz = 0;
            codigos[0] = nuevoCodigo;
            nombres[0] = nuevoNombre;
            cantidad++;
        } else { // Si el árbol no está vacío, se busca la posición adecuada para insertar el nuevo nodo
            int actual = raiz;
            while (true) { // Bucle infinito hasta encontrar la posición correcta para insertar
                if (nuevoCodigo < codigos[actual]) {
                    if (izquierdo[actual] == -1) { // Si no hay hijo izquierdo, se inserta el nuevo nodo aquí
                        int nuevoIndice = cantidad;
                        codigos[nuevoIndice] = nuevoCodigo; // Se inserta el nuevo código en la posición correspondiente
                        nombres[nuevoIndice] = nuevoNombre; // Se inserta el nuevo nombre en la posición correspondiente
                        izquierdo[actual] = nuevoIndice; // Se actualiza el hijo izquierdo del nodo actual para apuntar al nuevo nodo
                        cantidad++;
                        break;
                    } else {
                        actual = izquierdo[actual]; // Si hay hijo izquierdo, se continúa buscando en el subárbol izquierdo
                    }
                } else if (nuevoCodigo > codigos[actual]) { // Si el nuevo código es mayor, se busca en el subárbol derecho
                    if (derecho[actual] == -1) {
                        int nuevoIndice = cantidad;
                        codigos[nuevoIndice] = nuevoCodigo; // Se inserta el nuevo código en la posición correspondiente
                        nombres[nuevoIndice] = nuevoNombre; // Se inserta el nuevo nombre en la posición correspondiente
                        derecho[actual] = nuevoIndice; // Se actualiza el hijo derecho del nodo actual para apuntar al nuevo nodo
                        cantidad++;
                        break;
                    } else {
                        actual = derecho[actual]; // Si hay hijo derecho, se continúa buscando en el subárbol derecho
                    }
                } else {
                    System.out.println("⊠ El código ya existe");
                    break;
                }
            }
        }
    }

    public void buscar(int codigoBuscado) {
        if (raiz == -1) {
            System.out.println("Árbol vacío");
            return;
        }

        int actual = raiz;

        System.out.println("╭┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");

        while (actual != -1) {
            if (codigoBuscado == codigos[actual]) {
                System.out.println("| Encontrado: " + nombres[actual]);
                System.out.println("╰┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉┉");
                return;
            } else if (codigoBuscado < codigos[actual]) {
                actual = izquierdo[actual]; // Si el código buscado es menor, se continúa la búsqueda en el subárbol izquierdo
            } else {
                actual = derecho[actual]; // Si el código buscado es mayor, se continúa la búsqueda en el subárbol derecho
            }
        }
        System.out.println("⊠ El producto no existe");
    }

    public void inorden(int posicion) {
        if (posicion == -1) {
            return;
        }

        inorden(izquierdo[posicion]);
        System.out.println("|Código: " + codigos[posicion] + ", Nombre: " + nombres[posicion]);
        inorden(derecho[posicion]);
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

}
