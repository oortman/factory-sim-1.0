package src;

/**
 * Class that represents a type of product.
 */
public class Product {
    private final String type; // The type of product (aka the product's name)
    private final double value; // The value of this product, for use when selling product
    private final Material[] reqMaterials; // Array of materials required to produce the product
    private final int[] reqMatQuantities; // Array of amounts required for each material in reqMaterials
    private int quantity; // The quantity of this product in factory's storage

    /**
     * Constructor for the src.Product class.
     *
     * @param productType      - the type of product (name)
     * @param initialQuantity  - the initial quantity for the product in src.Factory storage
     * @param value            - the value of the product, for use in product sale
     * @param reqMaterials     - array of materials required to produce the product
     * @param reqMatQuantities - array of amounts required for each material in reqMaterials
     */
    public Product(String productType, int initialQuantity, double value, Material[] reqMaterials,
                   int[] reqMatQuantities) {
        this.type = productType;
        this.quantity = initialQuantity;
        this.value = value;
        this.reqMaterials = reqMaterials;
        this.reqMatQuantities = reqMatQuantities;
    }

    /**
     * Adds specified amount of this src.Product to factory's storage.
     *
     * @param amountToAdd - amount of this product to add to storage
     */
    public void addProducts(int amountToAdd) {
        quantity += amountToAdd;
    }

    /**
     * Removes specified amount of this src.Product from factory's storage.
     *
     * @param amountToRemove - amount of this product to remove from storage
     */
    public void removeProducts(int amountToRemove) {
        quantity -= amountToRemove;
    }

    /**
     * Getter for src.Product type.
     *
     * @return the product's type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for src.Product quantity.
     *
     * @return the quantity of this product in storage
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter for src.Product value.
     *
     * @return the value of this product
     */
    public double getValue() {
        return value;
    }

    /**
     * Getter for src.Product reqMaterials.
     *
     * @return the array of materials required to produce this product
     */
    public Material[] getReqMats() {
        return reqMaterials;
    }

    /**
     * Getter for src.Product reqMatQuantities.
     *
     * @return the array of quantities of each material required to produce this product
     */
    public int[] getReqMatQuantities() {
        return reqMatQuantities;
    }
}
