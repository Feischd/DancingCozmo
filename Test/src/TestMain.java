import java.io.File;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestMain extends Application {

	//TestMain ist gleichzeitig der Controller der GUI
	
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

}
