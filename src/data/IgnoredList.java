package data;

import java.util.ArrayList;

public class IgnoredList implements DataList{
	private ArrayList<String> ignoredList;
	private FileOperations fileIgnore;

	public IgnoredList(String filename) {
		fileIgnore = new FileOperations(filename);
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
