package main.java;

import java.util.ArrayList;
import java.util.List;

import main.manageLists.ManageLists;

public class Board {

	private List<Card> boardCardList;

	public Board() {
		boardCardList = new ArrayList<>();
	}
	
	public List<Card> getBoardCardList() {
		return boardCardList;
	}

	public void setBoardListSorted(String board) {
		boardCardList.clear();
		for (int i = 0; i < board.length(); i+=2) {
			Card card = new Card(board.charAt(i), board.charAt(i + 1));
			ManageLists.addCardToListByRank(boardCardList, card);
		}		
	}
}
