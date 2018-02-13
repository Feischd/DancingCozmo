public class Song {
    private String titel;
    private String kuenstler;
    private String pfad;
    private String genre;
    private int laenge;
    private int erscheinungsjahr;
    private String album;
    private String bild;
    private String sonstiges;


    public Song(String titel, String kuenstler, String pfad){
        this.titel = titel;
        this.kuenstler = kuenstler;
        this.pfad = pfad;
        genre = "";
        laenge = 0;
        erscheinungsjahr = 0;
        album = "";
        bild = "";
        sonstiges = "";
    }

    // setter
    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setLaenge(int laenge){
        this.laenge = laenge;
    }

    public void setErscheinungsjahr(int erscheinungsjahr){
        this.erscheinungsjahr = erscheinungsjahr;
    }

    public void setAlbum(String album){
        this.album = album;
    }

    public void setBild(String bild){
        this.bild = bild;
    }

    public void setSonstiges(String sonstiges){
        this.sonstiges = sonstiges;
    }

    //getter
    public String getTitel(){
        return titel;
    }

    public String getKuenstler(){
        return kuenstler;
    }

    public String getPfad(){
        return pfad;
    }

    public String getGenre(){
        return genre;
    }

    public int getLaenge(){
        return laenge;
    }

    public int getErscheinungsjahr(){
        return  erscheinungsjahr;
    }

    public String getAlbum(){
        return album;
    }

    public String getBild(){
        return bild;
    }

    public String getSonstiges(){
        return sonstiges;
    }
}
