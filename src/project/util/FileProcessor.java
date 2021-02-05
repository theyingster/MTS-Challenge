package project.util;

import java.io.*;
import java.util.*;

public class FileProcessor {
	String filename;
	int numLines;

	public FileProcessor(String input) {
		filename = input;
		numLines = 0;

	}

	/**
	 * 
	 * @param input
	 * @return reservations - all reservations of seats as a string separated by 
	 * 		   a newline character
	 */
	public String readFile(String input) {
		String reservations = "";
		try {
			// read file with scanner and add to returning string
			File file = new File(input);
			Scanner sc = new Scanner(file);
			String line = sc.nextLine();

			while (line != null) {
				reservations += line + "\n";
				line = sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException ex) {
			System.err.println("Input file not Found.");
			ex.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservations;
	}

	/**
	 * 
	 * @param hashmap
	 */
	public void writeToFile(HashMap<String, ArrayList<String>> hashmap) {
		try {
			FileWriter wr = new FileWriter("output.txt");

			// Initialize Iterator to iterate through Hashmap entries
			Iterator<Map.Entry<String, ArrayList<String>>> iterator = hashmap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, ArrayList<String>> pairs = iterator.next();
				String str = pairs.getKey() + " ";
				ArrayList<String> seats = pairs.getValue();
				for (int i = 0; i < seats.size(); i++) {
					if (i != seats.size() - 1){
						str += seats.get(i) + ", ";
					}
					else {
						str += seats.get(i);
					}
				}
				System.out.println(str);
				wr.write(str + "\n");
			}
			wr.close();
		} catch (IOException e) {
			System.out.println("Failed to write to file.");
			e.printStackTrace();
		}
		System.out.println(hashmap);
	}
}
