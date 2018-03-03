import java.util.ArrayList;

public class Sort {
/*
    public ArrayList<Song> sort(ArrayList<Song> songs, String type, int left, int right){
        Song temp;
        for(int i=1; i<songs.size(); i++) {
            for(int j=0; j<songs.size()-i; j++) {
                if(songs.get(j).getClicked() > songs.get(i).getClicked()) {
                    temp=songs.get(j);
                    songs.set(j, songs.get(j+1));
                    songs.set(j+1, temp);
                }

            }
        }
        return songs;
    }

*/
    public ArrayList<Song> sort(ArrayList<Song> songs, String type, int left, int right) {
        if (left < right) {
            int i = partition(songs, type, left,right);
            sort(songs, type, left,i-1);
            sort(songs,type, i+1, right);
        }
        return songs;
    }


    private int partition(ArrayList<Song> songs, String type, int left, int right) {
        int pivot, i, j;
        Song help;
        i     = left;
        j     = right-1;

        //if(type.equals("clicked")) {
            pivot = songs.get(right).getClicked();
            while (i <= j) {
                if (songs.get(i).getClicked() > pivot) {
                    help = songs.get(i);
                    songs.set(i, songs.get(j));
                    songs.set(j, help);
                    j--;
                } else i++;
            }
       // }
        /*else if(type.equals("name")) {
            while(i <= j){
                int z = 0;
                try{
                    System.out.println("test");
                    while(songs.get(i).getTrack().charAt(z) == songs.get(i).getTrack().charAt(z)){
                        System.out.println("test2");
                        z++;
                    }
                    System.out.println("test3");
                }
                catch (Exception e){
                    i++;
                    continue;
                }

                if(songs.get(i).getTrack().charAt(z) > songs.get(right).getTrack().charAt(z)) {
                    help = songs.get(i);
                    songs.set(i, songs.get(j));
                    songs.set(j, help);
                    j--;
                } else i++;
            }
        }

        else if(type.equals("genre")){
            while(i <= j){
                int z = 0;
                while(songs.get(i).getGenre().charAt(z) == songs.get(i).getGenre().charAt(z)){
                    z++;
                }
                if(songs.get(i).getGenre().charAt(z) > songs.get(right).getGenre().charAt(z)) {
                    help = songs.get(i);
                    songs.set(i, songs.get(j));
                    songs.set(j, help);
                    j--;
                } else i++;
            }
        }*/

        help = songs.get(i);
        songs.set(i, songs.get(j));
        songs.set(j, help);

        return i;
    }
}