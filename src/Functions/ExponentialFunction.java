package Functions;

public class ExponentialFunction extends Function {
    private double SX = 0;
    private double SXX = 0;
    private double SLNY = 0;
    private double SLNYX = 0;


    public ExponentialFunction(double[][] table) {
        super(table);
        init();
    }

    private void init() {
        n = table[0].length;
        for (int i = 0; i < n; i++) {
            SX = SX + table[0][i];
            SXX = SXX + table[0][i] * table[0][i];
            SLNY = SLNY + Math.log(table[1][i]);
            SLNYX = SLNYX + Math.log(table[1][i]) * table[0][i];
        }
        countA();
        countB();
        countFANDParam((Double x) -> a * Math.exp(b * x));
    }

    private void countA() {
        double LNA = (SLNY * SXX - SLNYX * SX) / (n * SXX - SX * SX);
        a = Math.exp(LNA);
    }

    private void countB() {
        b = (n * SLNYX - SLNY * SX) / (n * SXX - SX * SX);
    }

}
