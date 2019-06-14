import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;


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

    private Book book;
    private BackgroundTimer timer;
    private Thread timerThread;

    public GuiController() {}

    @FXML
    private void initialize() {
        book = new Book("text.txt");
        //TODO: implement better design for wait time
        timer = new BackgroundTimer(getDesiredWaitTime((int) speedSetter.getValue()));

        //TODO: restart thread if previousWord button has been pressed and then play pressed
        timerThread = new Thread(timer);

        previousWordBtn.setOnAction(event -> {
            timer.stopTimer();
            wordBox.setText(book.getPreviousWord());
        });

        nextWordBtn.setOnAction(event -> {
            timer.stopTimer();
            wordBox.setText(book.getNextWord());
        });

        toggleReaderBtn.setOnAction(event -> {
            timer.toggleBackgroundTimer();
            if (toggleReaderBtn.getText().equals("play")) {
                toggleReaderBtn.setText("pause");
            } else {
                toggleReaderBtn.setText("play");
            }
        });

        speedSetter.valueProperty().addListener(changeListener -> {
            timer.setWaitTime(getDesiredWaitTime((int) speedSetter.getValue()));
        });

        startTimer();
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

    private void performTimerMethod() {
        wordBox.setText(book.getNextWord());
    }

    class BackgroundTimer extends Timer implements Runnable {
        //private Timer timer;
        private int waitTime;
        boolean timerEnded;

        public BackgroundTimer(int waitTime) {
            //timer = new Timer();
            this.waitTime = waitTime;
            timerEnded = true;
        }

        @Override
        public void run() {
            while (book.hasValidIndex()) {
                while (timerEnded) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException i) {
                        i.printStackTrace();
                    }
                }
                startTimer();
                waitGivenTime(waitTime);
                resetTimer();
                performPeriodicFunction();
            }
        }

        private void toggleBackgroundTimer() {
            timerEnded ^= true;
        }

        private void stopTimer() {
            timerEnded = true;
        }

        private void setWaitTime(int waitTime) {
            this.waitTime = waitTime;
        }

        private void performPeriodicFunction() {
            performTimerMethod();
        }
    }

    public void startTimer() {
        timerThread.start();
    }

}
