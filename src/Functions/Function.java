package Functions;

public class Function {
    double[][] table;
    protected int n;
    protected double a;
    protected double b;
    protected double c;
    protected double[] f;
    protected double S = 0;
    protected double δ;

    public Function(double[][] table) {
        this.table = table;
        n = table[0].length;
        f = new double[n];
    }

    protected void countFANDParam(java.util.function.Function<Double, Double> fun) {
        for (int i = 0; i < table[0].length; i++) {
            f[i] = fun.apply(table[0][i]);
            S = S + Math.pow(f[i] - table[1][i], 2);
        }
        δ = Math.sqrt(S / n);
    }

    public double[] getF() {
        return f;
    }

    public double getS() {
        return S;
    }

    public double getδ() {
        return δ;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
