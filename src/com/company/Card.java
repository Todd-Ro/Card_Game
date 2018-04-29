package com.company;

public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rankInput, Suit suitInput) {
        this.rank = rankInput;
        this.suit = suitInput;
    }

    public Card(Suit suitInput, Rank rankInput) {
        this.rank = rankInput;
        this.suit = suitInput;
    }

    public String toString() {
        return (rank.rankName() + " of " + suit.suitName());
    }
}
