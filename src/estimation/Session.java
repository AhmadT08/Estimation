/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;

/**
 *
 * @author Ahmad
 */
public class Session {

    private int dealer = 0;
    private int multiplier;
    private Round r;
    private int roundNumber;
    private ArrayList<Round> roundList = new ArrayList();
    private ArrayList<Player> players = new ArrayList(4);

    public Session(Player p1, Player p2, Player p3, Player p4) {
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        this.multiplier = 0;
    }

    public void Start() {
        setPlayerSession();
        roundNumber = 14;
        r = new Round(0, players, this, roundNumber);
        r.startRound(dealer);
        clearPlayerState();
    }

    public void setPlayerSession() {
        for (Player p : players) {
            p.setSession(this);
        }
    }

    public void RestartRound(int multi) {
        System.out.println("Round restarted");
        clearPlayerState();
        r = new Round(multi + 2, players, this, roundNumber);
        if (dealer == 3) {
            dealer = 0;
        } else {
            dealer++;
        }
        r.startRound(dealer);
    }

    public void nextRound(Round previousRound) {
        roundList.add(previousRound);
        roundNumber++;
        if (dealer == 3) {
            dealer = 0;
        } else {
            dealer++;
        }
        clearPlayerState();
        r = new Round(0, players, this, roundNumber);
        r.startRound(dealer);
    }

    public void clearPlayerState() {
        for (Player p : players) {
            p.clearHand();
            p.clearTricks();
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
