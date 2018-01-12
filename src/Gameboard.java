/**
 * @author Timo Volkmann (199267)
 * @author Patrick Pitz ()
 * @author Marcel Ambros ()
 * @version 3.0
 *
 */

import java.util.Random;

public class Gameboard {
    public Fraction[][] board;
    int hoehe;
    int breite;

    // Konstruktor
    public Gameboard(int boardw, int boardh) {
        this.hoehe = boardh;
        this.breite = boardw;
        this.board = new Fraction[boardh][boardw];
        this.initFractionsBoard();
    }

    public String drawBoardString() {

        // Fraction Matrix in String Matrix umwandeln
        String[][] temp = new String[hoehe][breite];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                temp[y][x] = String.valueOf(board[y][x]);
            }
        }

        // get Player Positions
        for (Player p : Max.spieler) {
            temp[p.getY()][p.getX()] = p.getPSymbol();
        }

        // Trenner zwischen Feldern einfügen
        String divider = "+";
        for (int i = 0; i < breite; i++) {
            divider += "-------+";
        }

        // Spielfeld zeichnen
        String matrixoutput = divider+"\n";
        for (int y = 0; y < temp.length; y++) {
            matrixoutput += "| ";
            for (int x = 0; x < temp[y].length; x++) {

                // Fleder mit Leerzeichen auffüllen, wenn kürzer als 5
                String feld = "";
                for (int i = temp[y][x].length(); i < 5; i++) {
                    feld += " ";
                }

                matrixoutput += feld+temp[y][x]+" | ";
            }
            matrixoutput += "\n"+divider+"\n";
        }
        return matrixoutput;
    }

    // Spielfeld erstellen und mit Zufallszahlen belegen
    public void initFractionsBoard() {
        board = new Fraction[hoehe][breite];
        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                board[y][x] = randomFrac();
            }
        }
    }

    public Fraction getValue (int x, int y){
        return board[y][x];
    }

    public void setValue (int x, int y, Fraction value){
        board[y][x] = value;
    }

    // Neue Zufallszahlen, wenn alle Felder leer sind
    public void checkGameboard(){
        boolean allEmpty = true;
        for (int y = 0; y < hoehe; y++) {
            for (int x = 0; x < breite; x++) {
                if (board[y][x].floatValue() != 0) allEmpty = false;
            }
        }
        if(allEmpty) {
            initFractionsBoard();
            // An Spielerpositionen wieder mit 0 überschreiben
            for (Player p : Max.spieler) {
                setValue(p.getX(),p.getY(), new Fraction(0));
            }
        }
    }

    public static Fraction randomFrac(){
        Fraction frac;
        int zaehler, nenner;
        do {
            zaehler = Math.abs(new Random().nextInt(99))+1;
            nenner = Math.abs(new Random().nextInt(99))+1;
            frac = new Fraction(zaehler,nenner);
        } while (frac.floatValue()<1 || frac.floatValue()>10);

        return frac;
    }

}
