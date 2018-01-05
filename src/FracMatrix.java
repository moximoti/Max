/**
 * @author Timo Volkmann
 * @version 1.0
 */
/*
public class FracMatrix {
    private Fraction[][] cmatrix;

    public static void main(String[] args) {

        FracMatrix M0 = new FracMatrix(4, 4);
        M0.setValue(1, 2, 1.0);
        M0.setValue(2, 3, 1.0);
        M0.setValue(3, 4, 1.0);
        M0.setValue(4, 1, -1.0);
        M0.setValue(4, 2, 1.0);
        M0.setValue(4, 3, -2.0);
        M0.setValue(4, 4, 3.0);

        IO.writeln("Aufgabe 4. Matrix Input: ");
        IO.writeln(M0.toString());

        IO.writeln("Matrix potenzieren: ");
        IO.writeln("");
        FracMatrix M1 = M0;
        for (int i = 2; i <= 20; i++) {
            M1 = M1.multiply(M0);
        }
        IO.writeln(M1.toString());
    }

    private FracMatrix(int m, int n) {
        this.cmatrix = new Fraction[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.cmatrix[i][j] = new Fraction(0,0);
            }
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < cmatrix.length; i++) {
            s += "[";
            for (int j = 0; j < cmatrix[i].length; j++) {
                s += " "+ cmatrix[i][j];
            }
            s += " ]\n";
        }
        return s;
    }
    private void setValue(int i, int j, long x1, long x2) {
        cmatrix[i-1][j-1] = new Fraction(x1,x2);
    }
    private Fraction getValue(int i, int j) {
        return cmatrix[i-1][j-1];
    }

    private FracMatrix add(FracMatrix mat) {
        FracMatrix temp = new FracMatrix(this.cmatrix.length, this.cmatrix[0].length);
        for (int i = 0; i < this.cmatrix.length; i++) {                                 // # Zeilen, i: Zeilenindex
            for (int j = 0; j < this.cmatrix[i].length; j++) {                          // # Spalten, j: Spaltenindex
                temp.cmatrix[i][j] = this.cmatrix[i][j].add(mat.cmatrix[i][j]);
            }
        }
        return temp;
    }
    private FracMatrix multiply(FracMatrix mat) {
        FracMatrix temp = new FracMatrix(this.cmatrix.length, mat.cmatrix[0].length);
        for (int i = 0; i < this.cmatrix.length; i++) {
            for (int j = 0; j < mat.cmatrix[0].length; j++) {
                for (int k = 0; k < this.cmatrix[0].length; k++) {
                    temp.cmatrix[i][j] = temp.cmatrix[i][j].add(this.cmatrix[i][k].multiply(mat.cmatrix[k][j]));
                }
            }
        }
        return temp;
    }
}
*/