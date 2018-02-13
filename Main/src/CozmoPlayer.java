import Dances.Dance;

import java.util.ArrayList;
import java.io.IOException;
import java.lang.*;
import java.io.File;
import java.awt.Desktop;


public class CozmoPlayer {
    private ArrayList<String> dances;

    public CozmoPlayer(){
        dances = new ArrayList<>();
        dances.add("Blues");
    }

    public void play(Song song){
        // creation of dance
        createDance(song.getGenre(), song.getLaenge());
        // exection of python file
        execDance();
    }

    // creates dance depending on genre and length of song
    private void createDance(String genre, int laenge){
        if(dances.contains(genre)){
            switch(genre){
                case "Blues": new Dances.Blues().createDance(laenge/19);
                case "Country": new Dances.Country().createDance(laenge/22);
                case "DarkMusik": new Dances.DarkMusik().createDance(laenge/15);
                case "Disco": new Dances.Disco().createDance(laenge/18);
                case "Electro": new Dances.Electro().createDance(laenge/16);
                case "Folk": new Dances.Folk().createDance(laenge/19);
                case "Funk": new Dances.Funk().createDance(laenge/21);
                case "Hip-Hop": new Dances.HipHop().createDance(laenge/10);
                case "Jazz": new Dances.Jazz().createDance(laenge/14);
                case "Klassik": new Dances.Klassik().createDance(laenge/22);
                case "Lateinamerikanisch": new Dances.Lateinamerikanisch().createDance(laenge/21);
                case "Metal": new Dances.Metal().createDance(laenge/24);
                case "Pop": new Dances.Pop().createDance(laenge/16);
                case "Punk": new Dances.Punk().createDance(laenge/31);
                case "R&B": new Dances.RandB().createDance(laenge/21);
                case "Rap": new Dances.Rap().createDance(laenge/27);
                case "Reggae": new Dances.Reggae().createDance(laenge/4);
                case "Rock": new Dances.Rock().createDance(laenge/9);
                case "Rock'n'Roll": new Dances.RocknRoll().createDance(laenge/18);
                case "Schlager": new Dances.Schlager().createDance(laenge/19);
                case "Ska": new Dances.Ska().createDance(laenge/21);
                case "Traditionelle Musik": new Dances.TraditionelleMusik().createDance(laenge/23);
            }

        }
        else{
            new Dances.Universal().createDance(laenge/23);
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
