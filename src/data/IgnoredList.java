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

	public void addToList(String line) {
		line = line.toLowerCase();
		if (!ignoredList.contains(line))
			ignoredList.add(line);
	}

	public void deleteFromList(String line) {
		line = line.toLowerCase();
		if (ignoredList.contains(line))
			ignoredList.remove(line);
	}

	public void saveToFile() {
		fileIgnore.writeToFile(ignoredList);
	}

	public ArrayList<String> getIgnoredList() {
		return ignoredList;
	}

	public boolean contains(String line) {
		line = line.toLowerCase();
		return ignoredList.contains(line);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String line : ignoredList) {
			sb.append("   :" + line + "\n");
		}
		return sb.toString();
	}
}
