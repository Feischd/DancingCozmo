import java.io.File;
import java.util.ArrayList;

//Hier test
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

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
//					System.out.println("Dateiname: " + name);
//					System.out.println("Dateipfad: " + pfad);
//					System.out.println();

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

	public static void savePfad(String text) {
		TestMain.pfadSpeichern = verlaengere(TestMain.pfadSpeichern);
		TestMain.pfadSpeichern[i] = text;
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

	//Ist noch in arbeit
	public static void soundDateiAbspielen(String[] pfadtext) {

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pfadtext[1]));
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			audioInputStream.read(audio, 0, size);

			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);

			clip.start();
		} catch (Exception e) {
			// Falls Musik nicht da ist
			e.printStackTrace();
			System.out.println("[Soundfile nicht vorhanden]");
			System.exit(0);

		}

		// Ging nicht so

		// String bip = pfadtext[1];
		// Media hit = new Media(new File(bip).toURI().toString());
		// MediaPlayer mediaPlayer = new MediaPlayer(hit);
		// mediaPlayer.play();
	}
	
	public static void testausgabe(String pfadtext[]) {
		for (int i = 0; i < pfadtext.length - 1; i++) {
			System.out.println("Der Pfad lautet: " + pfadtext[i]);
		}
	}

}