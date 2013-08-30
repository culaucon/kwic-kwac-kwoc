package data;
import java.util.ArrayList;

public class IgnoredList {
	private ArrayList<String> ignoredList;
	private FileOperations fileIgnore;
	private final String IGNORE_NAME = "ignore.txt";
	
	public IgnoredList() {
		fileIgnore = new FileOperations(IGNORE_NAME);
		ignoredList = fileIgnore.readFromFile();
	}
	
	public void saveToFile() {
		fileIgnore.writeToFile(ignoredList);
	}
	
	public ArrayList<String> getIgnoredList() {
		return ignoredList;
	}
}
