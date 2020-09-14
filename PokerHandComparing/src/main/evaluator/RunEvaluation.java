package main.evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.Board;
import main.java.Hand;

public class RunEvaluation {

	public static void evaluate(Scanner scInput) {
		// Runs the evaluation loop till input = "stop"

		Board board = new Board();
		List<Hand> hands = new ArrayList<>();
		String input = "";
		do {
			hands.clear();
			// Get valid input
			do {
				if(scInput.hasNextLine()){
					input = scInput.nextLine();
					input = (input.isBlank()) ? "0" : input;
					} else {
						input = "0";
					}
			} while (!HoldemInputValidator.isValidInput(input));

			if (!input.equals("stop")) {
				// Populate the board card list and hand list
				board.setBoardListSorted(input.substring(0, 10));
				for (int i = 11; i < input.length(); i += 5) {
					hands.add(new Hand(input.substring(i, i + 4)));
				}
				// Set hand strengths and hand values for comparing
				for (int i = 0; i < hands.size(); i++) {
					HoldemHandStrenght.setStrenght(hands.get(i), board.getBoardCardList());
					hands.get(i).setRankSum();
					hands.get(i).setTieCheck();
				}
				// Sort hands by strength
				SortHandsByStrength.sort(hands);

				// Print out hands in order
				printHandsByStrength(hands);
			}
		} while (!input.equals("stop"));
	}

	private static void printHandsByStrength(List<Hand> hands) {
		String spacing = "";
		for (int i = hands.size() - 1; i >= 0; i--) {
			System.out.print(hands.get(i).toString());
			if(i == 0) {
				System.out.println();
				return;				
			} 
			if (hands.get(i).getStrength() == hands.get(i - 1).getStrength() &&
					hands.get(i).getTieCheck().equals(hands.get(i - 1).getTieCheck())) {
				spacing = "=";
			} else {
				spacing = " ";
			}
			System.out.print(spacing);
		}
	}
}
