import java.util.Random;
import java.util.Scanner;

public class Tictactoe {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static int[][] matrizEstadisticas = new int[2][3];

    public static int[][] matrizBoot = new int[3][3];
    public static int[][] matrizMejoresTirosYX = new int[9][2];

    public static void estadisticas() {
        System.out.println("\n\nESTADISTICA");
        System.out.println("BOOT   Empatadas "+matrizEstadisticas[0][0]+" Ganadas "+matrizEstadisticas[0][1]+" Perdidas "+matrizEstadisticas[0][2]);
        System.out.println("USER   Empatadas "+matrizEstadisticas[1][0]+" Ganadas "+matrizEstadisticas[1][1]+" Perdidas "+matrizEstadisticas[1][2]);
        scanner.nextLine();
    }

    public static void asignarGanador(int ganador) {
        if (ganador == 0) {
            matrizEstadisticas[0][0]++;
            matrizEstadisticas[1][0]++;
        } else {
            if (ganador == 2) {
                matrizEstadisticas[0][1]++;
                matrizEstadisticas[1][2]++;
            } else {
                matrizEstadisticas[0][2]++;
                matrizEstadisticas[1][1]++;
            }
        }
    }
    public static void turnoJugador(int tipoFicha) {
        System.out.println("***** turno del usuario");
        Tablero.motrarTablero();
        int posX;
        int posY;
        boolean seleccionoPosicionVacia;
        do {
            posX = solicitarNumero("Ingrese la coordenada en X", 0, 2);
            posY = solicitarNumero("Ingrese la coordenada en Y", 0, 2);
            seleccionoPosicionVacia = Tablero.posicionVacia(posX, posY);
            if (!seleccionoPosicionVacia) {
                Tablero.motrarTablero();
                System.out.println("\nla posicion seleccionada no esta vacía, intente de nuevo");
            }

        } while (!seleccionoPosicionVacia);
        Tablero.matriz[posY][posX] = tipoFicha;
        Tablero.motrarTablero();
        System.out.println("El jugador tiro. ");
        scanner.nextLine();
    }

    public static void turnoBoot(int tipoCasillaBoot) {
        bootValorarMatriz(tipoCasillaBoot);
        int cantMejoresTiros = bootMejorTiro();

        int y, x;
        if (cantMejoresTiros == 1) {
            y = matrizMejoresTirosYX[0][0];
            x = matrizMejoresTirosYX[0][1];
        } else {
            int tiroFinal = generarNumeroRandom(0, cantMejoresTiros - 1);
            y = matrizMejoresTirosYX[tiroFinal][0];
            x = matrizMejoresTirosYX[tiroFinal][1];
        }
        Tablero.matriz[y][x] = tipoCasillaBoot;
        Tablero.motrarTablero();
        System.out.println("El boot tira");
        System.out.println("Y " + y + " X " + x);
        scanner.nextLine();
    }

    public static int bootMejorTiro() {
        int cantidadMejoresTiros = 0;
        int maximo = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizBoot[i][j] > maximo) {
                    maximo = matrizBoot[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizBoot[i][j] == maximo) {
                    matrizMejoresTirosYX[cantidadMejoresTiros][0] = i;
                    matrizMejoresTirosYX[cantidadMejoresTiros][1] = j;
                    cantidadMejoresTiros++;
                }
            }
        }
        return cantidadMejoresTiros;
    }

    public static void bootValorarMatriz(int tipoCasillaBoot) {
        int valorPos;
        int tmp;
        for (int i = 0; i < 3; i++) {// filas
            for (int j = 0; j < 3; j++) { // columnas
                valorPos = 0;
                if (Tablero.matriz[i][j] == 0) {
                    // arriba
                    tmp = bootValorCasilla(j, i, 0, 1, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // abajo
                    tmp = bootValorCasilla(j, i, 0, -1, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // derecha
                    tmp = bootValorCasilla(j, i, 1, 0, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // izquierda
                    tmp = bootValorCasilla(j, i, -1, 0, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // arriba derecha
                    tmp = bootValorCasilla(j, i, 1, 1, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // arriba izquierda
                    tmp = bootValorCasilla(j, i, -1, 1, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // abajo derecha
                    tmp = bootValorCasilla(j, i, 1, -1, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    // abajo izquierda
                    tmp = bootValorCasilla(j, i, -1, -1, tipoCasillaBoot);
                    valorPos = Math.max(tmp, valorPos);
                    matrizBoot[i][j] = valorPos;
                } else {
                    matrizBoot[i][j] = 0;
                }
            }
        }
    }

    public static int bootValorCasilla(int x, int y, int multX, int multY, int casillaPrincipal) {
        int res = 1;
        int x1 = multX + x;
        int y1 = multY + y;

        if (dentroRango(x1, y1)) {
            int x2 = multX + x1;
            int y2 = multY + y1;

            if (!dentroRango(x2, 1)) {
                x2 = x - 2 * multX;
            }
            if (!dentroRango(1, y2)) {
                y2 = x - 2 * multY;
            }
            if (dentroRango(x2, y2)) {
                int p1 = Tablero.matriz[y1][x1];
                int p2 = Tablero.matriz[y2][x2];
                if ((p1 == p2) && (p1 == 0)) {
                    // estan vacias
                    res = 2;
                } else {
                    // no estan vacias
                    if (p1 == p2) {
                        // son iguales
                        if (p1 == casillaPrincipal) {
                            // aca gano
                            res = 15;
                        } else {
                            // pierdo
                            res = 10;
                        }
                    } else {
                        if ((p1 == 0) || (p2 == 0)) {
                            if ((p1 == casillaPrincipal) || (p2 == casillaPrincipal)) {
                                // yo puedo ganar 2 turno despues
                                res = 5;
                            } else {
                                // jugador puede ganar 2 turno despues
                                res = 4;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static boolean dentroRango(int x, int y) {
        boolean res = false;
        if ((x >= 0) && (y >= 0)) {
            if ((x < 3) && (y < 3)) {
                res = true;
            }
        }
        return res;
    }

    public static int generarNumeroRandom(int min, int max) {
        return random.nextInt((max+1) - min) + min;
    }

    public static int solicitarNumero(String mensaje, int limiteInferior, int limiteSuperior) {
        int numeroIngresado = 0;
        boolean hayErrorNumeroIngresado;
        do {
            try {

                System.out.println("\n" + mensaje);
                numeroIngresado = scanner.nextInt();
                if ((numeroIngresado >= limiteInferior) && (numeroIngresado <= limiteSuperior)) {
                    hayErrorNumeroIngresado = false;
                } else {
                    hayErrorNumeroIngresado = true;
                    System.out.println(
                            "Debe ingresar un número entre [" + limiteInferior + " . . " + limiteSuperior + "]");
                    System.out.println("Ingrese de nuevo.");
                }
            } catch (Exception e) {
                hayErrorNumeroIngresado = true;
                System.out.println(
                        "Debe ingresar un número entre [" + limiteInferior + " . . " + limiteSuperior + "]");
                System.out.println("Ingrese un numero entero.");

            }
            scanner.nextLine();
        } while (hayErrorNumeroIngresado);
        return numeroIngresado;
    }
}
