/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ahmad
 */
public class Round {

    private Session session;
    private Call call;
    private int Multiplier;
    private ArrayList<Integer> lastHand = new ArrayList();
    private ArrayList<Integer> cardPool = new ArrayList();
    private ArrayList<Player> players = new ArrayList();

    public Round(int multiplier, ArrayList<Player> p, Session s, int cursor) {
        session = s;
        call = new Call(true);
        this.Multiplier = multiplier;
        players = p;
        deal(players);
        setPlayerRounds();
        initiateBidding(cursor);
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call c) {
        this.call = c;
    }

    public int getMultiplier() {
        return Multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.Multiplier = multiplier;
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

            players.get(i).sortHand();
        }
    }

    public void setPlayerRounds() {
        for (Player p : players) {
            p.setRound(this);
        }
    }

    public void dashCall(int cursor) {
        int i = 0;
        int dashCounter = 0;

        while (i < 4) {
            if (dashCounter == 2) { //only two dash calls are allowed per round
                setMultiplier(getMultiplier() + 2); //when two players bid dash call in one round, 
                break;                            //the score multiplier increases by 2
            } else {
                if (players.get(cursor).dashCall()) {
                    dashCounter++;
                    System.out.println(players.get(cursor).name + " Dash call!");
                }

                cursor = nextCursor(cursor);

                i++;
            }
        }
    }

    public Call collectBids(int cursor) {
        int i = 0;
        int passCounter = 0;
        Call x = new Call(true);

        ArrayList<Call> calls = new ArrayList();
        ArrayList<Call> bidCalls = new ArrayList();

        while (i < 4) {
            if (players.get(cursor).getCall().isDashCall()) {
                passCounter++;
                cursor = nextCursor(cursor);
                i++;
            } else {
                Call c = players.get(cursor).openBidding();
                calls.add(c);

                if (c.isPassed()) {
                    passCounter++;
                } else {
                    bidCalls.add(c);
                }

                cursor = nextCursor(cursor);

                i++;
            }
        }

//        if (passCounter == 4) {
//            session.RestartRound(multiplier + 2);
//        } else {
//            for (int j = 0; j < calls.size(); j++) {
//                if (!calls.get(j).isPassed()) {
//                    x = calls.get(j);
//                }
//            }
//        }
        if (passCounter == 4) {
            session.RestartRound(Multiplier);
        } else if (bidCalls.size() > 1) {
            x = raiseFold(bidCalls);
        } else {
            x = bidCalls.get(0);
        }

        return x;
    }

    public Call raiseFold(ArrayList<Call> calls) {
        int passCounter = 0;
        Call c = null;
        Call temp;

        while (passCounter < calls.size() - 1) {
            passCounter = 0;

            for (int i = 0; i < calls.size(); i++) {
                if (calls.get(i).isPassed()) {
                    passCounter++;
                } else {
                    if (passCounter == calls.size() - 1) {
                        break;
                    } else {
                        temp = calls.get(i).getCaller().openBidding();

                        if (temp.isPassed()) {
                            passCounter++;
                        } else {
                            c = temp;
                        }
                    }
                }
            }
        }
        return c;
    }

    public int testDashCall() {
//        int cursor = 0;
        int dash = 0;
        for (int j = 0; j < 4; j++) {
            if (players.get(j).hasCall()) {
                System.out.println("\nHAS CALL");
                translate(players.get(j).getHand());
                System.out.println("");
            } else {
                if (players.get(j).dashCall()) {
                    System.out.println("\nDASHCALL!!");

                    if (players.get(j).isAvoid()) {
                        System.out.println("Avoid");
                    }

                    translate(players.get(j).getHand());
                    dash++;
                }
            }
        }
        return dash;
    }

    public void testHasCall() {
        for (int j = 0; j < 4; j++) {
            if (players.get(j).hasCall()) {
                System.out.println();

                if (players.get(j).isAvoid()) {
                    System.out.println("Avoid");
                }

                translate(players.get(j).getHand());
            }
        }
    }

    public int nextCursor(int cursor) {
        if (cursor == 3) {
            cursor = 0;
        } else {
            cursor++;
        }

        return cursor;
    }

    public int getSumOfBids() {
        int sum = 0;

        for (Player p : players) {
            sum += p.getCall().getTricks();
        }

        return sum;
    }

    public void secondRoundBids(int cursor) {
        int lastBidder;

        for (int i = 0; i < 4; i++) {
            if (players.get(i) != call.getCaller()) {
                if (!players.get(i).getCall().isDashCall()) {
                    players.get(i).setCall(new Call(true));
                }
            }
        }

        if (players.get(nextCursor(nextCursor(cursor))).getCall().isDashCall()) {
            lastBidder = nextCursor(cursor);
        } else if (players.get(nextCursor(nextCursor(cursor))).getCall().isDashCall()
                && players.get(nextCursor(cursor)).getCall().isDashCall()) {
            lastBidder = cursor;
        } else {
            lastBidder = nextCursor(nextCursor(cursor));
        }

        int i = 0;

        while (i < 3) {
            if (!players.get(cursor).getCall().isDashCall()) {
                if (cursor == lastBidder) {

                    if (getSumOfBids() > 13) {
                        players.get(cursor).secondRoundBidding(call.getTricks(), call);
                    } else if (getSumOfBids() < 13) {
                        players.get(cursor).secondRoundBidding(13 - getSumOfBids(), call);
                    } else {
                        players.get(cursor).secondRoundBidding(0, call);
                    }

                } else {
                    players.get(cursor).secondRoundBidding(call);
                }

                cursor = nextCursor(cursor);

                i++;
            } else {
                cursor = nextCursor(cursor);
                i++;
            }
        }
    }

    public String suitChecker(int card) {
        String x = "";
        if (card > 0 && card < 14) {
            x = "Clubs";
        } else if (card > 13 && card < 27) {
            x = "Diamonds";
        } else if (card > 26 && card < 40) {
            x = "Hearts";
        } else {
            x = "Spades";
        }
        return x;
    }

    public Player determineHandWinner(int card, ArrayList<Card> hand) {
        Player p = null;

        for (int i = 0; i < 4; i++) {
            if (hand.get(i).number == card) {
                p = hand.get(i).player;
            }
        }

        p.addTrick();
        System.out.println("\t" + p.getName() + " wins the hand with the " + p.translate(card) + "\n");

        return p;
    }

    public void winLoss() {
        for (Player p : players) {
            if (p.getCall().getTricks() == p.getTricks()) {
                System.out.println("\n" + p.getName() + " bid " + p.getCall().getTricks() + " and collected " + p.getTricks());
                System.out.println(p.getName() + " wins");
            } else {
                System.out.println("\n" + p.getName() + " bid " + p.getCall().getTricks() + " and collected " + p.getTricks());
                System.out.println(p.getName() + " loses");
            }
        }
    }

    public void start(int cursor) {
        System.out.println("Round has started. " + call.getCaller().getName() + " starts.");

        ArrayList<Card> hand = new ArrayList();
        ArrayList<Player> cardPlayers = new ArrayList();

        Suit trumpSuit = Suit.returnSuitByName(call.getSuit());
        Suit suit;

        for (int i = 0; i < 13; i++) {
            int first = players.get(cursor).playCard();
            hand.add(new Card(first, players.get(cursor)));
            cursor = nextCursor(cursor);
            
            suit = Suit.returnSuitByCard(first);
            
            if(call.getSuit().equals("Suns")){
                trumpSuit = suit;
            }

            for (int j = 0; j < 3; j++) {
                hand.add(new Card(players.get(cursor).playCard(suit, trumpSuit), players.get(cursor)));
                cursor = nextCursor(cursor);
            }
            Boolean hasTrumpCard = false;

            for (int j = 0; j < 4; j++) {
                if (trumpSuit.trumpCheck(hand.get(j).number)) {
                    hasTrumpCard = true;
                }
            }

            ArrayList<Integer> handCards = new ArrayList();

            for (int k = 0; k < 4; k++) {
                handCards.add(hand.get(k).number);
            }

            if (hasTrumpCard) {
                Player p = determineHandWinner(trumpSuit.compareWeight(handCards, suit), hand);
                cursor = players.indexOf(p);
            } else {
                Player p = determineHandWinner(suit.compareWeight(handCards, suit), hand);
                cursor = players.indexOf(p);
            }

            hand.clear();
        }
    }

    public void initiateBidding(int cursor) {
        dashCall(cursor);
        call = collectBids(cursor);
        call.getCaller().setCaller(true);

        for (Player p : players) {
            if (!p.isCaller()) {
                p.getCall().setSuit(call.getSuit());
                p.clearTricks();
            }
        }

        secondRoundBids(nextCursor(players.indexOf(call.getCaller())));

        if (getSumOfBids() > 13) {
            System.out.println("Total bids = " + getSumOfBids() + "\nGame state = over " + (getSumOfBids() - 13) + "\n");
        } else {
            System.out.println("Total bids = " + getSumOfBids() + "\nGame state = under " + (13 - getSumOfBids()) + "\n");
        }

        start(players.indexOf(call.getCaller()));
        winLoss();
        calculateScores();
    }

    public void calculateScores() {
        int multiplier;
        int winners = 0;
        int losers = 0;

        if (Multiplier == 0) {
            multiplier = 1;
        } else {
            multiplier = Multiplier;
        }

        for (Player p : players) {
            if (p.getCall().getTricks() == p.getTricks()) {
                winners++;
            } else {
                losers++;
            }
        }

        if (winners == 0) {
            session.RestartRound(multiplier + 2);
        } else {
            for (Player player : players) {
                int predicted = player.getCall().getTricks();
                int actual = player.getTricks();
                int risk = player.getCall().getRisk() * 10;

                if (player.getCall().isDashCall()) {
                    if (actual == predicted) {
                        if (winners == 1) {
                            player.incrementScore((33 + 10) * multiplier);
                        } else {
                            player.incrementScore(33 * multiplier);
                        }
                    } else {
                        if (losers == 1) {
                            player.decrementScore((33 + 10) * multiplier);
                        } else {
                            player.decrementScore(33 * multiplier);
                        }
                    }
                } else if (player.getCall().getTricks() == call.getTricks()) {
                    if (actual == predicted) {
                        if (actual > 7) {
                            if (winners == 1) {
                                player.incrementScore(((actual * actual) + 10 + risk) * multiplier);
                            } else {
                                player.incrementScore(((actual * actual) + risk) * multiplier);
                            }
                        } else if (actual <= 7) {
                            if (winners == 1) {
                                player.incrementScore(((23 + actual) + 10 + risk) * multiplier);
                            } else {
                                player.incrementScore((23 + actual + risk) * multiplier);
                            }
                        }
                    } else {
                        if (actual > 7) {
                            if (losers == 1) {
                                player.decrementScore((((actual * actual) / 2) + 10 + risk) * multiplier);
                            } else {
                                player.decrementScore((((actual * actual) / 2) + risk) * multiplier);
                            }
                        } else if (actual <= 7) {
                            if (losers == 1) {
                                player.decrementScore(((Math.abs(actual - predicted) + 10) + 10 + risk) * multiplier);
                            } else {
                                player.decrementScore(((Math.abs(actual - predicted) + 10) + risk) * multiplier);
                            }
                        }
                    }
                } else {
                    if (actual == predicted) {
                        if (winners == 1) {
                            player.incrementScore(((13 + actual) + 10 + risk) * multiplier);
                        } else {
                            player.incrementScore(((13 + actual) + risk) * multiplier);
                        }
                    } else {
                        if (losers == 1) {
                            player.decrementScore((Math.abs(actual - predicted) + 10 + risk) * multiplier);
                        } else {
                            player.decrementScore((Math.abs(actual - predicted) + risk) * multiplier);
                        }
                    }
                }
            }
        }
        calculatePositions();
//        HashMap<Player, Integer> positionMap = calculatePositions();
        for (int i = 1; i < 5; i++) {
            for (Player player : players) {
                if (player.getPosition() == i) {
                    System.out.println("Position " + i + ") " + player.getName() + " - " + player.getScore());
                }
            }
        }
    }

    public void calculatePositions() {
        HashMap<Player, Integer> positionMap = new HashMap();
        Player[] playerArray = new Player[4];

        for (int i = 0; i < 4; i++) {
            playerArray[i] = players.get(i);
        }

        for (int i = 0; i < 4; i++) {
            int position = 1;
            for (int j = 0; j < 4; j++) {
                if (playerArray[i].getScore() < playerArray[j].getScore()) {
                    position++;
                }
            }
            positionMap.put(playerArray[i], position);
            playerArray[i].setPosition(position);
        }
        //if two players have the same score, this makes it so that they don't have the same position
//        for (HashMap.Entry key1 : positionMap.entrySet()) {
//            for (HashMap.Entry key2 : positionMap.entrySet()) {
//                if (!key1.equals(key2)) {
//                    if (key1.getValue().equals(key2.getValue())) {
//                        key2.setValue((int) key2.getValue() - 1);
//                    }
//                }
//            }
//            Player p = (Player) key1.getKey();
//            p.setPosition((int) key1.getValue());
//        }
//        return positionMap;
    }
}
