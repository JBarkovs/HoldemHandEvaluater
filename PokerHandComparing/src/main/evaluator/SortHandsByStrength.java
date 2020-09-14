package main.evaluator;

import java.util.List;

import main.CardRanks;
import main.java.Hand;

public class SortHandsByStrength {
	
	public static void sort(List<Hand> hands) {
		shellSortHandList(hands);
		sortEqualStrengths(hands);
	}

	private static void shellSortHandList(List<Hand> hands) {
		// Sorts Hand List by Strength in descending order
		int n = hands.size();

		// Reduce the gap till sorted
		for (int gap = n / 2; gap > 0; gap /= 2) {
			//
			for (int i = gap; i < n; i++) {
				// The value for logical checks
				Hand temp = hands.get(i);

				int j;
				for (j = i; j >= gap && hands.get(j - gap).getStrength() < temp.getStrength(); j -= gap) {
					// If temp strength is larger then move the other value to the right
					// And adjust the new position for temp
					hands.set(j, hands.get(j - gap));
				}
				// Put temp back or move it in new position
				hands.set(j, temp);
			}
		}
	}
	
	private static void sortEqualStrengths(List<Hand> hands) {
		int counter = 0;
		for (int i = 0; i < hands.size() - 1; i++) {
			counter = 0;
			// Check hand strength and rankSum
			// if i+1 > i, then bringing the hand down the list
			// if swapped increment counter check again 
			while ((i - counter) >= 0 && hands.get(i - counter).getStrength() == hands.get(i + 1 - counter).getStrength()) {
				
				// Loops through and compares ranks of each card in hand.bestCombination
				for (int rankCheck = 0; rankCheck < 5; rankCheck++) {
					int hand1Rank = CardRanks.getCardRankStrenght(hands.get(i - counter).getBestCombination().get(rankCheck).getRank());
					int hand2Rank = CardRanks.getCardRankStrenght(hands.get(i + 1 - counter).getBestCombination().get(rankCheck).getRank());
					if(hand1Rank > hand2Rank) {
						// No need to check further
						break;
					} else if (hand1Rank < hand2Rank) {
						//Swaps hand i with hand i+1
						Hand temp = hands.get(i - counter);
						hands.remove(i - counter);
						hands.add(i + 1 - counter, temp);						
						break;
					}					
				}
				counter++;
			}
		}		
	}
	
}
