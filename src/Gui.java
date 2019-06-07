import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox readerWindow = new HBox(10);
        Accordion menuList = new Accordion(new TitledPane("Options", new VBox()));
        menuList.setExpandedPane(new TitledPane("Options", new VBox()));
        TextArea wordsBox = new TextArea();
        Button nextWord = new Button("next word");
        readerWindow.getChildren().addAll(menuList, wordsBox, nextWord);

        nextWord.setOnAction(e -> {wordsBox.clear();
                            wordsBox.setText(b1.getNextWord());});



        primaryStage.setScene(new Scene(readerWindow, 700, 500));
        primaryStage.show();
    }
}
