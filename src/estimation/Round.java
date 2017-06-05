/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ahmad
 */
public class Round {

    private final Session session;
    private Call call;
    private int Multiplier;
    private int dealer;
    private final int roundNumber;
    private ArrayList<Integer> lastHand = new ArrayList();
    private ArrayList<Call> roundCalls = new ArrayList();
    private ArrayList<Integer> cardPool = new ArrayList();
    private ArrayList<Player> players = new ArrayList();
    private Boolean restart = false;

    public Round(int multiplier, ArrayList<Player> p, Session sesh, int rn) {
        session = sesh;
        call = new Call(true);
        this.Multiplier = multiplier;
        players = p;
        roundNumber = rn;
    }

    public void startRound(int cursor) {
        if (restart) {
            setPlayerRounds();
            startBidding(cursor);
            startPlay(players.indexOf(call.getCaller()));
            winLoss();
            calculateScores();
            if (!restart) {
                session.nextRound(this);
            }
        } else {
            clearPlayerState();
            dealer = cursor;
            deal(players);
            setPlayerRounds();
            startBidding(cursor);
            startPlay(players.indexOf(call.getCaller()));
            winLoss();
            calculateScores();
            if (!restart) {
                session.nextRound(this);
            }
        }
    }

    public void clearPlayerState() {
        roundCalls = new ArrayList();
        call = new Call(true);
        for (Player p : players) {
            p.clearHand();
            p.clearTricks();
        }
    }

    public ArrayList<Integer> getLastHand() {
        return lastHand;
    }

    public ArrayList<Integer> getCardPool() {
        return cardPool;
    }

    public void addCardToCardPool(int card) {
        cardPool.add(card);
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

    public int getRoundNumber() {
        return roundNumber;
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
                    roundCalls.add(players.get(cursor).getCall());
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

        ArrayList<Call> bidCalls = new ArrayList();

        while (i < 4) {
            if (players.get(cursor).getCall().isDashCall()) {
                passCounter++;
                cursor = nextCursor(cursor);
                i++;
            } else {
                Call c = players.get(cursor).openBidding();

                roundCalls.add(c);

                if (c.isPassed()) {
                    passCounter++;
                } else {
                    bidCalls.add(c);
                }

                cursor = nextCursor(cursor);

                i++;
            }
        }

        if (passCounter == 4) {
            session.restartRound(Multiplier);
            restart = true;
        } else if (bidCalls.size() > 1) {
            x = raiseFold(bidCalls);
        } else {
            x = bidCalls.get(0);
        }

        if (roundNumber >= 14) {
            for (int j = 0; j < 4; j++) {
                if (roundCalls.get(j).getCaller().equals(x.getCaller())) {
                    roundCalls.set(j, x);
                }
            }
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

    public int nextCursor(int cursor) {
        if (cursor == 3) {
            cursor = 0;
        } else {
            cursor++;
        }

        return cursor;
    }

    public int previousCursor(int cursor) {
        if (cursor == 0) {
            cursor = 3;
        } else {
            cursor--;
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

        if (roundNumber < 14) {
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
        } else if (roundNumber >= 14) {

            lastBidder = previousCursor(cursor);
            Suit[] fastBiddingSuits = {new Suns(), new Spades(), new Hearts(), new Diamonds(), new Clubs()};
            Suit currentSuit = fastBiddingSuits[roundNumber - 14];

            System.out.println("Round " + roundNumber + ": Trump Suit [" + currentSuit.getName() + "]");

            int i = 0;

            while (i < 4) {
                if (cursor == lastBidder) {
                    if (getSumOfBids() > 13) {
                        players.get(cursor).fastBidding(currentSuit, 13);
                    } else if (getSumOfBids() < 13) {
                        players.get(cursor).fastBidding(currentSuit, 13 - getSumOfBids());
                    } else {
                        players.get(cursor).fastBidding(currentSuit, 0);
                    }
                } else {
                    players.get(cursor).fastBidding(currentSuit);
                }

                roundCalls.add(players.get(cursor).getCall());
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

    public void startPlay(int cursor) {
        System.out.println("Round has started. Score Multiplier = [x" + Multiplier + "]\n" + call.getCaller().getName() + " starts.");

        ArrayList<Card> hand = new ArrayList();
//        ArrayList<Player> cardPlayers = new ArrayList();

        Suit trumpSuit = Suit.returnSuitByName(call.getSuit());
        Suit suit;

        for (int i = 0; i < 13; i++) {
            lastHand.clear();
            int first = players.get(cursor).playCard();
            lastHand.add(first);
            hand.add(new Card(first, players.get(cursor)));
            cursor = nextCursor(cursor);

            suit = Suit.returnSuitByCard(first);

            if (call.getSuit().equals("Suns")) {
                trumpSuit = suit;
            }

            for (int j = 0; j < 3; j++) {
                int card = players.get(cursor).playCard(suit, trumpSuit);
                hand.add(new Card(card, players.get(cursor)));
                lastHand.add(card);
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
                cardPool.add(hand.get(k).number);
            }

            if (hasTrumpCard) {
                Player p = determineHandWinner(trumpSuit.compareWeight(handCards), hand);
                cursor = players.indexOf(p);
            } else {
                Player p = determineHandWinner(suit.compareWeight(handCards), hand);
                cursor = players.indexOf(p);
            }

            hand.clear();
        }
    }

    public void startBidding(int cursor) {
        if (roundNumber < 14) {
            dashCall(cursor);
            call = collectBids(cursor);
            if (!restart) {

                try {
                    call.getCaller().setCaller(true);
                } catch (Exception E) {
                    startRound(dealer);
                }

                for (Player p : players) {
                    if (!p.isCaller()) {
                        p.getCall().setSuit(call.getSuit());
                        p.clearTricks();
                    }
                }

                secondRoundBids(nextCursor(players.indexOf(call.getCaller())));
            }
        } else if (roundNumber >= 14) {
            secondRoundBids(cursor);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (roundCalls.get(i).getTricks() > roundCalls.get(j).getTricks()) {
                        if (i > j) {
                            Call temp = roundCalls.get(j);
                            roundCalls.set(j, roundCalls.get(i));
                            roundCalls.set(i, temp);
                        }
                    }
                }
            }

            if (roundCalls.get(0).getTricks() == roundCalls.get(2).getTricks()) {
                Multiplier += 2;
            }

            call = roundCalls.get(0);
            call.getCaller().setCaller(true);
        }

        if (getSumOfBids() > 13) {
            System.out.println("Total bids = " + getSumOfBids() + "\nGame state = over " + (getSumOfBids() - 13) + "\n");
            setPlayerState("Over");
        } else {
            System.out.println("Total bids = " + getSumOfBids() + "\nGame state = under " + (13 - getSumOfBids()) + "\n");
            setPlayerState("Under");
        }
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
            session.restartRound(multiplier + 2);
            restart = true;
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
                        if (predicted > 7) {
                            if (losers == 1) {
                                player.decrementScore((((predicted * predicted) / 2) + 10 + risk) * multiplier);
                            } else {
                                player.decrementScore((((predicted * predicted) / 2) + risk) * multiplier);
                            }
                        } else if (predicted <= 7) {
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
            calculatePositions();

            for (int i = 0; i < 4; i++) {
                for (Player player : players) {
                    if (player.getPosition() == i) {
                        System.out.println("Position " + (i + 1) + ") " + player.getName() + " - " + player.getScore());
                    }
                }
            }
        }

    }

    public void calculatePositions() {
        //bubble sort
        Player[] playerArray = new Player[4];

        for (int i = 0; i < 4; i++) {
            playerArray[i] = players.get(i);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (playerArray[i].getScore() > playerArray[j].getScore()) {
                    if (i > j) {
                        Player temp = playerArray[j];
                        playerArray[j] = playerArray[i];
                        playerArray[i] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            playerArray[i].setPosition(i);
        }
    }
    
    public void setPlayerState(String state){
        for (Player player : players) {
                if (player instanceof Computer) {
                    ((Computer) player).setGameState(state);
                }
            }
    }
}
