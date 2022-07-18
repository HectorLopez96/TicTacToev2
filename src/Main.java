public class Main {
    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n\nEscoger\n");
            System.out.println("1) jugar");
            System.out.println("2) Estadisticas");
            System.out.println("3) Salir");
            opcion = Tictactoe.solicitarNumero("Seleccione la opcion deseada:", 1, 3);
            switch (opcion) {
                case 1 -> jugar();
                case 2 -> Tictactoe.estadisticas();
                default -> System.out.println("Gracias por jugar, ha jugado contra la computadora");
            }
        } while (opcion != 3);
    }

    public static void jugar() {
        int cantidadTirosPosibles = 9;
        Tablero.limpiarTablero();
        boolean finDeJuego = false;
        int ganador;
        System.out.println("\n\nSeleccionando al jugador inicial de manera aleatoria");
        Tictactoe.scanner.nextLine();
        boolean bootIniciaJugada = Tictactoe.generarNumeroRandom(0, 1) == 1;
        System.out.println("\n****Turno de " + (bootIniciaJugada ? "BOOT X" : "USUARIO Juega con las fichas \"O\""));
        Tictactoe.scanner.nextLine();
        while (!finDeJuego) {
            // pintarTablero();
            System.out.println("\n\n\nTurno de " + (bootIniciaJugada ? "BOOT con las fichas \"X\"" : "USUARIO con las fichas \"O\""));
            if (bootIniciaJugada) {
                Tictactoe.turnoBoot(2);
            } else {
                Tictactoe.turnoJugador(1);
            }
            bootIniciaJugada = !bootIniciaJugada;

            cantidadTirosPosibles--;
            // turnoJugador(1);
            // revisar que alguien haya ganado
            ganador = Tablero.jugadorGanador();
            Tablero.motrarTablero();
            if (ganador != 0) {
                Tablero.motrarTablero();
                System.out.println("hay ganador, jugador " + ((ganador == 2) ? "BOOT X" : "USUARIO O"));
                Tictactoe.asignarGanador(ganador);
                finDeJuego = true;
            } else {
                if (cantidadTirosPosibles == 0) {
                    Tablero.motrarTablero();
                    System.out.println("Nadie Gano ");
                    Tictactoe.asignarGanador(0);
                    finDeJuego = true;
                }
            }
        }
    }
}

