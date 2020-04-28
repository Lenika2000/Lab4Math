package Functions;

public class Function {
    protected double[] X;
    protected double[] Y;
    protected int n;
    protected double a;
    protected double b;
    protected double c;
    protected double[] f;
    protected double S = 0;
    protected double δ;

    public Function(double[] X, double [] Y) {
        this.X = X;
        this.Y =Y;
        n = X.length;
        f = new double[n];//значения аппроксимирующей функции в точках х
    }

    protected void countFANDParam(java.util.function.Function<Double, Double> fun) {
        for (int i = 0; i < n; i++) {
            f[i] = fun.apply(X[i]);
            S = S + Math.pow(f[i] - Y[i], 2); //подсчет критерия минимизации
        }
        δ = Math.sqrt(S / n); //подсчет среднекрадратичного отклонения
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
