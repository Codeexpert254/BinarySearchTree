/**
 * Extensive explanation of the BinarySearchTree class.
 * This class is a binary search tree (BST) used to store country information.
 * It has methods for entering, removing, searching for, and retrieving country data,
 * as well as outputting the tree in different traversal sequences.
 *
 * @author Your Name
 * @version Date Last Modified
 */
package bst;

import java.util.Arrays;

/**
 * The BinarySearchTree class represents a binary search tree (BST) used to store country info.
 * It provides methods for inserting, removing, searching, and retrieving country data, as well as
 * printing the tree in different traversal sequences.
 */

public class BinarySearchTree {
    private Node root; // The root node of the binary search tree.

    /**
     * Constructor to create an empty binary search tree.
     */
    
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert a new node into the binary search tree based on country name.
     *
     * @param name      The name of the country.
     * @param happiness The happiness index of the country.
     */
    
    public void insert(String name, double happiness) {
        root = insertRecursive(root, name, happiness);
    }

     /**
     * A helper method to insert a node recursively into the binary search tree.
     *
     * @param current   The current node being examined.
     * @param name      The name of the country to insert.
     * @param happiness The happiness index of the country to insert.
     * @return The root of the updated subtree.
     */
    
    private Node insertRecursive(Node current, String name, double happiness) {
        if (current == null) {
            return new Node(name, happiness);
        }

        int compareResult = name.compareTo(current.getCountryName());

        if (compareResult < 0) {
            current.setLeft(insertRecursive(current.getLeft(), name, happiness));
        } else if (compareResult > 0) {
            current.setRight(insertRecursive(current.getRight(), name, happiness));
        }

        return current;
    }
    
    /**
     * Find the happiness index of a country based on its name.
     *
     * @param name The name of the country to search for.
     * @return The happiness index of the country, or -1 if the country is not found.
     */
    
    public double find(String name) {
        return findRecursive(root, name);
    }

    /**
     * A helper method to find the happiness index of a country based on its name recursively.
     *
     * @param current The current node being examined.
     * @param name    The name of the country to find.
     * @return The happiness index of the country, or -1 if the country is not found.
     */
    
    private double findRecursive(Node current, String name) {
        if (current == null) {
            return -1; // Country not found.
        }

        int compareResult = name.compareTo(current.getCountryName());

        if (compareResult == 0) {
            return current.getHappiness();
        } else if (compareResult < 0) {
            return findRecursive(current.getLeft(), name);
        } else {
            return findRecursive(current.getRight(), name);
        }
    }
    
    /**
     * Print the path from the root to a specific country in the binary search tree.
     *
     * @param name The name of the country to find and print the path for.
     */
    
    public void printPath(String name) {
        boolean found = printPathRecursive(root, name);
        if (!found) {
            System.out.println(name + " is not found.");
        }
    }

    /**
     * A helper method to print the path from the root to a specific country in the binary search tree.
     *
     * @param current The current node being examined.
     * @param name    The name of the country to find.
     * @return true if the country is found, false if it's not found.
     */
    
    private boolean printPathRecursive(Node current, String name) {
        if (current == null) {
            return false;
        }

        if (current.getCountryName().equals(name)) {
            System.out.print(current.getCountryName());
            return true;
        }

        if (printPathRecursive(current.getLeft(), name) || printPathRecursive(current.getRight(), name)) {
            System.out.print(" -> " + current.getCountryName());
            return true;
        }

        return false;
    }
    
    
    /**
     * Delete a node from the binary search tree based on country name.
     *
     * @param name The name of the country to delete.
     */
    
    public void delete(String name) {
        root = deleteRecursive(root, name);
    }

    /**
     * A helper method to delete a node from the binary search tree based on country name.
     *
     * @param current The current node being examined.
     * @param name    The name of the country to delete.
     * @return The root of the updated subtree.
     */
    
    private Node deleteRecursive(Node current, String name) {
        if (current == null) {
            return current;
        }

        int compareResult = name.compareTo(current.getCountryName());

        if (compareResult < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), name));
        } else if (compareResult > 0) {
            current.setRight(deleteRecursive(current.getRight(), name));
        } else {
            // Node with only one child or no child
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            }

            // Node with two children, get the inorder successor
            current.setCountryName(minValueNode(current.getRight()).getCountryName());
            current.setRight(deleteRecursive(current.getRight(), current.getCountryName()));
        }

        return current;
    }
    
    /**
     * A helper method to find the minimum value node in a subtree.
     *
     * @param node The root node of the subtree.
     * @return The node with the minimum value in the subtree.
     */
    
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }


    /**
     * Print the tree nodes in inorder traversal (LNR). 
     * LNR means:
     * Traverse the left subtree (L)
     * Traverse the right subtree (N).
     * Visit the current node (R).
     */
    
    public void printInorder() {
        System.out.printf("%-40s%-10s%n", "Name", "Happiness");
        System.out.println("-----------------------------------------------------------------");
        printInorderRecursive(root);
    }


    /**
     * A helper method to perform an inorder traversal of the tree and print the nodes.
     *
     * @param current The current node being examined.
     */
    
    private void printInorderRecursive(Node current) {
        if (current != null) {
            printInorderRecursive(current.getLeft()); // Recursively traverse the left subtree
            System.out.printf("%-40s%-10.2f%n", current.getCountryName(), current.getHappiness());
            // Print the current node
            printInorderRecursive(current.getRight()); // Recursively traverse the right subtree
        }
    }
    
    /**
     * Print the tree nodes in preorder traversal (NLR).
     */
    
    public void printPreorder() {
        System.out.printf("%-40s%-10s%n", "Name", "Happiness");
        System.out.println("-----------------------------------------------------------------");
        printPreorderRecursive(root);
    }

    /**
     * A helper method to perform an preorder traversal of the tree and print the nodes.
     *
     * @param current The current node being examined.
     */
    
    private void printPreorderRecursive(Node current) {
        if (current != null) {
            System.out.printf("%-40s%-10.2f%n", current.getCountryName(), current.getHappiness());
            // Print the current node
            printPreorderRecursive(current.getLeft()); // Recursively traverse the left subtree
            printPreorderRecursive(current.getRight()); // Recursively traverse the right subtree
        }
    }
    
    /**
     * Print the tree nodes in postorder traversal (LNR).
     */
    
    public void printPostorder() {
        System.out.printf("%-40s%-10s%n", "Name", "Happiness");
        System.out.println("-----------------------------------------------------------------");
        printPostorderRecursive(root);
    }

    /**
     * A helper method to perform an postorder traversal of the tree and print the nodes.
     *
     * @param current The current node being examined.
     */    
    
    private void printPostorderRecursive(Node current) {
        if (current != null) {
            printInorderRecursive(current.getLeft()); // Recursively traverse the left subtree
            printInorderRecursive(current.getRight()); // Recursively traverse the right subtree
            System.out.printf("%-40s%-10.2f%n", current.getCountryName(), current.getHappiness());
            // Print the current node
        }
    }

    
    /**
     * Get a list of the bottom countries based on their happiness indices.
     *
     * @param count The number of bottom countries to retrieve.
     * @return An array of country names representing the bottom countries by happiness in ascending order.
     */
    
    public String[] getBottomCountries(int count) {
        String[] bottomCountries = new String[count];
        double[] bottomHappiness = new double[count]; // Track the happiness values for the bottom countries
        Arrays.fill(bottomHappiness, Double.MAX_VALUE);

        getBottomCountriesRecursive(root, bottomCountries, bottomHappiness, count);
        return bottomCountries;
    }

    // Helper method to retrieve the bottom countries by happiness recursively.
    private void getBottomCountriesRecursive(Node current, String[] result, double[] happinessValues, int count) {
        if (current != null) {
            // Traverse the right subtree first
            getBottomCountriesRecursive(current.getRight(), result, happinessValues, count);

            // Check if the current country has lower happiness than the highest in the bottom list
            double currentHappiness = current.getHappiness();
            if (currentHappiness < happinessValues[count - 1]) {
                // Insert the current country into the bottom list while maintaining ascending order
                int index = count - 1;
                while (index > 0 && currentHappiness < happinessValues[index - 1]) {
                    happinessValues[index] = happinessValues[index - 1];
                    result[index] = result[index - 1];
                    index--;
                }
                happinessValues[index] = currentHappiness;
                result[index] = current.getCountryName();
            }

            // Continue with the left subtree
            getBottomCountriesRecursive(current.getLeft(), result, happinessValues, count);
        }
    }


    /**
     * Get a list of the top countries based on their happiness indices.
     *
     * @param count The number of top countries to retrieve.
     * @return An array of country names representing the top countries by happiness in descending order.
     */
    
    public String[] getTopCountries(int count) {
        String[] topCountries = new String[count];
        double[] topHappiness = new double[count]; // Track the happiness values for the top countries
        Arrays.fill(topHappiness, Double.MIN_VALUE);

        getTopCountriesRecursive(root, topCountries, topHappiness, count);
        return topCountries;
    }

    // Helper method to retrieve the top countries by happiness recursively.
    private void getTopCountriesRecursive(Node current, String[] result, double[] happinessValues, int count) {
        if (current != null) {
            // Traverse the right subtree first
            getTopCountriesRecursive(current.getRight(), result, happinessValues, count);

            // Check if the current country has higher happiness than the lowest in the top list
            double currentHappiness = current.getHappiness();
            if (currentHappiness > happinessValues[count - 1]) {
                // Insert the current country into the top list while maintaining descending order
                int index = count - 1;
                while (index > 0 && currentHappiness > happinessValues[index - 1]) {
                    happinessValues[index] = happinessValues[index - 1];
                    result[index] = result[index - 1];
                    index--;
                }
                happinessValues[index] = currentHappiness;
                result[index] = current.getCountryName();
            }

            // Continue with the left subtree
            getTopCountriesRecursive(current.getLeft(), result, happinessValues, count);
        }
    }
}
