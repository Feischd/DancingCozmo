import java.io.File;
import java.util.ArrayList;

public class TestMain {
	
	public static ArrayList<String> name = new ArrayList<>(0);
	public static ArrayList<String> pfad = new ArrayList<>(0);

	public static void main(String[] args) {
		
		//Ab hier Variablen
		
		//Benutzername muss jeweils geändert werden
//		File dir = new File("C:\\Users\\Alexander Feist\\Desktop");
		
		//Test ob Dateien gefunden werden
//		String find = ".txt";
		
		//Geht
		File dir = new File("C:\\Users\\Alexander Feist\\Music");
		
		//Test
//		File dir = new File("C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren");
		
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
		
		TestMethoden.öffneDatei(pfad);
		
	}

}
