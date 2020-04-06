
import java.io.*;

public class DataReceiver {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public double[][] receiveData() {
         while (true) {
            System.out.println("Нахождение функции, являющейся наилучшим приближением заданной табличной\n функции по методу наименьших квадратов");
            System.out.println("Для ввода данных с консоли нажмите 1, для ввода из файла нажмите 2");
            try {
                switch (in.readLine()) {
                    case "1":
                        return readFromConsole();
                    case "2":
                        return readFromFile();
                    default:
                        System.out.println("Произошла ошибка, повторите ввод еще раз");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Неверный ввод!");
            }
        }

    }

    public void answer(String s) {
        while (true) {
            System.out.println("Для вывода ответа в консоль нажмите 1\n"
                    + "Для вывода в файл нажмите 2");
            try {
                switch (in.readLine()) {
                    case "1":
                        System.out.println(s);
                        return;
                        case "2":
                        writeToFile(s);
                        return;

                    default:
                        System.out.println("Произошла ошибка, повторите ввод еще раз");
                }
            } catch (IOException e) {
                System.out.println("Неверный ввод!");
            }
        }
    }

    private void writeToFile(String s) {
        try (FileWriter writer = new FileWriter("answer.txt", false)) {
            writer.write(s);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("Произошла ошибка записи в файл");
        }
    }

    private double[][] readFromConsole() {
        while (true) {
            try {
                System.out.println("Для записи дробной части используйте точку. Чтобы разделить аргументы используйте пробел. Сначала строка X, затем Y");
                return getTableXY(in);
            } catch (IOException e) {
                System.out.println("Неверный ввод!");
            }
        }
    }

    private double[][] readFromFile() {
        while (true) {
            try {
                System.out.println("Введите полный путь к файлу, в котором хранится таблица y=f(x)");
                System.out.println("Для записи дробной части используйте точку. Чтобы разделить аргументы используйте пробел");
                String path = in.readLine();
                BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
                return getTableXY(reader);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Данные некорректны.Проверьте содержимое файла!");
            } catch (IOException e) {
                System.out.println("Произошла ошибка загрузки данных из файла!");
            }
        }
    }

    private double[][] getTableXY(BufferedReader reader) throws IOException {
        double[][] table = new double[2][];
        int length = -1;
        for (int i = 0; i < 2; i++) {
            String line = reader.readLine();
            String[] parts = line.trim().replace(",", ".").split(" ");
            if (length == -1 || length == parts.length) {
                table[i] = new double[parts.length];
                for (int j = 0; j < parts.length; j++) {
                    table[i][j] = Double.parseDouble(parts[j].trim());
                }
                length = parts.length;
            } else {
                throw new IOException("разная длина...");
            }
        }
        return table;
    }
}