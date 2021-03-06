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
public class Trick {

    private int card;
    private ArrayList<Integer> sacrifices = new ArrayList();
    private String type;
//    private Suit cutSuit;

    public Trick(int card, ArrayList<Integer> sacrifices, String type) {
        this.card = card;
        this.sacrifices = sacrifices;
        this.type = type;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public ArrayList<Integer> getSacrifices() {
        return sacrifices;
    }

    public void setSacrifices(ArrayList<Integer> sacrifices) {
        this.sacrifices = sacrifices;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void sortSacrifices() {
        Collections.sort(sacrifices);
        Collections.reverse(sacrifices);
    }

    public void removeSacrifice(int card) {
        sacrifices.remove(new Integer(card));
    }

}
