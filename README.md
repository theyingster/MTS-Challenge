# Movie Theater Seating Challenge

## Description:

This program takes an input file of reservations from command line and writes a completed reservation list to the output file. As I limited myself to 3 hours, I did not have time to implement the testing portion. To implement testing, I would first test instances of MovieTheater to ensure the constructors are functioning correctly. Then, I would create different inputs in input.txt with 0 reservations, reservations requesting more seats than available, reservations request more seats than available in any given row, and a non-edge case. These inputs will be tested against custom outputs through java assertion statements.

**NOTE:** The current driver class fails to compile due to issue with packaging, which is currently under debugging.

## Assumptions:

1. The 3 seat/1 row buffer all apply to diagonal seats. In other words, a scenario with a person seated at A1 and another at B2 is considered not safe as rows A and B do not have a row buffer.
2. Safety is more important than customer satisfaction. The buffer rule must not be broken even through other assumptions.
4. The input is in order of the reservations.
5. The input format is always correct.
6. The output does not necessarily have to be in the order of reservations.

## Approach:
1. Seats will be reserved based on the order of reservation and column number.
2. If the reservation seat count is greater than the number of seats available in all rows, allocate the members to any available spots starting from the front.
3. If the number of seats attempted to be reserved is greater than the number of available seats left, inform the reserver that the request failed.
4. Seats will be reserved with priority to the front of the theater. In other words, the reservation seating starts from the front.
5. Finally, let us realize that we are amidst the pandemic and it is required that there be a 3 seat buffer or a row buffer between each person. We can conclude that, for each row, there can be at most 5 seats available per row. Thus, the most optimal seating will be columns 2,6,10,14,18 as this
5-person seating also prioritizes centering. Now, by assumption 1, we can only have seats available every other row, which we will use 0,2,4,6,8 (A,C,E,G,I). Thus, we have a total of 25 available seats before any reservations.

## Steps:
1. Go to the ```src``` directory.
2. Compile all java files through the command ```javac src/project/driver/Driver.java src/project/movietheater/MovieTheater.java src/project/IO/FileProcessor.java```.
3. ```cd``` into to the driver folder in ```src/project``` and run ```java driver input.txt ```.  
