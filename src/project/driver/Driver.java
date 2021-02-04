package project.driver;

import java.io.*;
import java.util.*;
import project.IO.*;
import project.movietheater.*;

public class Driver {
	public static void main(String[] args) {
		if (args.length > 0) {
			FileProcessor fileProcessor = new FileProcessor(args[0]);
			MovieTheater movieTheater = new MovieTheater();

			try {
				File file = new File(args[0]);
				Scanner sc = new Scanner(file);
				String reservation = sc.nextLine();


				// attempt to make reservation
				while (reservation != null) {
					int output = movieTheater.reserveSpot(reservation);
					if (output == -1) {
						System.out.println("There is an insufficient number of seats left.");
					}

					reservation = sc.nextLine();
				}
				// write reservation number and reserved seats to file
				fileProcessor.writeToFile(movieTheater.getReservations());

				sc.close();
			} catch (FileNotFoundException e) {
				System.err.println("Input file not Found.");
				e.printStackTrace();
				System.exit(1);
			} catch (Exception e) {
				System.err.println("An error has occurred. Please try again.");
				e.printStackTrace();
			}
		}
	}
}
