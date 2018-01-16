import java.io.File;
import java.util.ArrayList;

public class TestMethoden {

	//Methode um nach einem bestimmten File in einem bestimmten Verzeichnis suchen
	public static ArrayList<File> searchFile(File dir, String find) {

		File[] files = dir.listFiles();
		ArrayList<File> matches = new ArrayList<File>();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getName().equalsIgnoreCase(find)) { // überprüft ob der Dateiname mit dem Suchstring
					// übereinstimmt. Groß-/Kleinschreibung wird
					// ignoriert.
					matches.add(files[i]);
					System.out.println("Treffer");
				}
				if (files[i].isDirectory()) {
					matches.addAll(searchFile(files[i], find)); // fügt der ArrayList die ArrayList mit den
					// Treffern aus dem Unterordner hinzu
				}
			}
		}
		return matches;
	}

}
