package Functions;

public class LogarithmicFunction extends Function {
    private double SY = 0;
    private double SLNX = 0;
    private double SLNXLNX = 0;
    private double SYLNX = 0;

    public LogarithmicFunction(double[][] table) {
        super(table);
        init();
    }

    private void init() {
        n = table[0].length;
        for (int i = 0; i < n; i++) {
            SY = SY + table[1][i];
            SLNX = SLNX + Math.log(table[0][i]);
            SLNXLNX = SLNXLNX + Math.log(table[0][i]) * Math.log(table[0][i]);
            SYLNX = SYLNX + table[1][i] * Math.log(table[0][i]);
        }
        countA();
        countB();
        countFANDParam((Double x) -> a * Math.log(x) + b);
    }

    private void countA() {
        a = (SY * SLNXLNX - SYLNX * SLNX) / (n * SLNXLNX - SLNX * SLNX);
    }

    private void countB() {
        b = (n * SYLNX - SY * SLNX) / (n * SLNXLNX - SLNX * SLNX);
    }

}
