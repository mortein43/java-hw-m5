import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("Завдання 1:");
        out.println("Введіть шлях до першого файлу:");
        String filePath1 = scanner.nextLine();
        out.println("Введіть шлях до другого файлу:");
        String filePath2 = scanner.nextLine();
        compareFiles(filePath1, filePath2);


        out.println("Завдання 2:");
        out.println("Введіть шлях до файлу для знаходження найдовшого рядка:");
        String filePath = scanner.nextLine();
        findLongestLine(filePath);


        out.println("Завдання 3:");
        out.println("Введіть шлях до файлу для завантаження масивів:");
        String arrayFilePath = scanner.nextLine();
        processArrays(arrayFilePath);


        out.println("Завдання 4:");
        out.println("Введіть шлях до файлу для збереження масиву:");
        String outputFilePath = scanner.nextLine();
        out.println("Введіть масив цілих чисел (через пробіл):");
        String arrayInput = scanner.nextLine();
        String[] arrayParts = arrayInput.split(" ");
        int[] array = new int[arrayParts.length];
        for (int i = 0; i < arrayParts.length; i++) {
            array[i] = Integer.parseInt(arrayParts[i]);
        }

        saveArrayToFile(outputFilePath, array);
    }

    private static void compareFiles(String filePath1, String filePath2) {
        try (BufferedReader br1 = new BufferedReader(new FileReader(filePath1));
                BufferedReader br2 = new BufferedReader(new FileReader(filePath2))) {

            String line1, line2;
            int lineNumber = 1;
            boolean filesMatch = true;

            while (true) {
                line1 = br1.readLine();
                line2 = br2.readLine();

                // Перевірити чи обидва файли досягли кінця
                if (line1 == null && line2 == null) {
                    break;
                }

                // Якщо один з файлів досяг кінця раніше
                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    filesMatch = false;
                    out.println("Рядки не збігаються в рядку " + lineNumber + ":");
                    out.println("Файл 1: " + (line1 != null ? line1 : "кінець файлу"));
                    out.println("Файл 2: " + (line2 != null ? line2 : "кінець файлу"));
                }

                lineNumber++;
            }

            if (filesMatch) {
                out.println("Файли ідентичні.");
            }

        } catch (IOException e) {
            out.println("Сталася помилка при читанні файлів: " + e.getMessage());
        }
    }

    private static void findLongestLine(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            String longestLine = "";
            int longestLength = 0;

            while ((line = br.readLine()) != null) {
                if (line.length() > longestLength) {
                    longestLength = line.length();
                    longestLine = line;
                }
            }

            System.out.println("Найдовший рядок має довжину " + longestLength + ":");
            System.out.println(longestLine);

        } catch (IOException e) {
            System.out.println("Сталася помилка при читанні файлу: " + e.getMessage());
        }
    }

    private static void processArrays(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            ArrayList<int[]> arrays = new ArrayList<>();
            String line;
            int totalSum = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int[] array = new int[parts.length];
                int sum = 0;
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                for (int i = 0; i < parts.length; i++) {
                    array[i] = Integer.parseInt(parts[i]);
                    sum += array[i];
                    if (array[i] > max) {
                        max = array[i];
                    }
                    if (array[i] < min) {
                        min = array[i];
                    }
                }

                arrays.add(array);
                totalSum += sum;

                System.out.println("Масив: " + java.util.Arrays.toString(array));
                System.out.println("Максимум: " + max);
                System.out.println("Мінімум: " + min);
                System.out.println("Сума: " + sum);
                System.out.println();
            }

            System.out.println("Загальна сума всіх масивів: " + totalSum);

        } catch (IOException e) {
            System.out.println("Сталася помилка при читанні файлу: " + e.getMessage());
        }
    }

    private static void saveArrayToFile(String filePath, int[] array) {
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write("Вихідний масив: ");
            for (int num : array) {
                writer.write(num + " ");
            }
            writer.write("\n");

            writer.write("Парні значення масиву: ");
            for (int num : array) {
                if (num % 2 == 0) {
                    writer.write(num + " ");
                }
            }
            writer.write("\n");

            writer.write("Не парні значення масиву: ");
            for (int num : array) {
                if (num % 2 != 0) {
                    writer.write(num + " ");
                }
            }
            writer.write("\n");

            writer.write("Обернений масив: ");
            for (int i = array.length - 1; i >= 0; i--) {
                writer.write(array[i] + " ");
            }
            writer.write("\n");

            System.out.println("Масив збережено у файл " + filePath);

        } catch (IOException e) {
            System.out.println("Сталася помилка при записі файлу: " + e.getMessage());
        }
    }
}