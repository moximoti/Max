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
    }

    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public String getScoreString() {
        //return this.score.toString().concat(" ["+playersymbol+"] ");
        return String.valueOf(this.score.floatValue()).concat(" ["+playersymbol+"] ");
    }
    public Fraction getScore() {
        return this.score;
    }

    public int getX(){
        return this.posX;
    }
    public int getY(){
        return this.posY;
    }
    public String getPSymbol(){
        return this.playersymbol;
    }

    public void setScore(){
        //this.score = this.score.add(Max.spielfeld.board[this.getX()][this.getY()]);
        this.score = this.score.add(Max.spielfeld.getValue(getX(),getY()));
    }

    public void bewegen() {
        String temp = getPSymbol().concat(" ist am Zug. Bitte Richtung eingeben (n,s,w,o): ");
        bewegen(temp);
    }
    public void bewegen(String ask) {
        String richtung = IO.readString(ask);
        int x =0;
        int y =0;

        // Zug berechnen
        switch (richtung) {
            case "n":
                if (this.posY > 0)
                    y = -1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "s":
                if (this.posY +1 < Max.boardh)
                    y = 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "w":
                if (this.posX > 0)
                    x = -1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "o":
                if (this.posX +1 < Max.boardw)
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
        if (checkPlayerPosition(this,this.getX()+x,this.getY()+y)) {
            Max.spielfeld.setValue(getX(),getY(), new Fraction(0));
            setPosition(this.posX +x,this.posY +y);
            setScore();
            System.out.println();
        } else {
            bewegen("Spieler können sich dieses Feld nicht teilen! Bitte andere Richtung wählen: ");
            }
    }

    public static boolean checkPlayerPosition(Player p, int x, int y) {
        boolean notinUse = true;

        for (int i = 0; i < Max.spieler.length; i++) {
            if (p.getPSymbol().equals(Max.spieler[i].getPSymbol())) continue;
            if (Max.spieler[i].getX() == x & Max.spieler[i].getY() == y) notinUse = false;
        }
        return notinUse;
    }



}
