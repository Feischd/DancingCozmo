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
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import ucar.nc2.util.IO;

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
	@FXML
	Button Show;
	
	Stage primaryStage;
	private Webservice ws;
	private ArrayList<Song> songs;
	private Cozmo cozmo;

	public Main() {
		cozmo = new Cozmo();
		ws = new Webservice();
		songs = new ArrayList<>();
	}

	// Zaehlvariable zum aktuellen speichern der Pfade
	static int i = 0;

	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	private static String[] pfadSpeicher = new String[1];
	private static String[] dateiNamen;

	public static void main(String[] args) throws IOException {

		// Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");

		// 4 Unterschiedliche Dateitypen sollen gefunden werden
		String findwma = ".wma";
		String findmp3 = ".mp3";
		String findm4a = ".m4a";
		String findaac = ".aac";
		String findwav = ".wav";

		// Ab hier Methoden aufrufen
		searchFile(dir, findwma);
		searchFile(dir, findmp3);
		searchFile(dir, findm4a);
		searchFile(dir, findaac);
		searchFile(dir, findwav);

		// noetig um die Liednamen in GUI anzuzeigen
		dateiNamen = new String[pfadSpeicher.length];

		// Lege Temporaere Pfade an
		legeDateiOrdnerAn();

		// legeSongsAn(pfadSpeicher);
		zerlegeAltenString();

		launch(args);
	}

	private static void legeDateiOrdnerAn() {
		new File("temp").mkdir();
	}

	private static void deleteTemp(){
		File path = new File("Main\\temp");
		deleteTempFiles(path);
	}

	private static void deleteTempFiles(File path){
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				deleteTempFiles(file);
			}
			file.delete();
		}
		path.delete();
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

	// Die einzelnen Pfade werden ausgegeben (Ueberpruefung ob alle Dateien gefunden
	// wurden)
	private static void testausgabe(String pfadtext[]) {
		for (int i = 0; i < pfadtext.length - 1; i++) {
			System.out.println("Der Pfad lautet: " + pfadtext[i]);
		}
	}

	// text = eingegebener Pfad
	private void konverter(String pathInput, String pathOutput){

		try{
			Runtime.getRuntime().exec(new String[] {"Main\\ffmpeg", "-vn", "-i", pathInput, "-ab", "128k", pathOutput});
		}catch(Exception e){
			System.out.println(e);
		}
	}

	private Song getMetadata(String path) {
		boolean converted = false;
		boolean metadataAvailable = false;
		Metadata metadata = null;
		while(!converted || !metadataAvailable){
			try{
				InputStream input = new FileInputStream(new File(path));
				converted = true;

				ContentHandler handler = new DefaultHandler();
				metadata = new Metadata();
				Parser parser = new Mp3Parser();

				ParseContext parseCtx = new ParseContext();
				parser.parse(input, handler, metadata, parseCtx);
				input.close();

				if(metadata.size()>2){
					metadataAvailable = true;
				}
			}catch(Exception e){

			}
		}
		System.out.println(metadata.get("title"));
		System.out.println(metadata.get("xmpDM:artist") );


		if((metadata.get("title") != null) && (metadata.get("xmpDM:artist") != null)){
			return ws.fillSongArray(new Song(metadata.get("title"), metadata.get("xmpDM:artist"), path));
		}else {
			 return new Song("", "", path);
		}
	}

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
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					// Hier muss der Temp-Ordner geloescht werden
					System.out.println("Hier Temp loeschen");
					deleteTemp();
//					loescheTempZumEnde();
				}
			});
			
		} catch (IOException e) {
		}
	}
	
	@FXML
	private void PlayClicked(ActionEvent event) {
		cozmo.play(selectedSong);
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		cozmo.stop();
	}
	
	@FXML
	private void ShowClicked(ActionEvent event) {
		addToListView();
	}

	private void addToListView() {

		for (int i = 0; i < dateiNamen.length; i++) {
			TextLiednamen.getItems().add(dateiNamen[i]);
		}
	}

	@FXML
	private void getIndex() throws IOException {
		int index = TextLiednamen.getSelectionModel().getSelectedIndex();

		 String pathOutput = "temp\\" + dateiNamen[index] + ".mp3";
			 konverter(pfadSpeicher[index], pathOutput);

			 zeigeDatenInDerGUI(getMetadata(pathOutput));
	}
	
	//Bild?
	private void zeigeDatenInDerGUI(Song song) {
		
		if(song.getTrack() != "" && song.getTrack() != null) {
			Titel.setText(song.getTrack());
		} else {
			Titel.setText("kein Eintrag");
		}
		if(song.getArtist() != "" && song.getArtist() != null) {
			Kuenstler.setText(song.getArtist());
		} else {
			Kuenstler.setText("kein Eintrag");
		}
		if(song.getGenre() != "" && song.getGenre() != null) {
			Genre.setText(song.getGenre());
		} else {
			Genre.setText("kein Eintrag");
		}
		if(song.getPublished() != 0) {
			Jahr.setText("" + song.getPublished());
		} else {
			Jahr.setText("keine Eintrag");
		}
		if(song.getAlbum() != "" && song.getAlbum() != null) {
			Album.setText(song.getAlbum());
		} else {
			Album.setText("kein Eintrag");
		}
		if(song.getInformation() != "" && song.getInformation() != null) {
			Sonstiges.setText(song.getInformation());
		} else {
			Sonstiges.setText("keine Eintrag");
		}
	}

	// letzter Teil des Pfades soll ausgeschnitten werden
	private static void zerlegeAltenString() {
		for(int i=0; i<pfadSpeicher.length; i++){
			String path = pfadSpeicher[i];
			String[] split = path.split("\\\\");
			dateiNamen[i] = split[split.length-1].replaceAll(".mp3", "").replaceAll(".wma", "").replaceAll(".wav", "".replaceAll("m4a", "").replaceAll("aac", ""));
		}
	}
}