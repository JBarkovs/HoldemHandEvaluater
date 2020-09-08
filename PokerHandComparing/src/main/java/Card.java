package main.java;

public class Card {
	
	private char rank;
	private char suit;
	
	public Card(char rank, char suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public void setCard(char rank, char suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public char getRank() {
		return rank;
	}

	public char getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		return (String.valueOf(rank) + String.valueOf(suit));
	}
}
