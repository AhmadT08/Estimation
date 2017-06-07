/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Ahmad
 */
public class User extends Player {

    public User(String n) {
        super();
        name = n;
    }

    @Override
    public Boolean dashCall() {
        Boolean b = false;

        System.out.println("\n" + name);
        translate();

        System.out.println("Dash call? Y/N");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        while (!choice.equals("Y") && !choice.equals("y") && !choice.equals("N") && !choice.equals("n")) {
            System.out.println("Please enter Y or N");
            choice = sc.nextLine();
        }

        switch (choice) {
            case "Y":
                b = true;
                setCall(new Call(true, this));
                break;
            case "y":
                b = true;
                setCall(new Call(true, this));
                break;
            case "N":
                b = false;
                setCall(new Call(0, this));
                break;
            case "n":
                b = false;
                setCall(new Call(0, this));
                break;
        }

        return b;
    }

    @Override
    public Call openBidding() {
        //players declare their bid along with the trump suit

        System.out.println("\n" + name + "\n");
        Call c = new Call(true);
        Boolean callInvalid = true;

        while (callInvalid) {
            if (!getCall().isDashCall()) { //a bid can only be made if the player has not called dash call
                System.out.println("Input 'Pass' to pass. Input anything else to bid.");
                Scanner sc = new Scanner(System.in);
                String x = sc.nextLine();

                if (x.toLowerCase().equals("pass")) { //allows the user to pass on bidding
                    c = new Call(true);
                    callInvalid = false;
                } else {
                    int number = 0;
                    while (number < 4 || number > 13) {
                        System.out.println("Please make a bid between 4 and 13");

                        try {
                            number = Integer.parseInt(sc.nextLine());
                        } catch (Exception E) {
                            System.out.println("Please enter a number");
                        }
                    }

                    int s = 0;
                    String[] suits = {"Suns", "Spades", "Hearts", "Diamonds", "Clubs"};
                    System.out.println("Choose a suit: \n1) Suns \n2) Spades \n3) Hearts \n4) Diamonds \n5) Clubs\n");
                    while (s < 1 || s > 5) {
                        System.out.println("Please choose a number between 1 and 5");

                        try {
                            s = Integer.parseInt(sc.nextLine());
                        } catch (Exception E) {
                            System.out.println("Please enter a number");
                        }
                    }
                    c = new Call(number, suits[s - 1], false, this);
                    if (c.isLargerThan(getRound().getCall())) {
                        System.out.println("Your call is " + number + " " + suits[s - 1]);
                        callInvalid = false;
                        getRound().setCall(c);
//                        this.setCaller(true);
                    } else {
                        System.out.println("Your call must be larger than " + getRound().getCall().getTricks() + " " + getRound().getCall().getSuit());
                    }
                }
            }
        }

        setCall(c);
        return c;
    }

    @Override
    public Call secondRoundBidding(Call call) {
        //players estimate how many tricks they can get, given the call

        System.out.println("\n" + name + "\n");

        int bid = -1;
        Scanner sc = new Scanner(System.in);

        Boolean b = true;
        while (b) {
            if (bid < 0 || bid > call.getTricks()) {
                System.out.println("Make a bid between 0 and " + call.getTricks());

                try {
                    bid = Integer.parseInt(sc.nextLine());
                } catch (Exception E) {
                    System.out.println("Please enter a number");
                }
            } else {
                b = false;
            }
        }

        Call c = new Call(bid, this);
        c.setSuit(getRound().getCall().getSuit());
        setCall(c);

        return c;
    }

    @Override
    public Call secondRoundBidding(int limit, Call call) {
        //players estimate how many tricks they can get given the call
        //for the last player bidding
        //bid must not be equal to the limit

        System.out.println("\n" + name);

        int bid = -1;
        Scanner sc = new Scanner(System.in);

        while (!inRange(bid, limit, call)) {

            System.out.println("Make a bid not equal to " + limit + " and less than or equal to " + call.getTricks());

            try {
                bid = Integer.parseInt(sc.nextLine());
            } catch (Exception E) {
                System.out.println("Please enter a number");
            }
        }

        int risk = Math.abs((bid - limit) / 2);

        Call c = new Call(bid, this, call.getSuit(), risk);
//        c.setSuit(getRound().getCall().getSuit());
        setCall(c);

        return c;
    }

    @Override
    public Call fastBidding(Suit suit) {
        System.out.println("\n" + name + "\n");
        translate();

        int bid = -1;
        Scanner sc = new Scanner(System.in);

        Boolean b = true;
        while (b) {
            if (bid < 0 || bid > 13) {
                System.out.println("Make a bid between 0 and 13");

                try {
                    bid = Integer.parseInt(sc.nextLine());
                } catch (Exception E) {
                    System.out.println("Please enter a number");
                }
            } else {
                b = false;
            }
        }

        Call c = new Call(bid, this, suit.getName());
        setCall(c);

        return c;
    }

    @Override
    public Call fastBidding(Suit suit, int limit) {
        //players estimate how many tricks they can get given the call
        //for the last player bidding
        //bid must not be equal to the limit

        System.out.println("\n" + name);

        int bid = -1;
        Scanner sc = new Scanner(System.in);

//        Boolean b = true;
        while (!inRange(bid, limit)) {

            System.out.println("Make a bid between 0 and 13 and not equal to " + limit);

            try {
                bid = Integer.parseInt(sc.nextLine());
            } catch (Exception E) {
                System.out.println("Please enter a number");
            }
        }

        int risk = Math.abs((bid - limit) / 2);

        Call c = new Call(bid, this, suit.getName(), risk);
        setCall(c);

        return c;
    }

    @Override
    public int playCard() {
        //user chooses a card to play
        //for the player who plays the first card in a hand

        System.out.println();
        if (isCaller()) {
            System.out.println(name + " (C) [" + getCall().getTricks() + " " + getCall().getSuit() + "] Tricks [" + getTricks() + "]\n");
        } else if (getCall().isDashCall()) {
            System.out.println(name + " (DC) [" + getCall().getTricks() + " " + getCall().getSuit() + "] Tricks [" + getTricks() + "]\n");
        } else {
            System.out.println(name + " [" + getCall().getTricks() + " " + getCall().getSuit() + "] Tricks [" + getTricks() + "]\n");
        }

        ArrayList<Integer> hand = getHand();
        Collections.sort(hand);
        Collections.reverse(hand);

        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + 1 + ") " + translate(hand.get(i)));
        }

        int number = 0;
        int result = 0;
        Scanner sc = new Scanner(System.in);

        while (number < 1 || number > hand.size()) {
            System.out.println("Please choose a number between 1 and " + hand.size());
            try {
                number = Integer.parseInt(sc.nextLine());
                result = hand.get(number - 1);
            } catch (Exception E) {

            }
        }
        System.out.println("\n\t" + name + " played the " + translate(result));
        removeCardFromHand(result);
        return result;
    }

    @Override
    public int playCard(Suit suit, Suit trumpSuit) {
        //user chooses a card to play
        //for all players aside from the first player to play the hand

        System.out.println();
        if (isCaller()) {
            System.out.println(name + " (C) [" + getCall().getTricks() + " " + getCall().getSuit() + "] Tricks [" + getTricks() + "]\n");
        } else if (getCall().isDashCall()) {
            System.out.println(name + " (DC) [" + getCall().getTricks() + " " + getCall().getSuit() + "] Tricks [" + getTricks() + "]\n");
        } else {
            System.out.println(name + " [" + getCall().getTricks() + " " + getCall().getSuit() + "] Tricks [" + getTricks() + "]\n");
        }

        ArrayList<Integer> hand = getHand();
        Collections.sort(hand);
        Collections.reverse(hand);

        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + 1 + ") " + translate(hand.get(i)));
        }
        System.out.println();

        int number = 0;
        int result = 0;
        Scanner sc = new Scanner(System.in);

        while (number < 1 || number > hand.size()) {
            if (isAvoid(suit.getName())) {
                System.out.println("Please choose a number between 1 and " + hand.size());
                try {
                    number = Integer.parseInt(sc.nextLine());
                    result = hand.get(number - 1);
                } catch (Exception E) {

                }
//            } else if (Suit.returnSuitByCard(result).getClass() == suit.getClass()) {
            } else {
                while (!suit.trumpCheck(result)) {
                    System.out.println("Please choose a card from the suit " + suit.getName());

                    try {
                        number = Integer.parseInt(sc.nextLine());
                        result = hand.get(number - 1);
                    } catch (Exception E) {

                    }
                }
            }
        }
        System.out.println("\t" + name + " played the " + translate(result) + "\n");
        removeCardFromHand(result);
        return result;
    }

    public Boolean inRange(int bid, int limit, Call call) {
        //checks if a bid is in valid range of a call and its limit
        //for the last player bidding in the second round bidding
        Boolean b = false;
        ArrayList<Integer> range = new ArrayList();

        for (int i = 0; i < 13; i++) {
            if (limit != i) {
                if (i <= call.getTricks()) {
                    range.add(i);
                }
            }
        }

        if (range.contains(bid)) {
            b = true;
        }
        return b;
    }

    public Boolean inRange(int bid, Call call) {
        //checks if a bid is in valid range of a call
        Boolean b = false;
        ArrayList<Integer> range = new ArrayList();

        for (int i = 0; i < 13; i++) {

            if (i <= call.getTricks()) {
                range.add(i);
            }

        }

        if (range.contains(bid)) {
            b = true;
        }
        return b;
    }

    public Boolean inRange(int bid, int limit) {
        Boolean b = false;
        ArrayList<Integer> range = new ArrayList();

        for (int i = 0; i < 13; i++) {
            if (i != limit) {
                range.add(i);
            }
        }

        if (range.contains(bid)) {
            b = true;
        }
        return b;
    }
}
