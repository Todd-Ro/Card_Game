package com.company;

public enum Suit {
    DIAMONDS ("Diamonds"), CLUBS ("Clubs"), HEARTS ("Hearts"), SPADES ("Spades");

    private final String suitName;

    Suit(String thisSuitName) {
        this.suitName = thisSuitName;
    }

    public String suitName() {
        return suitName;
    }
}
