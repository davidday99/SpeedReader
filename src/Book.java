import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Book {
    public String title;
    private String entireText;
    private ArrayList<String> words;
    private int currentWordIndex;

    public static void main(String[] args) throws Exception {
        Book b1 = new Book("text.txt");
        System.out.println(b1.getWordAtCurrentIndex());
        System.out.println(b1.getNextWord());
        System.out.println(b1.getNextWord());
        System.out.println(b1.getPreviousWord());
        b1.printWords();
    }

    public Book(String bookTitle) throws FileNotFoundException {
        title = bookTitle;
        currentWordIndex = 0;

        try {
            entireText = new Scanner(new File(title)).useDelimiter("\\A").next();
            words = new ArrayList<>(Arrays.asList(entireText.split(" ")));
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
            f.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printWords() {
        words.forEach(consumer -> System.out.println(consumer));
    }

    public String getWordAtCurrentIndex() {
        return words.get(currentWordIndex);
    }

    public String getNextWord() {
        String nextWord = words.get(currentWordIndex);
        currentWordIndex += 1;
        return nextWord;
    }

    public String getPreviousWord() {
        currentWordIndex -= 1;
        return words.get(currentWordIndex);
    }
}
