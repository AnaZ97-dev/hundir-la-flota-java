package barcos_ana_zafra_vallejo;

import java.util.Scanner;

/**
 * Realizar un programa Java que permita jugar a una versión simplificada de
 * Hundir la flota.
 *
 * Solo habrá un jugador humano (usuario) cuyo objetivo será hundir todos los
 * barcos del ordenador en un número determinado de intentos. El ordenador
 * tendrá un tablero con barcos y el usuario disparará para intentar hundirlos.
 *
 * @author Zafra Vallejo, Ana.
 */
public class Barcos_Ana_Zafra_Vallejo {

    //PROCESO
    //Funcion que pide un numero entre min y max y repite la pregunta hasta que sea valido para el nivel de personalizado.
    public static int pedirNumero(Scanner sc, String mensaje, int min, int max) {
        String texto;
        int numero;

        do {
            System.out.print(mensaje);
            texto = sc.next();
            numero = min - 1; // Por defecto, invalido.

            //Comprueba que lo que se introduce es igual a alguno de los numeros del rango, para no romper el programa con letras.
            for (int j = min; j <= max; j++) {
                if (texto.equals("" + j)) {
                    numero = j;
                }
            }
        } while (numero < min);

        return numero;
    }
    
    //Funcion que aniade la dificultad del juego.
    public static int[] nivelesDificultad(Scanner sc) {
        String nivel;
        //Array de 6 posiciones para introducir lanchas, buques,acorazado, portaaviones, intentos, tamanio tablero.
        int[] datosNivel = new int[6];
        int lanchas, buques, acorazados, portaaviones, intentos, tamanio;
        boolean nivelValido = false;

        //do-while para que no rompa el programa con otra opcion.
        do {
            System.out.println("\n---Selecciona el nivel de dificultad---");
            System.out.println("(facil, medio, dificil, personalizado)");
            nivel = sc.nextLine();
            nivel = nivel.toLowerCase();

            switch (nivel) {
                case "facil":
                    //Creacion de array para introducir las cantidades que pide cada nivel.
                    datosNivel = new int[]{5, 3, 1, 1, 50, 10};
                    nivelValido = true;
                    break;

                case "medio":
                    datosNivel = new int[]{2, 1, 1, 1, 30, 10};
                    nivelValido = true;
                    break;

                case "dificil":
                    datosNivel = new int[]{1, 1, 0, 0, 10, 10};
                    nivelValido = true;
                    break;

                case "personalizado":
                    //Se crea una funcion para no repetir do-while y agrandar las lineas de codigo.
                    //Los numeros de las cantidades son orientativas para el buen funcionamiento con respecto el espacio.
                    lanchas = pedirNumero(sc, "Introduzca el numero de lanchas (1 a 3): ", 1, 3);
                    buques = pedirNumero(sc, "Introduzca el numero de buques (0 a 3): ", 0, 3);
                    acorazados = pedirNumero(sc, "Introduzca el numero de acorazados (0 a 3): ", 0, 3);
                    portaaviones = pedirNumero(sc, "Introduzca el numero de portaaviones (0 a 3): ", 0, 3);
                    //El maximo de intentos es como si fuera sin limites y con un sentido del inicio.
                    intentos = pedirNumero(sc, "Introduzca el numero de intentos (minimo 1): ", 1, 999);
                    tamanio = pedirNumero(sc, "Introduzca el tamanio del tablero (10 al 15): ", 10, 15);

                    //Creacion del array para asignar cada cantidad a su variable correspondiente.
                    datosNivel = new int[]{lanchas, buques, acorazados, portaaviones, intentos, tamanio};
                    nivelValido = true;
                    break;

                default:
                    System.out.println("Selecciona una opcion de las anteriores.");
                    break;
            }

        } while (!nivelValido);

        return datosNivel;
    }

    //Funcion que aniade al tablero de los barcos los tipos de barcos.
    public static void cargarTableroBarcos(String tablero[][], int[] nivel) {
        //Se expone la matriz con "-"
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = " - ";
            }
        }

        //Creacion y asignacion de los barcos.
        int numeroLanchasColocadas = 0;
        int numeroBuquesColocados = 0;
        int numeroAcorazadosColocados = 0;
        int numeroPortaavionesColocados = 0;

        while (numeroLanchasColocadas < nivel[0]) {
            //Valores que no salgan de los limites del tablero.
            int fila = (int) (0 + Math.random() * (tablero.length - 1 - 0 + 1));
            int columna = (int) (0 + Math.random() * (tablero[0].length - 1 - 0 + 1));

            //Asignacion de la posicion si hay un espacio libre en el tablero.
            if (tablero[fila][columna].equals(" - ")) {
                tablero[fila][columna] = " L ";
                numeroLanchasColocadas++;
            }
        }

        while (numeroBuquesColocados < nivel[1]) {
            int fila = (int) (0 + Math.random() * (tablero.length - 1 - 0 + 1));
            int columna = (int) (0 + Math.random() * (tablero[0].length - 3 - 0 + 1));

            //Comprobacion para que el tamanio no se superponga con otros elementos que no sean espacios vacios.
            if (tablero[fila][columna].equals(" - ")) {

                if (tablero[fila][columna + 1].equals(" - ")) {

                    if (tablero[fila][columna + 2].equals(" - ")) {
                        tablero[fila][columna] = " B ";
                        tablero[fila][columna + 1] = " B ";
                        tablero[fila][columna + 2] = " B ";
                        numeroBuquesColocados++;
                    }

                }

            }

        }

        while (numeroAcorazadosColocados < nivel[2]) {
            int fila = (int) (0 + Math.random() * (tablero.length - 1 - 0 + 1));
            int columna = (int) (0 + Math.random() * (tablero[0].length - 4 - 0 + 1));

            if (tablero[fila][columna].equals(" - ")) {

                if (tablero[fila][columna + 1].equals(" - ")) {

                    if (tablero[fila][columna + 2].equals(" - ")) {

                        if (tablero[fila][columna + 3].equals(" - ")) {
                            tablero[fila][columna] = " Z ";
                            tablero[fila][columna + 1] = " Z ";
                            tablero[fila][columna + 2] = " Z ";
                            tablero[fila][columna + 3] = " Z ";
                            numeroAcorazadosColocados++;

                        }
                    }
                }
            }
        }

        while (numeroPortaavionesColocados < nivel[3]) {
            int fila = (int) (0 + Math.random() * (tablero.length - 5 - 0 + 1));
            int columna = (int) (0 + Math.random() * (tablero[0].length - 1 - 0 + 1));

            if (tablero[fila][columna].equals(" - ")) {

                if (tablero[fila + 1][columna].equals(" - ")) {

                    if (tablero[fila + 2][columna].equals(" - ")) {

                        if (tablero[fila + 3][columna].equals(" - ")) {

                            if (tablero[fila + 4][columna].equals(" - ")) {

                                tablero[fila][columna] = " P ";
                                tablero[fila + 1][columna] = " P ";
                                tablero[fila + 2][columna] = " P ";
                                tablero[fila + 3][columna] = " P ";
                                tablero[fila + 4][columna] = " P ";
                                numeroPortaavionesColocados++;
                            }
                        }
                    }

                }

            }
        }
    }

    //Funcion que crea el tablero del usuario en la forma de inicio.
    public static void cargarTableroUsuario(String tablero[][]) {
        //Se expone la matriz con "-".
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = " - ";
            }
        }
    }

    //Funcion que visualiza el tablero cargado junto a la estetica de letras y numeros para las coordenadas.
    public static void visualizarTableros(String[][] tablero) {
        // Cabecera con los numeros de las columnas.
        System.out.print(" ");

        for (int j = 0; j < tablero[0].length; j++) {
            if (j < 10) {
                System.out.print(" " + j + " ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println();

        // Filas, cada una con su letra delante.
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((char) ('A' + i));
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }

    //Funcion que permite jugar con las opciones seleccionadas y que permite ganar o perder.
    public static void disparos(Scanner sc, String[][] tableroBarcos, String[][] tableroUsuario, int intentos, int casillasBarcos) {
        String filaLetra;
        String columnaTexto;
        int columna;
        int disparo = 0, tocado = 0;

        do {
            System.out.println("\n---Selecciona la posicion en la que quieres disparar---");
            System.out.print("Indica la fila (letra): ");
            filaLetra = sc.next();
            filaLetra = filaLetra.toUpperCase(); //Por si la introduce en minuscula.
            int fila = -1; // Por defecto, invalida.
            //Comprueba de que no se aniadan mas caracteres que unicamente la primera letra. 
            if (filaLetra.length() == 1) {
                fila = filaLetra.charAt(0) - 'A';
            }

            System.out.print("Indica la columna (numero): ");
            columnaTexto = sc.next();
            columna = -1; // Por defecto, invalida.
            //Comprueba que lo que se introduce es igual a los numeros de la cabecera, para no romper el programa con letras. 
            for (int j = 0; j < tableroBarcos.length; j++) {
                if (columnaTexto.equals("" + j)) {
                    columna = j;
                }
            }

            System.out.println("");

            //SITUACIONES DISPARO
            //Que el disparo no sobrepase los limites.
            if (fila > tableroBarcos.length - 1 || fila < 0 || columna < 0 || columna > tableroBarcos.length - 1) {
                System.out.println("Posicion invalida.");
                //Comparar con el tablero del usuario para que los lugares marcados con A y X no se vuelvan a disparar.
            } else if (tableroUsuario[fila][columna].equals(" A ") || tableroUsuario[fila][columna].equals(" X ")) {
                System.out.println("No puedes volver a disparar en el mismo lugar.");
                //Si dispara en un lugar vacio en el tablero barcos, en el de usuario se coloca la A de agua.
                //Se cuenta un disparo lanzado.
            } else if (tableroBarcos[fila][columna].equals(" - ")) {
                tableroUsuario[fila][columna] = " A ";
                disparo++;
                visualizarTableros(tableroUsuario);
                System.out.println("Resultado: Agua.");
                //Si dispara en un sitio que no esta vacio en el tablero barcos, es que hay un barco, y en el de usuario se coloca la X de tocado.
                //Se cuenta un disparo lanzado y un barco tocado.
            } else if (!tableroBarcos[fila][columna].equals(" - ")) {
                tableroUsuario[fila][columna] = " X ";
                disparo++;
                tocado++;
                visualizarTableros(tableroUsuario);
                System.out.println("Resultado: Tocado.");
            }

        //Se repite hasta que los disparos realizados no sean iguales a los intentos y el numero de barcos tocados no sean iguales a la cantidad de barcos.
        } while (disparo < intentos && tocado < casillasBarcos);

        //SALIDA
        System.out.println("\n==========");
        System.out.println("RESULTADO");
        System.out.println("==========");

        if (tocado == casillasBarcos) {
            System.out.println("Has ganado!");
        } else {
            System.out.println("Has perdido!");
        }

        System.out.println("\n---TABLERO OPONENTE---");
        visualizarTableros(tableroBarcos);
        System.out.println("\n---TABLERO USUARIO---");
        visualizarTableros(tableroUsuario);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //DECLARACION VARIABLES
        Scanner sc = new Scanner(System.in);
        String[][] tableroBarcos;
        String[][] tableroUsuario;

        //PROGRAMA
        System.out.println("========================");
        System.out.println("JUEGO DE HUNDIR LA FLOTA");
        System.out.println("========================");

        // Array de nivel con valor de la funcion que incluye el metodo de los niveles a elegir.
        int[] nivel = nivelesDificultad(sc);

        //Se introduce la posicion 5 del array nivel en la matriz de los tableros de barco y usuario para dinamizar el tamanio.  
        tableroBarcos = new String[nivel[5]][nivel[5]];
        tableroUsuario = new String[nivel[5]][nivel[5]];

        System.out.println("\nCargando tablero del oponente...");

        cargarTableroBarcos(tableroBarcos, nivel);

        System.out.println("\nA continuacion, se muestra tu tablero: ");

        System.out.println("");

        cargarTableroUsuario(tableroUsuario);
        visualizarTableros(tableroUsuario);

        //Se crea la variable para multiplicar cada nivel con la cantidad de tipos de barco.
        int casillasBarcos = nivel[0] * 1 + nivel[1] * 3 + nivel[2] * 4 + nivel[3] * 5;

        disparos(sc, tableroBarcos, tableroUsuario, nivel[4], casillasBarcos);

        sc.close();
        System.out.println("\nFin del programa.");

    }

}