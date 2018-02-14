import Dances.Classic;
import Dances.Dark;
import Dances.Latin;
import Dances.Traditional;

import java.io.IOException;
import java.lang.*;
import java.io.File;
import java.awt.Desktop;


public class CozmoPlayer {
    public void play(Song song){
        // creation of dance
        createDance(song.getGenre(), song.getLaenge());
        // exection of python file
        execDance();
    }

    // creates dance depending on genre and length of song
    private void createDance(String genre, int laenge){
            switch(genre){
                case "Blues": new Dances.Blues().createDance(laenge/19);
                    break;
                case "Country": new Dances.Country().createDance(laenge/22);
                    break;
                case "Dark": new Dark().createDance(laenge/15);
                    break;
                case "Disco": new Dances.Disco().createDance(laenge/18);
                    break;
                case "Electro": new Dances.Electro().createDance(laenge/16);
                    break;
                case "Folk": new Dances.Folk().createDance(laenge/19);
                    break;
                case "Funk": new Dances.Funk().createDance(laenge/21);
                    break;
                case "Hip-Hop": new Dances.HipHop().createDance(laenge/10);
                    break;
                case "Jazz": new Dances.Jazz().createDance(laenge/14);
                    break;
                case "Classic": new Classic().createDance(laenge/22);
                    break;
                case "Latin": new Latin().createDance(laenge/21);
                    break;
                case "Metal": new Dances.Metal().createDance(laenge/24);
                    break;
                case "Pop": new Dances.Pop().createDance(laenge/16);
                    break;
                case "Punk": new Dances.Punk().createDance(laenge/31);
                    break;
                case "R&B": new Dances.RandB().createDance(laenge/21);
                    break;
                case "Rap": new Dances.Rap().createDance(laenge/27);
                    break;
                case "Reggae": new Dances.Reggae().createDance(laenge/4);
                    break;
                case "Rock": new Dances.Rock().createDance(laenge/9);
                    break;
                case "Rock'n'Roll": new Dances.RocknRoll().createDance(laenge/18);
                    break;
                case "Schlager": new Dances.Schlager().createDance(laenge/19);
                    break;
                case "Ska": new Dances.Ska().createDance(laenge/21);
                    break;
                case "Traditional": new Traditional().createDance(laenge/23);
                    break;
                default: new Dances.Universal().createDance(laenge/23);
                    break;
            }
    }

    private void execDance(){
        try {
            Desktop.getDesktop().open(new File("cozmoDance.py"));
        } catch (IOException e1) {

            e1.printStackTrace();
        }
    }
}
