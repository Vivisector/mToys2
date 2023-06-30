import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Toy {
    private String name;
    private double weight;
    private int count;

    public Toy(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }
}

class ToyStore {
    private List<Toy> toyList;
    private Random random;

    public ToyStore() {
        toyList = new ArrayList<>();
        random = new Random();
    }

    public void addNewToy(String name, double weight) {
        Toy toy = new Toy(name, weight);
        toyList.add(toy);
    }

    public Toy getRandomToy() {
        double totalWeight = 0;
        for (Toy toy : toyList) {
            totalWeight += toy.getWeight();
        }

        double randomWeight = random.nextDouble() * totalWeight;
        double currentWeight = 0;

        for (Toy toy : toyList) {
            currentWeight += toy.getWeight();

            if (randomWeight <= currentWeight) {
                return toy;
            }
        }

        // This code should not be reached unless there are no toys in the store
        return null;
    }

    public List<Toy> getToyList() {
        return toyList;
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (Toy toy : toyList) {
            totalWeight += toy.getWeight();
        }
        return totalWeight;
    }
}
