/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Ahmad
 */
public class Session {

    private int dealer = 0;
    private Round r;
    private int roundNumber;
    private ArrayList<Round> roundList = new ArrayList();
    private String difficulty;
    private ArrayList<Player> players = new ArrayList(4);

    public Session(Player p1, Player p2, Player p3, Player p4, String difficulty) {
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        this.difficulty = difficulty;
        Start();
    }

    public void Start() {
        roundNumber = 1;
        r = new Round(1, players, this, dealer);
        clearPlayerHands();
    }

    public void RestartRound(int multi) {
        System.out.println("All players passed, round restarted");
        clearPlayerHands();
        r = new Round(multi, players, this, dealer);
    }

    public void nextRound() {
        roundNumber++;
        if (dealer == 3) {
            dealer = 0;
        } else {
            dealer++;
        }
        clearPlayerHands();
        r = new Round(1, players, this, dealer);
    }

    public void clearPlayerHands() {
        for (Player p : players) {
            p.clearHand();
        }
    }

    public void endSession() {
        Player winner = new Player();

        for (int i = 0; i < 4; i++) {
            if (players.get(i).getPosition() == 1) {
                winner = players.get(i);
            }
        }

        System.out.println("The winner is " + winner.getName() + " with a score of " + winner.getScore());
    }
}
