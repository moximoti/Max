public class Player {
    private int positionx = 0;
    private int positiony = 0;
    private Fraction score = new Fraction(0);
    private String playersymbol = "";

    public Player(int x, int y, Fraction s, String p) {
        this.positionx = x;
        this.positiony = y;
        this.score = s;
        this.playersymbol = p;
    }

    public void setPosition(int x, int y){
        this.positionx = x;
        this.positiony = y;
    }

    public String getScoreString() {
        //return this.score.toString().concat(" ["+playersymbol+"] ");
        return String.valueOf(this.score.floatValue()).concat(" ["+playersymbol+"] ");
    }
    public Fraction getScore() {
        return this.score;
    }

    public int getX(){
        return this.positionx;
    }
    public int getY(){
        return this.positiony;
    }
    public String getPSymbol(){
        return this.playersymbol;
    }
    //TODO in Hauptprogramm übernehmen
    public void spielzug(){
        System.out.print(getPSymbol()+" ");
        bewegen();
        System.out.println("Neue Punktzahl: "+getScoreString());
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public void setScore(){
        this.score = this.score.add(Max.fracBoard[this.getX()][this.getY()]);
    }

    public void bewegen() {
        String temp = "Bitte Richtung eingeben (n,s,w,o): ";
        bewegen(temp);
    }
    public void bewegen(String ask) {
        String richtung = IO.readString(ask);
        int x =0;
        int y =0;
        switch (richtung) {
            case "n":
                if (this.positiony > 0)
                    y -= 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "s":
                if (this.positiony+1 < Max.boardh)
                    y += 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "w":
                if (this.positionx > 0)
                    x -= 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            case "o":
                if (this.positionx+1 < Max.boardw)
                    x += 1;
                else bewegen("Spielfeldrand erreicht! Bitte andere Richtung wählen: ");
                break;
            default:
                bewegen();
        }
        if (Max.p1.getX()==Max.p2.getX() && Max.p1.getY()==Max.p2.getY()) {
            bewegen("Spieler können sich dieses Feld nicht teilen! Bitte andere Richtung wählen: ");
            }
        else {
            Max.fracBoard[this.getX()][this.getY()] = new Fraction(0);
            setPosition(positionx+x,positiony+y);
            setScore();
        }
    }
}
