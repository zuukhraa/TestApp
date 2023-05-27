import java.io.*;
import java.util.*;

public class Test {

    // Сначала покажу студента, который уже проходил тест и у него есть сохраненные результаты.
    // Потом покажу студента, который проходит тест в первый раз.
    // 1 - знает, 0 - не знает

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine();
        String fileName = "results.txt";
        HashMap<String, ArrayList> results = new HashMap<>();
        String nameModules = "Name:[Begin, Began, Begun]";

        int countBegin = 0;
        int countBegan = 0;
        int countBegun = 0;

        try {
            File file = new File(fileName);

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String firstLine = reader.readLine();
                String line;

                if (firstLine == null || !firstLine.equals(nameModules)) {
                    FileWriter writer = new FileWriter(file, true);
                    writer.write(System.lineSeparator() + nameModules);
                    writer.close();
                }

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    results.put(parts[0], new ArrayList<>(Arrays.asList(parts[1].replaceAll("\\[|\\]|\\s", "").split(","))));

                }
                reader.close();
            }

            System.out.println("Формы глагола begin. Вставьте пропущенные слова. Введите правильный ответ.");

            if (results.containsKey(name)) {
                System.out.println("Ваши результаты по модулям: " + "Begin = " + results.get(name).get(0) +
                        " Began = " + results.get(name).get(1) + " Begun = " + results.get(name).get(2));
                    results.remove(name);
                } else {
                System.out.println("Результаты по модулям: " + "Begin = " + countBegin +
                        " Began = " + countBegan + " Begun = " + countBegun);
                results.put(name, new ArrayList<>(Arrays.asList("0", "0", "0")));
            }

            System.out.println("1) I will _ reading this book tomorrow");
            System.out.println("1. begin " + "" + "2. began " + "" + "3. begun");
            int answer1 = scanner.nextInt();
            if (answer1 == 1) {
                countBegin++;
            }

            System.out.println("2) The movie, _ last year, is still in production");
            System.out.println("1. begin " + "" + "2. began " + "" + "3. begun");
            int answer2 = scanner.nextInt();
            if (answer2 == 3) {
                countBegun++;
            }

            System.out.println("3) He _ by producing from under his arm a great letter, nearly as large as himself");
            System.out.println("1. begin " + "" + "2. began " + "" + "3. begun");
            int answer3 = scanner.nextInt();
            if (answer3 == 2) {
                countBegan++;
            }

            System.out.println("Результаты по модулям: " + "Begin = " + countBegin +
                    " Began = " + countBegan + " Begun = " + countBegun);
            results.put(name, new ArrayList<Object>(Arrays.asList(Integer.toString(countBegin), Integer.toString(countBegan), Integer.toString(countBegun))));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(nameModules);
            writer.write("\n");
            for (String key : results.keySet()) {
                writer.write(key + ":" + Arrays.toString(results.get(key).toArray()));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}