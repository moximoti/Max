/**
 * @author Timo Volkmann
 */

import java.math.BigInteger;

final class Fraction extends Number implements Comparable<Fraction> {

    private BigInteger zaehler;
    private BigInteger nenner;

    public Fraction(BigInteger zaehler, BigInteger nenner) {
        if(nenner.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Nenner darf nicht Null sein!");
        }
        BigInteger ggt = getggT(zaehler, nenner);

        if(nenner.compareTo(BigInteger.ZERO) < 0) {
            this.zaehler = zaehler.negate().divide(ggt);
            this.nenner = nenner.negate().divide(ggt);
        } else {
            this.zaehler = zaehler.divide(ggt);
            this.nenner = nenner.divide(ggt);
        }

        if (this.zaehler.equals(BigInteger.ZERO)) this.nenner = BigInteger.ONE;

        /*if (this.zaehler.mod(this.nenner).equals(BigInteger.ZERO)) {
            this.zaehler = this.zaehler.divide(this.nenner);
            this.nenner = this.nenner.divide(this.nenner);
        }*/

    }

    public Fraction(BigInteger zaehler) {
        this.zaehler = zaehler;
        this.nenner = BigInteger.ONE;
    }

    public Fraction(long zaehler, long nenner) {
        this(BigInteger.valueOf(zaehler),BigInteger.valueOf(nenner));
    }
    public Fraction(long zaehler) {
        this(BigInteger.valueOf(zaehler));
    }

    //Main Methode: Test
    public static void main(String[] args) {
        Fraction test = new Fraction(BigInteger.valueOf(8),BigInteger.valueOf(2));
        Fraction test2 = new Fraction(BigInteger.valueOf(16),BigInteger.valueOf(-4));
        System.out.println(test.toString());
        System.out.println(test2.toString());
        /*System.out.println((test.divide(test)).toString());
        System.out.println((test.multiply(test)).toString());
        System.out.println((test.add(test)).toString());
        System.out.println((test.substract(test)).toString());
        System.out.println((test.divide(test2)).toString());
        System.out.println((test.multiply(test2)).toString());
        System.out.println(test.add(test2).substract(test.multiply(test2)).toString());
        System.out.println((test.substract(test2)).toString());*/

    }

    private BigInteger getggT(BigInteger x, BigInteger y) {
        return x.gcd(y);
    }

    private Fraction reduceAndNormalize() {
        /*BigInteger ggt = getggT(this.zaehler, this.nenner);
        if(nenner.compareTo(BigInteger.ZERO) < 0) {
            this.zaehler = zaehler.negate();
            this.nenner = nenner.negate();
        }
        if (zaehler.equals(0)) this.nenner = BigInteger.ONE;
        return new Fraction(this.zaehler.divide(ggt), this.nenner.divide(ggt));*/
        return new Fraction(this.zaehler, this.nenner);
    }

    Fraction add(Fraction r) {
        //Erweitern und addieren (z1*n2 + n1*z2) / (z1*z2)
        Fraction erg = new Fraction(this.zaehler.multiply(r.nenner).add(r.zaehler.multiply(this.nenner)), this.nenner.multiply(r.nenner));
        //Kürzen und zurückgeben
        return erg.reduceAndNormalize();
    }

    Fraction substract(Fraction r) {
        //Erweitern und subtrahieren (z1*n2 - n1*z2) / (z1*z2)
        Fraction erg = new Fraction(this.zaehler.multiply(r.nenner).subtract(r.zaehler.multiply(this.nenner)), this.nenner.multiply(r.nenner));
        //Kürzen und zurückgeben
        return erg.reduceAndNormalize();
    }

    Fraction multiply(Fraction r) {
        return new Fraction(this.zaehler.multiply(r.zaehler),this.nenner.multiply(r.nenner)).reduceAndNormalize();
    }

    Fraction divide(Fraction r) {
        if (r.zaehler.equals(BigInteger.ZERO)) throw new ArithmeticException("Durch 0 teilen nicht möglich!");
        return new Fraction(this.zaehler.multiply(r.nenner),this.nenner.multiply(r.zaehler)).reduceAndNormalize();
    }

    /*public String toString() {
        //return isInteger() ? String.valueOf(this.zaehler.divide(this.nenner)) : String.valueOf(this.zaehler+"/"+this.nenner);
        return String.valueOf(this.zaehler+"/"+this.nenner);
    }*/

    public String toString() {
        String z =this.zaehler.toString();
        String n =this.nenner.toString();
        if (this.zaehler.longValue() <= 9) z = " "+z+"";
        if (this.nenner.longValue() <= 9) n = " "+n+"";
        return this.zaehler.equals(BigInteger.ZERO)?"0":String.valueOf(z+"/"+n);
    }

    public float floatValue() {
        return (this.zaehler.floatValue())/(this.nenner.floatValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fraction)) return false;
        Fraction o = (Fraction) obj;
        return this.nenner.equals(o.nenner) && this.zaehler.equals(o.zaehler); // super.equals(obj);
    }

    @Override
    public int hashCode() {
        return zaehler.hashCode()+nenner.hashCode();
    }


    boolean isInteger() {
        return zaehler.mod(nenner).equals(BigInteger.ZERO);
    }

    public int compareTo(Fraction o) {
        return o.nenner.multiply(zaehler).compareTo(nenner.multiply(o.zaehler));
    }
    public int intValue() {
        return 0;
    }
    public long longValue() {
        return this.zaehler.longValue()/this.nenner.longValue();
    }
    public double doubleValue() {
        return (this.zaehler.doubleValue())/(this.nenner.doubleValue());
    }

    public BigInteger getNenner() {
        return nenner;
    }

    public BigInteger getZaehler() {
        return zaehler;
    }
}