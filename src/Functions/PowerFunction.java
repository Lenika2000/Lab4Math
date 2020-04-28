package Functions;

public class PowerFunction extends Function {
    private double SLNY = 0;
    private double SLNX = 0;
    private double SLNYLNX = 0;
    private double SLNXLNX = 0;

    public PowerFunction(double[] X, double [] Y) {
        super(X,Y);
        init();
    }

    private void init() {
        for (int i = 0; i < n; i++) {
            SLNY = SLNY + Math.log(Y[i]);
            SLNX = SLNX + Math.log(X[i]);
            SLNYLNX = SLNYLNX + Math.log(Y[i]) * Math.log(X[i]);
            SLNXLNX = SLNXLNX + Math.log(X[i]) * Math.log(X[i]);
        }
        countA();
        countB();
        countFANDParam((Double x) -> a * Math.pow(x, b));
    }

    private void countA() {
        double LNA = (SLNY * SLNXLNX - SLNYLNX * SLNX) / (n * SLNXLNX - SLNX * SLNX);
        a = Math.exp(LNA);
    }

    private void countB() {
        b = (n * SLNYLNX - SLNY * SLNX) / (n * SLNXLNX - SLNX * SLNX);
    }

}
