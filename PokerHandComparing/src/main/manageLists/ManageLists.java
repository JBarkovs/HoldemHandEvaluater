package main.manageLists;

import java.util.List;

import main.CardRanks;
import main.java.Card;

public class ManageLists {
	
	public static int getLocationOfCardRank(List<Card> cardList, char highestRank) {
		for (int location = 0; location < 6; location++) {
			// Find the location of the card with first instance of this rank
			if (cardList.get(location).getRank() == highestRank) {
				return location;
			}
		}
		return 0;
	}
		
	public static void addCardToListByRank(List<Card> cardList ,Card card) {
		if(cardList.size() == 0) {
			cardList.add(card);
			return;
		}
		for (int i = 0; i < cardList.size(); i++) {
			if(CardRanks.getCardRankStrenght(cardList.get(i).getRank()) < CardRanks.getCardRankStrenght(card.getRank())) {
				cardList.add(i, card);
				return;
			}
			if (i == cardList.size() - 1) {
				cardList.add(card);
				return;
			}
		}		
	}
	
	public static void moveCardsToThePosition(List<Card> cardList, int locationOfCard, int cardAmount, int newPosition) {
		if (locationOfCard == newPosition) {
			return;
		}
		for (int i = 0; i < cardAmount; i++) {
			cardList.add(newPosition, cardList.get(locationOfCard + i));
			cardList.remove(locationOfCard + i + 1);
		}			
	}
}
