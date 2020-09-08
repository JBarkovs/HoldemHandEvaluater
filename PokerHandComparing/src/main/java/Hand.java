package main.java;

import java.util.ArrayList;
import java.util.List;

import main.CardRanks;

public class Hand {
	
	private Card card1, card2;
	private List<Card> bestCombination;
	private int combinationStrenght;
	private int rankSum;
	private String tieCheck;
	
	public Hand(String hand) {
		card1 = new Card(hand.charAt(0), hand.charAt(1));
		card2 = new Card(hand.charAt(2), hand.charAt(3));
		bestCombination = new ArrayList<>();
		combinationStrenght = 0;
		rankSum = 0;
		tieCheck = "";
	}

	public Card getCard1() {
		return card1;
	}

	public Card getCard2() {
		return card2;
	}
	
	public int getStrength() {
		return combinationStrenght;
	}

	public void setCombination(int combStrenght) {
		this.combinationStrenght = combStrenght;
	}

	public int getRankSum() {
		return rankSum;
	}

	public void setRankSum() {
		for (int i = 0; i < 5; i++) {
			rankSum = rankSum + CardRanks.getCardRankStrenght(bestCombination.get(i).getRank());
		}
	}

	public List<Card> getBestCombination() {
		return bestCombination;
	}

	public void setBestCombination(List<Card> cardsForCombination) {
		this.bestCombination.clear(); // In case of a flush
		this.bestCombination.addAll(cardsForCombination);
	}

	public String getTieCheck() {
		return tieCheck;
	}

	public void setTieCheck() {
		for (int i = 0; i < 5; i++) {
			tieCheck = tieCheck.concat(String.valueOf(bestCombination.get(i).getRank()));
		}
	}

	@Override
	public String toString() {
		return (card1.toString() + card2.toString());
	}
}
