import java.io.File;
import java.util.ArrayList;

public class TestMethoden {

	// Methode um nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	public static ArrayList<File> searchFile(File dir, String find) {

		int zaehler = 0;
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

					saveName(name);
					savePfad(pfad);

					name = "";
					pfad = "";

					matches.add(files[i]);
					zaehler = zaehler + 1;
				}

				if (files[i].isDirectory()) {
					// fügt der ArrayList die ArrayList mit den Treffern aus dem Unterordner hinzu
					matches.addAll(searchFile(files[i], find));
				}
			}
		}

		return matches;
	}

	// Noch ausprogrammieren
	public static void saveName(String text) {
		TestMain.name.add(text);
	}

	public static void savePfad(String text) {
		TestMain.pfad.add(text);
	}

}