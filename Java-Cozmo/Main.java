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
	private Song selectedSong;
	private static String[] format = {".wma", ".mp3", ".m4a", ".aac", ".wav"};


	public Main() {
		cozmo = new Cozmo();
		ws = new Webservice();
		songs = new ArrayList<>();
		selectedSong = new Song("", "", "");
	}


	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	//private static String[] pfadSpeicher = new String[1];
	private static ArrayList<String> pfadSpeicher = new ArrayList<>();
	//private static String[] dateiNamen;
	private static ArrayList<String> dateiNamen = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");
		searchFile(dir);

		// noetig um die Liednamen in GUI anzuzeigen
		//dateiNamen = new String[pfadSpeicher.size()];

		// Lege Temporaere Pfade an
		new File("temp").mkdir();

		// legeSongsAn(pfadSpeicher);
		zerlegeAltenString();

		launch(args);
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
	private static ArrayList<File> searchFile(File dir) throws IOException {
		// Dateien werden gesucht, gefunden und die Pfade gespeichert
		File[] files = dir.listFiles();

		// Matches koennen spaeter eventuell entfernt werden
		ArrayList<File> matches = new ArrayList<File>();
		if (files != null) {
			for(String typ: format){
				for (int i=0; i<files.length; i++) {
					if (files[i].getName().endsWith(typ)) {
						pfadSpeicher.add(files[i].getPath());
						matches.add(files[i]);
					}

					if (files[i].isDirectory()) {
						// fuegt der ArrayList die ArrayList mit den Treffern aus dem Unterordner hinzu
						matches.addAll(searchFile(files[i]));
					}
				}
			}
		}
		return matches;
	}


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
				File file = new File(path);
				InputStream input = new FileInputStream(file);
				converted = true;

				path = file.getAbsolutePath();

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

		String track = metadata.get("title");
		String artist = metadata.get("xmpDM:artist");

		if(track != null && artist != null){
			return ws.fillSongArray(new Song(metadata.get("title"), metadata.get("xmpDM:artist"), path));
		}
		else {
			if(track == null){
				track = "";
			}
			if(artist == null){
				artist = "";
			}
			return new Song(track, artist, path);
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
		System.out.println(selectedSong.getPath());
		cozmo.play(selectedSong);
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		cozmo.stop();
	}
	
	@FXML
	private void ShowClicked(ActionEvent event) {
		for (int i = 0; i < dateiNamen.size(); i++) {
			TextLiednamen.getItems().add(dateiNamen.get(i));
		}
	}


	@FXML
	private void getIndex(){
		int index = TextLiednamen.getSelectionModel().getSelectedIndex();

		 String pathOutput = "temp\\" + dateiNamen.get(index) + ".mp3";
		 konverter(pfadSpeicher.get(index), pathOutput);

		 selectedSong = getMetadata(pathOutput);

		 zeigeDatenInDerGUI(selectedSong);
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
			Jahr.setText("kein Eintrag");
		}
		if(song.getAlbum() != "" && song.getAlbum() != null) {
			Album.setText(song.getAlbum());
		} else {
			Album.setText("kein Eintrag");
		}
		if(song.getInformation() != "" && song.getInformation() != null) {
			Sonstiges.setText(song.getInformation());
		} else {
			Sonstiges.setText("kein Eintrag");
		}
	}

	// letzter Teil des Pfades soll ausgeschnitten werden
	private static void zerlegeAltenString() {
		for(int i=0; i<pfadSpeicher.size(); i++){
			String path = pfadSpeicher.get(i);
			String[] split = path.split("\\\\");
			dateiNamen.add(i, split[split.length-1].replaceAll(".mp3", "").replaceAll(".wma", "").replaceAll(".wav", "".replaceAll("m4a", "").replaceAll("aac", "")));
		}
	}
}