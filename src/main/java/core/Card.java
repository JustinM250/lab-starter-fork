
package core;

import java.util.Random;
public class Card {
    static public enum Rank{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
    }
    static public enum Suit{
        HEART, DIAMOND, CLUB, SPADE;
    }

    //    private static final Random randomGenerator = new Random();
//    private static Rank getRandomRank(){
//        return Rank.values()[randomGenerator.nextInt(Rank.values().length)];
//    }
//    private static Suit getRandomSuit(){
//        return Suit.values()[randomGenerator.nextInt(Suit.values().length)];
//    }
    Rank cardRank;
    Suit cardSuit;

    /**
     * @return returns the integer equivalent of the card's rank. ACE = 1, TWO-TEN are self explanatory. JACK = 11, QUEEN = 12, KING = 13.
     */
    public int rankToInt(){
        switch(cardRank){
            case ACE: return 1;
            case TWO: return 2;
            case THREE: return 3;
            case FOUR: return 4;
            case FIVE: return 5;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case JACK: return 11;
            case QUEEN: return 12;
            case KING: return 13;
        }
        return -1;
    }

    /**
     * @return returns the string equivalent of the suit.
     */
    public String suitToString(){
        switch(cardSuit){
            case HEART: return "♥";
            case DIAMOND: return "♦";
            case CLUB: return "♣";
            case SPADE: return "♠";
        }
        return "";
    }
//    public Card(){
//        this.cardRank = getRandomRank();
//        this.cardSuit = getRandomSuit();
//    }

    public Card(Rank r, Suit s){
        this.cardRank = r;
        this.cardSuit = s;
    }

    /**
     * @return returns the rank and suit, in a string
     */
    public String cardToString(){
        return rankToInt() + suitToString();
    }

    // This doesn't work with my IDE issues.
//    public boolean equals(Object object) {
//        if (object == null || getClass() != object.getClass()) return false;
//        if (!super.equals(object)) return false;
//        Card card = (Card) object;
//        return cardRank == card.cardRank && cardSuit == card.cardSuit;
//    }
//
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), cardRank, cardSuit);
//    }
}
// END ------------------------------------------------------------------------------- 