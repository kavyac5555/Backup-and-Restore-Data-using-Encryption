package simpleDatabase;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelpArticleManager {
    private List<HelpArticle> articles; // List to store help articles
    private String storageFileName; // File to store articles persistently

    // Constructor to initialize HelpArticleManager with the storage file name
    public HelpArticleManager(String storageFileName) {
        this.storageFileName = storageFileName;
        this.articles = new ArrayList<>(); // Initialize articles list
        loadArticles(); // Load articles from file at startup
    }

    // Load articles from file on startup
    private void loadArticles() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(storageFileName))) {
            articles = (List<HelpArticle>) in.readObject(); // Deserialize articles from file
            System.out.println("Articles loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing articles found. Starting fresh."); // If no file is found, start with an empty list
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading articles: " + e.getMessage()); // Handle errors during deserialization
        }
    }

    // Save articles to file for persistence
    public void saveArticles() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(storageFileName))) {
            out.writeObject(articles); // Serialize articles to file
            System.out.println("Articles saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving articles: " + e.getMessage()); // Handle file saving errors
        }
    }

    // Backup articles to a specified backup file
    public void backupArticles(String backupFileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(backupFileName))) {
            out.writeObject(articles); // Serialize articles to the backup file
            System.out.println("Backup successful to " + backupFileName);
        } catch (IOException e) {
            System.err.println("Backup failed: " + e.getMessage()); // Handle backup errors
        }
    }

    // Restore articles from a specified backup file
    public void restoreArticles(String backupFileName) {
        articles.clear(); // Clear current articles before restoring
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(backupFileName))) {
            articles = (List<HelpArticle>) in.readObject(); // Deserialize articles from the backup file
            System.out.println("Articles restored from " + backupFileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Restore failed: " + e.getMessage()); // Handle restore errors
        }
    }

    // Add an article to the list of articles
    public void addArticle(HelpArticle article) {
        articles.add(article); // Add the article to the articles list
        System.out.println("Article added successfully.");
    }

    // List all articles with their sequence number, title, authors, and references
    public void listArticles() {
        System.out.println("Listing Articles:");
        for (int i = 0; i < articles.size(); i++) {
            HelpArticle article = articles.get(i);
            // Convert authors to a comma-separated string
            String authors = String.join(", ", article.getAuthorsAsStringArray());
            // Print the article's sequence number, title, and authors
            System.out.printf("%d: %s by %s%n", i + 1, new String(article.getTitle()), authors);
            // Print the article's references
            System.out.println("References: ");
            for (char[] reference : article.getReferences()) {
                System.out.println(" - " + new String(reference));
            }
        }
    }

    // Delete an article by its sequence number
    public void deleteArticle(int sequenceNumber) {
        // Validate the sequence number to ensure it exists
        if (sequenceNumber < 1 || sequenceNumber > articles.size()) {
            System.out.println("Invalid sequence number. Please try again.");
            return;
        }
        // Remove the article at the specified index
        articles.remove(sequenceNumber - 1);
        System.out.println("Article deleted successfully.");
    }
}
