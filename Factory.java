import java.util.ArrayList;

/**
 * Class that represents a factory to be used in the simulator.
 */
public class Factory {
    private double cash; // Cash held in the factory
    protected final ArrayList<Material> materialStorage; // ArrayList that stores the material types
    protected final ArrayList<Product> productStorage; // ArrayList that stores the product types

    /**
     * Constructor for Factory class.
     *
     * @param startingCash - cash for the Factory to start with on creation
     * @param materialStorage - ArrayList of Materials
     * @param productStorage - ArrayList of Products
     */
    public Factory(double startingCash, ArrayList<Material> materialStorage,
                   ArrayList<Product> productStorage) {
        this.cash = startingCash;
        this.materialStorage = materialStorage;
        this.productStorage = productStorage;
    }

    /**
     * Purchase an amount of the specified material, if the Factory has enough cash.
     *
     * @param material - Material to buy
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
     * @param product - the Product to produce
     * @param amountToProduce - the amount of specified product to produce
     */
    public void makeProduct(Product product, int amountToProduce) {
        if (hasRequiredMats(product)) {
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
     * @return true if there are enough materials of each required type in storage; false otherwise
     */
    private boolean hasRequiredMats(Product product) {
        Material[] reqMats = product.getReqMats();
        int[] reqMatQuantities = product.getReqMatQuantities();

        for (int i = 0; i < reqMats.length; i++) {
            if (reqMats[i].getQuantity() <= reqMatQuantities[i]) {
                return false;
            }
        }

        useMaterials(reqMats, reqMatQuantities);
        return true;
    }

    /**
     * Uses (removes) materials required for making a product.
     *
     * @param reqMats - materials required for making a product
     * @param reqMatQuantities - amount of each material required for making a product
     */
    private void useMaterials(Material[] reqMats, int[] reqMatQuantities) {
        for (int i = 0; i < reqMats.length; i++) {
            reqMats[i].removeMats(reqMatQuantities[i]);
        }
    }

    /**
     * Sell an amount of the specified product, if stock in Factory storage allows.
     *
     * @param product - the Product to sell from storage
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
}
