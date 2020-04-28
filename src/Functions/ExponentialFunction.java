package Functions;

public class ExponentialFunction extends Function {
    private double SX = 0;
    private double SXX = 0;
    private double SLNY = 0;
    private double SLNYX = 0;


    public ExponentialFunction(double[] X, double [] Y) {
        super(X,Y);
        init();
    }

    private void init() {
        for (int i = 0; i < n; i++) {
            SX = SX + X[i];
            SXX = SXX + X[i] * X[i];
            SLNY = SLNY + Math.log(Y[i]);
            SLNYX = SLNYX + Math.log(Y[i]) * X[i];
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
