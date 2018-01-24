import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

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
					// System.out.println("Dateiname: " + name);
					// System.out.println("Dateipfad: " + pfad);
					// System.out.println();

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

	public static void savePfad(String text) {
		TestMain.pfadSpeicher = verlaengere(TestMain.pfadSpeicher);
		TestMain.pfadSpeicher[i] = text;
		i = i + 1;
	}

	public static String[] verlaengere(String[] array) {
		String[] hilfe = new String[array.length + 1];

		for (int i = 0; i < array.length; i++) {
			hilfe[i] = array[i];
		}
		hilfe[array.length] = "";

		return hilfe;

	}

	// Ist noch in arbeit
	public static void soundDateiAbspielen(String[] pfadtext) {

		// String bip = pfadtext[1];
		// Media hit = new Media(new File(pfadtext[1]).toURI().toString());
		// MediaPlayer mediaPlayer = new MediaPlayer(hit);
		// mediaPlayer.play();

		// String path = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is
		// geboren\\03 Het Gelders Volkslied.wma";
		// Media media = new Media(new File(path).toURI().toString());
		// //.toURI().toString()
		// MediaPlayer mediaPlayer = new MediaPlayer(media);
		// mediaPlayer.setAutoPlay(true);
		// MediaView mediaView = new MediaView(mediaPlayer);
		
		

		//Pfad meines MediaPlayers
		//Player wird geöffnet und das Lied wiedergegeben (egal welche Endung)
		//Unterschiedliche Player, Pfade, ....
		try {
			Process a = Runtime.getRuntime().exec(new String[]{"C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe", pfadtext[1]});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public static void testausgabe(String pfadtext[]) {
		for (int i = 0; i < pfadtext.length - 1; i++) {
			System.out.println("Der Pfad lautet: " + pfadtext[i]);
		}
	}

}