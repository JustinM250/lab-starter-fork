
package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardStack {
    private static final Random randomGenerator = new Random();

    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * @param generate_52, if true, fills the stack with the standard 52 cards. If false, the stack is created empty.
     */
    public CardStack(boolean generate_52){
        if (generate_52) {
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 4; j++) {
                    this.cards.add(new Card(Card.Rank.values()[i], Card.Suit.values()[j]));
                }
            }
            Collections.shuffle(cards);
        }
    }

    /**
     * @return returns cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * @return returns the number of cards in the stack.
     */
    public int getStackSize(){
        return cards.size();
    }

    /**
     * @param the card to add to the stack.
     */
    public void addToStack(Card newCard){
        cards.add(newCard);
    }

//    public void pullCardFromTopIntoStack(CardStack newStack){
//        int chosenIndex = cards.size()-1;
//        newStack.cards.add(cards.get(chosenIndex));
//        cards.remove(chosenIndex);
//    }

    /**
     * @return returns a the top card on the stack, and removes that card from the stack.
     */
    public Card pullCardFromTop(){
        int chosenIndex = cards.size()-1;
        Card returnedCard = cards.get(chosenIndex);
        cards.remove(chosenIndex);
        return returnedCard;
    }

    /**
     * @return returns a one-line string of all cards (the rank and suit), space separated.
     */
    public String stackToString(){
        String st = "";
        for (int i = 0; i < cards.size(); i++) {
            st += cards.get(i).cardToString() + " ";
        }
        return st;
    }

    /**
     * @return returns the integer value of all cards in the stack.
     */
    public int getStackValue(){
        int n = 0;
        for (int i = 0; i < cards.size(); i++) {
            n += cards.get(i).rankToInt();
        }
        return n;
    }

    // This doesn't work with my IDE issues.
//    public boolean equals(Object object) {
//        if (object == null || getClass() != object.getClass()) return false;
//        if (!super.equals(object)) return false;
//        CardStack cardStack = (CardStack) object;
//        return java.util.Objects.equals(cards, cardStack.cards);
//    }
//
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), cards);
//    }


}
// END ------------------------------------------------------------------------------- 