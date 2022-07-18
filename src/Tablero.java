public class Board {
    public static int[][] Array = new int[3][3];

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
        for (int i = 0; i < Array.length; i++){
            if(Array[fila][i] == 0){
                fila_mostrar.append(" ");
            }
            if (Array[fila][i] == 1) {
                fila_mostrar.append("X");
            }
            if (Array[fila][i] == 2) {
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
                Board.Array[i][j]=0;
            }
        }
    }
    public static int jugadorGanador() {
        int res = 0;
        for (int i = 0; i < Array.length; i++) {
            for (int j = 0; j < Array[0].length; j++) {
                if (Array[i][j] != 0) {
                    // horizontal
                    if (revisarCasilla(i - 2, j, Array[i][j]) && revisarCasilla(i - 1, j, Array[i][j])) {
                        return Array[i][j];
                    }
                    if (revisarCasilla(i + 2, j, Array[i][j]) && revisarCasilla(i + 1, j, Array[i][j])) {
                        return Array[i][j];
                    }
                    // vertical
                    if (revisarCasilla(i, j - 2, Array[i][j]) && revisarCasilla(i, j - 1, Array[i][j])) {
                        return Array[i][j];
                    }
                    if (revisarCasilla(i, j + 2, Array[i][j]) && revisarCasilla(i, j + 1, Array[i][j])) {
                        return Array[i][j];
                    }
                    // diagonal
                    if (revisarCasilla(i + 2, j + 2, Array[i][j]) && revisarCasilla(i + 1, j + 1, Array[i][j])) {
                        return Array[i][j];
                    }
                    if (revisarCasilla(i + 2, j - 2, Array[i][j]) && revisarCasilla(i + 1, j - 1, Array[i][j])) {
                        return Array[i][j];
                    }
                    if (revisarCasilla(i - 2, j + 2, Array[i][j]) && revisarCasilla(i - 1, j + 1, Array[i][j])) {
                        return Array[i][j];
                    }
                    if (revisarCasilla(i - 2, j - 2, Array[i][j]) && revisarCasilla(i - 1, j - 1, Array[i][j])) {
                        return Array[i][j];
                    }
                }
            }
        }
        return res;
    }

    public static boolean revisarCasilla(int y, int x, int tipo) {
        boolean res = false;
        if ((x >= 0) && (y >= 0)) {
            if ((x < Array[0].length) && (y < Array.length)) {
                // la pos exista
                if (tipo == Array[y][x]) {
                    res = true;
                }
            }
        }
        return res;
    }

    public static boolean posicionVacia(int x, int y) {
        return Array[y][x] == 0;
    }
}

