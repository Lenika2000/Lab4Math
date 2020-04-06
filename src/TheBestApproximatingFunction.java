import Functions.*;
import Graphics.LineChart;
import org.jfree.ui.RefineryUtilities;

import static java.lang.String.valueOf;

public class TheBestApproximatingFunction {
    DataReceiver dataReceiver = new DataReceiver();

    Function[] functions = new Function[5];
    String[][] answer;
    double[][] table;
    int n;
    StringBuffer buffAnswer = new StringBuffer();
    String[] title;
    String[] functionsDescription;


    TheBestApproximatingFunction() {
        init();
    }

    private void init() {
        table = dataReceiver.receiveData();
    }


    void getTheBestApproximatingFunction() {
        createAnswerTable();
        dataReceiver.answer(valueOf(buffAnswer));
        printGraphics();

    }


    private void createDescriptions() {
        title = new String[]{"Линейная функция", "Полиноминальная функция", "Экспоненциальная функция", "Логарифмическая функция", "Степенная функция"};
        functionsDescription = new String[]{String.format("F=%.4fx+%.4f", functions[0].getA(), functions[0].getB()),
                String.format("F=%.4fx^2+%.4fx+%.4f", functions[1].getA(), functions[1].getB(), functions[1].getC()),
                String.format("F=%.4fe^(%.4fx)", functions[2].getA(), functions[2].getB()),
                String.format("F=%.4flnx+%.4f", functions[3].getA(), functions[3].getB()),
                String.format("F=%.4fx^%.4f", functions[4].getA(), functions[4].getB())};
    }

    private void printGraphics() {
        Double[] horizontalPercent = {0.0, 1.0, 1.0, 0.0, 0.5};
        Double[] verticalPercent = {0.0, 0.0, 1.1, 1.1, 0.5};
        for (int i = 0; i < 5; i++) {
            LineChart lineChart0 = new LineChart(title[i], functions[i].getF()
                    , table[0], table[1], functionsDescription[i]);
            lineChart0.pack();
            RefineryUtilities.positionFrameOnScreen(lineChart0, horizontalPercent[i], verticalPercent[i]);
            lineChart0.setVisible(true);
        }

    }

    private void createAnswerTable() {
        n = table[0].length;//количество точек
        answer = new String[5 + n][7];
        for (int i = 0; i < n; i++) {
            answer[i][0] = String.format("|%10.2f ", table[0][i]); //столбец Х в итоговой таблице
            answer[i][1] = String.format("|%10.2f ", table[1][i]); // столбец Y в итоговой таблице
        }
        answer[n][0] = String.format("|    S      ");
        answer[n][1] = String.format("|    =      ");
        answer[n + 1][0] = String.format("|    δ      ");
        answer[n + 1][1] = String.format("|    =      ");
        answer[n + 2][0] = String.format("|    A      ");
        answer[n + 2][1] = String.format("|    =      ");
        answer[n + 3][0] = String.format("|    B      ");
        answer[n + 3][1] = String.format("|    =      ");
        answer[n + 4][0] = String.format("|    C      ");
        answer[n + 4][1] = String.format("|    =      ");
        functions[0] = new LinearFunction(table);
        functions[1] = new QuadraticFunction(table);
        functions[2] = new ExponentialFunction(table);
        functions[3] = new LogarithmicFunction(table);
        functions[4] = new PowerFunction(table);
        for (int j = 2; j < 7; j++) {
            double[] f = functions[j - 2].getF();
            for (int i = 0; i < n; i++) {
                answer[i][j] = String.format("|%10.2f ", f[i]); //столбец каждой из функций в итоговой таблице
            }
            answer[n][j] = String.format("|%10.2f ", functions[j - 2].getS());
            answer[n + 1][j] = String.format("|%10.2f ", functions[j - 2].getδ());
            answer[n + 2][j] = String.format("|%10.2f ", functions[j - 2].getA());
            answer[n + 3][j] = String.format("|%10.2f ", functions[j - 2].getB());
        }
        answer[n + 4][2] = String.format("|-----------");
        answer[n + 4][3] = String.format("|%10.2f ", functions[1].getC());
        for (int i = 4; i < 7; i++) {
            answer[n + 4][i] = String.format("|-----------");
        }

        buffAnswer.append("|         Вид f(x)      | Линейная  |  Полином. | Экспонен. | Логарифм. | Степенная |\n")
                .append("-------------------------------------------------------------------------------------\n")
                .append("|     X     |     Y     |  F=ax+b   |F=ax^2+bx+c| F=ae^(bx) |  F=alnx+b |  F=ax^b   |\n")
                .append("-------------------------------------------------------------------------------------\n");

        for (int i = 0; i < 5 + table[0].length; i++) {
            for (int j = 0; j < 7; j++) {
                buffAnswer.append(answer[i][j]);
            }
            buffAnswer.append("|\n");
            buffAnswer.append("-------------------------------------------------------------------------------------\n");
        }

        createDescriptions();
        //поиск лучшей аппроксимирующей функции
        double min = 1000000;
        int k = 0;
        for (int i = 0; i < 5; i++) {
            if (functions[0].getδ() < min) {
                min = functions[0].getδ();
                k = i;
            }
        }
        buffAnswer.append(title[k] + " " + functionsDescription[k] + " является наилучшим приближением заданной табличной функции");
    }
}
