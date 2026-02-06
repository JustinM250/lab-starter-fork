/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.Random;
public class Card {
    private enum Rank{
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
    }
    private enum Suit{
        HEART, DIAMOND, CLUB, SPADE;
    }

    private static final Random randomGenerator = new Random();
    private static Rank getRandomRank(){
        return Rank.values()[randomGenerator.nextInt(Rank.values().length)];
    }
    private static Suit getRandomSuit(){
        return Suit.values()[randomGenerator.nextInt(Suit.values().length)];
    }
    Rank cardRank;
    Suit cardSuit;

    private int rankToInt(){
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

    private String suitToString(){
        switch(cardSuit){
            case HEART: return "♥";
            case DIAMOND: return "♦";
            case CLUB: return "♣";
            case SPADE: return "♠";
        }
        return "";
    }
    public Card(){
        this.cardRank = getRandomRank();
        this.cardSuit = getRandomSuit();
    }

    public String cardToString(){
        return rankToInt() + suitToString();
    }



}
