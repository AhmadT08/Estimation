/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Ahmad
 */
public class Player {

    protected String name;
    private int score;
    private Call c;
    private Boolean caller;
    private int position;
    private int tricks;
    private Round round;
    private Session session;
    private ArrayList<Integer> hand = new ArrayList();

    public Player() {
        score = 0;
        c = new Call(true);
    }

    public String getName() {
        return name;
    }

    public Call getCall() {
        return c;
    }

    public void setCall(Call c) {
        this.c = c;
    }

    public Boolean isCaller() {
        return caller;
    }

    public void setCaller(Boolean caller) {
        this.caller = caller;
    }

    public int getTricks() {
        return tricks;
    }

    public void addTrick() {
        tricks++;
    }

    public int getScore() {
        return score;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void incrementScore(int score) {
        this.score += score;
    }

    public void decrementScore(int score) {
        this.score -= score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addCardToHand(int c) {
        this.hand.add(c);
    }

    public void removeCardFromHand(int c) {

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) == c) {
                hand.remove(i);
            }
        }
    }

    public void clearHand() {
        hand = new ArrayList();
    }

    public ArrayList<Integer> getHand() {
        return hand;
    }

    public void translate() {
        Collections.sort(hand);
        Collections.reverse(hand);

        for (int j = 0; j < 13; j++) {
            int card = hand.get(j); //Get next card
            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                if (card % 13 == 0) {
                    System.out.println("Ace of Clubs"); //The ace is the 13th card in the suit
                } else if (card % 13 == 12) {
                    System.out.println("King of Clubs"); //The king is the 12th card in the suit
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Clubs"); //The queen is the 11th card in the suit
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Clubs"); //The jack is the 10th card in the suit 
                } else {
                    System.out.println(((card % 13) + 1) + " of Clubs");
                }
            }
            if ((card - 1) / 13 == 1) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Diamonds");
                } else if (card % 13 == 12) {
                    System.out.println("King of Diamonds");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Diamonds");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Diamonds");
                } else {
                    System.out.println(((card % 13) + 1) + " of Diamonds");
                }
            }
            if ((card - 1) / 13 == 2) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Hearts");
                } else if (card % 13 == 12) {
                    System.out.println("King of Hearts");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Hearts");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Hearts");
                } else {
                    System.out.println(((card % 13) + 1) + " of Hearts");
                }
            }
            if ((card - 1) / 13 == 3) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Spades");
                } else if (card % 13 == 12) {
                    System.out.println("King of Spades");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Spades");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Spades");
                } else {
                    System.out.println(((card % 13) + 1) + " of Spades");
                }
            }

        }
    }

    public void sortHand() {
        Collections.sort(hand);
    }

    public Boolean dashCall() {
        return false;
    }

    public Call openBidding() {
        Call c = new Call(true);
        return c;
    }

    public Boolean isAvoid() {
        Boolean b = false;

        ArrayList<Integer> h = getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
            }
        }

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);
            if (temp.isEmpty()) {
                b = true;
            }
        }

        return b;
    }

    public Boolean fullHouse(ArrayList<Integer> c) {
        Boolean b = false;

        if (c.size() > 3) {
            if (c.get(c.size() - 1) % 13 == 0) {
                if (c.get(c.size() - 4) % 13 == 10) {
                    b = true;
                }
            }
        }

        return b;
    }

    public Boolean has3Masters(ArrayList<Integer> c) {
        Boolean b = false;

        if (c.size() > 2) {
            if (c.get(c.size() - 1) % 13 == 0) {
                if (c.get(c.size() - 3) % 13 >= 10) {
                    b = true;
                }
            }
        }

        if (c.size() > 2) {
            if (c.get(c.size() - 1) % 13 == 12) {
                if (c.get(c.size() - 3) % 13 >= 10) {
                    b = true;
                }
            }
        }

        return b;
    }

    public Boolean hasCall() {
        Boolean b = false;

        int aceCounter = 0;

        ArrayList<Integer> h = getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if (card % 13 == 0) {
                aceCounter++;
            }

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
            }
        }

        for (int i = 0; i < 4; i++) {
            if (fullHouse(handSuits.get(i))) {
                b = true;
            }

            if (has3Masters(handSuits.get(i))) {
                if (aceCounter > 1) {
                    b = true;
                }
            }

        }

        return b;
    }

    public Call secondRoundBidding(Call call) {
        //initiates second round of bidding for when the player is not on the caller's left
    
        Call c1 = new Call(true);
        return c1;
    }

    public Call secondRoundBidding(int limit, Call call) {
        //initiates second round of bidding for when the player is on the caller's left
        //this method takes into account that the total sum of all bids cannot equal 13

        Call c1 = new Call(true);
        return c1;
    }

}
