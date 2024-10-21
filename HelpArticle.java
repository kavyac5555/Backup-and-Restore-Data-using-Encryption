package simpleDatabase;
import java.io.Serializable;

public class HelpArticle implements Serializable {
    // Title of the article
    private char[] title; 
    
    // Authors of the article, where each author is represented as a char array
    private char[][] authors; 
    
    // Abstract of the article
    private char[] abstractText; 
    
    // Keywords related to the article (currently not used in your application)
    private char[][] keywords; 
    
    // Body of the article
    private char[] body; 
    
    // References cited in the article
    private char[][] references; 

    // Constructor to initialize a HelpArticle with its attributes
    public HelpArticle(char[] title, char[][] authors, char[] abstractText, char[][] keywords, char[] body, char[][] references) {
        this.title = title;
        this.authors = authors;
        this.abstractText = abstractText;
        this.keywords = keywords;
        this.body = body;
        this.references = references;
    }

    // Method to return the authors as an array of Strings
    public String[] getAuthorsAsStringArray() {
        String[] authorsStr = new String[authors.length];
        for (int i = 0; i < authors.length; i++) {
            authorsStr[i] = new String(authors[i]); // Convert each author's char[] to a String
        }
        return authorsStr;
    }

    // Getter for the article's title
    public char[] getTitle() {
        return title;
    }

    // Getter for the article's body
    public char[] getBody() {
        return body;
    }

    // Getter for the article's abstract
    public char[] getAbstractText() {
        return abstractText;
    }

    // Getter for the article's references
    public char[][] getReferences() {
        return references;
    }
}
