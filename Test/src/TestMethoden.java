import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TestMethoden {
	
	static int i = 0;

	// Methode um nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	public static ArrayList<File> searchFile(File dir, String find) {

		String name = "";
		String pfad = "";
		
		File[] files = dir.listFiles();
		ArrayList<File> matches = new ArrayList<File>();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {

				if (files[i].getName().endsWith(find)) {

					name = name + files[i].getName();
					pfad = pfad + files[i].getPath();
					System.out.println("Dateiname: " + name);
					System.out.println("Dateipfad: " + pfad);
					System.out.println();

//					saveName(name);
					savePfad(pfad);

					name = "";
					pfad = "";

					matches.add(files[i]);
				}

				if (files[i].isDirectory()) {
					// fügt der ArrayList die ArrayList mit den Treffern aus dem Unterordner hinzu
					matches.addAll(searchFile(files[i], find));
				}
			}
		}

		return matches;
	}

//	public static void saveName(String text) {
//		TestMain.name.add(text);
//	}

	public static void savePfad(String text) {
		TestMain.pfadSpeichern = verlängere(TestMain.pfadSpeichern);
		TestMain.pfadSpeichern[i] = text;
		i = i + 1;

				
		
//		TestMain.pfad.add(text);
	}
	
	//vorher ArrayList<String> text
	public static void öffneDatei(String[] pfadtext) {
		
		//Kopie
//		String bip = "bip.mp3";
//		Media hit = new Media(new File(bip).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(hit);
//		mediaPlayer.play();
	}
	
	public static String[] verlängere(String[] array) {
		String[] hilfe = new String[array.length + 1];
		
		for(int i = 0; i < array.length; i++) {
			hilfe[i] = array[i];
		}
		hilfe[array.length] = "";
		
		
		return hilfe;
		
	}

}