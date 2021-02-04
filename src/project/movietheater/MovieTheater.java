package project.movietheater;

import java.util.*;

public class MovieTheater {
	// initialize variables
	String[] ROWS = {"A","C","E","G","I"};
	String[] COLUMNS = {"2","6","10","14","18"};

	int rows;
	int columns;
	int numberOfSeats;
	HashMap<String, ArrayList<String>> finalReservations;
	int remainingSeats[];

	// constructor
	public MovieTheater() {
		rows = 5;
		columns = 5;
		numberOfSeats = rows * columns;
		finalReservations = new HashMap<>();
		remainingSeats = new int[rows];
		for (int i = 0; i < rows; i++){
			remainingSeats[i] = columns;
		}
	}

	/**
	 * 
	 * @param reservation
	 * @return 1 if succeed in reserving spot, -1 if failed
	 */
	public int reserveSpot(String reservation) {
		String[] input = reservation.split(" ");
		String reservationNumber = input[0];
		int seatsReserved = Integer.parseInt(input[1]);
		int output = -1;

		// reserves seats if there are enough available seats
		if (numberOfSeats >= seatsReserved) {
			if (seatsReserved > 5) {
				while (seatsReserved > 5) {
					output = allocate(reservationNumber, 5);
					seatsReserved -= 5;
				}
				output = allocate(reservationNumber, seatsReserved);
			} 
			else {
				output = allocate(reservationNumber, seatsReserved);
			}
			return output;
		}
		return output;
	}

	/**
	 * Helper Function to find first row in rows that has at least 'threshold'
	 * number of available seats
	 */
	private int findFirstRow(int[] rows, int threshold){
		int index = 0;
		for (int row : rows) {
			if (row >= threshold){
				return index;
			}
			index ++;
		}
		return -1;
	}

	/**
	 * 
	 * @param reservationNumber
	 * @param seats
	 * @return 0, placeholder return value for succeeding in reserving seats
	 */
	private int allocate(String reservationNumber, int seats) {
		int firstAvailableRow = findFirstRow(remainingSeats, seats);
		// 
		if (firstAvailableRow == -1) {
			// addition does not matter, simply using recursive calls to make reservations more readable
			/* 
				make recursive call to split the reservation if and only if there are no rows that can 
				accomodate the group
			*/
			return allocate(reservationNumber, seats - 1) + allocate(reservationNumber, 1);
		}
		String rowName = ROWS[firstAvailableRow];
		ArrayList<String> seatNumbers = new ArrayList<>();

		// add all reserved seats to temporary ArrayList, which will be added to the hashmap
		for (int i = remainingSeats[firstAvailableRow]; i > 0; i --){
			seatNumbers.add(rowName + COLUMNS[this.columns - remainingSeats[firstAvailableRow]]);
			remainingSeats[firstAvailableRow] --;
		}
		if (finalReservations.containsKey(reservationNumber)) {
			finalReservations.get(reservationNumber).addAll(seatNumbers);
		} 
		else {
			finalReservations.put(reservationNumber, seatNumbers);
		}
		return 0;
	}

	public HashMap<String, ArrayList<String>> getReservations() {
		return finalReservations;
	}

	// GET METHODS
	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public int getRow() {
		return this.rows;
	}

	public int getColumn() {
		return this.columns;
	}
}
