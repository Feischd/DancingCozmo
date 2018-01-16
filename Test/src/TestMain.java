import java.io.File;

public class TestMain {

	public static void main(String[] args) {
		
		//Nach einem File suchen in einem Verzeichnis
		File dir = new File("C:\\Users\\Alexander Feist\\Desktop");
		//System.out.println("Test");
//		String find = "Akku!.txt";
		String find = ".txt";
		TestMethoden.searchFile(dir, find);
	}

}
