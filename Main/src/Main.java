import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	// Alles FXML Sachen
	@FXML
	AnchorPane AnchorP;

	@FXML
	TextArea TextLiednamen;

	@FXML
	ImageView Bild;

	@FXML
	TextField Titel;

	@FXML
	TextField Kuenstler;

	@FXML
	TextField Genre;

	@FXML
	TextField Jahr;

	@FXML
	TextField Album;

	@FXML
	TextArea Sonstiges;

	@FXML
	Button Stop;

	@FXML
	Button Play;

	// Beide nur Testweise, spaeter richtig ausprogrammieren und TextArea schreiben
	// in anderer Methode
	@FXML
	private void PlayClicked(ActionEvent event) {
		String text = TextLiednamen.getText();
		text = text + "\n Hallo";
		TextLiednamen.setText(text);
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		String text = TextLiednamen.getText();
		text = text + "\n Tschuess";
		TextLiednamen.setText(text);
	}

	
	
	private void addToTextArea(String text) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Zaehlvariable zum aktuellen speichern der Pfade (wird spaeter entfernt)
	static int i = 0;

	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	private static String[] pfadSpeicher = new String[1];
	private static AudioFilePlayer player = new AudioFilePlayer();

	public static void main(String[] args) {

		Controller controller = new Controller();

		// Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");

		// 4 Unterschiedliche Dateitypen sollen gefunden werden
		String findwma = ".wma";
		String findmp3 = ".mp3";
		String findm4a = ".m4a";
		String findaac = ".aac";

		// Ab hier Methoden aufrufen
		searchFile(dir, findwma);
		searchFile(dir, findmp3);
		searchFile(dir, findm4a);
		searchFile(dir, findaac);

		// pruefen, ob 1. Alle Dateien gefunden worden sind 2. ein
		// beliebiges Stueck abgespielt werden kann
		testausgabe(pfadSpeicher);
		soundDateiAbspielen(pfadSpeicher);

		launch(args);

	}

	// Ab hier stehen alle Methoden, die spaeter aufgerufen werden

	// Methode um nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	private static ArrayList<File> searchFile(File dir, String find) {

		String name = "";
		String pfad = "";

		// Dateien werden gesucht, gefunden und die Pfade gespeichert
		File[] files = dir.listFiles();

		// Matches koennen spaeter eventuell entfernt werden
		ArrayList<File> matches = new ArrayList<File>();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {

				if (files[i].getName().endsWith(find)) {

					name = name + files[i].getName();
					pfad = pfad + files[i].getPath();

					savePfad(pfad);

					name = "";
					pfad = "";

					matches.add(files[i]);
				}

				if (files[i].isDirectory()) {
					// fuegt der ArrayList die ArrayList mit den Treffern aus dem Unterordner hinzu
					matches.addAll(searchFile(files[i], find));
				}
			}
		}

		return matches;
	}

	// Pfad speichern
	private static void savePfad(String text) {
		pfadSpeicher = verlaengere(pfadSpeicher);
		pfadSpeicher[i] = text;
		i = i + 1;
	}

	// Das Array zum Speichern der Pfade um 1 Stelle verlaengern, Array ist flexibel
	private static String[] verlaengere(String[] array) {
		String[] hilfe = new String[array.length + 1];

		for (int i = 0; i < array.length; i++) {
			hilfe[i] = array[i];
		}
		hilfe[array.length] = "";

		return hilfe;

	}

	// Musik wird abgespielt
	private static void soundDateiAbspielen(String[] pfadtext) {

		// Play with AudioFilePlayer in GUI
		// player.play(pfadtext[1]);
	}

	// Die einzelnen Pfade werden ausgegeben (Ueberpruefung ob alle Dateien gefunden
	// wurden)
	private static void testausgabe(String pfadtext[]) {
		for (int i = 0; i < pfadtext.length - 1; i++) {
			System.out.println("Der Pfad lautet: " + pfadtext[i]);
		}
	}

	Stage primaryStage;

	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	private void mainWindow() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("window.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			// primaryStage.setMinHeight(600.00);
			// primaryStage.setMinWidth(900.00);
			primaryStage.setResizable(false);
			primaryStage.setTitle("DancingCozmo");

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
		}
	}
}

//hallo alex