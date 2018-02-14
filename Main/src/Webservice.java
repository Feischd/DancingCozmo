import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.*;
import java.io.InputStream;

import java.net.URL;
import java.util.Scanner;

import java.net.URLConnection;


public class Webservice {
    private ArrayList<Song> songs;

    public Webservice(){
        songs = new ArrayList<>();
        Song song = new Song("believe", "cher", "kein Pfad");
        songs.add(0, song);
    }

    public void fillSongArray(){
        for(int i=0; i<songs.size(); i++){
            String songData = getSongData(songs.get(i));
            // get length of song
           // songs.get(i).setLaenge(getDuration(songData));
            // get date song was published
          //  songs.get(i).setErscheinungsjahr(getPublished(songData));
            System.out.println(getPublished(songData));
        // genre immer bei den tags das erste auswählen

        }
    }


    // get XML with song information
    private String getSongData(Song song){
        String songData = "";
        InputStream is = null;
        try
        {
            URL url = new URL( " http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=e0499e4c41404deb64230e4881e2eb27&artist=" + song.getKuenstler() + "&track=" + song.getTitel());
            is = url.openStream();
            songData = new Scanner(is).useDelimiter("Z").next();
        }

        catch ( Exception e ) {
            e.printStackTrace();
        }

        finally {
            if ( is != null )
                try { is.close(); } catch ( IOException e ) { }
        }

       return songData;
    }


    private int getDuration(String songData){
        return Integer.parseInt(songData.substring(songData.indexOf("<duration>")+10, songData.indexOf("</duration>")));
    }

    private int getPublished(String songData){
        // get general information of release (month day year)
        String release = songData.substring(songData.indexOf("<published>")+11, songData.indexOf(","));
        // get year of release
        String[] releaseSplit = release.split(" ");
        return Integer.parseInt(releaseSplit[2]);
    }
}



