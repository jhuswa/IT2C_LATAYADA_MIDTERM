package com.scc.ui;

import com.scc.Dao.ScenarioDao;
import com.scc.model.FoodItem;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        ScenarioDao scenarioDao = new ScenarioDao();
        int choice = 0;

        do {
            System.out.println("==================================================");
            System.out.println("          CANTEEN INVENTORY MANAGEMENT            ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Food Item");
            System.out.println("[2] View Canteen Inventory");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline token
            } else {
                scanner.nextLine(); // Clear invalid input buffer
                System.out.println("\nInvalid input. Please enter a number between 1 and 3.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- ADD NEW FOOD ITEM ---");
                    System.out.print("Enter Item Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Category (MEAL / SNACK / BEVERAGE): ");
                    String category = scanner.nextLine().toUpperCase();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Stock Quantity: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    boolean success = scenarioDao.addFoodItem(name, category, price, stock);
                    if (success) {
                        System.out.println("\nSUCCESS: Food item added to inventory!\n");
                    } else {
                        System.out.println("\nFAILED: Could not add food item.\n");
                    }
                    break;

                case 2:
                    List<FoodItem> inventory = scenarioDao.getAllFoodItems();

                    System.out.println("\n==================================================");
                    System.out.println("            CANTEEN FOOD INVENTORY                ");
                    System.out.println("==================================================");
                    System.out.println("+-----+---------------------+-------------+------------+------------+");
                    System.out.println("| ID  | ITEM NAME           | CATEGORY    | PRICE      | STOCK      |");
                    System.out.println("+-----+---------------------+-------------+------------+------------+");

                    for (FoodItem item : inventory) {
                        System.out.printf("| %-3d | %-19s | %-11s | %-10.2f | %-10d |\n",
                                item.getId(),
                                item.getName(),
                                item.getCategory(),
                                item.getPrice(),
                                item.getStock());
                    }

                    System.out.println("+-----+---------------------+-------------+------------+------------+");
                    System.out.println("Total Records Displayed: " + inventory.size() + "\n");
                    break;

                case 3:
                    System.out.println("\nExiting system... Thank you!\n");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please select between 1 and 3.\n");
            }

        } while (choice != 3);

        scanner.close();
    }
}