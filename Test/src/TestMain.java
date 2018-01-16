import java.io.File;

public class TestMain {

	public static void main(String[] args) {
		
		//Nach einem File suchen in einem Verzeichnis
		File a = new File("C:\\Users\\Alexander Feist\\Desktop");
		//System.out.println("Test");
		String b = "Akku!.txt";
		TestMethoden.searchFile(a, b);
	}

}
