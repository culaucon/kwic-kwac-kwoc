package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import data.*;

public class Control {

	private static IgnoredList ignoredList;
	private static MovieTitles movieTitles;
	private static ArrayList<Shift> shifts;

	private static final String EXIT = "exit";
	private static final String ADD_IGNORED = "+i";
	private static final String DELETE_IGNORED = "-i";
	private static final String ADD_TITLE = "+t";
	private static final String DELETE_TITLE = "-t";
	private static final String DISPLAY = "disp";
	private static final String IGNORE = "ignore";
	private static final String TITLE = "title";

	private static final String MSG_ADDED_IGNORED = "Added word(s) to ignored list";
	private static final String MSG_DELETED_IGNORED = "Deleted word(s) from ignored list";
	private static final String MSG_ADDED_TITLE = "Added movie title";
	private static final String MSG_DELETED_TITLE = "Deleted movie title";

	private static final String MSG_INVALID_COMMAND = "Invalid Command";
	private static final String MSG_DISPLAY_IGNORE = "Displaying ignored words:";
	private static final String MSG_DISPLAY_TITLE = "Displaying movie titles:";
	private static final String MSG_DISPLAY_RESULT = "Displaying KWIC-KWAC-KWOC result:";
	private static final String MSG_NO_SHIFTS = "No shifts are present";
	private static final String MSG_INVALID_DISPLAY = "Invalid display command";

	public static void main(String[] args) {
		initialize();
		displayWelcome();
		handleInput();
	}

	private static void initialize() {
		ignoredList = new IgnoredList();
		movieTitles = new MovieTitles();
	}

	private static void displayWelcome() {
		System.out.println("Welcome to KWIC-KWAC-KWOC");
		System.out.println("List of valid commands:");
		System.out.println("   \"+i\" and \"-i\" for adding and deleting ignored words");
		System.out.println("   \"+t\" and \"-t\" for adding and deleting movie titles");
		System.out.println("   \"disp ignore\" or \"disp title\" for displaying the corresponding list");
		System.out.println("   \"disp\" for displaying the result of KWIC-KWAC-KWOC");
		System.out.println("   \"exit\" for exiting the program properly and saving the lists for future use");
	}

	private static void handleInput() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String line = scanner.nextLine();
			line = line.trim();
			if (line.equals(EXIT))
				break;
			int indexFirstSpace = line.indexOf(" ");
			if (indexFirstSpace == -1) {
				indexFirstSpace = line.length();
			}
			performAction(line.substring(0, indexFirstSpace), line
					.substring(indexFirstSpace));
		}
		ignoredList.saveToFile();
		movieTitles.saveToFile();
	}

	private static void performAction(String command, String args) {
		command = command.trim();
		args = args.trim();
		if (command.equals(ADD_IGNORED))
			addIgnored(args);
		else if (command.equals(DELETE_IGNORED))
			deleteIgnored(args);
		else if (command.equals(ADD_TITLE))
			addTitle(args);
		else if (command.equals(DELETE_TITLE))
			deleteTitle(args);
		else if (command.equals(DISPLAY))
			display(args);
		else
			System.out.println(MSG_INVALID_COMMAND);
	}

	private static void addIgnored(String args) {
		String[] words = args.split(" ");
		for (int i = 0; i < words.length; i++)
			ignoredList.addToList(words[i]);
		System.out.println(MSG_ADDED_IGNORED);
	}

	private static void deleteIgnored(String args) {
		String[] words = args.split(" ");
		for (int i = 0; i < words.length; i++)
			ignoredList.deleteFromList(words[i]);
		System.out.println(MSG_DELETED_IGNORED);
	}

	private static void addTitle(String args) {
		String title = reformatTitle(args);
		movieTitles.addToList(title);
		System.out.println(MSG_ADDED_TITLE);
	}

	private static void deleteTitle(String args) {
		String title = reformatTitle(args);
		movieTitles.deleteFromList(title);
		System.out.println(MSG_DELETED_TITLE);
	}

	private static String reformatTitle(String args) {
		String[] words = args.split(" ");
		String title = words[0];
		for (int i = 1; i < words.length; i++)
			title += " " + words[i];
		return title;
	}

	private static void display(String args) {
		if (args.equals(IGNORE)) {
			System.out.println(MSG_DISPLAY_IGNORE);
			System.out.println(ignoredList);
		} else if (args.equals(TITLE)) {
			System.out.println(MSG_DISPLAY_TITLE);
			System.out.println(movieTitles);
		} else if (args.equals("")) {
			System.out.println(MSG_DISPLAY_RESULT);
			processResult();
			printResult();
		} else {
			System.out.println(MSG_INVALID_DISPLAY);
		}
	}

	private static void processResult() {
		shifts = new ArrayList<Shift>();
		int numberTitles = movieTitles.getSize();
		for (int i = 0; i < numberTitles; i++) {
			String title = movieTitles.getTitle(i);
			String[] words = title.split(" ");
			for (int j = 0; j < words.length; j++) {
				if (ignoredList.contains(words[j]))
					continue;
				else
					shifts.add(new Shift(j, words));
			}
		}
		Collections.sort(shifts);
	}

	private static void printResult() {
		for (Shift shift : shifts) {
			System.out.println(shift);
		}
		if (shifts.size() == 0) {
			System.out.println(MSG_NO_SHIFTS);
		}
	}
}
