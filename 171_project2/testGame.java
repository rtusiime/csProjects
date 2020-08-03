import java.util.Scanner;

//*Kevin Tusiime
// rtusiime
//31529433
// Project2
// 2:00pm - 3:15pm
// I did not collaborate with anyone on this assignment.

public class testGame {
	//Main method
		
		public static void main(String[] args) {
			/*//create deck of cards
			Deck deckOfCards = new Deck();
			
			//print deck
			System.out.print("deckofCards: ");
			deckOfCards.printDeck();
			
			System.out.println("\nshuffle deck and print it again");
			
			//shuffle deck and print it again
			deckOfCards.shuffleDeck().printDeck();
			
			System.out.println("\ndraw two hands");
			
			
			//draw 2 hands
			System.out.println("\nfirst hand: ");
			Hand hand1 = new Hand(deckOfCards);
			hand1.printHand();
			System.out.print("\nDeck afterwards: ");
			deckOfCards.printDeck();
			
			System.out.println("\nsecond hand hand: ");
			Hand hand2 = new Hand(deckOfCards);
			hand2.printHand();
			System.out.print("\nDeck afterwards: ");
			deckOfCards.printDeck();
			
			//demonstrating sort Hand method
			//System.out.println(" ");
			//System.out.println("\nSorting hand1: ");
			//hand1.sortHand().printHand();
			
			//demonstrating checkType method
			System.out.println("\nHand1 is a:  ");
			System.out.println(hand1.checkHandType());
			System.out.println(" ");
			hand1.printHand();
			
			System.out.println(" ");
			System.out.println("\n hand2 is a: ");
			
			System.out.println(hand2.checkHandType());
			System.out.println(" ");
			hand2.printHand();
			System.out.println(" ");
			hand1.handCompare(hand2);
			
			
			}
			*/
			System.out.println("Please make your own hand of cards by typing a string. The string will be of the form RSRSRSRSRS where R can be any one of the\r\n" + 
					"set (2,3,4,5,6,7,8,9,T,t,J,j,Q,q,K,k,A,a) and S can be any one of the set (C,c,H,h,S,s,D,d).\r\n");
			
			Scanner scanner = new Scanner(System.in);
			String s = scanner.next();
			Hand hand1 = new Hand(s);
		
			
			System.out.println("Please make a second hand of cards by typing a string. The string will be of the form RSRSRSRSRS where R can be any one of the\r\n" + 
					"set (2,3,4,5,6,7,8,9,T,t,J,j,Q,q,K,k,A,a) and S can be any one of the set (C,c,H,h,S,s,D,d).\r\n");
			String x = scanner.next();
			Hand hand2 = new Hand(x);
			
			
			System.out.println("\nHand1 is a:  ");
			System.out.println(hand1.checkHandType());
			System.out.println(" ");
			hand1.printHand();
			
			System.out.println(" ");
			System.out.println("\n hand2 is a: ");
			
			System.out.println(hand2.checkHandType());
			System.out.println(" ");
			hand2.printHand();
			System.out.println(" ");
			hand1.handCompare(hand2);
			
		}
		
		
}
