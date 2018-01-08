import java.util.Random;

public class Gameboard {
    public Fraction[][] board;
    int hoehe;
    int breite;

    public Gameboard(int boardw, int boardh) {
        this.hoehe = boardh;
        this.breite = boardw;
        this.board = new Fraction[boardw][boardh];
    }

    public String drawBoardString() {

        String[][] temp = new String[breite][hoehe];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                //temp[x][y] = String.valueOf(board[x][y].floatValue());
                temp[x][y] = String.valueOf(board[x][y]);
            }
        }

        // get Player Positions
        for (int i = 0; i < Max.spieler.length; i++) {
            temp[Max.spieler[i].getX()][Max.spieler[i].getY()] = Max.spieler[i].getPSymbol();
        }

        String matrixoutput = "";
        for (int y = 0; y < temp.length; y++) {
            for (int x = 0; x < temp[y].length; x++) {
                String out = "";

                // Strings auffüllen
                for (int i = temp[x][y].length(); i < 5; i++) { //vorher i < 5
                    out = out.concat(" ");
                }
                matrixoutput += out+temp[x][y]+"  ";
            }
            matrixoutput += "\n\n";
        }
        return matrixoutput;
    }

    public void initFractionsBoard() {
        board = new Fraction[this.breite][this.hoehe];
        for (int y = 0; y < this.hoehe; y++) {
            for (int x = 0; x < this.breite; x++) {
                board[x][y] = randomFrac();
            }
        }
    }

    public static Fraction randomFrac(){
        Fraction random;
        do {
            random = new Fraction(Math.abs(new Random().nextLong()%99)+1,Math.abs(new Random().nextLong()%99)+1);
        } while (random.longValue()<=1 || random.longValue()>=10);

        return random;
    }
    public Fraction getValue (int x, int y){
        return board[x][y];
    }

    public void setValue (int x, int y, Fraction value){
        board[x][y] = value;
    }


}
