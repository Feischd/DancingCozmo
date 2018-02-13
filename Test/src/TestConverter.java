import java.io.IOException;

public class TestConverter {
	
	public static void main(String[] args) throws IOException {

		String name = "test";
		String[] basisPfad = new String[1];
		
		//Hier die Pfade einfuegen
//		String basisPfad = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied"; // ohne
		// .wma
		
		basisPfad[0] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\03 Het Gelders Volkslied";
//		basisPfad[1] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\04 Winteroorlog";
//		basisPfad[2] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\05 En wij stappen stevig voort";
//		basisPfad[3] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\06 Furor Teutonicus";
//		basisPfad[4] = "C:\\Users\\Alexander Feist\\Music\\Heidevolk\\De Strijdlust is geboren\\07 Het bier zal weer vloeien";
		
		for(int i = 0; i < basisPfad.length; i++) {
			//Geht mit allen unterschiedlichen Formaten
			String inputPfad = basisPfad[i] + ".wma";
//			String outputPfad = basisPfad[i] + ".mp3";
			
			String outputPfad = "temp\\" + name + ".mp3";
			
			ProcessBuilder builder = new ProcessBuilder("ffmpeg", "-vn", "-i", inputPfad, "-ab", "128k", outputPfad);
			Process process = builder.start();
		}
	}
}
