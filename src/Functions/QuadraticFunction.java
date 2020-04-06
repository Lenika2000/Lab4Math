package Functions;


import Gauss.GaussMethod;

public class QuadraticFunction extends Function {
    private double SX = 0;
    private double SXX = 0;
    private double SXXX = 0;
    private double SXXXX = 0;
    private double SY = 0;
    private double SXY = 0;
    private double SXXY = 0;

    private double[][] matrix = new double[3][4];

    public QuadraticFunction(double[][] table) {
        super(table);
        init();
    }

    private void init() {
        n = table[0].length;
        for (int i = 0; i < n; i++) {
            SX = SX + table[0][i];
            SXX = SXX + table[0][i] * table[0][i];
            SXXX = SXXX + table[0][i] * table[0][i] * table[0][i];
            SXXXX = SXXXX + table[0][i] * table[0][i] * table[0][i] * table[0][i];
            SY = SY + table[1][i];
            SXY = SXY + table[0][i] * table[1][i];
            SXXY = SXXY + table[0][i] * table[0][i] * table[1][i];
        }
        countABC();
        countFANDParam((Double x) -> a * x * x + b * x + c);

    }

    private void countABC() {
        matrix[0][0] = n;
        matrix[1][0] = SX;
        matrix[0][1] = SX;
        matrix[2][0] = SXX;
        matrix[1][1] = SXX;
        matrix[0][2] = SXX;
        matrix[2][1] = SXXX;
        matrix[1][2] = SXXX;
        matrix[2][2] = SXXXX;
        matrix[0][3] = SY;
        matrix[1][3] = SXY;
        matrix[2][3] = SXXY;
        GaussMethod gaussMethod = new GaussMethod(matrix);
        double[] answer = gaussMethod.getAnswer();
        a = answer[2];
        b = answer[1];
        c = answer[0];
    }

}
