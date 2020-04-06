package Functions;

public class LinearFunction extends Function {
    private double SX = 0;
    private double SXX = 0;
    private double SY = 0;
    private double SXY = 0;

    public LinearFunction(double[][] table) {
        super(table);
        init();
    }

    private void init() {
        n = table[0].length;
        for (int i = 0; i < n; i++) {
            SX = SX + table[0][i];
            SXX = SXX + table[0][i] * table[0][i];
            SY = SY + table[1][i];
            SXY = SXY + table[0][i] * table[1][i];
        }
        countA();
        countB();
        super.countFANDParam((Double x) -> a * x + b);
    }

    private void countA() {
        super.a = (SXY * n - SX * SY) / (SXX * n - SX * SX);
    }

    private void countB() {
        super.b = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);
    }

}
