import Dances.Dance;

import java.util.ArrayList;
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
                case "blues": new Dances.Blues().createDance(laenge/19);
                    break;
                case "country": new Dances.Country().createDance(laenge/22);
                    break;
                case "darkmusik": new Dances.DarkMusik().createDance(laenge/15);
                    break;
                case "disco": new Dances.Disco().createDance(laenge/18);
                    break;
                case "electro": new Dances.Electro().createDance(laenge/16);
                    break;
                case "folk": new Dances.Folk().createDance(laenge/19);
                    break;
                case "funk": new Dances.Funk().createDance(laenge/21);
                    break;
                case "hip-hop": new Dances.HipHop().createDance(laenge/10);
                    break;
                case "jazz": new Dances.Jazz().createDance(laenge/14);
                    break;
                case "klassik": new Dances.Klassik().createDance(laenge/22);
                    break;
                case "lateinamerikanisch": new Dances.Lateinamerikanisch().createDance(laenge/21);
                    break;
                case "metal": new Dances.Metal().createDance(laenge/24);
                    break;
                case "pop": new Dances.Pop().createDance(laenge/16);
                    break;
                case "punk": new Dances.Punk().createDance(laenge/31);
                    break;
                case "r&b": new Dances.RandB().createDance(laenge/21);
                    break;
                case "rap": new Dances.Rap().createDance(laenge/27);
                    break;
                case "reggae": new Dances.Reggae().createDance(laenge/4);
                    break;
                case "rock": new Dances.Rock().createDance(laenge/9);
                    break;
                case "rocknroll": new Dances.RocknRoll().createDance(laenge/18);
                    break;
                case "schlager": new Dances.Schlager().createDance(laenge/19);
                    break;
                case "ska": new Dances.Ska().createDance(laenge/21);
                    break;
                case "traditionellemusik": new Dances.TraditionelleMusik().createDance(laenge/23);
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
