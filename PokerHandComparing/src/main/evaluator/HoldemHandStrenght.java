package main.evaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.CardRanks;
import main.java.Card;
import main.java.Hand;
import main.manageLists.ManageLists;

public class HoldemHandStrenght {

	private final static List<Card> workList = new ArrayList<>();
	private final static Map<Character, Integer> cardMap = new HashMap<>();
	private static boolean isCombinationSet;


	public static void setStrenght(Hand hand, List<Card> boardCardList) {
		isCombinationSet = false;
		int potentialCombination = 10;
		do {
			setWorkList(hand, boardCardList);
			potentialCombination--;
			switch (potentialCombination) {
			case 9:
				isCombinationSet = isStraightFlush(hand);
				break;
			case 8:
				populateCardMap();
				isCombinationSet = isFourOfaKind(hand);
				break;
			case 7:
				isCombinationSet = isFullHouse(hand);
				break;
			case 6:
				isCombinationSet = (hand.getStrength() == potentialCombination);
				break;
			case 5:
				isCombinationSet = isStraight(hand);
				break;
			case 4:
				isCombinationSet = isThreeOfaKind(hand);
				break;
			case 3:
				isCombinationSet = isTwoPair(hand);
				break;
			case 2:
				isCombinationSet = isPair(hand);
				break;
			default:
				// High card fall back
				isCombinationSet = true;
				break;
			}
		} while (!isCombinationSet);

		hand.setCombination(potentialCombination);
		if (potentialCombination != 6) {
			hand.setBestCombination(workList);
		}
	}

	private static boolean isStraightFlush(Hand hand) {
		if (isFlush(hand) && isStraight(hand)) {
			return true;
		}
		return false;
	}

	private static boolean isFlush(Hand hand) {
		Map<Character, Integer> suitsMap = new HashMap<>();
		suitsMap.put('h', 0);
		suitsMap.put('d', 0);
		suitsMap.put('c', 0);
		suitsMap.put('s', 0);

		// Determine the amount of each suit in workList
		for (Card card : workList) {
			suitsMap.put(card.getSuit(), suitsMap.get(card.getSuit()) + 1);
		}
		// Gets the suit of the highest number
		char maxValueKey = 'h';
		for (Entry<Character, Integer> map : suitsMap.entrySet()) {
			if (map.getValue() > suitsMap.get(maxValueKey)) {
				maxValueKey = map.getKey();
			}
		}

		// if at least 5 of same suit, then remove Cards with the rest of the suits
		if (suitsMap.get(maxValueKey) > 4) {
			for (int i = 0; i < workList.size();) {
				if (workList.get(i).getSuit() != maxValueKey) {
					workList.remove(i);
				} else {
					i++;
				}
			}
			// Flash method as exception sets the winning hand itself
			// Not to make this check again
			hand.setBestCombination(workList);
			hand.setCombination(6);
			return true;
		}
		return false;
	}

	private static boolean isStraight(Hand hand) {
		int cardsInRow = 0;
		for (int i = 0; i < workList.size() - 1;) {
			// remove same value ranks in row
			while (CardRanks.getCardRankStrenght(workList.get(i).getRank()) == CardRanks.getCardRankStrenght(workList.get(i + 1).getRank())) {
				workList.remove(i + 1);
				if (i == workList.size() - 1) {
					return false;
				}
			}
			// if the difference in rank of current card and the next is 1 or
			// if it's 2 and A, then increment counter
			// else remove previous card/s and start the counting from 0
			if ((CardRanks.getCardRankStrenght(workList.get(i).getRank()) - CardRanks.getCardRankStrenght(workList.get(i + 1).getRank()) == 1) ||
					(workList.get(i).getRank() == '2' && workList.get(i + 1).getRank() == 'A')) {
				cardsInRow++;
				if (cardsInRow == 4) {
					return true;
				}
			} else {
				// if there are less than 5 cards left to check, then straight is not possible
				if (workList.size() - (i + 1) < 5) {
					return false;
				}
				for (int j = 0; j <= i;) {
					if (workList.get(j).getRank() == 'A') {
						// For the weakest straight
						workList.add(workList.get(j));
					}
					workList.remove(j);
					i--;
				}
				cardsInRow = 0;
			}
			i++;
		}
		return false;
	}

	private static boolean isFourOfaKind(Hand hand) {
		char highestRank = getHighestRankFromAmount(4);
		if (highestRank != '0') {
			ManageLists.moveCardsToThePosition(workList, ManageLists.getLocationOfCardRank(workList, highestRank), 4, 0);
			return true;
		}
		return false;
	}

	private static boolean isFullHouse(Hand hand) {
		if (isThreeOfaKind(hand)) {
			char nextHighestRank = getHighestRankFromAmount(2, 3, workList.get(0).getRank());
			if (nextHighestRank != '0') {
				ManageLists.moveCardsToThePosition(workList, ManageLists.getLocationOfCardRank(workList, nextHighestRank), 2, 3);
				return true;
			}
		}
		return false;
	}

	private static boolean isThreeOfaKind(Hand hand) {
		char highestRank = getHighestRankFromAmount(3);
		if (highestRank != '0') {
			ManageLists.moveCardsToThePosition(workList, ManageLists.getLocationOfCardRank(workList, highestRank), 3, 0);
			return true;
		}
		return false;
	}

	private static boolean isTwoPair(Hand hand) {
		if (isPair(hand)) {
			char nextHighestRank = getHighestRankFromAmount(2, workList.get(0).getRank());
			if (nextHighestRank != '0') {
				ManageLists.moveCardsToThePosition(workList, ManageLists.getLocationOfCardRank(workList, nextHighestRank), 2, 2);
				return true;
			}
		}
		return false;
	}

	private static boolean isPair(Hand hand) {
		char highestRank = getHighestRankFromAmount(2);
		if (highestRank != '0') {
			ManageLists.moveCardsToThePosition(workList, ManageLists.getLocationOfCardRank(workList, highestRank), 2, 0);
			return true;
		}
		return false;
	}

	private static void populateCardMap() {
		// count amount of each rank
		cardMap.clear();
		for (int i = 0; i < workList.size(); i++) {
			if (cardMap.containsKey(workList.get(i).getRank())) {
				cardMap.put(workList.get(i).getRank(), cardMap.get(workList.get(i).getRank()) + 1);
			} else {
				cardMap.put(workList.get(i).getRank(), 1);
			}
		}
	}

	private static char getHighestRankFromAmount(int amount1) {
		char compare = '0';
		return getHighestRankFromAmount(amount1, compare);
	}

	private static char getHighestRankFromAmount(int amount1, char compare) {
		return getHighestRankFromAmount(amount1, amount1, compare);
	}

	private static char getHighestRankFromAmount(int amount1, int amount2, char compare) {
		// Find a rank with the "amount" given
		// if it isn't the "compare" value 
		char highestRank = '0';
		for (Map.Entry<Character, Integer> map : cardMap.entrySet()) {
			if ((map.getValue() == amount1 || map.getValue() == amount2) && map.getKey() != compare) {
				if (highestRank == '0') {
					highestRank = map.getKey();
				} else if (CardRanks.getCardRankStrenght(map.getKey()) > CardRanks.getCardRankStrenght(highestRank)) {
					highestRank = map.getKey();
				}
			}
		}
		return highestRank;
	}

	private static void setWorkList(Hand hand, List<Card> boardCardList) {
		// Add all 7 cards for evaluation
		workList.clear();
		workList.addAll(boardCardList);
		ManageLists.addCardToListByRank(workList, hand.getCard1());
		ManageLists.addCardToListByRank(workList, hand.getCard2());
	}
}
