package main;

import java.util.HashMap;
import java.util.Map;

public enum CardRanks {

	TWO('2', 1), THREE('3', 2), FOUR('4', 3), FIVE('5', 4), SIX('6', 5), SEVEN('7', 6), EIGHT('8', 7), NINE('9', 8),
	TEN('T', 9), JACK('J', 10), QUEEN('Q', 11), KING('K', 12), ACE('A', 13);

	private final char value;
	private final int ranking;
	private static final Map<Character, Integer> CardRankMap = new HashMap<>();

	private CardRanks(char value, int ranking) {
		this.value = value;
		this.ranking = ranking;
	}

	static {
		for (CardRanks card : values()) {
			CardRankMap.put(card.value, card.ranking);
		}
	}

	// For sorting purpose
	public static int getCardRankStrenght(char rank) {
		return CardRankMap.get(rank);
	}

	// For checking correct input
	public static boolean isValueInMap(char ch) {
		return CardRankMap.containsKey(ch);
	}

}
