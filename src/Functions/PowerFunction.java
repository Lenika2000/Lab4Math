package Functions;

public class PowerFunction extends Function {
    private double SLNY = 0;
    private double SLNX = 0;
    private double SLNYLNX = 0;
    private double SLNXLNX = 0;

    public PowerFunction(double[][] table) {
        super(table);
        init();
    }

    private void init() {
        n = table[0].length;
        for (int i = 0; i < n; i++) {
            SLNY = SLNY + Math.log(table[1][i]);
            SLNX = SLNX + Math.log(table[0][i]);
            SLNYLNX = SLNYLNX + Math.log(table[1][i]) * Math.log(table[0][i]);
            SLNXLNX = SLNXLNX + Math.log(table[0][i]) * Math.log(table[0][i]);
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
