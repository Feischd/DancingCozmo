import java.io.File;
import java.util.ArrayList;

public class TestMain {
	
	public static ArrayList<String> name = new ArrayList<>(0);
	public static ArrayList<String> pfad = new ArrayList<>(0);

	public static void main(String[] args) {
		
		//Ab hier Variablen
		
		//Benutzername muss jeweils geändert werden
		File dir = new File("C:\\Users\\Alexander Feist\\Desktop");
		String find = ".txt";		//Endung anpassen
		
		//Ab hier Methoden aufrufen
		TestMethoden.searchFile(dir, find);
		TestMethoden.öffneDatei(pfad);
		
	}

}
