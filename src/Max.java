import java.util.Random;

public class Max {
    // Settings
    static final Fraction win = new Fraction(80);
    static final int boardw = 8; // Spielbrett Breite
    static final int boardh = 8; // Spielbrett Höhe
    static final int anzSpieler = 2; // Anzahl Spieler

    // Globale Variablen
    //public static Fraction[][] fracBoard = new Fraction[boardw][boardh];
    public static Player[] spieler = new Player[anzSpieler];
    public static Gameboard spielfeld = new Gameboard(boardw, boardh);


    public static void main(String[] args) {
        // Spielerobjekte erstellen

        for (int i = 0; i < spieler.length; i++) {
            if (i==0) spieler[i] = new Player(4, 3, "- B -");
            if (i==1) spieler[i] = new Player(3,4,"- W -");
            if (i==2) spieler[i] = new Player(0,0,"- X -");
            if (i==3) spieler[i] = new Player(7,7,"- Y -");
            if (i>=4) spieler[i] = new Player(new Random().nextInt(7),new Random().nextInt(7),IO.readString("Spieler "+(i+1)+" Bitte Spielersymbol wählen: "));
        }

        // Spielbrett erstellen mit Zufallszahlen belegen und ausgeben
        spielfeld.initFractionsBoard();
        ausgabe();

        // Spieler abwechselnd am Zug
        int playeriterator;
        for (int i = 0;; i++) {
            playeriterator = i%anzSpieler;
            spielzug(spieler[playeriterator]);

            // Gewinnbedingung!!!!
            if(spieler[playeriterator].getScore().doubleValue() >= win.doubleValue()) {
                System.out.println(spieler[playeriterator].getPSymbol().concat(" gewinnt!"));
                System.out.println("Spiel beendet!");
                break;
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
