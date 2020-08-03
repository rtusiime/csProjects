//*Kevin Tusiime
// rtusiime
//31529433
// Project2
// 2:00pm - 3:15pm
// I did not collaborate with anyone on this assignment.

import java.util.Scanner;

public class Hand {
	private Card repeatingCard1 = null;//will be used in the checkType method in order to store the Card that Is part of a pair/ triple or four of a kind
	private Card repeatingCard2 = null;//will be used in the checkType method in order to store the Card that Is part of a pair/ triple or four of a kind
	private Card nonRepeatingCard = null;//will be used in the checkType method in order to store the Card that isn't part of a pair
	private Card nonRepeatingCard2 = null;//will be used in the checkType method in order to store the Card that isn't part of a pair
	private Card nonRepeatingCard3 = null;//will be used in the checkType method in order to store the Card that isn't part of a pair
	private Card[] hand = new Card[5];
	int a = 0;
	int randomIndex=0;
	
	//Hand constructor
	public Hand(Deck deckPickedFrom) {
		for(int i=0;i<=4;i++) {
			while(deckPickedFrom.getDeck()[a]==null) {
				a++;
			}
			this.hand[i]= deckPickedFrom.getDeck()[a];
			deckPickedFrom.setDeck(null, a);//this is the real-world equivalent of "removing" the card at position a from the deck
		}
	}
	
	//returns a particular Card in a hand array at position i
	public Card getCardInHand(int i) {
		return this.hand[i];
	}
	
	
	public void printHand() {
		for(Card i : this.hand) {
			System.out.println(i);
		}
	}
	
	
	
	//sorting hand from smallest to largest:
	public Hand sortHand() {
		Card c;//will be used to temporarily store the value of a card during the swap
		for(int i=0; i<4;i++) {	
		for(int j=0; j<(4-i); j++) {
				if(rankValue(this.hand[j].getRank())>rankValue(this.hand[j+1].getRank())){//this will swap two Card if Card at index j is bigger than Card at index j+1
					c = this.hand[j+1];
					this.hand[j+1]= this.hand[j];
					this.hand[j] = c;
				}
			}
		}
		if(this.hand[0].getRank().equals("2")&&this.hand[1].getRank().equals("3")&&this.hand[2].getRank().equals("4")&&this.hand[3].getRank().equals("5")&&this.hand[4].getRank().equals("Ace")) {
			this.hand[4].setRank("A");
			this.sortHand();
		}
		return this;
	}
	
	
	
	
	//Checking what type of hand it is
	public String checkHandType() {
		this.sortHand();
		String handType= "High Card";//by default, it will be a high card unless it proves to be any of the other types of cards
		int sR = 0;//this is the index of the card element in the hand array whose suit we will compare every other card to
		int i =0;
		int c1 = rankValue(this.hand[i].getRank());//Rank of First Card in a given Hand
		int c2 = rankValue(this.hand[i+1].getRank());//Rank of Second Card in a given Hand
		int c3 = rankValue(this.hand[i+2].getRank());//Rank of Third Card in a given Hand
		int c4 = rankValue(this.hand[i+3].getRank());//Rank of Fourth Card in a given Hand
		int c5 = rankValue(this.hand[i+4].getRank());//Rank of Fifth Card in a given Hand
		
			//same suit: Royal Flush
		while(i<5&&this.hand[i].getSuit().equals(this.hand[sR].getSuit())) {//this simply means that the suit of all cards is equal
			if(this.hand[i].getRank().equals("10")||this.hand[i].getRank().equals("J")||this.hand[i].getRank().equals("K")||this.hand[i].getRank().equals("Q")||this.hand[i].getRank().equals("Ace")) {
					if(i==4) {
						handType = "Royal Flush";
					}
					i++;
			}
			else {
				break;
			}
		}
		// same suit: Straight Flush
		while(i<4&&this.hand[i].getSuit().equals(this.hand[sR].getSuit())) {
			if(rankValue(this.hand[i+1].getRank())-rankValue(this.hand[i].getRank())==1) {
					if(i==3) {
						handType = "Straight Flush";
					}
					i++;
			}
			else {
				break;
			}
		}
		
		//same suit: Flush
		while(i<4&&this.hand[i].getSuit().equals(this.hand[sR].getSuit())) {
			if(rankValue(this.hand[i+1].getRank())-rankValue(this.hand[i].getRank())!=1) {
					if(i==3) {
						handType = "Flush";
					}
					i++;
			}
			else {
				break;
			}
		}
		//Four of a kind
		for(int j=0;j<=1;j++) {
		if(rankValue(this.hand[j].getRank())==rankValue(this.hand[j+1].getRank())&&rankValue(this.hand[j].getRank())==rankValue(this.hand[j+2].getRank())&&rankValue(this.hand[j].getRank())==rankValue(this.hand[j+3].getRank())) {
			this.repeatingCard1 = this.hand[j];
			handType = "Four of a kind";
			break;
		}
		}
		
		//Full house
		if(c1==c2&&c1!=c3&&c3==c4&&c3==c5) {
			this.repeatingCard1 = this.hand[0];
			this.repeatingCard2 = this.hand[2];
			handType = "Full House";	
			}
		else if(c1==c2&&c1==c3&&c1!=c4&&c4==c5) {
			this.repeatingCard1 = this.hand[3];
			this.repeatingCard2 = this.hand[0];
			handType = "Full House";
		}
	
		//Straight
		while(i<4) {
			if(rankValue(this.hand[i+1].getRank())-rankValue(this.hand[i].getRank())==1) {
					if(i==3) {
						handType = "Straight";
					}
					i++;
			}
			else {
				break;
			}
		}
		
		//three of a kind
		if(c1==c2&&c1==c3&&c1!=c4&&c4!=c5) {
			this.repeatingCard1 = this.hand[0];
			handType = "Three of a kind";
		}
		else if(c1!=c2&&c2==c3&c2==c4&&c2!=c5) {
			this.repeatingCard1 = this.hand[1];
			handType = "Three of a kind";
		}
		else if(c1!=c2&&c2!=c3&&c3==c4&&c3==c5) {
			this.repeatingCard1 = this.hand[2];
			handType = "Three of a kind";
		}
		
		//Two pair
		if(c1==c2&&c1!=c3&&c3==c4&&c3!=c5) {
			this.repeatingCard1 = this.hand[0];
			this.repeatingCard2 = this.hand[2];
			this.nonRepeatingCard = this.hand[4];
			handType = "Two Pair";	
		}
		else if(c1!=c2&&c2==c3&c2!=c4&&c4==c5) {
			this.repeatingCard1 = this.hand[1];
			this.repeatingCard2 = this.hand[3];
			this.nonRepeatingCard = this.hand[0];
			handType = "Two Pair";
		}
		else if(c1==c2&&c1!=c3&&c3!=c4&&c4==c5) {
			this.repeatingCard1 = this.hand[0];
			this.repeatingCard2 = this.hand[3];
			this.nonRepeatingCard = this.hand[2];
			handType = "Two Pair";
		}
		
		//Pair
		if(c1==c2&&c1!=c3&&c3!=c4&&c4!=c5) {
			this.repeatingCard1 = this.hand[0];
			this.nonRepeatingCard = this.hand[2];
			this.nonRepeatingCard2 = this.hand[3];
			this.nonRepeatingCard3 = this.hand[4];
			handType = "Pair";	
		}
		else if(c1!=c2&&c2==c3&c2!=c4&&c4!=c5) {
			this.repeatingCard1 = this.hand[1];
			this.nonRepeatingCard = this.hand[0];
			this.nonRepeatingCard2 = this.hand[3];
			this.nonRepeatingCard3 = this.hand[4];
			handType = "Pair";
		}
		else if(c1!=c2&&c2!=c3&&c3==c4&&c3!=c5) {
			repeatingCard1 = this.hand[2];
			this.nonRepeatingCard = this.hand[0];
			this.nonRepeatingCard2 = this.hand[1];
			this.nonRepeatingCard3 = this.hand[4];
			handType = "Pair";
		}
		else if(c1!=c2&&c2!=c3&&c3!=c4&&c4==c5) {
			repeatingCard1 = this.hand[3];
			this.nonRepeatingCard = this.hand[0];
			this.nonRepeatingCard2 = this.hand[1];
			this.nonRepeatingCard3 = this.hand[2];
			handType = "Pair";
		}
		
		return handType;
	}
	
	
	
	//method for comparing hands
	public void handCompare(Hand secondHand) {
		if(handValue(this.checkHandType())<handValue(secondHand.checkHandType())) {
			System.out.println("\nHand_2 wins");
		}
		else if(handValue(this.checkHandType())>handValue(secondHand.checkHandType())) {
			System.out.println("\nHand_1 wins");
		}
		//special case when two hands are equal
		else {
			//for the case of a Royal flush, there is no tie breaker
			if(this.checkHandType().equals("Royal Flush")) {
				System.out.println("It's a tie!");
			}
			//for the case of a Straight flush, the hand with the highest rank Card wins
			else if(this.checkHandType().equals("Straight Flush")) {
				for(int i=4; i>=0; i--) {//this will look for the card with the highest rank among both hands. If the maximum Card of both hands is equal, the method will check the second highest card of both hands, and so on, till the very last card
					if(rankValue(this.hand[i].getRank())>rankValue(secondHand.hand[i].getRank())) {
				
					System.out.println("Hand_1 wins");
					break;
				}
				else if(rankValue(this.hand[4].getRank())<rankValue(secondHand.hand[4].getRank())) {
					System.out.println("Hand_2 wins");
					break;
				}
				else if(i==0) {//when both Straight Flushes have the same rank Cards even to the lowest card, it's a tie
					System.out.print("It's a tie");
				}
				}
			}
			//for the case of a Four of a kind, the hand with the highest four wins
			else if(this.checkHandType().equals("Four of a kind")) {
				if(rankValue(this.repeatingCard1.getRank())>rankValue(secondHand.repeatingCard1.getRank())) {
					System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.repeatingCard1.getRank())<rankValue(secondHand.repeatingCard1.getRank())) {
					System.out.println("Hand_2 wins");
				}
				//two hands can never have a four of a kind	with the same rank	
			}
			//for the case of a Full house, the hand with the highest triple wins.
			else if(this.checkHandType().equals("Full House")) {
				if(rankValue(this.repeatingCard2.getRank())>rankValue(secondHand.repeatingCard2.getRank())) {
					System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.repeatingCard2.getRank())<rankValue(secondHand.repeatingCard2.getRank())) {
					System.out.println("Hand_2 wins");
				}
				//two hands can never have a triple with the same rank		
			}
			
			//for the case of a Flush, the hand with the highest rank card wins
			else if(this.checkHandType().equals("Flush")) {
				if(rankValue(this.hand[4].getRank())>rankValue(secondHand.hand[4].getRank())) {
					System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.hand[4].getRank())<rankValue(secondHand.hand[4].getRank())) {
					System.out.println("Hand_1 wins");
				}
				//two hands can never have a max card with the same suit and rank because a deck only has one of each
			}
			
			//for the case of a Straight, the hand with the highest rank card wins
			else if(this.checkHandType().equals("Straight")) {
				for(int i=4; i>=0;i--) {
				if(rankValue(this.hand[i].getRank())>rankValue(secondHand.hand[i].getRank())) {
					System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.hand[i].getRank())<rankValue(secondHand.hand[i].getRank())) {
					System.out.println("Hand_1 wins");
				}
			}
			}
			
			//for the case of Three of a kind the hand with the highest triple wins
			else if(this.checkHandType().equals("Three of a kind")) {
				if(rankValue(this.repeatingCard1.getRank())>rankValue(secondHand.repeatingCard1.getRank())) {
					System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.repeatingCard1.getRank())<rankValue(secondHand.repeatingCard1.getRank())) {
					System.out.println("Hand_1 wins");
				}
				//two hands can never have a identical triples
			}
			
			//for the case of Two pair, the hand with the highest ranked pair wins.
			else if(this.checkHandType().equals("Two Pair")){
				if(rankValue(this.repeatingCard2.getRank())>rankValue(secondHand.repeatingCard2.getRank())) {
			
				System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.repeatingCard2.getRank())<rankValue(secondHand.repeatingCard2.getRank())) {
				System.out.println("Hand_2 wins");
				}
				else {//in the event that the highest pair is equal for both, check the lower pair
					if(rankValue(this.repeatingCard1.getRank())>rankValue(secondHand.repeatingCard1.getRank())) {
					System.out.println("Hand_1 wins");
					}
					else if(rankValue(this.repeatingCard1.getRank())<rankValue(secondHand.repeatingCard1.getRank())) {
					System.out.println("Hand_2 wins");
					}
					else {//in the event that even the lower pair is equal for both, check third card
						if(rankValue(this.nonRepeatingCard.getRank())>rankValue(secondHand.nonRepeatingCard.getRank())){
						System.out.print("Hand_1 wins");
						}
						else if(rankValue(this.nonRepeatingCard.getRank())<rankValue(secondHand.nonRepeatingCard.getRank())){
						System.out.println("Hand_2 wins");
						}
						else {
						System.out.println("it's a tie");
						}
					}
			}
		}
			
			//for the case of a pair, the hand with the highest pair wins.
			else if(this.checkHandType().equals("Pair")){
				if(rankValue(this.repeatingCard1.getRank())>rankValue(secondHand.repeatingCard1.getRank())) {
						System.out.println("Hand_1 wins");
				}
				else if(rankValue(this.repeatingCard1.getRank())<rankValue(secondHand.repeatingCard1.getRank())) {
				System.out.println("Hand_2 wins");
				}
				else {//in the event that the pair is equal for both, check the highest single card
					if(rankValue(this.nonRepeatingCard3.getRank())>rankValue(secondHand.nonRepeatingCard3.getRank())){
						System.out.print("Hand_1 wins");
					}
					else if(rankValue(this.nonRepeatingCard3.getRank())<rankValue(secondHand.nonRepeatingCard3.getRank())){
						System.out.println("Hand_2 wins");
					}
					else {
						if(rankValue(this.nonRepeatingCard2.getRank())>rankValue(secondHand.nonRepeatingCard2.getRank())) {
							System.out.println("Hand_1 wins");
						}
						else if(rankValue(this.nonRepeatingCard2.getRank())<rankValue(secondHand.nonRepeatingCard2.getRank())) {
							System.out.println("Hand_2 wins");
						}
						else {
							if(rankValue(this.nonRepeatingCard.getRank())>rankValue(secondHand.nonRepeatingCard.getRank())) {
								System.out.println("Hand_1 wins");
							}
							else if(rankValue(this.nonRepeatingCard.getRank())<rankValue(secondHand.nonRepeatingCard.getRank())) {
								System.out.println("Hand_2 wins");
							}
							else {
								System.out.println("It's a tie");
							}
						}
					}
					
				}
			}
			
			//for the case of no other case, the card with the highest rank Card wins.
			else if(this.checkHandType().equals("High Card")){
				for(int a=4; a>=0; a--) {//if the highest Card are the same, the loop will check the second highest Card and so on..
					if(rankValue(this.getCardInHand(a).getRank())<rankValue(secondHand.getCardInHand(a).getRank())) {
						System.out.println("Hand_2 wins");
						break;
					}
					else if(rankValue(this.getCardInHand(a).getRank())>rankValue(secondHand.getCardInHand(a).getRank())) {
						System.out.println("Hand_1 wins");
						break;
					}
				}
			}
			
		}
		
	}
	//method for assigning a value to a type of hand
	public int handValue(String s) {
		int value = 0;
		switch(s) {
		
		case "Royal Flush":
			value = 10;
			break;
		case "Straight Flush":
			value = 9;
			break;
		case "Four of a kind":
			value=8;
			break;
		case "Full House":
			value=7;
			break;
		case "Flush":
			value=6;
			break;
		case "Straight":
			value=5;
			break;
		case "Three of a kind":
			value=4;
			break;
		case "Two Pair":
			value=3;
			break;
		case "Pair":
			value=2;
			break;
		case "High Card":
			value=1;
			break;
		}
		return value;
		}
	
	
	
	
	//method for returning the value of the Rank of a card
	public int rankValue(String s) {
		int value = 0;
		switch(s) {
		case "A":
			value = 1;
			break;
		case "2":
			value = 2;
			break;
		case "3":
			value = 3;
			break;
		case "4":
			value=4;
			break;
		case "5":
			value=5;
			break;
		case "6":
			value=6;
			break;
		case "7":
			value=7;
			break;
		case "8":
			value=8;
			break;
		case "9":
			value=9;
			break;
		case "10":
			value=10;
			break;
		case "J":
			value=11;
			break;
		case "Q":
			value=12;
			break;
		case "K":
			value=13;
			break;
		case "Ace":
			value=14;
			break;
		}
		return value;
		}
	//createHand constructor that prompts user to create 2 hands by inserting a 10ranks and suits alternatively
	public Hand(String s) {
		int y= 0;
				for(int i=0; i<=4; i++) {
		this.hand[i]= new Card(convertFormat(String.valueOf(s.charAt(y+1))), convertFormat(String.valueOf(s.charAt(y))));
				y= y+2;
		}
	}
	//creating a method that equates user Input to already known cases of ranks and suits
	public String convertFormat(String c) {
			String value = null;
			switch(c) {
			case "A":
				value = "Ace";
				break;
			case "a":
				value = "Ace";
				break;
			case "2":
				value = "2";
				break;
			case "3":
				value = "3";
				break;
			case "4":
				value="4";
				break;
			case "5":
				value="5";
				break;
			case "6":
				value="6";
				break;
			case "7":
				value="7";
				break;
			case "8":
				value="8";
				break;
			case "9":
				value="9";
				break;
			case "T":
				value="10";
				break;
			case "t":
				value="10";
				break;
			case "J":
				value="J";
				break;
			case "j":
				value="J";
				break;
			case "Q":
				value="Q";
				break;
			case "q":
				value="Q";
				break;
			case "K":
				value="K";
				break;
			case "k":
				value="K";
				break;
			case "H":
				value = "Hearts";
				break;
			case "h":
				value = "Hearts";
				break;
			case "C":
				value = "Clubs";
				break;
			case "c":
				value = "Clubs";
				break;
			case "D":
				value = "Diamonds";
				break;
			case "d":
				value = "Diamonds";
				break;
			case "S":
				value = "Spades";
				break;
			case "s":
				value = "Spades";
				break;
			}
			return value;
			}

	}


