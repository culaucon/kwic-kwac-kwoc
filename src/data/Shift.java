package data;

public class Shift implements Comparable<Shift> {

	private int startIndex;
	private String[] words;

	public Shift(int startIndex, String[] words) {
		this.startIndex = startIndex;
		this.words = words;
	}

	public String toString() {
		int id = startIndex;
		String string = "   :" + words[id];
		while (true) {
			id++;
			if (id >= words.length)
				id -= words.length;
			if (id == startIndex)
				break;
			string += " " + words[id];
		}
		return string;
	}

	@Override
	public int compareTo(Shift shift) {
		int id1 = startIndex, id2 = shift.startIndex;
		do {
			if (!words[id1].equals(shift.words[id2])) {
				return words[id1].compareTo(shift.words[id2]);
			}
			id1++;
			if (id1 >= words.length)
				id1 -= words.length;
			id2++;
			if (id2 >= shift.words.length)
				id2 -= shift.words.length;

		} while (id1 != startIndex);
		return 0;
	}
}
