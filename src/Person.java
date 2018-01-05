/**
 * @author Timo Volkmann
 * @version 2.0
 * @date 05.12.2017
 */
import java.util.*;

public class Person implements Comparable<Person>, Cloneable, SimpleTreeNode {
    private String name, vorname, beruf, lfarbe, ltier;
    private int geburtsjahr;
    private float groesse;
    private float gehalt;

    public Person(String vorname, String name, String beruf, String lfarbe, String ltier, int gjahr, float groesse, float gehalt) {
        this.vorname=vorname;
        this.name=name;
        this.beruf=beruf;
        this.lfarbe=lfarbe;
        this.ltier=ltier;
        this.geburtsjahr=gjahr;
        this.groesse=groesse;
        this.gehalt=gehalt;

    }

    public Person() throws Exception {
        IO.writeln("Neue Person erzeugen... Bitte folgende Daten angeben! ");
        this.vorname = IO.promptAndRead("Vorname: ");
        this.name= IO.promptAndRead("Nachname: ");
        this.beruf= IO.promptAndRead("Beruf: ");
        this.lfarbe= IO.promptAndRead("Lieblingsfarbe: ");
        this.ltier= IO.promptAndRead("Lieblingstier: ");
        this.geburtsjahr= IO.readInt("Geburtsjahr: ");
        this.groesse= IO.readFloat("Größe: ");
        this.gehalt= IO.readFloat("Gehalt: ");
        IO.writeln("");

    }

    public String toString() {
        int alter = new GregorianCalendar().get(Calendar.YEAR)-this.geburtsjahr;
        String tempgroesse;
        String tempgehalt;

        if (this.groesse <= 1.52) tempgroesse = "klein";
        else if (this.groesse < 1.83) tempgroesse = "mittel";
        else tempgroesse = "groß";

        if (this.gehalt <= 1200) tempgehalt = "wenig";
        else if (this.gehalt < 3000) tempgehalt = "mittel";
        else tempgehalt = "viel";

        return ("\nVorname: \t\t"+this.vorname+"\n"+
                "Nachname: \t\t"+this.name+"\n"+
                "Beruf: \t\t\t"+this.beruf+"\n"+
                "Alter: \t\t\t"+alter+"\n"+
                "Größe: \t\t\t"+tempgroesse+"\n"+
                "Gehalt: \t\t"+tempgehalt+"\n"+
                "Lieblingsfarbe: "+this.lfarbe+"\n"+
                "Lieblingstier: \t"+this.ltier+"\n");
    }
    public void compareWith(Person h) {
        int jahr = new GregorianCalendar().get(Calendar.YEAR);
        int altersunterschied = (jahr - this.geburtsjahr) - (jahr - h.geburtsjahr);
        int gehaltunterschied = (int) (this.gehalt-h.gehalt);
        float groessenunterschied = this.groesse-h.groesse;

        if (altersunterschied == 0) IO.writeln(this.vorname+" und "+h.vorname+" sind gleich alt.");
        else if (altersunterschied == 1) IO.writeln(this.vorname+" ist "+altersunterschied+" Jahr älter als "+h.vorname+".");
        else if (altersunterschied > 1) IO.writeln(this.vorname+" ist "+altersunterschied+" Jahre älter als "+h.vorname+".");
        else if (altersunterschied == -1) IO.writeln(this.vorname+" ist "+Math.abs(altersunterschied)+" Jahr jünger als "+h.vorname+".");
        else if (altersunterschied < -1) IO.writeln(this.vorname+" ist "+Math.abs(altersunterschied)+" Jahre jünger als "+h.vorname+".");

        if (gehaltunterschied == 0) IO.writeln(this.vorname+" und "+h.vorname+" verdienen gleich viel.");
        else if (gehaltunterschied > 0) IO.writeln(this.vorname+" verdient "+gehaltunterschied+" Euro mehr als "+h.vorname+".");
        else if (gehaltunterschied < 0) IO.writeln(this.vorname+" verdient "+Math.abs(gehaltunterschied)+" Euro weniger als "+h.vorname+".");

        if (groessenunterschied == 0) IO.writeln(this.vorname+" und "+h.vorname+" sind gleich groß.");
        else if (groessenunterschied > 0) IO.writeln(this.vorname+" ist "+groessenunterschied+" m größer als "+h.vorname+".");
        else if (groessenunterschied < 0) IO.writeln(this.vorname+" ist "+Math.abs(groessenunterschied)+" m kleiner als "+h.vorname+".");

    }
    public void gehtAusMit(Person h) throws Exception {
        IO.writeln(this.vorname+" geht mit "+h.vorname+" aus.");
    }
    public static void main(String[] args) throws Exception {
        Person[] persons = new Person[10];
        String[] names = {"A","B","C","D","E","F","G","E","H","I","J","K","L","M"};

        for(int i=0;i<persons.length;i++)
        {
            int random = (int) Math.floor(Math.random()*names.length);
            persons[i] = new Person(names[random],"<Nachname>","<Beruf>","<Farbe>","<Tier>",1950+(int)(Math.random()*50),(float)Math.random()*2,(float)(Math.random()*10000));
        }

        // Test
        printArray(persons);
        System.out.println("\nSortiert: \n");
        bubbleSort(persons);
        printArray(persons);
        persons[0].addChild(persons[1]);
        persons[0].addChild(persons[2]);
        persons[0].addChild(persons[3]);
        persons[0].addChild(persons[4]);
        persons[4].addChild(persons[6]);
        persons[4].addChild(persons[7]);
        persons[7].addChild(persons[8]);
        persons[8].addChild(persons[9]);
        printTree(persons[0]);
        System.out.println();
        Person Klonkrieger =persons[0].clone();
        printTree(persons[0]);
        System.out.println();
        printTree(Klonkrieger);
        System.out.println();
        System.out.println(persons[0].vorname+" hat "+persons[0].getChildCnt()+" Child-Elemente");
    }

    public static void bubbleSort (Comparable[] comps) {
        boolean sorted;
        do {sorted = true;
            for (int i=0; i < comps.length-1; i++) {
                if (comps[i].compareTo(comps[i+1]) > 0){ // steht falsch?
                    Comparable tmp = comps[i];// tauschen
                    comps[i] = comps[i+1];// .. in 3 ..
                    comps[i+1] = tmp;// Schritten
                    sorted = false; // und noch nicht fertig
                }
            }
        } while (!sorted); // wiederholen solange nicht fertig
    } // end bubbleSort
    
    @Override
    public int compareTo(Person o) {
        return vorname.compareTo(o.vorname);
    }

    private SimpleTreeNode treeNode=new DefaultTreeNode("");
    @Override
    public void addChild(SimpleTreeNode child) {
        treeNode.addChild(child);
    }

    @Override
    public int getChildCnt() {
        return treeNode.getChildCnt();
    }

    @Override
    public SimpleTreeNode getChild(int pos) {
        return treeNode.getChild(pos);
    }
    @Override
    public Person clone() {
        Person p=new Person(vorname,name,beruf,lfarbe,ltier,geburtsjahr,groesse,gehalt);

        for(int i=0;i<this.getChildCnt();i++)
        {
            p.addChild(((Person)getChild(i)).clone());
        }
        return p;
    }
    
    public static void printTree(Person n){
        printTree(n,"");
    }
    private static void printTree(Person n,String einruecken){
        String ausgabe = einruecken+n.vorname+", "+n.name;
        System.out.println(ausgabe);
        if (!einruecken.isEmpty()) {
            einruecken = einruecken.replace("|-","  ");
        }
        for(int i=0;i<n.getChildCnt();i++)
        {
            printTree((Person)n.getChild(i),einruecken+"|- ");
        }
    }
    
    private static void printArray(Person[] personen) {
        for(int i=0;i<personen.length;i++)
            System.out.println(personen[i].toString());
    }


}
