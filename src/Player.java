public class Player {
    private int posX;
    private int posY;
    private Fraction score;
    private String playersymbol;

    public Player(int x, int y, String p) {
        this.posX = x;
        this.posY = y;
        this.score = new Fraction(0);
        this.playersymbol = p;
        Max.spielfeld.setValue(posX,posY, new Fraction(0));
    }

    public String getScoreString() {
        return String.valueOf(score.floatValue()).concat(" ["+playersymbol+"] ");
    }
    public Fraction getScore() {
        return score;
    }

    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }
    public String getPSymbol(){
        return playersymbol;
    }

    public void addScore(){
        score = score.add(Max.spielfeld.getValue(getX(),getY()));
    }

    public void bewegen() {
        String temp = playersymbol.concat(" ist am Zug. Bitte Richtung eingeben (n,s,w,o): ");
        bewegen(temp);
    }
    public void bewegen(String ask) {
        String richtung = IO.readString(ask);
        int x =0;
        int y =0;

        // Zug berechnen
        switch (richtung) {
            case "n":
                if (posY > 0)
                    y = -1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "s":
                if (posY +1 < Max.boardh)
                    y = 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "w":
                if (posX > 0)
                    x = -1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "o":
                if (posX +1 < Max.boardw)
                    x = 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                bewegen();
        }

        // Zug ausführen wenn Feld frei
        if (checkPlayerPosition(getX()+x,getY()+y)) {
            posX += x;
            posY += y;
            addScore();
            Max.spielfeld.setValue(posX,posY, new Fraction(0));
            Max.spielfeld.checkGameboard();
            System.out.println();
        } else {
            bewegen("Spieler können sich dieses Feld nicht teilen! Bitte andere Richtung wählen: ");
            }
    }

    public boolean checkPlayerPosition(int x, int y) {
        boolean notInUse = true;

        for (int i = 0; i < Max.spieler.length; i++) {
            // eigene Position überspringen
            if (equals(Max.spieler[i])) continue;
            // wenn Position übereinstimmt, setze notInUse auf false
            if (Max.spieler[i].posX == x & Max.spieler[i].posY == y) notInUse = false;
        }
        return notInUse;
    }



}
