import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Book {
    public String title;
    private String entireText;    // string containing full text
    private ArrayList<String> words;    // array containing each whitespace-separated word
    private int currentWordIndex;    // index set to currently viewed word in the arraylist

    public Book(String bookTitle) {
        title = bookTitle;
        currentWordIndex = 0;

        try {
            entireText = new Scanner(new File(title)).useDelimiter("\\A").next();    // store entire text in the string
            words = new ArrayList<>(Arrays.asList(entireText.split("[\\s\\n]+")));  // split by whitespace, newlines
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
            f.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Print out all words contained in the arraylist, each on a separate line
     */
    public void printWords() {
        words.forEach(consumer -> System.out.println(consumer));
    }

    /**
     * Check that currentWordIndex is within bounds of the arraylist
     * @return
     */
    public boolean hasValidIndex() {
        return currentWordIndex >= 0 && currentWordIndex < words.size();
    }

    /**
     * Get the word at the current index
     * @return string contained at currentWordIndex of arraylist, null if index has reached end of arraylist
     */
    public String getWordAtCurrentIndex() {
        if (hasValidIndex()) return words.get(currentWordIndex);
        else return null;
    }

    /**
     * Get the word at currentIndex and increment index
     * Returns same word as getWord atCurrentIndex(), the only difference is
     * that this method will then move on to the next word in the arraylist
     * @return string contained at currentWordIndex of arraylist, null if index has reached end of arraylist
     */
    public String getNextWord() {
        if (hasValidIndex()) {
            String nextWord = words.get(currentWordIndex);
            currentWordIndex += 1;
            return nextWord;
        } else {
            return null;
        }
    }

    /**
     * Get the word contained at the previous index
     * This method will decrement the index so that it is now viewing the previous word
     * @return string contained at currentWordIndex after it has been decremented, null if index is at arraylist head
     */
    public String getPreviousWord() {
        currentWordIndex -= 1;
        if (hasValidIndex()) return words.get(currentWordIndex);
        else {
            currentWordIndex += 1;    // return index to initial location prior to method call
            return null;
        }
    }

    public static void main(String[] args) {
        Book b1 = new Book("text.txt");
       /* System.out.println(b1.getWordAtCurrentIndex());
        System.out.println(b1.getNextWord());
        System.out.println(b1.getNextWord());
        System.out.println(b1.getPreviousWord());*/
       b1.printWords();
    }
}
