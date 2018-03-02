import java.io.IOException;

public class Cozmo {

    // creates dance for cozmo and let him dance, also playing music
    public void play(Song song){
        createDance(song.getGenre(), song.getDuration());
        try {
            Runtime.getRuntime().exec("cmd /C start temp\\cozmoDance.py");
            Runtime.getRuntime().exec(new String[] {"C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe", song.getPath()});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // stops cozmo and music
    public void stop(){
        try{
            Runtime.getRuntime().exec("taskkill /f /im wmplayer.exe");
            Runtime.getRuntime().exec("taskkill /f /im py.exe") ;
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // creates dance for cozmo depending on genre and duration of song
    private void createDance(String genre, int laenge){
        laenge = laenge / 1000;
        switch(genre){
            case "Blues": new Dances.Blues().createDance(laenge/19);
                break;
            case "Country": new Dances.Country().createDance(laenge/22);
                break;
            case "Dark": new Dances.Dark().createDance(laenge/15);
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
            case "Classic": new Dances.Classic().createDance(laenge/22);
                break;
            case "Latin": new Dances.Latin().createDance(laenge/21);
                break;
            case "Metal": new Dances.Metal().createDance(laenge/24);
                break;
            case "Pop": new Dances.Pop().createDance(laenge/16);
                break;
            case "Punk": new Dances.Punk().createDance(laenge/23);
                break;
            case "R&B": new Dances.RandB().createDance(laenge/21);
                break;
            case "Rap": new Dances.Rap().createDance(laenge/20);
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
            case "Traditional": new Dances.Traditional().createDance(laenge/23);
                break;
            default: new Dances.Universal().createDance(laenge/23);
                break;
        }
    }
}
