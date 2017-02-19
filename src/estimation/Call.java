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
    private String suit = "";
    private Boolean dashCall;
    private static ArrayList<String> suitRanking = new ArrayList<>(Arrays.asList("Clubs", "Diamonds", "Hearts", "Spades", "Suns"));

    public Call(int tricks, String suit, Boolean dashcall) {
        this.tricks = tricks;
        this.suit = suit;
        if (tricks > 0) {
            this.dashCall = false;
        }
        this.dashCall = dashcall;
    }
    
    public Call(int tricks){
        this.tricks = tricks;
        dashCall = false;
    }

    public Call(Boolean dash) {
        this.tricks = 0;
        dashCall = true;
    }

    public int getTricks() {
        return tricks;
    }

    public void setTricks(int tricks) {
        this.tricks = tricks;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Boolean isDashCall() {
        return dashCall;
    }

    public String compareSuits(String x, String y) //returns the larger of the two suits
    {
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
        } else if (c.tricks == this.tricks && c.suit.equals(compareSuits(c.suit, this.suit))) {
            b = false;
        } else if (c.tricks == this.tricks && this.suit.equals(compareSuits(c.suit, this.suit))) {
            b = true;
        } else if (c.tricks < this.tricks) {
            b = true;
        }

        return b;
    }
}
