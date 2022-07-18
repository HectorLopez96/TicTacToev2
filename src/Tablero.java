public class Tablero {
    public static int[][] matriz = new int[3][3];

    public static void motrarTablero(){
        System.out.println("| - | - | - |");
        System.out.println(imprimirFilaTablero(0));
        System.out.println("| - | - | - |");
        System.out.println(imprimirFilaTablero(1));
        System.out.println("| - | - | - |");
        System.out.println(imprimirFilaTablero(2));
        System.out.println("| - | - | - |");

    }
    public static String imprimirFilaTablero(int fila) {
        StringBuilder fila_mostrar = new StringBuilder("| ");
        for (int i = 0; i < matriz.length; i++){
            if(matriz[fila][i] == 0){
                fila_mostrar.append(" ");
            }
            if (matriz[fila][i] == 1) {
                fila_mostrar.append("X");
            }
            if (matriz[fila][i] == 2) {
                fila_mostrar.append("O");
            }
            fila_mostrar.append(" | ");
        }
        fila_mostrar.deleteCharAt(fila_mostrar.lastIndexOf(" "));
        return fila_mostrar.toString();
    }

    public static void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tablero.matriz[i][j]=0;
            }
        }
    }
    public static int jugadorGanador() {
        int res = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] != 0) {
                    // horizontal
                    if (revisarCasilla(i - 2, j, matriz[i][j]) && revisarCasilla(i - 1, j, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i + 2, j, matriz[i][j]) && revisarCasilla(i + 1, j, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    // vertical
                    if (revisarCasilla(i, j - 2, matriz[i][j]) && revisarCasilla(i, j - 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i, j + 2, matriz[i][j]) && revisarCasilla(i, j + 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    // diagonal
                    if (revisarCasilla(i + 2, j + 2, matriz[i][j]) && revisarCasilla(i + 1, j + 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i + 2, j - 2, matriz[i][j]) && revisarCasilla(i + 1, j - 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i - 2, j + 2, matriz[i][j]) && revisarCasilla(i - 1, j + 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                    if (revisarCasilla(i - 2, j - 2, matriz[i][j]) && revisarCasilla(i - 1, j - 1, matriz[i][j])) {
                        return matriz[i][j];
                    }
                }
            }
        }
        return res;
    }

    public static boolean revisarCasilla(int y, int x, int tipo) {
        boolean res = false;
        if ((x >= 0) && (y >= 0)) {
            if ((x < matriz[0].length) && (y < matriz.length)) {
                // la pos exista
                if (tipo == matriz[y][x]) {
                    res = true;
                }
            }
        }
        return res;
    }

    public static boolean posicionVacia(int x, int y) {
        return matriz[y][x] == 0;
    }
}

