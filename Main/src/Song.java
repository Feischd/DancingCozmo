public class Song {
    private String track;
    private String artist;
    private String path;
    private String genre;
    private int duration;
    private int published;
    private String album;
    private String cover;
    private String information;


    public Song(String track, String artist, String path){
        this.track = track;
        this.artist = artist;
        this.path = path;
        genre = "";
        duration = 0;
        published = 0;
        album = "";
        cover = "";
        information = "";
    }

    // setter
    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setPublished(int published){
        this.published = published;
    }

    public void setAlbum(String album){
        this.album = album;
    }
    
    public void setCover(String cover){
        this.cover = cover;
    }

    public void setInformation(String information){
        this.information = information;
    }

    //getter
    public String getTrack(){
        return track;
    }

    public String getArtist(){
        return artist;
    }

    public String getPath(){
        return path;
    }

    public String getGenre(){
        return genre;
    }

    public int getDuration(){
        return duration;
    }

    public int getPublished(){
        return  published;
    }

    public String getAlbum(){
        return album;
    }

    public String getCover(){
        return cover;
    }

    public String getInformation(){
        return information;
    }
}