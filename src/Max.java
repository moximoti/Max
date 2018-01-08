import java.util.Random;

public class Max {
    // Settings
    static final Fraction win = new Fraction(80);
    static final int boardw = 8; // Spielbrett Breite
    static final int boardh = 8; // Spielbrett Höhe
    static final int anzSpieler = 4; // Anzahl Spieler

    // Globale Variablen
    public static Fraction[][] fracBoard = new Fraction[boardw][boardh];
    // public static Spielfeld board = new Spielfeld(HöheUndBreite_8, );
    public static Player[] spieler = new Player[anzSpieler];

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
        fracBoard = initFractionsBoard();
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

    public static String[][] convertFractionBoard(Fraction[][] board) {
        String[][] temp = new String[boardw][boardh];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                //temp[x][y] = String.valueOf(board[x][y].floatValue());
                temp[x][y] = String.valueOf(board[x][y]);
            }
        }
        return temp;
    }

    public static String drawBoardString(String[][] board) {
        // get Player Positions
        for (int i = 0; i < spieler.length; i++) {
            board[spieler[i].getX()][spieler[i].getY()] = spieler[i].getPSymbol();
        }

        String matrixoutput = "";
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                String out = "";
                // Strings auffüllen
                for (int i = board[x][y].length(); i < 5; i++) { //vorher i < 5
                    out = out.concat(" ");
                }
                //System.out.print(board[x][y]+out +"  ");
                //System.out.print(out+board[x][y]+"  ");
                matrixoutput += out+board[x][y]+"  ";
                //System.out.print(x+"(x) "+y+"()    ");
            }
            matrixoutput += "\n\n"; //System.out.println();
            //System.out.println();
        }
        return matrixoutput;
    }

    public static Fraction[][] initFractionsBoard() {
        Fraction[][] fracMatrix = new Fraction[boardw][boardh];
        for (int y = 0; y < boardh; y++) {
            for (int x = 0; x < boardw; x++) {
                 fracMatrix[x][y] = randomFrac();
            }
        }
    return fracMatrix;
    }

    public static Fraction randomFrac(){
        Fraction random;
        do {
            random = new Fraction(Math.abs(new Random().nextLong()%99)+1,Math.abs(new Random().nextLong()%99)+1);
        } while (random.longValue()<=1 || random.longValue()>=10);

        return random;
    }

    public static void spielzug(Player x){
        x.bewegen();
        ausgabe();
    }

    public static void ausgabe(){
        // Spielfeld
        System.out.println(drawBoardString(convertFractionBoard(fracBoard)));
        // Spielstatus
        System.out.println("Punktestand:");
        for (int i = 0; i < spieler.length; i++) {
            System.out.println(spieler[i].getScoreString());
        }
        System.out.println();
    }

}
