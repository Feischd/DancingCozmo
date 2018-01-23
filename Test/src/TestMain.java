import java.io.File;
import java.util.ArrayList;

public class TestMain {
	
	public static String[] pfadSpeichern = new String[1];

	public static void main(String[] args) {
		
		//Funktioniert nur für mich
//		File dir = new File("C:\\Users\\Alexander Feist\\Music");
		
		//Angemeldeter Benutzer
		File dir = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Music");
		
		//mp3, wma, m4a, aac
		String findwma = ".wma";
		String findmp3 = ".mp3";
		String findm4a = ".m4a";
		String findaac = ".aac";
		
		//Ab hier Methoden aufrufen
		TestMethoden.searchFile(dir, findwma);
		TestMethoden.searchFile(dir, findmp3);
		TestMethoden.searchFile(dir, findm4a);
		TestMethoden.searchFile(dir, findaac);
		
		TestMethoden.oeffneDatei(pfadSpeichern);
		

		
	}

}
