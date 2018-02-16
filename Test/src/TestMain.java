import java.io.File;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestMain extends Application {

	//TestMain ist gleichzeitig der Controller der GUI
	
	@FXML
	ListView List;
	
	@FXML
	Button Stop;
	
	@FXML
	Button Play;
	
	Stage primaryStage;

	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	private void mainWindow() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("TestFMXL.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			// primaryStage.setMinHeight(600.00);
			// primaryStage.setMinWidth(900.00);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Test");

			Scene scene = new Scene(pane); // create new scene, append pane to scene
			// scene.getStylesheets().add(getClass().getResource("/css/MainWindow.css").toExternalForm());
			primaryStage.setScene(scene); // append scene to stage
			primaryStage.show(); // show stage

			} catch (IOException e) {
		}
	}

	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	public static String[] pfadSpeicher = new String[1];

	public static void main(String[] args) {

		// Fehler
		// launch(args);

		// Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");

		// 4 Unterschiedliche Dateitypen sollen gefunden werden
		// mp3, wma, m4a, aac
		String findwma = ".wma";
		String findmp3 = ".mp3";
		String findm4a = ".m4a";
		String findaac = ".aac";

		// Ab hier Methoden aufrufen
		TestMethoden.searchFile(dir, findwma);
		TestMethoden.searchFile(dir, findmp3);
		TestMethoden.searchFile(dir, findm4a);
		TestMethoden.searchFile(dir, findaac);

		// Testmethoden um zu pruefen, ob 1. Alle Dateien gefunden worden sind 2. ein
		// beliebiges Stueck abgespielt werden kann
		TestMethoden.testausgabe(pfadSpeicher);
		TestMethoden.soundDateiAbspielen(pfadSpeicher);

		launch(args);
	}
	
	
	
	@FXML
	private void PClick(ActionEvent event) {
//		addToTextArea("Hallo");
	}

	@FXML
	private void SClick(ActionEvent event) {
//		addToTextArea("Tschuess");
	}

//	@FXML
	private void addToTextArea(String text) {
		
//		List.getItems().add(text);
		
//		String alterText = ListView.getText();
//		String neuerText = alterText + "\n" + text;
//		ListView.setText(neuerText);
	}
	
	@FXML
	private void getIndex() {
		int index = List.getSelectionModel().getSelectedIndex();
	}
	
	
	
	//vorher war TextArea:  <TextArea id="TextLiednamen" editable="false" prefHeight="450.0" prefWidth="250.0" text="Hier werden die Liednamen stehen" />

}
