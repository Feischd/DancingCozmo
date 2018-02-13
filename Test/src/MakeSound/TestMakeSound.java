package MakeSound;

public class TestMakeSound {

	// The MakeSound class only works by using the wav format, so there should be a
	// converter to get the selected file into wav-format.
	// For implementation this possibly helps:
	// https://docs.oracle.com/javase/tutorial/sound/converters.html
	public static void main(String[] args) {
		MakeSound ms = new MakeSound();
		//ms.playSound("01_Titelnummer_1.wav");
		
		//MP3 Pfad angeben
		ms.playSound("C:\\Users\\Ramona\\Music\\Mittalter-Rock\\Saltatio Mortis\\Wachstum über alles\\B00DJU3ETW_(disc_1)_01_-_Wachstum_über_alles.mp3");
	}

}
