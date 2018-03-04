import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.imageio.ImageIO;
import java.io.Serializable;

public class Main extends Application implements Serializable {

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
	TextField Suche;
	@FXML
	TextArea Sonstiges;
//	@FXML
//	Button Stop;
//	@FXML
//	Button Play;
//	@FXML
//	Button Show;
//	@FXML
//	Button Dark;
//	@FXML
//	Button Light;
//	@FXML
//	Button DurchsuchePfad;

	Stage primaryStage;
	private Webservice ws;
	private Sort sort;
	private static ArrayList<Song> songs = new ArrayList<>();
	private Cozmo cozmo;
	private Song selectedSong;
	private static String[] format = new String[] { ".wma", ".mp3", ".m4a", ".aac", ".wav" };

	public Main() {
		cozmo = new Cozmo();
		ws = new Webservice();
		sort = new Sort();
		selectedSong = new Song("");
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void deleteTemp() {
		File path = new File("temp");
		deleteTempFiles(path);
	}

	private void deleteTempFiles(File path) {
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				deleteTempFiles(file);
			}
			file.delete();
		}
		path.delete();
	}

	// nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	private void searchFile(File dir) {
		// Dateien werden gesucht, gefunden und die Pfade gespeichert
		try {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					searchFile(files[i]);
				}

				for (String typ : format) {
					if (files[i].getName().endsWith(typ)) {
						songs.add(new Song(files[i].getPath()));
					}
				}
			}
		} catch (Exception e) {

		}
	}

	private Song convert(Song song) {
		String pathOut = "temp\\" + song.getFileName() + ".mp3";
		try {
			Runtime.getRuntime()
					.exec(new String[] { "Main\\ffmpeg", "-vn", "-i", song.getPath(), "-ab", "128k", pathOut });
			song.setPath(pathOut);
		} catch (Exception e) {
			System.out.println(e);
		}
		return song;
	}

	private Song getMetadata(Song song) {
		boolean converted = false;
		boolean metadataAvailable = false;
		Metadata metadata = null;
		while (!converted || !metadataAvailable) {
			try {
				File file = new File(song.getPath());
				song.setPath(file.getAbsolutePath());
				InputStream input = new FileInputStream(file);
				converted = true;

				ContentHandler handler = new DefaultHandler();
				metadata = new Metadata();
				Parser parser = new Mp3Parser();

				ParseContext parseCtx = new ParseContext();
				parser.parse(input, handler, metadata, parseCtx);
				input.close();

				if (metadata.size() > 8) {
					metadataAvailable = true;
				}
			} catch (Exception e) {

			}
		}

		String track = metadata.get("title");
		String artist = metadata.get("xmpDM:artist");
		if (track != null && artist != null) {
			song.setTrack(track);
			song.setArtist(artist);
			song = ws.fillSongArray(song);
		} else {
			if (track != null) {
				song.setTrack(track);
			}
			if (artist != null) {
				song.setArtist(artist);
			}
		}
		return song;
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		// delete old temp files if exists
		if (new File("temp").exists()) {
			deleteTemp();
		}
		// Lege Temporaere Pfade an
		new File("temp").mkdir();

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
			scene.getStylesheets().add("Light.css");
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					deleteTemp();
				}
			});
		} catch (IOException e) {
		}
	}

	@FXML
	void Lightcolor(ActionEvent event) {
		File file = new File("Light.css");
		String Light = file.toString();
		AnchorP.getStylesheets().add(Light);
		AnchorP.getStylesheets().remove("Dark.css");
	}

	@FXML
	void Darkcolor(ActionEvent event) {
		File file = new File("Dark.css");
		String Dark = file.toString();
		AnchorP.getStylesheets().add(Dark);
		AnchorP.getStylesheets().remove("Light.css");
	}

	@FXML
	private void PlayClicked(ActionEvent event) {
		cozmo.play(selectedSong);
	}

	@FXML
	private void StopClicked(ActionEvent event) {
		cozmo.stop();
	}

	/*
	 * @Alex: Bitte eine Combo-Box in die Gui einbauen und dort die Kriterien 'Name
	 * aufsteigend', 'Name absteigend', 'Meist geklickt' hinzuf�gen. 
	 * 
	 * Der Code f�r die jeweiligen Ereignisse ist wie bei ShowClicked implementiert. Hier f�r
	 * Name aufsteigend. F�r Meist geklickt und Name absteigend analog mit 'clicked'
	 * und 'nameDown'. Wenn das soweit funktioniert, kannst du den
	 * ShowClicked-Button entfernen.
	 */
//	@FXML
//	private void ShowClicked(ActionEvent event) {
//
//		int index = 0;
//		for (Song song : sort.sort(songs, "clicked", 0, songs.size() - 1)) {
//			TextLiednamen.getItems().set(index++, song.getFileName());
//		}
//	}
	
	@FXML
	private void absteigend(ActionEvent event) {
		//System.out.println("absteigend");
		int index = 0;
		for (Song song : sort.sort(songs, "nameDown", 0, songs.size() - 1)) {
			TextLiednamen.getItems().set(index++, song.getFileName());
		}
	}
	
	@FXML
	private void aufsteigend(ActionEvent event) {
		//System.out.println("aufsteigend");
		int index = 0;
		for (Song song : sort.sort(songs, "nameUp", 0, songs.size() - 1)) {
			TextLiednamen.getItems().set(index++, song.getFileName());
		}
	}
	
	@FXML
	private void meistens(ActionEvent event) {
		//System.out.println("am meisten geklickt");
		int index = 0;
		for (Song song : sort.sort(songs, "clicked", 0, songs.size() - 1)) {
			TextLiednamen.getItems().set(index++, song.getFileName());
		}
	}

	@FXML
	private void Search(ActionEvent event) {
		String text = Suche.getText();

		if (text != null) {
			// Angemeldeter Benutzer
			File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");
			searchFile(dir);
		} else {
			File dir = new File(text);
			searchFile(dir);
		}

		// show search result
		for (Song song : songs) {
			TextLiednamen.getItems().add(song.getFileName());
		}
	}

	@FXML
	private void getIndex() {
		int index = TextLiednamen.getSelectionModel().getSelectedIndex();
		songs.set(index, getMetadata(convert(songs.get(index))));
		selectedSong = songs.get(index);
		selectedSong.raiseClicked();
		zeigeDatenInDerGUI(selectedSong);
	}

	private void zeigeDatenInDerGUI(Song song) {
		if (song.getTrack() != "" && song.getTrack() != null) {
			Titel.setText(song.getTrack());
		} else {
			Titel.setText("kein Eintrag");
		}
		if (song.getArtist() != "" && song.getArtist() != null) {
			Kuenstler.setText(song.getArtist());
		} else {
			Kuenstler.setText("kein Eintrag");
		}
		if (song.getGenre() != "" && song.getGenre() != null) {
			Genre.setText(song.getGenre());
		} else {
			Genre.setText("kein Eintrag");
		}
		if (song.getPublished() != 0) {
			Jahr.setText("" + song.getPublished());
		} else {
			Jahr.setText("kein Eintrag");
		}
		if (song.getAlbum() != "" && song.getAlbum() != null) {
			Album.setText(song.getAlbum());
		} else {
			Album.setText("kein Eintrag");
		}
		if (song.getInformation() != "" && song.getInformation() != null) {
			Sonstiges.setText(song.getInformation());
		} else {
			Sonstiges.setText("kein Eintrag");
		}
		if (song.getCover() != "" && song.getCover() != null) {
			boolean cover = false;
			try {
				ImageIO.write(ImageIO.read(new URL(song.getCover())), "jpg", new File("temp/cover.jpg"));
				cover = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (cover) {
				//Versuch
//				Image bild = new Image(Bild.getClass().getResource("test2.png").toExternalForm());
//				Bild.setImage(bild);
				
				ImageView img = new ImageView();
				img.setId("test2.jpg");
				
				
				
				
//				Image bild = new Image(getClass().getResource("test.jpg").toExternalForm());
//				Bild = new ImageView(bild);
				
				// hier wuerde das Cover in die Gui geladen werden.
			}
		} else {
			ImageView img = new ImageView();
			img.setId("test2.jpg");
			// hier wuerde das Cover auf 'kein Cover' o.ae. gesetzt werden.
		}
	}
}