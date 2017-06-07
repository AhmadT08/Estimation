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
    private Round currentRound;
    private int roundNumber;
    private ArrayList<Round> roundList = new ArrayList();
    private final ArrayList<Player> players = new ArrayList(4);

    public Session(Player p1, Player p2, Player p3, Player p4) throws InterruptedException {
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        this.multiplier = 0;
        System.out.println("\nSession starting in...");
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("\n-------------------------------------\n");
    }

    public void startSession() throws InterruptedException {
        setPlayerSession();
        roundNumber = 1;
        currentRound = new Round(0, players, this, roundNumber);
        currentRound.startRound(dealer);
        clearPlayerState();
    }

    public void setPlayerSession() {
        for (Player p : players) {
            p.setSession(this);
        }
    }

    public void restartRound(int multi) throws InterruptedException {
        System.out.println("Round restarted");
        clearPlayerState();
        currentRound = new Round(multi + 2, players, this, roundNumber);
        if (dealer == 3) {
            dealer = 0;
        } else {
            dealer++;
        }
        currentRound.startRound(dealer);
    }

    public void nextRound(Round previousRound) throws InterruptedException {
        roundList.add(previousRound);
        if (roundNumber == 18) {
            endSession();
        } else {
            roundNumber++;
            if (dealer == 3) {
                dealer = 0;
            } else {
                dealer++;
            }
            clearPlayerState();
            currentRound = new Round(0, players, this, roundNumber);
            currentRound.startRound(dealer);
        }

    }

    public void clearPlayerState() {
        for (Player p : players) {
            p.clearHand();
            p.clearTricks();
        }
    }

    public void endSession() {
        Player winner = players.get(1);

        for (int i = 0; i < 4; i++) {
            if (players.get(i).getPosition() == 0) {
                winner = players.get(i);
            }
        }

        System.out.println("The winner is " + winner.getName() + " with a score of " + winner.getScore() + "\n --------------------------------------------------------");

        for (int i = 0; i < 4; i++) {
            for (Player player : players) {
                if (player.getPosition() == i) {
                    System.out.println("Position " + (i + 1) + ") " + player.getName() + " - " + player.getScore());
                }
            }
        }

    }
}
