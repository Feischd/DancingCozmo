import java.io.File;

public class TestMain {

	public static String[] pfadSpeicher = new String[1];

	public static void main(String[] args) {

		// Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");

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

		TestMethoden.testausgabe(pfadSpeicher);

		TestMethoden.soundDateiAbspielen(pfadSpeicher);

	}

}
