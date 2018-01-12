import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Timo Volkmann (199267)
 * @author Patrick Pitz ()
 * @author Marcel Ambros ()
 * @version 3.0
 *
 */

public class Player {
    private int x;
    private int y;
    private Fraction score;
    private String playersymbol;

    public Player(int x, int y, String p) {
        this.x = x;
        this.y = y;
        this.score = new Fraction(0);
        this.playersymbol = p;
        Max.spielfeld.setValue(x,y, new Fraction(0));
    }

    public String getScoreString() {
        NumberFormat scoreFormat = NumberFormat.getNumberInstance(Locale.US);
        ((DecimalFormat) scoreFormat).applyPattern("###00.0");
        return scoreFormat.format(score.floatValue())+ " [" + playersymbol + "] ";
    }

    public Fraction getScore() {
        return this.score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPSymbol() {
        return playersymbol;
    }

    public void addScore() {
        this.score = this.score.add(Max.spielfeld.getValue(getX(), getY()));
    }

    public void bewegen() {
        String temp = playersymbol.concat(" ist am Zug. Bitte Richtung eingeben (n,s,w,o): ");
        bewegen(temp);
    }

    public void bewegen(String ask) {
        String richtung = IO.readString(ask);
        int posX = x;
        int posY = y;

        // Zug berechnen
        switch (richtung) {
            case "n":
                posY--;
                break;
            case "s":
                posY++;
                break;
            case "w":
                posX--;
                break;
            case "o":
                posX++;
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                bewegen();
        }

        if (isMovePossible(posX, posY)) {
            x = posX;
            y = posY;
            addScore();
            Max.spielfeld.setValue(posX,posY, new Fraction(0));
            Max.spielfeld.checkGameboard();
        } else {
            bewegen();
        }
    }

    private boolean isMovePossible(int moveX, int moveY) {
        if (moveX < 0 || moveY < 0 || moveY >= Max.boardh || moveX >= Max.boardw) {
            System.out.println("Spielfeldrand erreicht. Bitte andere Richtung wählen! ");
            return false;
        }
        for (Player p : Max.spieler) {
            if (equals(p)) continue;
            if ((p.x == moveX && p.y == moveY)) {
                System.out.println("Spieler können sich dieses Feld nicht teilen. Bitte andere Richtung wählen! ");
                return false;
            }
        }
        return true;
    }
}