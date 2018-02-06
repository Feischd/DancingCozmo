import java.io.IOException;

public class TestConverter {
	
	public static void main(String[] args) throws IOException {

		//Hier die Pfade einfuegen
		String basisPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied"; // ohne
		// .wma
		
		//Geht mit allen unterschiedlichen Formaten
		String inputPfad = basisPfad + ".wma";
		String outputPfad = basisPfad + ".wav";
		ProcessBuilder builder = new ProcessBuilder("ffmpeg", "-vn", "-i", inputPfad, "-ab", "128k", outputPfad);
		Process process = builder.start();

	}

}
