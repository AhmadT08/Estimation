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
public class Round {

    private Session session;
    private Call c;
    private Boolean times2 = false;
    private Boolean times4 = false;
    private Boolean times6 = false;
    private Boolean times8 = false;
    private int multiplier;
    private ArrayList<Integer> lastHand = new ArrayList();
    private ArrayList<Integer> cardPool = new ArrayList();
    private ArrayList<Player> players = new ArrayList();

    public Round(int multiplier, ArrayList<Player> p, Session s, int dealer) {
        session = s;

        if (multiplier == 2) {
            times2 = true;
            this.multiplier = multiplier;
        } else if (multiplier == 4) {
            times4 = true;
            this.multiplier = multiplier;
        } else if (multiplier == 6) {
            times6 = true;
            this.multiplier = multiplier;
        } else if (multiplier == 8) {
            times8 = true;
            this.multiplier = multiplier;
        } else {
            this.multiplier = multiplier;
        }

        players = p;
        deal(players);
        initiateBidding(dealer);
    }

    public Call getC() {
        return c;
    }

    public void setC(Call c) {
        this.c = c;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        if (multiplier == 2) {
            times2 = true;
            times4 = false;
            times6 = false;
            times8 = false;
            this.multiplier = multiplier;
        } else if (multiplier == 4) {
            times2 = false;
            times4 = true;
            times6 = false;
            times8 = false;
            this.multiplier = multiplier;
        } else if (multiplier == 6) {
            times2 = false;
            times4 = false;
            times6 = true;
            times8 = false;
            this.multiplier = multiplier;
        } else if (multiplier == 8) {
            times2 = false;
            times4 = false;
            times6 = false;
            times8 = true;
            this.multiplier = multiplier;
        }

    }

    public void translate(ArrayList<Integer> list) {
        Collections.sort(list);
        Collections.reverse(list);

        for (int j = 0; j < 13; j++) {
            int card = list.get(j);
            if ((card - 1) / 13 == 0) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Clubs");
                } else if (card % 13 == 12) {
                    System.out.println("King of Clubs");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Clubs");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Clubs");
                } else {
                    System.out.println(card % 13 + " of Clubs");
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
                    System.out.println(card % 13 + " of Diamonds");
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
                    System.out.println(card % 13 + " of Hearts");
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
                    System.out.println(card % 13 + " of Spades");
                }
            }

        }
    }

    public void deal(ArrayList<Player> players) {
        ArrayList<Integer> cards = new ArrayList(52);
        for (int i = 1; i < 53; i++) {
            cards.add(i);
        }
        Collections.shuffle(cards);

        for (int i = 0; i < 4; i++) {
            for (int j = 13 * i; j < 13 * (i + 1); j++) {
                players.get(i).addCardToHand(cards.get(j));
            }
        }
    }

    public void dashCall(int dealer) {
        int i = 0;
        int dashCounter = 0;

        while (i < 4) {
            if (dashCounter == 2) { //only two dash calls are allowed per round
                setMultiplier(getMultiplier()+2); //when two players bid dash call in one round, 
                break;                            //the score multiplier increases by 2
            } else {
                if (players.get(dealer).dashCall()) {
                    dashCounter++; 
                }
                if (dealer == 3) {
                    dealer = 0; //move index to next player
                } else {
                    dealer++;
                }
                i++;
            }
        }
    }

    public void initiateBidding(int dealer) {
        players.get(dealer).translate();
        dashCall(dealer);
        players.get(dealer).openBidding();

    }

}
