import java.io.File;
import java.util.ArrayList;

public class TestMain {
	
	public static ArrayList<String> name = new ArrayList<>(0);
	public static ArrayList<String> pfad = new ArrayList<>(0);

	public static void main(String[] args) {
		
		//Nach einem File suchen in einem Verzeichnis 
		//Benutzername muss jeweils geändert werden
		File dir = new File("C:\\Users\\Alexander Feist\\Desktop");
//		String find = "Akku!.txt";
		String find = ".txt";		//Endung anpassen
		TestMethoden.searchFile(dir, find);
		
	}

}
