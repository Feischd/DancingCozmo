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

	// Attribute
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
	
	Stage primaryStage;

	// Zaehlvariable zum aktuellen speichern der Pfade (wird spaeter entfernt)
	static int i = 0;

	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	private static String[] pfadSpeicher = new String[1];
	//nicht loeschen!!!!!!
	private static AudioFilePlayer player = new AudioFilePlayer();

	//Methoden
	public static void main(String[] args) {

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

	// nach einem bestimmten File in einem bestimmten Verzeichnis suchen
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
//			addToTextArea(pfadtext[i]);
		}
	}

	
//Alles zu Mp3 Konvertieren, falls Metadaten, Titel + Kuenstler auslesen --> 
	//Speichern in temp mit Kuenstler + Titel
	
	
	
	
	
	
	
	//text ist fuer den Pfad
	private void TestKonverter(String text) throws IOException {
	
	String[] basisPfad = new String[5];
	
//	String basisPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied"; // ohne
	// .wma
	
	basisPfad[0] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied";
	basisPfad[1] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\04 Winteroorlog";
	basisPfad[2] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\05 En wij stappen stevig voort";
	basisPfad[3] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\06 Furor Teutonicus";
	basisPfad[4] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\07 Het bier zal weer vloeien";
	
	for(int i = 0; i < basisPfad.length; i++) {
		String inputPfad = basisPfad[i] + ".wma";
		String outputPfad = basisPfad[i] + ".mp3";
		
		ProcessBuilder builder = new ProcessBuilder("ffmpeg", "-vn", "-i", inputPfad, "-ab", "128k", outputPfad);
		Process process = builder.start();
	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	//GUI
	


	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	private void mainWindow() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassLoader.getSystemResource("window.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();
			primaryStage.setResizable(false);
			primaryStage.setTitle("DancingCozmo");

			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
		}
	}

	// Beide nur Testweise, spaeter richtig ausprogrammieren und TextArea schreiben
	// in anderer Methode
	@FXML
	private void PlayClicked(ActionEvent event) {
		addToTextArea("Hallo");
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		addToTextArea("Tschuess");
	}
	
	@FXML
	private void addToTextArea(String text) {
		String alterText = TextLiednamen.getText();
		String neuerText = alterText + "\n" + text;
		TextLiednamen.setText(neuerText);
	}
}