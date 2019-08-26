import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Book {
    public String title;
    private String entireText;    // string containing full text
    private List<String> words;    // array containing each whitespace-separated word
    private ListIterator<String> index;
    private boolean nextPressed;
    private boolean prevPressed;

    public Book(String bookTitle) {
        title = bookTitle;
        nextPressed = false;
        prevPressed = false;

        try {
            entireText = new Scanner(new File(title)).useDelimiter("\\A").next();    // store entire text in the string
            words = new ArrayList<>(Arrays.asList(entireText.split("[\\s\\n]+")));  // split by whitespace, newlines
            index = words.listIterator();
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
            f.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the entire contents of the book
     * @return string containing the book's text
     */
    public String getBookText() {
        return entireText;
    }

    /**
     * Print out all words contained in the arraylist, each on a separate line
     */
    public void printWords() {
        words.forEach(consumer -> System.out.println(consumer));
    }

    /**
     * Get the next word using the ListIterator
     * @return next string in arraylist, null if index has reached end of arraylist
     */
    public String getNextWord() {
        //if the prev button was previously pressed, index must be incremented twice to get the next string
        if (prevPressed && index.hasNext()) {
            prevPressed = false;
            index.next();
        }
        if (index.hasNext()) {
            nextPressed = true;
            return index.next();
        } else {  //if no next word exists, reset next to avoid skipping over the last word
            nextPressed = false;
        }
        return null;
    }

    /**
     * Get the previous word using the ListIterator
     * @return previous string in arraylist, null if index is at arraylist head
     */
    public String getPreviousWord() {
        //if the next button was previously pressed, index must be decremented twice to get the previous string
        if (nextPressed && index.hasPrevious()) {
            nextPressed = false;
            index.previous();
        }
        if (index.hasPrevious()) {
            prevPressed = true;
            return index.previous();
        } else {  //if no previous word exists, reset prevPressed to avoid skipping over the first word
            prevPressed = false;
        }
        return null;
    }

    public static void main(String[] args) {
        Book b1 = new Book("text.txt");
        b1.printWords();
    }
}
