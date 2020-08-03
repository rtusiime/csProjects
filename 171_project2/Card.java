//*Kevin Tusiime
// rtusiime
//31529433
// Project2
// 2:00pm - 3:15pm
// I did not collaborate with anyone on this assignment.


public class Card {
	
	private String suit;
	private String rank;
	
	public Card(String suit,String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	public String toString() {
		 return ("(" + rank + " of "+ suit+ ")" );
	}
	public String getSuit() {
		return this.suit;
	}
	public String getRank() {
		return this.rank;
	}
	public void setRank(String s) {
		this.rank = s;
	}
}
