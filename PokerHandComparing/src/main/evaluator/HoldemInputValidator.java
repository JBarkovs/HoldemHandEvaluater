package main.evaluator;

import java.util.ArrayList;
import java.util.List;

import main.CardRanks;
import main.CardSuits;

public class HoldemInputValidator {

	public static boolean isValidInput(String input) {
		if (input.equals("stop")) {
			return true;
		}else if (isCorrectLenght(input)) {
			if (isCorrectFormat(input)) {
				if (isCorrectCharacters(input)) {
					if (!isAnyCardDuplicate(input)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isCorrectLenght(String input) {
		if (input.length() > 19 && (input.length() % 5) == 0 && input.length() < 61) {
			return true;
		}
		System.err.println("Incorrect amount of charcters in input!");
		return false;
	}

	public static boolean isCorrectFormat(String input) {

		for (int i = 10; i < input.length(); i += 5) {
			if (input.charAt(i) != ' ') {
				System.err.println("Incorrect spacing in input!");
				return false;
			}
		}
		return true;
	}

	public static boolean isCorrectCharacters(String input) {
		for (int i = 0; i < 10; i+= 2) {
			if(!CardRanks.isValueInMap(input.charAt(i)) || !CardSuits.isValueInMap(input.charAt(i + 1))) {
				System.err.println("'" + input.substring(i, i+2) + "' is not a valid card!");
				return false;
			}
		}
		for (int i = 11; i < input.length(); i+=5) {
			if(!CardRanks.isValueInMap(input.charAt(i)) || !CardSuits.isValueInMap(input.charAt(i + 1))) {
				System.err.println("'" + input.substring(i, i+2) + "' is not a valid card!");
				return false;
			}
			if(!CardRanks.isValueInMap(input.charAt(i + 2)) || !CardSuits.isValueInMap(input.charAt(i + 3))) {
				System.err.println("'" + input.substring(i + 2, i + 4) + "' is not a valid card!");
				return false;
			}			
		}
		return true;
	}
	
	public static boolean isAnyCardDuplicate(String input) {
		List<String> cardsUsed = new ArrayList<>();
		cardsUsed.add(input.substring(0, 3));
		for (int i = 2; i < 10; i+= 2) {
			if(cardsUsed.contains(input.substring(i, i + 2))) {
				System.err.println("'" + input.substring(i, i+2) + "' was already in iput!");
				return true;
			}
			cardsUsed.add(input.substring(i, i + 2));
		}
		for (int i = 11; i < input.length(); i+=5) {
			if(cardsUsed.contains(input.substring(i, i + 2))) {
				System.err.println("'" + input.substring(i, i+2) + "' was already in iput!");
				return true;
			}
			cardsUsed.add(input.substring(i, i + 2));
			if(cardsUsed.contains(input.substring(i + 2, i + 4))) {
				System.err.println("'" + input.substring(i + 2, i + 4) + "' was already in iput!");
				return true;
			}
			cardsUsed.add(input.substring(i + 2, i + 4));
		}		
		return false;
	}
}
