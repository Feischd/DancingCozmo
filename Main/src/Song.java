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
	private String fileName;
	private int clicked;

	public Song(String path) {
		this.path = path;
		this.track = "";
		this.artist = "";

		genre = "";
		duration = 0;
		published = 0;
		album = "";
		cover = "";
		information = "";
		setFileName();
		clicked = 0;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setPublished(int published) {
		this.published = published;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public void setPath(String path) {
		this.path = path;
		setFileName();
	}

	private void setFileName() {
		String[] splitArray = path.split("\\\\");
		this.fileName = splitArray[splitArray.length - 1].replaceAll(".mp3", "").replaceAll(".wma", "")
				.replaceAll(".wav", "".replaceAll("m4a", "").replaceAll("aac", ""));
	}

	public void raiseClicked() {
		this.clicked++;
	}

	public String getTrack() {
		return track;
	}

	public String getArtist() {
		return artist;
	}

	public String getPath() {
		return path;
	}

	public String getGenre() {
		return genre;
	}

	public int getDuration() {
		return duration;
	}

	public int getPublished() {
		return published;
	}

	public String getAlbum() {
		return album;
	}

	public String getCover() {
		return cover;
	}

	public String getInformation() {
		return information;
	}

	public String getFileName() {
		return fileName;
	}

	public int getClicked() {
		return clicked;
	}
}