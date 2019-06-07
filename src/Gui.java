import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

    Book b1 = new Book("text.txt");
    TextArea wordsBox = new TextArea();
    Button nextWord = new Button("next word");



    class BackgroundTimer implements Runnable {
        private Timer timer;
        private int waitTime;
        boolean timerEnded;

        public BackgroundTimer(int waitTime) {
            timer = new Timer();
            this.waitTime = waitTime;
            timerEnded = false;
        }

        @Override
        public void run() {
            while (b1.hasValidIndex()) {
                timer.startTimer();
                timer.waitGivenTime(waitTime);
                timer.resetTimer();
                wordsBox.clear();
                wordsBox.setText(b1.getNextWord());
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox readerWindow = new HBox(10);
        Accordion menuList = new Accordion(new TitledPane("Options", new VBox()));
        menuList.setExpandedPane(new TitledPane("Options", new VBox()));

        readerWindow.getChildren().addAll(menuList, wordsBox, nextWord);

        nextWord.setOnAction(e -> {wordsBox.clear();
                            wordsBox.setText(b1.getNextWord());});



        primaryStage.setScene(new Scene(readerWindow, 700, 500));
        primaryStage.show();

        /* this code should be placed within some button setOnAction */
        Thread t1 = new Thread(new BackgroundTimer(200));
        t1.start();
    }
}
