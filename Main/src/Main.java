import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
	ListView TextLiednamen;
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
	private static AudioFilePlayer afp;
	private static Webservice ws;
	private static CozmoPlayer cp;
	private static ArrayList<Song> songs;
	private static Process p;
	private static Process p2;


	public Main(){
		afp = new AudioFilePlayer();
		ws = new Webservice();
		cp = new CozmoPlayer();
		songs = new ArrayList<>();
		songs.add(new Song("Believe", "Cher", "C:\\Users\\Patrick\\Documents\\DHBW\\Programmieren\\DancingCozmo\\01_Titelnummer_1.wav"));
		songs.set(0, ws.fillSongArray(songs.get(0)));
		System.out.println(songs.get(0).getGenre());

	}

	// Zaehlvariable zum aktuellen speichern der Pfade (wird spaeter entfernt)
	static int i = 0;
	
	static int nummer = 0;

	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	private static String[] pfadSpeicher = new String[1];
	// nicht loeschen!!!!!!
	private static AudioFilePlayer player = new AudioFilePlayer();

	// Methoden
	public static void main(String[] args) throws IOException {


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
//		testausgabe(pfadSpeicher);
//		soundDateiAbspielen(pfadSpeicher);
		
		launch(args);

	}

	// nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	private static ArrayList<File> searchFile(File dir, String find) throws IOException {

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
	private static void savePfad(String text) throws IOException {
		pfadSpeicher = verlaengere(pfadSpeicher);
		pfadSpeicher[i] = text;
		i = i + 1;
		
		//uebergebener Pfad wird direkt passend Konvertiert und Konvertierte Datei abgespeichert
//		konverter(text);
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

	// Alles zu Mp3 Konvertieren, falls Metadaten, Titel + Kuenstler auslesen -->
	// Speichern in temp mit Kuenstler + Titel

	// text = eingegebener Pfad
	private static void konverter(String text) throws IOException {

		String[] tempPfade = new String[pfadSpeicher.length];
		
		// Alternative, falls temp in Project spaeter nicht gehen sollte
		// File f = new File("C:\\Users\\" + System.getProperty("user.name") +
		// "\\_Dancing Cozmo Temp\\");
		File f = new File("temp");
		f.mkdir();

//		String inputPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied.wma";

			String inputPfad = text;
			String outputPfad = "temp\\" + nummer + ".mp3";
			ProcessBuilder builder = new ProcessBuilder("ffmpeg", "-vn", "-i", inputPfad, "-ab", "128k", outputPfad);
			Process process = builder.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tempPfade[nummer] = outputPfad;
			
			nummer = nummer + 1;
			
//			getMetadata(outputPfad);
		
		
		
		
		
		// Alternative, falls temp in Projekt spaeter nicht gehen sollte
		// String outputPfad = "C:\\Users\\" + System.getProperty("user.name") +
		// "\\_Dancing Cozmo Temp\\" + name + ".mp3";

//		String outputPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied" + ".mp3";
	}

	// Ab hier Metadaten

	private static void getMetadata(String text) {

		String fileLocation = text;

		try {

			InputStream input = new FileInputStream(new File(fileLocation));
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();

			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();

			// List all metadata
			String[] metadataNames = metadata.names();

			for (String name : metadataNames) {
				System.out.println(name + ": " + metadata.get(name));
			}

			// Retrieve the necessary info from metadata
			// Names - title, xmpDM:artist etc. - mentioned below may differ based
			// System.out.println("----------------------------------------------");
			// System.out.println("Title: " + metadata.get("title"));
			// System.out.println("Artists: " + metadata.get("xmpDM:artist"));
			// System.out.println("Composer : " + metadata.get("xmpDM:composer"));
			// System.out.println("Genre : " + metadata.get("xmpDM:genre"));
			// System.out.println("Album : " + metadata.get("xmpDM:album"));

//			addToTextArea(metadata.get("xmpDM:artist") + " " + metadata.get("title"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}

	// Konvertieren
	// speichern
	// --> mit nummern
	// Metadaten auslesen
	// Diese in Gui anzeigen
	// --> in TextArea mit zusammengesetztem namen

	// GUI

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
		addToListView("Hallo");
		cp.play(songs.get(0));
		//afp.play(songs.get(0).getPath());

        try {
            //Process a = Runtime.getRuntime()
            p= Runtime.getRuntime().exec(new String[] { "C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe", songs.get(0).getPath() });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //Process a = Runtime.getRuntime()
            p2= Runtime.getRuntime().exec(new String[] { "C:\\Windows\\System32\\cmd.exe", "cd C:\\Users\\Patrick\\Documents\\DHBW\\Programmieren\\DancingCozmo\\cozmoDance.py" });
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		addToListView("Tschuess");
		p.destroy();
		p2.destroy();
	}

//	@FXML
	private void addToListView(String text) {
		TextLiednamen.getItems().add(text);
//		String alterText = TextLiednamen.getText();
//		String neuerText = alterText + "\n" + text;
//		TextLiednamen.setText(neuerText);
	}

	@FXML
	private void getIndex() {
		int index = TextLiednamen.getSelectionModel().getSelectedIndex();
		System.out.println(index);
	}

}