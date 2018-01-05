/**
 * @author Timo Volkmann
 * @version 1.0
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

class IO {
    public static void write(String s) {
        writeAndFlush(s);
    }
    public static void writeln(String s) {
        writeAndFlush(s+"\n");
    }
    public static String promptAndRead(String s) throws Exception {
        writeAndFlush(s);
        return br.readLine();
    }
    private static void writeAndFlush(String s) {
        System.out.print(s);
        System.out.flush();
    }

    public static long readLong(String s) {
        String temp;
        try {
            temp = promptAndRead(s).trim();
            return Long.parseLong(temp);
        } catch (Exception e) {
            return readLong("Kein gültiger Long! Bitte erneut eingeben! ");
        }
    }

    public static String readString(String s) {
        String temp;
        try {
            temp = promptAndRead(s).trim();
            return temp;
        } catch (Exception e) {
            return readString("Kein gültiger String! Bitte erneut eingeben! ");
        }
    }

    public static double readDouble(String s) {
        String temp = null;
        try {
            temp = promptAndRead(s).trim();
            if (temp.contains(",")) temp = temp.replace(",",".");
            return Double.parseDouble(temp);
        } catch (Exception e) {
            return readDouble("Kein gültiger Float! Bitte erneut eingeben! ");
        }
    }

    public static int readInt(String s) {
        String temp;
        try {
            temp = promptAndRead(s).trim();
            return Integer.parseInt(temp);
        } catch (Exception e) {
            return readInt("Kein gültiger Integer! Bitte erneut eingeben! ");
        }
    }

    public static float readFloat(String s) {
        String temp = null;
        try {
            temp = promptAndRead(s).trim();
            if (temp.contains(",")) temp = temp.replace(",",".");
            return Float.parseFloat(temp);
        } catch (Exception e) {
            return readFloat("Kein gültiger Float! Bitte erneut eingeben! ");
        }
    }

    public static BigInteger readBigI(String s) {
        String temp = null;
        try {
            temp = promptAndRead(s).trim();
            return new BigInteger(temp);
        } catch (Exception e) {

            return readBigI("Kein gültiger BigInteger! Bitte erneut eingeben! ");
        }
    }

    public static BigDecimal readBigD(String s) throws Exception {
        String temp = null;
        try {
            temp = promptAndRead(s).trim();
            if (temp.contains(",")) temp = temp.replace(",",".");
            return new BigDecimal(temp);
        } catch (Exception e) {
            return readBigD("Kein gültiger BigDecimal! Bitte erneut eingeben! ");
        }
    }

    public static Fraction readFrac(String s) {
        String in = null;
        BigInteger zaehler, nenner;
        try {
            in = promptAndRead(s);
            if (!in.contains("/")) {
                zaehler = new BigInteger(in);
                return new Fraction(zaehler);
            }
            zaehler = new BigInteger(in.substring(0,in.indexOf("/")));
            //System.out.println(zaehler);
            nenner = new BigInteger(in.substring(in.indexOf("/")+1,in.length()));
            //System.out.println(nenner);
            return new Fraction(zaehler, nenner);
        } catch (Exception e) {
            return readFrac("Kein Bruch! Erneut eingeben: ");
        }
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(readFrac("Bruch eingeben: "));
        //System.out.println(readBigI("BigI eingeben: "));
    }

    private static BufferedReader br =
            new BufferedReader (new InputStreamReader (System.in));
}