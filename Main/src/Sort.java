import java.util.ArrayList;

public class Sort {

	/*
	 * Takes unsorted ArrayList and returns it sorted. Sortalgorithm bases on
	 * Quicksort.
	 */
	public ArrayList<Song> sort(ArrayList<Song> songs, String type, int left, int right) {
		if (left < right) {
			int i = partition(songs, type, left, right);
			sort(songs, type, left, i - 1);
			sort(songs, type, i + 1, right);
		}
		return songs;
	}

	private int partition(ArrayList<Song> songs, String type, int left, int right) {
		int i, j;
		Song help;
		i = left;
		j = right - 1;
		// criteria 'clicked'
		if (type.equals("clicked")) {
			int pivot = songs.get(right).getClicked();
			while (i <= j) {
				if (songs.get(i).getClicked() < pivot) {
					help = songs.get(i);
					songs.set(i, songs.get(j));
					songs.set(j, help);
					j--;
				} else {
					i++;
				}
			}
		}

		// criteria 'nameDown'
		else if (type.equals("nameDown")) {
			while (i <= j) {
				// check on characters. If they are equal, the following characters will be
				// compared.
				int z = 0;
				try {
					while (songs.get(i).getFileName().charAt(z) == songs.get(right).getFileName().charAt(z)) {
						z++;
					}
				} catch (Exception e) {
					j--;
					continue;
				}

				if (songs.get(i).getFileName().length() > z) {
					if (songs.get(i).getFileName().charAt(z) < songs.get(right).getFileName().charAt(z)) {
						help = songs.get(i);
						songs.set(i, songs.get(j));
						songs.set(j, help);
						j--;
					} else {
						i++;
					}
				}
			}
		}

		// criteria 'nameUp'
		else if (type.equals("nameUp")) {
			while (i <= j) {
				// check on characters. If they are equal, the following characters will be
				// compared.
				int z = 0;
				try {
					while (songs.get(i).getFileName().charAt(z) == songs.get(right).getFileName().charAt(z)) {
						z++;
					}
				} catch (Exception e) {
					j--;
					continue;
				}

				if (songs.get(i).getFileName().length() > z) {
					if (songs.get(i).getFileName().charAt(z) > songs.get(right).getFileName().charAt(z)) {
						help = songs.get(i);
						songs.set(i, songs.get(j));
						songs.set(j, help);
						j--;
					} else
						i++;
				}
			}
		}

		help = songs.get(i);
		songs.set(i, songs.get(right));
		songs.set(right, help);

		return i;
	}
}