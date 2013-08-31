package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {

	private String fileName;

	public FileOperations(String name) {
		fileName = name;
	}

	public ArrayList<String> readFromFile() {
		ArrayList<String> fileData = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				fileData.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			createNewFile();
			return new ArrayList<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileData;
	}

	public void writeToFile(ArrayList<String> fileData) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			for (String line : fileData) {
				writer.write(line + "\n");
			}
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void createNewFile() {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
