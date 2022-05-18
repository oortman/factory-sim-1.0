import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that represents the factory to be simulated.
 */
public class FactorySim {
    /**
     * Main method for simulator creation and main interaction loop.
     *
     * @param args - arguments for the main method
     */
    public static void main(String[] args) {
        // Create materials available in sim
        Material wood = new Material("Wood", 0, 1.00);

        // Create products available in sim
        Material[] plankMats = {wood};
        int[] plankMatQuantities = {5};
        Product plank = new Product("Plank", 0, 8.00, plankMats,
                plankMatQuantities);

        // Create factory
        ArrayList<Material> factoryMats = new ArrayList<>();
        factoryMats.add(wood);
        ArrayList<Product> factoryProducts = new ArrayList<>();
        factoryProducts.add(plank);
        Factory factory = new Factory(100.00, factoryMats, factoryProducts);

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to FactorySim 1.0!");

        // Main loop
        int userCommand = 0;
        while (userCommand != 4) {
            System.out.println("You have $" + factory.getCash());
            factory.printMatsInStorage();
            factory.printProdsInStorage();
            System.out.println("Choose an Action");
            System.out.println("1: Buy material" +
                    "\n2: Make product" +
                    "\n3: Sell product" +
                    "\n4: Exit FactorySim");

            userCommand = keyboard.nextInt();
            switch (userCommand) {
                case 1:
                    factory.printMatPrices();
                    System.out.println("Which material would you like to purchase?");
                    Material material = factory.toMaterial(keyboard.next());
                    System.out.println("How much would you like to purchase?");
                    int amountToBuy = keyboard.nextInt();
                    factory.buyMaterial(material, amountToBuy);
                    break;
                case 2:
                    factory.printProdReqs();
                    System.out.println("What product would you like to produce?");
                    Product productToMake = factory.toProduct(keyboard.next());
                    System.out.println("How many would you like to make?");
                    int amountToMake = keyboard.nextInt();
                    factory.makeProduct(productToMake, amountToMake);
                    break;
                case 3:
                    factory.printProdValues();
                    System.out.println("Which product would you like to sell?");
                    Product productToSell = factory.toProduct(keyboard.next());
                    System.out.println("How many would you like to sell?");
                    int amountToSell = keyboard.nextInt();
                    factory.sellProduct(productToSell, amountToSell);
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
}
