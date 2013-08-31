package data;

import java.util.ArrayList;

public class MovieTitles implements DataList {
	private ArrayList<String> movieTitles;
	private FileOperations fileTitle;

	public MovieTitles(String titleName) {
		fileTitle = new FileOperations(titleName);
		movieTitles = fileTitle.readFromFile();
	}

	public void addToList(String line) {
		if (!movieTitles.contains(line))
			movieTitles.add(line);
	}

	public void deleteFromList(String line) {
		if (movieTitles.contains(line))
			movieTitles.remove(line);
	}

	public void saveToFile() {
		fileTitle.writeToFile(movieTitles);
	}

	public ArrayList<String> getMovieTitles() {
		return movieTitles;
	}

	public int getSize() {
		return movieTitles.size();
	}

	public String getTitle(int pos) {
		if (pos < 0 || pos >= movieTitles.size())
			return null;
		return movieTitles.get(pos);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String line : movieTitles) {
			sb.append("   :" + line + "\n");
		}
		return sb.toString();
	}
}