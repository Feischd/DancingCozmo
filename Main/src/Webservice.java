import java.util.ArrayList;
import java.io.*;
import java.io.InputStream;

import java.net.URL;
import java.util.Scanner;


public class Webservice {
    private ArrayList<ArrayList> genres;

    public Webservice(){
        genres = new ArrayList<>();
        fillGenreArray();
    }

    public ArrayList fillSongArray(ArrayList<Song> songs){
        for(int i=0; i<songs.size(); i++){
            String songData = getSongData(songs.get(i));
            // get length of song
            songs.get(i).setDuration(getDuration(songData));
            // get date song was published
            songs.get(i).setPublished(getPublished(songData));
            // get name of album
            songs.get(i).setAlbum(getAlbum(songData));
            // get source of cover
            songs.get(i).setCover(getCover(songData));
            // get summary of background information
            songs.get(i).setInformation(getInformation(songData).replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&#10;&#10;", "\n"));
            // get genre
            songs.get(i).setGenre(getGenre(songData));
        }
        return songs;
    }


    // get XML (as String) with song information
    private String getSongData(Song song){
        String songData = "";
        InputStream is = null;
        try
        {
            URL url = new URL( " http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=e0499e4c41404deb64230e4881e2eb27&artist=" + song.getArtist() + "&track=" + song.getTrack());
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





    // Methods for song information
    private int getDuration(String songData){
        return Integer.parseInt(songData.substring(songData.indexOf("<duration>")+10, songData.indexOf("</duration>")));
    }


    private int getPublished(String songData){
        // get general information of release (month day year)
        String release = songData.substring(songData.indexOf("<published>")+11);
        release = release.substring(0, release.indexOf(","));
        // get year of release
        String[] releaseSplit = release.split(" ");

        return Integer.parseInt(releaseSplit[2]);
    }


    private String getAlbum(String songData){
        // get general album information
        String album = songData.substring(songData.indexOf("<album"));
        return album.substring(album.indexOf("<title>")+7, album.indexOf("</title>"));
    }


    private String getCover(String songData){
        // select image size large
        String cover = songData.substring(songData.indexOf("<image size=\"large\">"));
        return cover.substring(20, cover.indexOf("</image>"));
    }


    private String getInformation(String songData){
        return songData.substring(songData.indexOf("<summary>")+9, songData.indexOf("</summary>"));
    }


    private String getGenre(String songData){
        // get all genres the song is part of and put them into genresTemp-ArrayList
        String sub = songData;
        ArrayList<String> genresTemp = new ArrayList<>();
        while(sub.indexOf("<tag>") > -1){
            // there are more than one <tag>, so the 'tag-String' needs to be cut
            sub = sub.substring(sub.indexOf("<tag>")+5);
            // adding <tag> to array
            genresTemp.add("0 " + sub.substring(sub.indexOf("<name>")+6, sub.indexOf("</name>")).replaceAll(" ", "").replaceAll("[A-Z]", "[a-z]"));
        }

        // Compares given Genres (tempGenre) with Genre-List; by match: Name of Genre-List will be taken and 1 instead of 0 above
        for(int i=0; i<genresTemp.size(); i++){
            for(int z=0; z<genres.size(); z++){
                for(int x=0; x<genres.get(z).size(); x++){
                    String[] temp = genresTemp.get(i).split(" ");
                    if(temp[1].equals(genres.get(z).get(x))){
                        genresTemp.set(i, Integer.parseInt(temp[0])+1 + " " + genres.get(z).get(0));
                        break;
                    }
                }
            }
        }

        // checks on more than one matches by the same genre and sums the indexes above the genre
        for(int x=0; x<genresTemp.size()-1; x++){
            String[] temp1 = genresTemp.get(x).split(" ");
            for(int y=x+1; y<genresTemp.size(); y++){
                String[] temp2 = genresTemp.get(y).split(" ");
                if(temp1[1].equals(temp2[1])){
                    genresTemp.set(x, Integer.parseInt(temp1[0])+Integer.parseInt(temp2[0]) + " " + temp1[1]);
                }
            }
        }

        // Checks on the indexes above the genres. The genre with the highest index will be taken. If there are genres with the same
        // index, the first of them will be chosen
        int i1 = 0;
        String[] temp1 = genresTemp.get(i1).split(" ");
        for(int i2=1; i2<genresTemp.size(); i2++){
            String[] temp2 = genresTemp.get(i2).split(" ");
            if(Integer.parseInt(temp2[0])>Integer.parseInt(temp1[0])){
                i1 = i2;
                temp1 = genresTemp.get(i1).split(" ");
            }
        }

        String[] genre = genresTemp.get(i1).split(" ");
        return genre[1];
    }










    // Methods to fill Genre-Array

    // get Genres into the Genre-ArrayList
    private void fillGenreArray(){
        genres.add(getBlues());
        genres.add(getClassic());
        genres.add(getCountry());
        genres.add(getDark());
        genres.add(getDisco());
        genres.add(getElectro());
        genres.add(getFolk());
        genres.add(getFunk());
        genres.add(getHipHop());
        genres.add(getJazz());
        genres.add(getLatin());
        genres.add(getMetal());
        genres.add(getPop());
        genres.add(getPunk());
        genres.add(getRandb());
        genres.add(getRap());
        genres.add(getReggae());
        genres.add(getRock());
        genres.add(getRocknRoll());
        genres.add(getSchlager());
        genres.add(getSka());
        genres.add(getTraditional());
    }

    private ArrayList getBlues(){
        ArrayList<String> blues = new ArrayList<>();
        blues.add("Blues");
        blues.add("blues");
        blues.add("soul");
        return blues;
    }

    private ArrayList getClassic(){
        ArrayList<String> classic = new ArrayList<>();
        classic.add("Classic");
        classic.add("classic");
        classic.add("classical");
        classic.add("piano");
        classic.add("opera");
        classic.add("soundtrack");
        return classic;
    }

    private ArrayList getCountry(){
        ArrayList<String> country = new ArrayList<>();
        country.add("Country");
        country.add("country");
        return country;
    }

    private ArrayList getDark(){
        ArrayList<String> dark = new ArrayList<>();
        dark.add("Dark");
        dark.add("dark");
        dark.add("darkmusic");
        dark.add("gothic");
        dark.add("deathrock");
        dark.add("goth");
        return dark;
    }

    private ArrayList getDisco(){
        ArrayList<String> disco = new ArrayList<>();
        disco.add("Disco");
        disco.add("disco");
        disco.add("80s");
        disco.add("90s");
        disco.add("party");
        return disco;
    }

    private ArrayList getElectro(){
        ArrayList<String> electro = new ArrayList<>();
        electro.add("Electro");
        electro.add("electro");
        electro.add("electronic");
        electro.add("electronica");
        electro.add("experimental");
        electro.add("ambient");
        electro.add("industrid");
        electro.add("chillout");
        electro.add("dance");
        return electro;
    }

    private ArrayList getFolk(){
        ArrayList<String> folk = new ArrayList<>();
        folk.add("Folk");
        folk.add("folk");
        folk.add("psychedelic");
        return folk;
    }

    private ArrayList getFunk(){
        ArrayList<String> funk = new ArrayList<>();
        funk.add("Funk");
        funk.add("funk");
        funk.add("trance");
        return funk;
    }

    private ArrayList getHipHop(){
        ArrayList<String> hiphop = new ArrayList<>();
        hiphop.add("Hip-Hop");
        hiphop.add("hiphop");
        hiphop.add("hip-hop");
        return hiphop;
    }

    private ArrayList getJazz(){
        ArrayList<String> jazz = new ArrayList<>();
        jazz.add("Jazz");
        jazz.add("jazz");
        jazz.add("instrumental");
        jazz.add("acoustic");
        return jazz;
    }

    private ArrayList getLatin(){
        ArrayList<String> latin = new ArrayList<>();
        latin.add("Latin");
        latin.add("latin");
        latin.add("cuba");
        latin.add("salsa");
        latin.add("cuban");
        latin.add("tango");
        latin.add("rumba");
        latin.add("rhumba");
        return latin;
    }

    private ArrayList getMetal(){
        ArrayList<String> metal = new ArrayList<>();
        metal.add("Metal");
        metal.add("metal");
        metal.add("blackmetal");
        metal.add("deathmetal");
        metal.add("thrashmetal");
        metal.add("progressivemetal");
        metal.add("heavymetal");
        metal.add("metalcore");
        return metal;
    }

    private ArrayList getPop(){
        ArrayList<String> pop = new ArrayList<>();
        pop.add("Pop");
        pop.add("pop");
        return pop;
    }

    private ArrayList getPunk(){
        ArrayList<String> punk = new ArrayList<>();
        punk.add("Punk");
        punk.add("punk");
        punk.add("punkrock");
        punk.add("hardcore");
        punk.add("newwave");
        return punk;
    }

    private ArrayList getRandb(){
        ArrayList<String> randb = new ArrayList<String>();
        randb.add("R&B");
        randb.add("randb");
        randb.add("r&b");
        randb.add("rhythmandblues");
        randb.add("oldies");
        return randb;
    }

    private ArrayList getRap(){
        ArrayList<String> rap = new ArrayList<>();
        rap.add("Rap");
        rap.add("rap");
        rap.add("gangstarap");
        rap.add("eminem");
        return rap;
    }

    private ArrayList getReggae(){
        ArrayList<String> reggae = new ArrayList<>();
        reggae.add("Reggae");
        reggae.add("reggae");
        reggae.add("ragtime");
        return reggae;
    }

    private ArrayList getRock(){
        ArrayList<String> rock = new ArrayList<>();
        rock.add("Rock");
        rock.add("rock");
        rock.add("alternative");
        rock.add("classicrock");
        rock.add("indie");
        rock.add("indierock");
        rock.add("hardrock");
        rock.add("progressiverock");
        rock.add("post-rock");
        return rock;
    }

    private ArrayList getRocknRoll(){
        ArrayList<String> rocknroll = new ArrayList<>();
        rocknroll.add("Rock'n'Roll");
        rocknroll.add("rocknroll");
        rocknroll.add("rock'n'roll");
        rocknroll.add("rockandroll");
        return rocknroll;
    }

    private ArrayList getSchlager(){
        ArrayList<String> schlager = new ArrayList<>();
        schlager.add("Schlager");
        schlager.add("schlager");
        schlager.add("germanschlager");
        schlager.add("deutscherschlager");
        return schlager;
    }

    private ArrayList getSka(){
        ArrayList<String> ska = new ArrayList<>();
        ska.add("Ska");
        ska.add("ska");
        return ska;
    }

    private ArrayList getTraditional(){
        ArrayList<String> traditional = new ArrayList<>();
        traditional.add("Traditional");
        traditional.add("traditional");
        traditional.add("guitar");
        traditional.add("traditonalmusic");
        traditional.add("flamenco");
        return traditional;
    }
}