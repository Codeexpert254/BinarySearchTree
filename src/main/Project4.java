/**
 * COP 3530: Project 4 – Binary Search Trees
 * <p>
 * This class represents a menu-driven application or program that allows users to interact with a binary search tree
 * of nations and their happiness ratings. Users can perform various activities, such as loading data from a CSV file,
 * printing the tree in different traversal orders, adding and removing countries, finding a country's happiness score,
 * and displaying the happiest and least happy countries.
 * <p>
 * Input: The Input contains a user's choice/options from the menu plus any additional input for certain operations.
 * Output: The Output contains the results of the chosen operation which is displayed according to the format specified.
 *
 * @author [Your Name]
 * @version [Date Last Modified]
 */

// Import Statements: Imports all the necessary classes for your application or program
package main;

import java.io.File;
import bst.BinarySearchTree;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * The main class for Project 4 - Binary Search Trees.
 */
public class Project4 {
    /**
     * The entry point for the program.
     * Initializes a binary search tree (tree) and loads data from a CSV file using the loadCSVData method.
     * Then, it displays a menu system for user interaction.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        loadCSVData(tree);

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            printMenu();
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 9) {
                    switch (choice) {
                        case 1:
                            printInorder(tree);
                            break;
                        case 2:
                            printPreorder(tree);
                            break;
                        case 3:
                            printPostorder(tree);
                            break;
                        case 4:
                            insertCountry(tree, scanner);
                            break;
                        case 5:
                            deleteCountry(tree, scanner);
                            break;
                        case 6:
                            searchAndPrintCountry(tree, scanner);
                            break;
                        case 7:
                            printBottomCountries(tree, scanner);
                            break;
                        case 8:
                            printTopCountries(tree, scanner);
                            break;
                        case 9:
                            System.out.println("Have a good day!");
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Enter 1-9:");
                }
            } else {
                scanner.nextLine();  // Clear the invalid input
                System.out.println("Invalid choice. Enter 1-9.");
            }
        } while (choice != 9);
    }

    /**
     * Loads data from a CSV file and adds it to the binary search tree.
     * An error message is displayed if the CSV file is not found or if there are any parsing errors.
     *
     * @param tree The binary search tree to load data into.
     */
    private static void loadCSVData(BinarySearchTree tree) {
        try {
            File file = new File("src/data/Countries4.csv");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length == 6) {
                    String name = data[0];
                    double happiness = Double.parseDouble(data[5]);
                    tree.insert(name, happiness);
                }
            }

            System.out.println("Data loaded into the binary search tree.");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found. Make sure it is in the 'data' folder.");
        }
    }

    /**
     * Displays the main menu options for the user.
     */
    private static void printMenu() {
        System.out.println("1) Print tree inorder");
        System.out.println("2) Print tree preorder");
        System.out.println("3) Print tree postorder");
        System.out.println("4) Insert a country with name and happiness");
        System.out.println("5) Delete a country for a given name");
        System.out.println("6) Search and print a country and its path for a given name");
        System.out.println("7) Print bottom countries regarding happiness");
        System.out.println("8) Print top countries regarding happiness");
        System.out.println("9) Exit");
    }

    /**
     * Prints the binary search tree in inorder traversal.
     *
     * @param tree The binary search tree to print.
     */
    private static void printInorder(BinarySearchTree tree) {
        System.out.println("Inorder Traversal:");
        tree.printInorder();
        System.out.println();
    }

    /**
     * Prints the binary search tree in preorder traversal.
     *
     * @param tree The binary search tree to print.
     */
    private static void printPreorder(BinarySearchTree tree) {
        System.out.println("Preorder Traversal:");
        tree.printPreorder();
        System.out.println();
    }

    /**
     * Prints the binary search tree in postorder traversal.
     *
     * @param tree The binary search tree to print.
     */
    private static void printPostorder(BinarySearchTree tree) {
        System.out.println("Postorder Traversal:");
        tree.printPostorder();
        System.out.println();
    }

    /**
     * Prompts the user to input a country name and its happiness rating, and adds it to the binary tree.
     *
     * @param tree    The binary search tree to add the country to.
     * @param scanner The scanner for user input.
     */
    private static void insertCountry(BinarySearchTree tree, Scanner scanner) {
        System.out.print("Enter country name: ");
        String name = scanner.nextLine();
        System.out.print("Enter country happiness: ");
        double happiness = scanner.nextDouble();
        scanner.nextLine();
        tree.insert(name, happiness);
        System.out.println(name + " with happiness of " + happiness + " is inserted.");
    }

    /**
     * Asks the user to input a country name for deletion, and removes it from the binary search tree.
     *
     * @param tree    The binary search tree to remove the country from.
     * @param scanner The scanner for user input.
     */
    private static void deleteCountry(BinarySearchTree tree, Scanner scanner) {
        System.out.print("Enter country name: ");
        String name = scanner.nextLine();
        tree.delete(name);
        System.out.println(name + " is deleted from the binary search tree.");
    }

    /**
     * Asks the user to input a country name and searches for it in the binary search tree.
     * If found, it prints the country's name and happiness score along with its path in the tree.
     * If not found, it outputs a message that the country is not found.
     *
     * @param tree    The binary search tree to search in.
     * @param scanner The scanner for user input.
     */
    private static void searchAndPrintCountry(BinarySearchTree tree, Scanner scanner) {
        System.out.print("Enter country name: ");
        String name = scanner.nextLine();
        double happiness = tree.find(name);
        if (happiness == -1) {
            System.out.println(name + " is not found.");
        } else {
            System.out.println(name + " is found with happiness of " + happiness);
            System.out.print("Path: ");
            tree.printPath(name);
            System.out.println();
        }
    }

    /**
     * Asks the user to input the number of countries to list and retrieves a list of the bottom countries
     * regarding happiness. It displays the list in a tabular format.
     *
     * @param tree    The binary search tree to retrieve countries from.
     * @param scanner The scanner for user input.
     */
    private static void printBottomCountries(BinarySearchTree tree, Scanner scanner) {
        System.out.print("Enter the number of countries: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        
        String[] bottomCountries = tree.getBottomCountries(count);
       
        if (bottomCountries.length == 0 ) {
            System.out.println("No countries found.");
        } else {
            System.out.println("Bottom " + count + " countries by happiness:");
            System.out.println("Name                                   Happiness");
            System.out.println("-----------------------------------------------------------------");
            for (String country : bottomCountries) {
                double happiness = tree.find(country);
                System.out.printf("%-40s %-10s%n", country, happiness);
            }
        }
    }

    /**
     * Asks the user to input the number of countries to list and then retrieves a list of the top countries
     * regarding happiness. It displays the list in a tabular format.
     *
     * @param tree    The binary search tree to retrieve countries from.
     * @param scanner The scanner for user input.
     */
    private static void printTopCountries(BinarySearchTree tree, Scanner scanner) {
        System.out.print("Enter the number of countries: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        String[] topCountries = tree.getTopCountries(count);

        if (topCountries.length == 0) {
            System.out.println("No countries found.");
        } else {
            System.out.println("Top " + count + " countries by happiness:");
            System.out.println("Name                                   Happiness");
            System.out.println("-----------------------------------------------------------------");

            for (String country : topCountries) {
                double happiness = tree.find(country);
                System.out.printf("%-40s %-10.3f%n", country, happiness);
            }
        }
    }
}
