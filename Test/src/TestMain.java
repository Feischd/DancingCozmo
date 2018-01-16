import java.io.File;

//		String[] namen = new String[0];

public class TestMain {

	public static void main(String[] args) {
		
		//Nach einem File suchen in einem Verzeichnis //Benutzername muss jeweils geändert werden
		File dir = new File("C:\\Users\\Alexander Feist\\Desktop");
		//System.out.println("Test");
//		String find = "Akku!.txt";
		String find = ".txt";
		TestMethoden.searchFile(dir, find);
	}

}
