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
    private void createDance(String genre, double laenge){
        if(dances.contains(genre)){
            switch(genre){
                case "Blues": new Dances.Blues().createDance((int) laenge/18);
            }

        }
        else{
            //Standardtanz auswählen; unabhaengig vom Genre
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
