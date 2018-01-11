public class Test {
    public static void main(String[] args) {
        Fraction randomFrac;
        int sum, counterA = 0,counterB = 0,counterC = 0,counterD = 0;
        for (int i = 0; i < 1000; i++) {
            randomFrac = Gameboard.randomFrac();

            System.out.print(randomFrac);
            System.out.print("  ");
            System.out.println(randomFrac.doubleValue());

            //if (randomFrac.floatValue() < 2) counterA++;
            //if (randomFrac.floatValue() < 5) counterB++;
            if (randomFrac.floatValue() <= 3) counterC++;
            if (randomFrac.floatValue() > 3) counterD++;
        }
        sum = counterA + counterB + counterC + counterD;
        System.out.println("Counter A: "+(int)((((float)counterA)/sum)*100));
        System.out.println("Counter B: "+(int)((((float)counterB)/sum)*100));
        System.out.println("Counter C: "+(int)((((float)counterC)/sum)*100));
        System.out.println("Counter D: "+(int)((((float)counterD)/sum)*100));

    }
}
