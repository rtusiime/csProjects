//*Kevin Tusiime
// rtusiime
//31529433
// Project2
// 2:00pm - 3:15pm
// I did not collaborate with anyone on this assignment.
import java.util.*;

public class Deck {
	
	
	
	private Card[] deck = new Card[52];
	public String suitSet[] = new String[] {"Hearts", "Clubs", "Spades", "Diamonds"};
	public String rankSet[] = new String[] {"2","3","4","5","6","7","8","9","10","J","Q","K","Ace"};
	int a = 0;//this will be used to refer to the index of each Card element in the deck array as the deck is being created
	
	public Deck() {
		for(int i=0; i<suitSet.length;i++) {
			for(int j=0; j<rankSet.length;j++) {
	this.deck[a] = new Card(suitSet[i] , rankSet[j]);
				a++;
			}
		}
}
	//getter for Deck object
	public Card[] getDeck() {
		return this.deck;
	}
	
	//setter for Deck object
	public void setDeck(Card card, int index) {//if we want to replace a card within a deck with another card
		this.deck[index]= card;
	}
	
	public Deck shuffleDeck() {
		Random randomizer = new Random();
		for(int i=0; i<this.deck.length;i++) {
			int randomIndex = randomizer.nextInt(this.deck.length);//random index of deck array whose Card element we will switch with another
			Card temporaryStorage = this.deck[randomIndex];//when we have picked a random card in the deck array, temporaryStorage stores it as we put another card at its index
			this.deck[randomIndex]= this.deck[i];//replacing random card with another card
			this.deck[i] = temporaryStorage;		
		}
		return this;//returns newly shuffled deck
	}

	public void printDeck() {
		for(Card i : this.deck) {
			System.out.print(" "+i+" ");
		}
	}
	
	
}
