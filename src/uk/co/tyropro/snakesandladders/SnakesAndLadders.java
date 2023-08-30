package uk.co.tyropro.snakesandladders;

import java.util.Scanner;

public class SnakesAndLadders {
	// board with the snakes and ladders
	// (0 means nothing, anything else is where the snake
	// 	or ladder goes to)
	static int[] board = {
			0, 0, 0, 14, 0, 0, 0, 0, 31, 0,
			0, 0, 0, 0, 0, 0, 7, 0, 0, 0,
			42, 0, 0, 0, 0, 0, 0, 84, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			67, 0, 0, 34, 0, 0, 0, 0, 0, 0,
			0, 19, 0, 60, 0, 0, 0, 0, 0, 0,
			0, 91, 0, 0, 0, 0, 0, 0, 0, 99,
			0, 0, 0, 0, 0, 0, 36, 0, 0, 0,
			0, 0, 73, 0, 75, 0, 0, 79, 0, 0,
			};
	
	

	// check if player has landed on a space with a snake or a ladder
	static Integer CheckMovement(Integer space) {
		// logic for snakes and ladders
		if (board[space-1] != 0 ) {
			if (board[space-1] < space) { // logic for snake
				System.out.println("You have landed on a snake!");
			} else if (board[space-1] > space) { // logic for ladders
				System.out.println("You have landed on a ladder!");
			}
			space = board[space-1];
			System.out.println("You are now on space " + space.toString() + ".");
			return space; // returns new space 
		} else {
			return space;
		}
	}
	
	// check if player has won
	static boolean CheckWin(Integer int_space) {
		if (int_space == 100) { // logic for if lands on 100
			return true;
		} else {
			return false;
		}
	}
	
	// check if player is over goal
	static Integer CheckOverGoal(Integer int_space) {
		if (int_space > 100) { // logic for if goes over 100 
			Integer int_spacesOver = int_space - 100; // finds out how many spaces over 100
			int_space -= int_spacesOver * 2; // moves the player back how many spaces they have gone over
											 // (*2 necessary because *1 would put player on 100)
			
			System.out.println("You have rolled " + int_spacesOver.toString() + " spaces over 100.");
			System.out.println("You are now on space " + int_space.toString() + ".");
		}
		return int_space;
	}
	
	static Integer Turn(Integer int_space) {
		// roll a dice
		double dbl_roll = Math.floor(Math.random() * 6) + 1;
		Integer int_roll = (int)dbl_roll;
		
		// increment current space by dice roll
		int_space += int_roll;
		
		// output dice roll and current space
		System.out.println("You rolled a " + int_roll.toString() + ".");
		System.out.println("You are now on space " + int_space.toString() + ".");
		
		return int_space;
	}
	
	public static void main(String[] args) {
		System.out.println("Snakes & Ladders");
		
		try (
			// Scanner for user input
			Scanner sc = new Scanner(System.in)) {
			// boolean for the while loop 
			boolean bool_play = true;	
			
			// player's space on board
			Integer int_space = 1;
			
			// count the number of turns the user takes
			Integer int_turns = 0;

			// code loop
			while (bool_play) {
				// gets user input
				char[] chara_input = sc.nextLine().toCharArray();
				
				// checks if user has entered the exit loop
				if (chara_input.length == 1 && chara_input[0] == 'q') {					
					// sets to false to end loop
					return;
				}

				// play 1 turn
				int_space = Turn(int_space);
				
				// check if player has won
				if (CheckWin(int_space)) {
					bool_play = false;
				}
				
				// check if the player has gone over the goal
				int_space = CheckOverGoal(int_space);
				
				// check if there is a snake or ladder on the current space
				int_space = CheckMovement(int_space);
				
				int_turns++;
				
				
			}
			
			// converts 'turns' to string
			String str_turns = int_turns.toString();
			
			// prints you win message
			System.out.println("You win!!!\nIt took " + str_turns + " turns!");
		}
	}
}
