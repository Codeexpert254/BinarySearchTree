package bst;

public class Node {
    private String countryName; // The name of the country.
    private double happiness; // The happiness index of the country.
    private Node left; // Reference to the left child node.
    private Node right; // Reference to the right child node.

    /**
     * Constructor to create a new Node with the specified country name and happiness index.
     *
     * @param countryName The name of the country.
     * @param happiness   The happiness index of the country.
     */
    
    public Node(String countryName, double happiness) {
        this.countryName = countryName;
        this.happiness = happiness;
        this.left = null;
        this.right = null;
    }

	/**
     * Print the details of the node.
     */
    
    public void print() {
        System.out.println("Name: " + countryName);
        System.out.println("Happiness: " + happiness);
    }

    /**
     * Get the name of the country stored in this node.
     *
     * @return The name of the country.
     */
    
    public String getCountryName() {
        return countryName;
    }

    /**
     * Get the happiness index of the country stored in this node.
     *
     * @return The happiness index.
     */
    
    public double getHappiness() {
        return happiness;
    }

    /**
     * Get the reference to the left child node.
     *
     * @return The left child node.
     */
    
    public Node getLeft() {
        return left;
    }

    /**
     * Get the reference to the right child node.
     *
     * @return The right child node.
     */
    
    public Node getRight() {
        return right;
    }

    /**
     * Set the left child node.
     *
     * @param node The left child node to set.
     */
    
    public void setLeft(Node node) {
        left = node;
    }

    /**
     * Set the right child node.
     *
     * @param node The right child node to set.
     */
    
    public void setRight(Node node) {
        right = node;
    }

    /**
     * Set the country name of the node.
     *
     * @param countryName The country name to set.
     */
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


}

