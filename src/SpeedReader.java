import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpeedReader extends Application {
    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox window = fxmlLoader.load(this.getClass().getResource("GuiView.fxml").openStream());
        scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Speed Reader");
        primaryStage.show();
    }
}
