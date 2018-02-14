import java.util.ArrayList;

public class TestWebservice {
    public static void main(String[] args){
        Webservice ws = new Webservice();

        Song song = new Song("Believe", "cher", "kein Pfad");
        Song song2 = new Song("boulevard of broken dreams", "green day", "kein Pfad");
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(song);
        songs.add(song2);
        songs = ws.fillSongArray(songs);

        for(Song s: songs){
            System.out.println(s.getAlbum());
            System.out.println(s.getCover());
            System.out.println(s.getPublished());
            System.out.println(s.getGenre());
            System.out.println(s.getArtist());
            System.out.println(s.getDuration());
            System.out.println(s.getPath());
            System.out.println(s.getInformation());
            System.out.println(s.getTrack());
        }


        //URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=e0499e4c41404deb64230e4881e2eb27&artist=green%20day&track=boulevard%20of%20broken%20dreams");
    }
}
