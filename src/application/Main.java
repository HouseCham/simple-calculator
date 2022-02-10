package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	private static Stage historyStage = null;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
		Scene scene = new Scene(root);
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
		stage.setTitle("Calculator");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
		createHistoryStage();
	}
	
	public void createHistoryStage() {
		historyStage = new Stage();
		historyStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
		historyStage.setTitle("History");
		historyStage.setResizable(false);
		historyStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	public static Stage getHistoryStage() {
		return historyStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
