package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Deck theDeck = new Deck();
        Hand myHand = new Hand(theDeck, 5);
        System.out.println(myHand.toString());
        boolean done = false;
        System.out.println("Do you wish to discard cards? Type 1 for yes, 0 for no");
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        if (selection == 0) {
            done = true;
            end(myHand);
        }
        else if (selection == 1) {
            int removedCards = 0;
            while (done == false) {
                System.out.println("Here are your cards by index number: ");
                System.out.println(myHand.orderedToString());
                System.out.println("Enter the index number of a card to remove, or enter 0 to proceed with this hand");
                int selection2 = scanner.nextInt();

                if (selection2 == 0) {
                    done = true;
                    if (removedCards == 0) {
                        end(myHand);
                    }
                    else {
                        myHand.draw(removedCards);
                        end(myHand);
                    }
                }
                else {
                    Card goneCard = myHand.getSecondPile().remove(selection2 - 1);
                    myHand.getCardSet().remove(goneCard);
                    removedCards++;
                }
            }
        }


    }

    public static void end(Hand hand) {
        System.out.println("Your final hand is: ");
        System.out.println(hand.toString());
    }
}
