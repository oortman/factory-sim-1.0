package src;

import java.util.ArrayList;

/**
 * Class that represents a factory to be used in the simulator.
 */
public class Factory {
    protected final ArrayList<Material> materialStorage; // ArrayList that stores the material types
    protected final ArrayList<Product> productStorage; // ArrayList that stores the product types
    private double cash; // Cash held in the factory

    /**
     * Constructor for src.Factory class.
     *
     * @param startingCash    - cash for the src.Factory to start with on creation
     * @param materialStorage - ArrayList of Materials
     * @param productStorage  - ArrayList of Products
     */
    public Factory(double startingCash, ArrayList<Material> materialStorage,
                   ArrayList<Product> productStorage) {
        this.cash = startingCash;
        this.materialStorage = materialStorage;
        this.productStorage = productStorage;
    }

    /**
     * Purchase an amount of the specified material, if the src.Factory has enough cash.
     *
     * @param material    - src.Material to buy
     * @param amountToBuy - amount of the specified material to purchase
     */
    public void buyMaterial(Material material, int amountToBuy) {
        if (cash >= material.getPrice() * amountToBuy) {
            material.addMats(amountToBuy);
            cash -= material.getPrice() * amountToBuy;
        } else {
            System.out.println("You don't have enough cash!");
        }
    }

    /**
     * Produce an amount of the specified product, if required materials are available in storage.
     *
     * @param product         - the src.Product to produce
     * @param amountToProduce - the amount of specified product to produce
     */
    public void makeProduct(Product product, int amountToProduce) {
        if (hasRequiredMats(product, amountToProduce)) {
            useMaterials(product, amountToProduce);
            product.addProducts(amountToProduce);
        } else {
            System.out.println("You don't have the required materials!");
        }
    }

    /**
     * Checks if there are enough materials of each required type in storage to produce the
     * specified product.
     *
     * @param product - the product to check the requirements for production
     * @param amount - the amount of specified product intended to be produced
     * @return true if there are enough materials of each required type in storage; false otherwise
     */
    private boolean hasRequiredMats(Product product, int amount) {
        Material[] reqMats = product.getReqMats();
        int[] reqMatQuantities = product.getReqMatQuantities();

        for (int i = 0; i < reqMats.length; i++) {
            if (reqMats[i].getQuantity() < reqMatQuantities[i] * amount) {
                return false;
            }
        }

        return true;
    }

    /**
     * Uses (removes) the materials from storage to make products.
     *
     * @param product - the product being produced
     * @param amount - the number of products being produced
     */
    private void useMaterials(Product product, int amount) {
        for (int i = 0; i < product.getReqMats().length; i++) {
            product.getReqMats()[i].removeMats(product.getReqMatQuantities()[i] * amount);
        }
    }

    /**
     * Sell an amount of the specified product, if stock in src.Factory storage allows.
     *
     * @param product      - the src.Product to sell from storage
     * @param amountToSell - the amount of specified product to sell
     */
    public void sellProduct(Product product, int amountToSell) {
        if (product.getQuantity() >= amountToSell) {
            product.removeProducts(amountToSell);
            cash += product.getValue() * amountToSell;
        } else {
            System.out.println("You don't have enough product!");
        }
    }

    /**
     * Getter for factory's cash.
     *
     * @return the amount of cash that this factory has
     */
    public double getCash() {
        return cash;
    }

    /**
     * Prints the materials in this factory along with their quantities in storage.
     */
    public void printMatsInStorage() {
        System.out.println("Materials in Storage");

        for (Material material : materialStorage) {
            System.out.printf("\t%s: %d\n", material.getType(), material.getQuantity());
        }
    }

    /**
     * Prints the products in this factory along with their quantities in storage.
     */
    public void printProdsInStorage() {
        System.out.println("Products in Storage");

        for (Product product : productStorage) {
            System.out.printf("\t%s: %d\n", product.getType(), product.getQuantity());
        }
    }

    /**
     * Prints the materials in this factory along with their purchase prices.
     */
    public void printMatPrices() {
        System.out.println("\nsrc.Material Catalog");

        for (Material material : materialStorage) {
            System.out.printf("\t%s: $%.2f\n", material.getType(), material.getPrice());
        }
    }

    /**
     * Prints the products in this factory along with their sale values.
     */
    public void printProdValues() {
        System.out.println("\nsrc.Product Catalog");

        for (Product product : productStorage) {
            System.out.printf("\t%s: $%.2f\n", product.getType(), product.getValue());
        }
    }

    /**
     * Converts the user-inputted string to its corresponding material (if material exists).
     *
     * @param materialString user-inputted string that represents a material type
     * @return the material that's type matches the materialString; null if no material exists for
     * that type
     */
    public Material toMaterial(String materialString) {
        for (Material material : materialStorage) {
            if (material.getType().equalsIgnoreCase(materialString)) {
                return material;
            }
        }

        System.out.println("Invalid material!");
        return null;
    }

    /**
     * Converts the user-inputted string to its corresponding product (if product exists).
     *
     * @param productString user-inputted string that represents a product type
     * @return the product that's type matches the productString; null if no product exists for
     * that type
     */
    public Product toProduct(String productString) {
        for (Product product : productStorage) {
            if (product.getType().equalsIgnoreCase(productString)) {
                return product;
            }
        }

        System.out.println("Invalid product!");
        return null;
    }

    /**
     * Prints a list of products that can be made in this factory, their required materials, and
     * amounts of each material required.
     */
    public void printProdReqs() {
        System.out.println("\nRequired Materials to Produce Each src.Product");

        for (int i = 0; i < productStorage.size(); i++) {
            System.out.println(productStorage.get(i).getType());

            Material[] reqMats = productStorage.get(i).getReqMats().clone();
            int[] reqMatQuantities = productStorage.get(i).getReqMatQuantities().clone();

            for (int j = 0; j < reqMats.length; j++) {
                System.out.printf("\t%s: %d\n", reqMats[j].getType(), reqMatQuantities[j]);
            }
        }
    }
}
