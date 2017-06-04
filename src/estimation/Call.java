/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ahmad
 */
public class Call {

    private int tricks;
    private Suit suit;
    private Boolean dashCall;
    private Boolean pass;
    private Player caller;
    private int risk = 0;
    private static ArrayList<String> suitRanking = new ArrayList<>(Arrays.asList("Clubs", "Diamonds", "Hearts", "Spades", "Suns"));

    public Call(int tricks, String suit, Boolean dashcall, Player p) {
        this.tricks = tricks;

        switch (suit) {
            case "Clubs":
                this.suit = new Clubs();
                break;
            case "Diamonds":
                this.suit = new Diamonds();
                break;
            case "Hearts":
                this.suit = new Hearts();
                break;
            case "Spades":
                this.suit = new Spades();
                break;
            case "Suns":
                this.suit = new Suns();
                break;
        }

        pass = false;
        this.dashCall = dashcall;
        if (tricks > 0) {
            this.dashCall = false;
        }
        caller = p;
    }

    public Call(int tricks, Player p, String suit) {
        this.tricks = tricks;
        dashCall = false;
        pass = false;
        caller = p;

        switch (suit) {
            case "Clubs":
                this.suit = new Clubs();
                break;
            case "Diamonds":
                this.suit = new Diamonds();
                break;
            case "Hearts":
                this.suit = new Hearts();
                break;
            case "Spades":
                this.suit = new Spades();
                break;
            case "Suns":
                this.suit = new Suns();
                break;
        }
    }

    public Call(int tricks, Player p, String suit, int risk) {
        this.tricks = tricks;
        dashCall = false;
        pass = false;
        caller = p;
        this.risk = risk;

        switch (suit) {
            case "Clubs":
                this.suit = new Clubs();
                break;
            case "Diamonds":
                this.suit = new Diamonds();
                break;
            case "Hearts":
                this.suit = new Hearts();
                break;
            case "Spades":
                this.suit = new Spades();
                break;
            case "Suns":
                this.suit = new Suns();
                break;
        }
    }

    public Call(int tricks, Player p) {
        this.tricks = tricks;
        dashCall = false;
        pass = true; //used to be pass = false;
        caller = p;
    }

    public Call(Boolean dash, Player p) {
        this.tricks = 0;
        dashCall = true;
        pass = true;
        caller = p;
    }

    public Call(Boolean p) {
        tricks = 0;
        dashCall = false;
        pass = true;
    }

    public int getTricks() {
        return tricks;
    }

    public void setTricks(int tricks) {
        this.tricks = tricks;
    }

    public String getSuit() {
        return suit.getName();
    }

    public int getRisk() {
        return risk;
    }

    public void setSuit(String suit) {
        switch (suit) {
            case "Clubs":
                this.suit = new Clubs();
                break;
            case "Diamonds":
                this.suit = new Diamonds();
                break;
            case "Hearts":
                this.suit = new Hearts();
                break;
            case "Spades":
                this.suit = new Spades();
                break;
            case "Suns":
                this.suit = new Suns();
                break;
        }
    }

    public Boolean isDashCall() {
        return dashCall;
    }

    public Boolean isPassed() {
        return pass;
    }

    public Player getCaller() {
        return caller;
    }

    public void setCaller(Player player) {
        caller = player;
    }

    public String returnLargerSuit(String x, String y) {
        //returns the larger of the two suits

        String result = "";

        int x1, y1;
        x1 = y1 = 0;

        for (int i = 0; i < suitRanking.size(); i++) {
            if (suitRanking.get(i).equals(x)) {
                x1 = i;
            }
            if (suitRanking.get(i).equals(y)) {
                y1 = i;
            }
        }

        if (x1 > y1) {
            result = x;
        }
        if (y1 > x1) {
            result = y;
        }

        return result;
    }

    public Boolean isLargerThan(Call c) {
        Boolean b = false;

        if (c.tricks > this.tricks) {
            b = false;
        } else if (c.tricks == this.tricks && c.suit.getName().equals(returnLargerSuit(c.suit.getName(), this.suit.getName()))) {
            b = false;
        } else if (c.tricks == this.tricks && this.suit.getName().equals(c.suit.getName())) {
            b = false;
        } else if (c.tricks == this.tricks && this.suit.getName().equals(returnLargerSuit(c.suit.getName(), this.suit.getName()))) {
            b = true;
        } else if (c.tricks < this.tricks) {
            b = true;
        }

        return b;
    }
}
