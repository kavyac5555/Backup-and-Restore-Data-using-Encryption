package simpleDatabase;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Initialize HelpArticleManager to manage articles, loading from "articles.dat"
        HelpArticleManager articleManager = new HelpArticleManager("articles.dat");

        // Main loop for user interaction with commands
        String command;
        do {
            System.out.println("Enter command (add, list, delete, backup, restore, exit):");
            command = scanner.nextLine(); // Read user command

            switch (command) {
                case "add":
                    // Adding a new article
                    System.out.println("Enter title:");
                    char[] title = scanner.nextLine().toCharArray(); // Read article title

                    System.out.println("Enter authors (comma-separated):");
                    String[] authorInputs = scanner.nextLine().split(",\\s*"); // Read authors and split by commas
                    char[][] authors = new char[authorInputs.length][];
                    for (int i = 0; i < authorInputs.length; i++) {
                        authors[i] = authorInputs[i].toCharArray(); // Convert each author to a char array
                    }

                    System.out.println("Enter abstract:");
                    char[] abstractText = scanner.nextLine().toCharArray(); // Read article abstract

                    System.out.println("Enter body:");
                    char[] body = scanner.nextLine().toCharArray(); // Read article body

                    System.out.println("Enter references (comma-separated):");
                    String[] referenceInputs = scanner.nextLine().split(",\\s*"); // Read references and split by commas
                    char[][] references = new char[referenceInputs.length][];
                    for (int i = 0; i < referenceInputs.length; i++) {
                        references[i] = referenceInputs[i].toCharArray(); // Convert each reference to a char array
                    }

                    // Create a new HelpArticle object with the provided data
                    HelpArticle article = new HelpArticle(title, authors, abstractText, new char[0][], body, references);

                    // Add the article to the HelpArticleManager
                    articleManager.addArticle(article);
                    break;

                case "list":
                    // List all stored articles
                    articleManager.listArticles();
                    break;

                case "delete":
                    // Delete an article by its sequence number
                    System.out.println("Enter the sequence number of the article to delete:");
                    int sequenceNumber = Integer.parseInt(scanner.nextLine()); // Read sequence number
                    articleManager.deleteArticle(sequenceNumber); // Remove the article
                    break;

                case "backup":
                    // Backup articles to a specified file
                    System.out.println("Enter backup file name:");
                    String backupFile = scanner.nextLine(); // Read backup file name
                    articleManager.backupArticles(backupFile); // Perform backup
                    break;

                case "restore":
                    // Restore articles from a specified backup file
                    System.out.println("Enter backup file name:");
                    String restoreFile = scanner.nextLine(); // Read restore file name
                    articleManager.restoreArticles(restoreFile); // Restore articles from backup
                    break;

                case "exit":
                    // Exit the application, saving articles to the default file
                    articleManager.saveArticles(); // Save articles before exiting
                    System.out.println("Exiting application.");
                    break;

                default:
                    // Handle unknown commands
                    System.out.println("Unknown command. Please try again.");
            }
        } while (!command.equals("exit")); // Loop until "exit" command is entered

        // Close the scanner after exiting
        scanner.close();
    }
}
