import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestMethoden {

	//Uebergeordnete Zaehlvariable
	static int i = 0;

	// Methode um nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	public static ArrayList<File> searchFile(File dir, String find) {

		String name = "";
		String pfad = "";

		//Dateien werden gesucht, gefunden und die Pfade gespeichert
		File[] files = dir.listFiles();
		ArrayList<File> matches = new ArrayList<File>();
		if (files != null) {
			for (int j = 0; j < files.length; j++) {

				if (files[j].getName().endsWith(find)) {

					name = name + files[j].getName();
					pfad = pfad + files[j].getPath();
					// System.out.println("Dateiname: " + name);
					// System.out.println("Dateipfad: " + pfad);
					// System.out.println();

					savePfad(pfad);

					name = "";
					pfad = "";

					matches.add(files[j]);
				}

				if (files[j].isDirectory()) {
					// fuegt der ArrayList die ArrayList mit den Treffern aus dem Unterordner hinzu
					matches.addAll(searchFile(files[j], find));
				}
			}
		}

		return matches;
	}

	//Pfad speichern
	public static void savePfad(String text) {
		TestMain.pfadSpeicher = verlaengere(TestMain.pfadSpeicher);
		TestMain.pfadSpeicher[i] = text;
		i = i + 1;
	}

	//Das Array zum Speichern der Pfade um 1 Stelle verlaengern, Array ist flexibel
	public static String[] verlaengere(String[] array) {
		String[] hilfe = new String[array.length + 1];

		for (int i = 0; i < array.length; i++) {
			hilfe[i] = array[i];
		}
		hilfe[array.length] = "";

		return hilfe;

	}

	//Musik wird abgespielt
	public static void soundDateiAbspielen(String[] pfadtext) {

		// Pfad fuer MediaPlayer wird aktuell vorrausgesetzt ohne dass Pfadaenderungen
		// vorgenommen wurden

		// Player wird geoeffnet und das Lied wiedergegeben (egal welche Endung)
		// Unterschiedliche Player, Pfade, ....
		try {
			Process a = Runtime.getRuntime()
					.exec(new String[] { "C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe", pfadtext[1] });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Die einzelnen Pfade werden ausgegeben (Ueberpruefung ob alle Dateien gefunden wurden)
	public static void testausgabe(String pfadtext[]) {
		for (int i = 0; i < pfadtext.length - 1; i++) {
			System.out.println("Der Pfad lautet: " + pfadtext[i]);
		}
	}
}