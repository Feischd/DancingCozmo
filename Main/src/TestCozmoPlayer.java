public class TestCozmoPlayer {

    public static void main(String[] args){
        CozmoPlayer cp = new CozmoPlayer();
        Song song = new Song("Test", "TestK", "01_Titelnummer_1.wav");
        song.setGenre("Blues");
        cp.play(song);
    }


}
