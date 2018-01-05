import java.util.Random;

public class Max {

    static Fraction win = new Fraction(80);
    static int boardw = 8; // Spielbrett Breite
    static int boardh = 8; // Spielbrett Höhe
    static Fraction[][] fracBoard = new Fraction[boardw][boardh];

    static Player p1 = new Player(4,3,new Fraction(0),"--B--");
    static Player p2 = new Player(3,4,new Fraction(0),"--W--");



    public static void main(String[] args) {
        // Spielbrett erstellen und mit Zufallszahlen belegen
        fracBoard = initFractionsBoard();
        drawBoardString(storeBoardString(fracBoard));

        for (int i = 0;; i++) {
            if (i%2==0) {
                p1.spielzug();
                drawBoardString(storeBoardString(fracBoard));
                if(p1.getScore().doubleValue() >= win.doubleValue()) {
                    System.out.println(p1.getPSymbol().concat(" gewinnt!"));
                    System.out.println("Spiel beendet!");
                    break;
                }
            }
            if (i%2==1) {
                p2.spielzug();
                drawBoardString(storeBoardString(fracBoard));
                if(p2.getScore().doubleValue() >= win.doubleValue()) {
                    System.out.println(p2.getPSymbol().concat(" gewinnt!"));
                    System.out.println("Spiel beendet!");
                    break;
                }
            }

        }

    }

    // nicht benötigt
    public static void drawBoardFrac(Fraction[][] board) {

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                String out = "";
                for (int i = board[x][y].toString().length(); i < 5; i++) {
                    out = out.concat("");
                }
                System.out.print(out+board[x][y].longValue()+"  ");
                //System.out.print(x+"(x) "+y+"()    ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public static String[][] storeBoardString(Fraction[][] board) {
        String[][] temp = new String[boardw][boardh];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                //temp[x][y] = String.valueOf(board[x][y].floatValue());
                temp[x][y] = String.valueOf(board[x][y]);
            }
        }
        return temp;
    }

    public static void drawBoardString(String[][] board) {
        board[p1.getX()][p1.getY()] = p1.getPSymbol();
        board[p2.getX()][p2.getY()] = p2.getPSymbol();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                String out = "";
                for (int i = board[x][y].length(); i < 5; i++) { //vorher i < 5
                    out = out.concat(" ");
                }
                //System.out.print(board[x][y]+out +"  ");
                System.out.print(out+board[x][y]+"  ");
                //System.out.print(x+"(x) "+y+"()    ");
            }
            System.out.println();
            System.out.println();
        }
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
            random = new Fraction(Math.abs(new Random().nextLong()%99),Math.abs(new Random().nextLong()%99)+1);
        } while (random.longValue()<=1 || random.longValue()>=10);

        return random;
    }


}
