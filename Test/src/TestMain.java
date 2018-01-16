import java.io.File;

public class TestMain {

	public static void main(String[] args) {
		
		//Nach einem File suchen in einem Verzeichnis 
		//Benutzername muss jeweils geändert werden
		File dir = new File("C:\\Users\\Alexander Feist\\Desktop");
//		String find = "Akku!.txt";
		String find = ".txt";
		TestMethoden.searchFile(dir, find);
	}

}
