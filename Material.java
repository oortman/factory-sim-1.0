/**
 * Class that represents a type of material to be purchased and/or used to produce Products.
 */
public class Material {
    private final String type; // The type of material (aka the material's name)
    private final double price; // The price of material used at material purchase
    private int quantity; // Quantity of the material held in factory's storage

    /**
     * Constructor for the Material class.
     *
     * @param materialType    - the type of material (name)
     * @param initialQuantity - the initial quantity for the material in Factory storage
     * @param price           - the price of the material, for use in material purchasing
     */
    public Material(String materialType, int initialQuantity, double price) {
        this.type = materialType;
        this.quantity = initialQuantity;
        this.price = price;
    }

    /**
     * Adds specified amount of this Material to factory's storage.
     *
     * @param amountToAdd - amount of this material to add to storage
     */
    public void addMats(int amountToAdd) {
        quantity += amountToAdd;
    }

    /**
     * Removes specified amount of this Material from factory's storage.
     *
     * @param amountToRemove - amount of this material to remove from storage
     */
    public void removeMats(int amountToRemove) {
        quantity -= amountToRemove;
    }

    /**
     * Getter for Material type.
     *
     * @return the material's type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for Material quantity.
     *
     * @return the quantity of this material in storage
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter for Material price.
     *
     * @return the price of this material
     */
    public double getPrice() {
        return price;
    }
}
