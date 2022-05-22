import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that represents the simulation.
 */
public class FactorySim {
    private Scanner keyboard;
    private Factory factory;

    /**
     * Constructor for the simulator.
     */
    public FactorySim() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Runs the setup and main loop of the simulator.
     */
    public void run() {
        setup();

        int userCommand = 0;
        while (userCommand != 4) {
            printBasicInfo();

            userCommand = keyboard.nextInt();
            interpretCommand(userCommand);
        }
    }

    /**
     * Sets up simulator by establishing materials, products, and factory to be used.
     */
    private void setup() {
        // Create materials available in sim
        Material wood = new Material("Wood", 0, 1.00);
        Material steel = new Material("Steel", 0, 3.00);
        Material fabric = new Material("Fabric", 0, 1.50);

        // Create products available in sim
        Material[] plankMats = {wood};
        int[] plankMatQuantities = {5};
        Product plank = new Product("Plank", 0, 8.00, plankMats,
                plankMatQuantities);

        Material[] chairMats = {wood, fabric};
        int[] chairMatQuantities = {5, 10};
        Product chair = new Product("Chair", 0, 25.00, chairMats,
                chairMatQuantities);

        Material[] benchMats = {wood, steel};
        int[] benchMatQuantities = {5, 5};
        Product bench = new Product("Bench", 0, 35.00, benchMats,
                benchMatQuantities);

        // Create factory
        ArrayList<Material> factoryMats = new ArrayList<>();
        factoryMats.add(wood);
        factoryMats.add(steel);
        factoryMats.add(fabric);
        ArrayList<Product> factoryProducts = new ArrayList<>();
        factoryProducts.add(plank);
        factoryProducts.add(chair);
        factoryProducts.add(bench);
        factory = new Factory(100.00, factoryMats, factoryProducts);
    }

    /**
     * Prints the basic factory info and command options to the user.
     */
    private void printBasicInfo() {
        System.out.println("\nYou have $" + factory.getCash());
        factory.printMatsInStorage();
        factory.printProdsInStorage();
        System.out.println("\nChoose an Action");
        System.out.println("1: Buy material" +
                "\n2: Make product" +
                "\n3: Sell product" +
                "\n4: Exit FactorySim");
    }

    /**
     * Interprets the user's input and performs associated action. If command is not valid, prints
     * an error message.
     *
     * @param command the user input to be interpreted
     */
    private void interpretCommand(int command) {
        switch (command) {
            case 1:
                factory.printMatPrices();
                System.out.println("Which material would you like to purchase?");
                Material material = factory.toMaterial(keyboard.next());
                if (material != null) {
                    System.out.println("How much would you like to purchase?");
                    int amountToBuy = keyboard.nextInt();
                    factory.buyMaterial(material, amountToBuy);
                }
                break;
            case 2:
                factory.printProdReqs();
                System.out.println("What product would you like to produce?");
                Product productToMake = factory.toProduct(keyboard.next());
                if (productToMake != null) {
                    System.out.println("How many would you like to make?");
                    int amountToMake = keyboard.nextInt();
                    factory.makeProduct(productToMake, amountToMake);
                }
                break;
            case 3:
                factory.printProdValues();
                System.out.println("Which product would you like to sell?");
                Product productToSell = factory.toProduct(keyboard.next());
                if (productToSell != null) {
                    System.out.println("How many would you like to sell?");
                    int amountToSell = keyboard.nextInt();
                    factory.sellProduct(productToSell, amountToSell);
                }
                break;
            case 4:
                System.out.println("Thank you for using FactorySim!");
                System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
}
