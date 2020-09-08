package main;

import java.util.HashMap;
import java.util.Map;

public enum CardSuits {
	
	Hearts('h'), Diamonds('d'), Clubs('c'), Spades('s');
	
	private final char suit;
	private static final Map<Character, String> CardSuitMap = new HashMap<>(); 

	private CardSuits(char suit) {
		this.suit = suit;
	}
	
	static {
		for (CardSuits suits : values()) {
			CardSuitMap.put(suits.suit, suits.toString());
		}
	}
	
	// For checking correct input
	public static boolean isValueInMap(char ch) {
		return CardSuitMap.containsKey(ch);
	}

}
