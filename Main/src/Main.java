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

	public Main() {
		cozmo = new Cozmo();
		ws = new Webservice();
		songs = new ArrayList<>();
		
		//17.02 von Alex als Kommentar
//		songs.add(new Song("Believe", "Cher",
//				"C:\\Users\\Patrick\\Documents\\DHBW\\Programmieren\\DancingCozmo\\01_Titelnummer_1.wav"));
//		songs.set(0, ws.fillSongArray(songs.get(0)));
		// selectedSong muss spaeter auf null geaendert werden!
//		selectedSong = songs.get(0);
	}

	// Zaehlvariable zum aktuellen speichern der Pfade
	static int i = 0;

	// static int nummer = 0;

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

		// pruefen, ob 1. Alle Dateien gefunden worden sind 2. ein
		// beliebiges Stueck abgespielt werden kann
		// testausgabe(pfadSpeicher);
		// soundDateiAbspielen(pfadSpeicher);

		// Lege Temporaere Pfade an
		legeDateiOrdnerAn();

		// legeSongsAn(pfadSpeicher);
		zerlegeAltenString();

		launch(args);

	}

	private static void legeDateiOrdnerAn() {
		// Alternative, falls temp in Project spaeter nicht gehen sollte
		// File f = new File("C:\\Users\\" + System.getProperty("user.name") +
		// "\\_Dancing Cozmo Temp\\");
		//File f = new File("temp");
		//f.mkdir();
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

	// private static void legeSongsAn(String[] pfadeAlt) {
	// songs.add(new Song(titel, kuenstler, pfad));
	// }

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

		// uebergebener Pfad wird direkt passend Konvertiert und Konvertierte Datei
		// abgespeichert
		// konverter(text);
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

	// Alles zu Mp3 Konvertieren, falls Metadaten, Titel + Kuenstler auslesen -->
	// Speichern in temp mit Kuenstler + Titel

	// text = eingegebener Pfad
	private void konverter(String pathInput, String pathOutput){

		// String[] tempPfade = new String[pfadSpeicher.length];
		//String inputPfad = pfadSpeicher[indexGeklickt];
//		String outputPfad = "temp\\" + indexGeklickt + ".mp3";
		//String pathOutput = "temp\\" + name + ".mp3";

		// String inputPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De
		// Strijdlust is geboren\\03 Het Gelders Volkslied.wma";

		// String inputPfad = text;
		// String outputPfad = "temp\\" + nummer + ".mp3";
		
		//erstes nur bei Alex
//		ProcessBuilder builder = new ProcessBuilder("ffmpeg", "-vn", "-i", inputPfad, "-ab", "128k", outputPfad);
		//ProcessBuilder builder = new ProcessBuilder("Main\\ffmpeg", "-vn", "-i", pathInput, "-ab", "128k", "temp\\" + name + ".mp3");
		//Process process = builder.start();

		try{
			Runtime.getRuntime().exec(new String[] {"Main\\ffmpeg", "-vn", "-i", pathInput, "-ab", "128k", pathOutput});
		}catch(Exception e){
			System.out.println(e);
		}



//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// tempPfade[nummer] = outputPfad;

		// nummer = nummer + 1;

		//getMetadata(outputPfad, indexGeklickt);

		// Alternative, falls temp in Projekt spaeter nicht gehen sollte
		// String outputPfad = "C:\\Users\\" + System.getProperty("user.name") +
		// "\\_Dancing Cozmo Temp\\" + name + ".mp3";

		// String outputPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De
		// Strijdlust is geboren\\03 Het Gelders Volkslied" + ".mp3";
	}

	// Ab hier Metadaten

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
			//songs.add(index, new Song(metadata.get("title"), metadata.get("xmpDM:artist"), path));
			//songs.set(index, ws.fillSongArray(songs.get(index)));
			return ws.fillSongArray(new Song(metadata.get("title"), metadata.get("xmpDM:artist"), path));

		}else {
//				songs.set(index, new Song("", "", ""));
			 return new Song("", "", path);
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
	
//	//noch ausprogrammieren
//	private void loescheTempZumEnde() {
//		
//		File file = new File( "Main/temp");
//		File file = f;
//
//		if ( file.isDirectory() )
//		{         
//		     File[] listFiles = file.listFiles();
//
//		     for ( int i = 0; i < listFiles.length; i++ )
//		     {            
//		         file=( listFiles[ i ] );
//		         file.delete();
//		     }
//		}
//		
//		System.out.println("Temp geloescht");
//		
//	}

//	   public static void deleteDir(File path) {
//		      for (File file : path.listFiles()) {
//		         if (file.isDirectory())
//		            deleteDir(file);
//		         file.delete();
//		      }
//		      path.delete();
//		   }
	
	// Beide nur Testweise, spaeter richtig ausprogrammieren und TextArea schreiben
	// in anderer Methode
	@FXML
	private void PlayClicked(ActionEvent event) {
//		addToListView("Hallo");
		cozmo.play(selectedSong);
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		// addToListView("Tschuess");
		cozmo.stop();
	}
	
	@FXML
	private void ShowClicked(ActionEvent event) {
		addToListView("");
	}

	private void addToListView(String text) {

		// vorher
		// for(int i = 0; i < pfadSpeicher.length; i++) {
		// TextLiednamen.getItems().add(pfadSpeicher[i]);
		// }

		// jetzt
		for (int i = 0; i < dateiNamen.length; i++) {
			TextLiednamen.getItems().add(dateiNamen[i]);
		}

		// TextLiednamen.getItems().add(text);
		// String alterText = TextLiednamen.getText();
		// String neuerText = alterText + "\n" + text;
		// TextLiednamen.setText(neuerText);
	}

	@FXML
	private void getIndex() throws IOException {
		int index = TextLiednamen.getSelectionModel().getSelectedIndex();


//		if (songs.get(index).getTrack() == null) {
//			konverter(index);
//		}
		
		 //if(songs.contains(index)) {

		// }else {
		 String pathOutput = "temp\\" + dateiNamen[index] + ".mp3";
			 konverter(pfadSpeicher[index], pathOutput);
			 getMetadata(pathOutput);
		 //}
		 
		 zeigeDatenInDerGUI(index);
		
	}
	
	//Bild?
	private void zeigeDatenInDerGUI(int indexDesSongs) {
		/*Titel.clear();
		Kuenstler.clear();
		Genre.clear();
		Jahr.clear();
		Album.clear();
		Sonstiges.clear();*/
		
		if(songs.get(indexDesSongs).getTrack() != "" && songs.get(indexDesSongs).getTrack() != null) {
			Titel.setText(songs.get(indexDesSongs).getTrack());
		} else {
			Titel.setText("keinen Titel gefunden");
		}
		if(songs.get(indexDesSongs).getArtist() != "" && songs.get(indexDesSongs).getArtist() != null) {
			Kuenstler.setText(songs.get(indexDesSongs).getArtist());
		} else {
			Kuenstler.setText("kein Kuenstler gefunden");
		}
		if(songs.get(indexDesSongs).getGenre() != "" && songs.get(indexDesSongs).getGenre() != null) {
			Genre.setText(songs.get(indexDesSongs).getGenre());
		} else {
			Genre.setText("kein Genre gefunden");
		}
		if(songs.get(indexDesSongs).getPublished() != 0) {
			Jahr.setText("" + songs.get(indexDesSongs).getPublished());
		} else {
			Jahr.setText("keine Jahresangabe gefunden");
		}
		if(songs.get(indexDesSongs).getAlbum() != "" && songs.get(indexDesSongs).getAlbum() != null) {
			Album.setText(songs.get(indexDesSongs).getAlbum());
		} else {
			Album.setText("kein Album gefunden");
		}
		if(songs.get(indexDesSongs).getInformation() != "" && songs.get(indexDesSongs).getInformation() != null) {
			Sonstiges.setText(songs.get(indexDesSongs).getInformation());
		} else {
			Sonstiges.setText("keine Zusatzinformationen gefunden");
		}
		
		
	}

	// letzter Teil des Pfades soll ausgeschnitten werden
	// ...\\...\\...\hallo.txt --> also hallo.txt
	private static void zerlegeAltenString() {
		for(int i=0; i<pfadSpeicher.length; i++){
			String path = pfadSpeicher[i];
			String[] split = path.split("\\\\");
			dateiNamen[i] = split[split.length-1].replaceAll(".mp3", "").replaceAll(".wma", "").replaceAll(".wav", "".replaceAll("m4a", "").replaceAll("aac", ""));
		}
	}
}

