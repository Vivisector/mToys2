import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ToyStoreApp {
    private static final int NUM_SAMPLES = 1000;
    private static final String OUTPUT_FILE = "toy_results.txt";

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Добавление игрушек с их весами и вероятностями
        toyStore.addNewToy("Кукла", 0.6);
        toyStore.addNewToy("Солдатик", 0.2);
        toyStore.addNewToy("Машинка", 0.2);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));

            // Выполнение выборок и запись результатов в файл
            for (int i = 0; i < NUM_SAMPLES; i++) {
                Toy randomToy = toyStore.getRandomToy();
                if (randomToy != null) {
                    randomToy.incrementCount();
                    writer.write("Выборка " + (i + 1) + ": Выиграна игрушка - " + randomToy.getName());
                    writer.newLine();
                } else {
                    writer.write("Выборка " + (i + 1) + ": В магазине нет игрушек.");
                    writer.newLine();
                }
            }

            // Запись вероятности выигрыша каждой игрушки
            writer.newLine();
            writer.write("Вероятность выигрыша каждой игрушки:");
            writer.newLine();

            double totalWeight = toyStore.getTotalWeight();
            for (Toy toy : toyStore.getToyList()) {
                double probability = (double) toy.getCount() / NUM_SAMPLES;
                int percentage = (int) (probability * 100);
                writer.write(toy.getName() + ": " + percentage + "%");
                writer.newLine();
            }

            writer.close();

            System.out.println("Результаты сохранены в файл: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл.");
            e.printStackTrace();
        }
    }
}
