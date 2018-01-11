/**
 * @author Timo Volkmann (199267)
 * @author Patrick Pitz ()
 * @author Marcel Ambros ()
 * @version 3.0
 *
 */

import java.util.Random;

public class Max {
    // Settings
    static final Fraction win = new Fraction(20);
    static final int boardw = 8; // Spielbrett Breite
    static final int boardh = 8; // Spielbrett Höhe
    static final int anzSpieler = 2; // Anzahl Spieler

    // Globale Variablen
    public static Player[] spieler;
    public static Gameboard spielfeld;


    public static void main(String[] args) throws Exception {

        gameloop:
        while (true) {
            // Spielbrett und Spielerobjekte erstellen
            spielfeld = new Gameboard(boardw, boardh);
            spieler = new Player[anzSpieler];
            for (int i = 0; i < spieler.length; i++) {
                if (i==0) spieler[i] = new Player(4, 3, "- B -");
                if (i==1) spieler[i] = new Player(3,4,"- W -");
                if (i==2) spieler[i] = new Player(1,1,"- X -");
                if (i==3) spieler[i] = new Player(6,6,"- Y -");
                if (i>=4) spieler[i] = new Player(new Random().nextInt(7),new Random().nextInt(7),IO.readString("Spieler "+(i+1)+" Bitte Spielersymbol wählen: "));
            }

            // Anfangs-Spielbrett ausgeben
            ausgabe();

            // Spieler abwechselnd am Zug
            int playeriterator;
            for (int i = 0;; i++) {
                playeriterator = i%anzSpieler;
                spielzug(spieler[playeriterator]);

                // Gewinnbedingung!!!!
                if(spieler[playeriterator].getScore().doubleValue() >= win.doubleValue()) {
                    System.out.println(spieler[playeriterator].getPSymbol().concat(" gewinnt!"));
                    System.out.println("Spiel beendet!\n");
                    Thread.sleep(2000);
                    switch (IO.readString("Für neues Spiel [y] drücken! ")) {
                        case "y":
                            continue gameloop;
                        default:
                            System.exit(0);
                    }
                }
            }
        }
    }

    public static void spielzug(Player x){
        x.bewegen();
        ausgabe();
    }

    public static void ausgabe(){
        // Spielfeld
        System.out.println(spielfeld.drawBoardString());
        // Spielstatus
        System.out.println("Punktestand:");
        for (int i = 0; i < spieler.length; i++) {
            System.out.println(spieler[i].getScoreString());
        }
        System.out.println();
    }

}
