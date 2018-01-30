import java.io.File;

public class TestMain {

	// Hilfsarray um die einzelnen Pfade der Musikdateien zu speichern
	public static String[] pfadSpeicher = new String[1];

	public static void main(String[] args) {

		// Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");

		// 4 Unterschiedliche Dateitypen sollen gefunden werden
		// mp3, wma, m4a, aac
		String findwma = ".wma";
		String findmp3 = ".mp3";
		String findm4a = ".m4a";
		String findaac = ".aac";

		// Ab hier Methoden aufrufen
		TestMethoden.searchFile(dir, findwma);
		TestMethoden.searchFile(dir, findmp3);
		TestMethoden.searchFile(dir, findm4a);
		TestMethoden.searchFile(dir, findaac);

		// Testmethoden um zu pruefen, ob 1. Alle Dateien gefunden worden sind 2. ein
		// beliebiges Stueck abgespielt werden kann
		TestMethoden.testausgabe(pfadSpeicher);
		TestMethoden.soundDateiAbspielen(pfadSpeicher);

	}

}
