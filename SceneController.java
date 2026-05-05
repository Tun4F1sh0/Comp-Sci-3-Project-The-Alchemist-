import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Region;

public class SceneController {

    private static boolean isLightMode = false;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private Label volumeLabel;
    private static double currentVolume = 50.0;
    @FXML private ToggleButton themeToggle;
    @FXML private Slider volumeSlider;

    @FXML
    public void initialize() {
        // Theme
        if (themeToggle != null) {
            themeToggle.setSelected(isLightMode);
            themeToggle.setText(isLightMode ? "Light Mode" : "Dark Mode");
        }

        // Volume
        if (volumeSlider != null && volumeLabel != null) {
            volumeSlider.setValue(currentVolume);
            volumeLabel.setText((int) currentVolume + "%");

            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                currentVolume = newValue.doubleValue(); 
                volumeLabel.setText((int) currentVolume + "%");
            });
        }
    }

    //Navigate
    public void switchScene(ActionEvent event, String fxmlFile) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        
        if (isLightMode) {
            root.setStyle("-fx-background-color: #ecf0f1;");
        } else {
            root.setStyle("-fx-background-color: #2c3e50;");
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToStart(ActionEvent event) throws IOException { switchScene(event, "start.fxml"); }
    public void switchToPlay(ActionEvent event) throws IOException { switchScene(event, "play.fxml"); }
    public void switchToSettings(ActionEvent event) throws IOException { switchScene(event, "settings.fxml"); }
    public void switchToJournal(ActionEvent event) throws IOException { switchScene(event, "journal.fxml"); }


    //Settings 
    @FXML
    void toggleTheme(ActionEvent event) {
        isLightMode = themeToggle.isSelected();
        
        Region root = (Region) themeToggle.getScene().getRoot();

        if (isLightMode) {
            themeToggle.setText("Light Mode");
            root.setStyle("-fx-background-color: #ecf0f1; -fx-padding: 30;");
        } else {
            themeToggle.setText("Dark Mode");
            root.setStyle("-fx-background-color: #2c3e50; -fx-padding: 30;");
        }
    }
}
