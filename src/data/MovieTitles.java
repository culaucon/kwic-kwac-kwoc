package data;
import java.util.ArrayList;

public class MovieTitles {
	private ArrayList<String> movieTitles;
	private FileOperations fileTitle;
	private final String TITLE_NAME = "title.txt";
	
	public MovieTitles() {
		fileTitle = new FileOperations(TITLE_NAME);
		movieTitles = fileTitle.readFromFile();
	}
	
	public void saveToFile() {
		fileTitle.writeToFile(movieTitles);
	}
	
	public ArrayList<String> getMovieTitles() {
		return movieTitles;
	}
}