import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GuiController {
    @FXML
    private Button previousWordBtn;
    @FXML
    private Button nextWordBtn;
    @FXML
    private Button toggleReaderBtn;
    @FXML
    private Slider speedSetter;
    @FXML
    private Text wordBox;
    @FXML
    private TextArea textView;

    private Book book;
    private Timeline timer;  //controls the animation of the words getting printed on the screen

    public GuiController() {}

    @FXML
    private void initialize() {
        //TODO: allow user to enter custom text file name
        book = new Book("text.txt");

        previousWordBtn.setOnAction(event -> {
            timer.stop();
            toggleReaderBtn.setText("play");
            wordBox.setText(book.getPreviousWord());
        });

        nextWordBtn.setOnAction(event -> {
            timer.stop();
            toggleReaderBtn.setText("play");
            wordBox.setText(book.getNextWord());
        });

        toggleReaderBtn.setOnAction(event -> {
            if (isTimerRunning()) {
                timer.stop();
                toggleReaderBtn.setText("play");
            } else {
                timer.play();
                toggleReaderBtn.setText("pause");
            }
        });

        speedSetter.valueProperty().addListener(changeListener -> {
            boolean timerOn = true;
            if (timer.getCurrentRate() == 0.0) {timerOn = false;}
            timer.stop();
            timer = new Timeline(new KeyFrame(Duration.millis(getDesiredWaitTime((int) speedSetter.getValue())),
                    event -> wordBox.setText(book.getNextWord())));
            timer.setCycleCount(Timeline.INDEFINITE);
            if (timerOn) {timer.play();}
        });

        textView.setText(book.getBookText());

        timer = new Timeline(new KeyFrame(Duration.millis(getDesiredWaitTime((int) speedSetter.getValue())),
                event -> wordBox.setText(book.getNextWord())));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    //TODO: implement better design for wait time
    public int getDesiredWaitTime(int wordsPerMin) {
        if (wordsPerMin >= 30 && wordsPerMin < 60) {
            return 600;
        } else if (wordsPerMin >= 60 && wordsPerMin < 90) {
            return 400;
        } else {
            return 200;
        }
    }

    private boolean isTimerRunning() {
        return timer.getCurrentRate() != 0.0;
    }
}
