import java.io.File;

public class DeleteFile {
	public void main(String[] args) {
		// Name flexibel an den Nutzer anpassen
		File f = new File("C://Users//Patrick//Documents//DHBW//Programmieren//DancingCozmo//test3.py");
		f.delete();
	}
}
