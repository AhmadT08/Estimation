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
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad
 */
public class GameOver extends GameState {

    public GameOver(Computer c1) {
        computer = c1;
        tricks = new ArrayList();
    }

    @Override
    public int playCard() {
        int card = 0;
        ArrayList<Integer> hand = computer.getHand();
        ArrayList<Integer> masters = new ArrayList();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < hand.size(); i++) { //Organize hand into suits
            int card1 = hand.get(i); //Get next card

            if ((card1 - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card1);
                if (card1 % 13 == 0 || card1 % 13 == 12 || card1 % 13 == 11 || card1 % 13 == 10) {
                    masters.add(card1);
                    clubMasters.add(card1);
                }
            }
            if ((card1 - 1) / 13 == 1) {
                diamonds.add(card1);
                if (card1 % 13 == 0 || card1 % 13 == 12 || card1 % 13 == 11 || card1 % 13 == 10) {
                    masters.add(card1);
                    diamondMasters.add(card1);
                }
            }
            if ((card1 - 1) / 13 == 2) {
                hearts.add(card1);
                if (card1 % 13 == 0 || card1 % 13 == 12 || card1 % 13 == 11 || card1 % 13 == 10) {
                    masters.add(card1);
                    heartMasters.add(card1);
                }
            }
            if ((card1 - 1) / 13 == 3) {
                spades.add(card1);
                if (card1 % 13 == 0 || card1 % 13 == 12 || card1 % 13 == 11 || card1 % 13 == 10) {
                    masters.add(card1);
                    spadeMasters.add(card1);
                }
            }
        }

        if (computer.isCaller()) {
            if (computer.getCall().getSuit().equals("Suns")) {
                for (Trick trick : tricks) {
                    if (cardWinnable(trick.getCard())) {
                        card = trick.getCard();
                        tricks.remove(trick);
                        break;
                    }
                }
            } else {
                for (Trick trick : tricks) {
                    if (cardWinnable(trick.getCard())) {
                        card = trick.getCard();
                        tricks.remove(trick);
                        break;
                    }
                }
            }
        } else {
            if (computer.getCall().getSuit().equals("Suns")) {
                for (Trick trick : tricks) {
                    if (cardWinnable(trick.getCard())) {
                        card = trick.getCard();
                        tricks.remove(trick);
                        break;
                    }
                }
            } else {
                for (Trick trick : tricks) {
                    if (cardWinnable(trick.getCard())) {
                        card = trick.getCard();
                        tricks.remove(trick);
                        break;
                    }
                }
            }
        }

        if (card == 0) {
            outer:
            for (Trick trick : tricks) {
                for (int i : hand) {
                    if (!trick.getSacrifices().contains(i)) {
                        card = i;
                        break outer;
                    }
                }
            }
        }

        if (card == 0) {
            card = hand.get(hand.size() - 1);
        }

        return card;
    }

    @Override
    public int playCard(Suit suit, Suit trumpSuit) {
        ArrayList<Integer> hand = computer.getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        HashMap<String, ArrayList<Integer>> suitMap = new HashMap();
        suitMap.put("Spades", spades);
        suitMap.put("Hearts", hearts);
        suitMap.put("Diamonds", diamonds);
        suitMap.put("Clubs", clubs);

        for (int i = 0; i < hand.size(); i++) { //Organize hand into suits
            int card1 = hand.get(i); //Get next card

            if ((card1 - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card1);
            }
            if ((card1 - 1) / 13 == 1) {
                diamonds.add(card1);
            }
            if ((card1 - 1) / 13 == 2) {
                hearts.add(card1);
            }
            if ((card1 - 1) / 13 == 3) {
                spades.add(card1);
            }
        }

        int card = 0;

        if (computer.isAvoid(suit.getName())) {
            outer:
            for (Trick trick : tricks) {
                for (int i : hand) {
                    if (!trick.getSacrifices().contains(i) && trick.getCard() != i) {
                        card = i;
                        break outer;
                    }
                }
            }
            int i = hand.size() - 1;
            while (computer.getCall().getTricks() == computer.getTricks() && trumpSuit.trumpCheck(card)) {
                card = i;
                i--;
            }
        } else {
            for (Trick trick : tricks) {
                if (Suit.returnSuitByCard(trick.getCard()).getName().equals(suit.getName()) && !cardWinnable(trick.getCard())) {
                    ArrayList<Integer> sacrifices = trick.getSacrifices();
                    if (sacrifices.isEmpty()) {
                        card = trick.getCard();
                    } else {
                        card = sacrifices.get(sacrifices.size() - 1);
                        sacrifices.remove(new Integer(card));
                    }
                }
            }
            if (card == 0) {
                ArrayList<Integer> temp = suitMap.get(suit.getName());
                card = temp.get(temp.size() - 1);
            }
        }

        if (card == 0) {
            card = hand.get(hand.size() - 1);
        }

        return card;
    }

    @Override
    public void determineTrickPaths() {

        ArrayList<Integer> hand = computer.getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));
        HashMap<ArrayList<Integer>, ArrayList<Integer>> suitMap = new HashMap();

        for (int i = 0; i < hand.size(); i++) { //Organize hand into suits
            int card = hand.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    clubMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    diamondMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    heartMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    spadeMasters.add(card);
                }
            }
        }

        suitMap.put(spades, spadeMasters);
        suitMap.put(hearts, heartMasters);
        suitMap.put(diamonds, diamondMasters);
        suitMap.put(clubs, clubMasters);

        for (ArrayList<Integer> suit : handSuits) {
            if (computer.isCaller()) {
                if (suit.size() > 0) {
                    if (Suit.returnSuitByName(computer.getCall().getSuit()).trumpCheck(suit.get(0)) && !computer.getCall().getSuit().equals("Suns")) {
                        if (suitMap.get(suit).size() == 4) {
                            for (int i : suit) {
                                tricks.add(new Trick(i, new ArrayList(), "Regular"));
                            }
                        } else if (suitMap.get(suit).size() == 3) {
                            for (int i = 0; i < suit.size() - 1; i++) {
                                tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                            }
                        } else {
                            for (int i = 0; i < suitMap.get(suit).size(); i++) {
                                if (suit.get(i) % 13 == 0) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                                }
                                if (suit.get(i) % 13 == 12 && suit.size() > 1) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 11 && suit.size() > 2) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 10 && suit.size() > 3) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                            }
                            for (int i = 2; i < suitMap.get(suit).size() - 2; i++) {
                                tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                            }
                        }
                    } else {
                        if (suit.size() == 1) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                            }
                        }

                        if (suit.size() == 2) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1))), "Regular"));
                            }
                        }
                        if (suit.size() == 3) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                            }
                        }
                        if (suit.size() == 4) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                                if (suit.get(2) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(2), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            }
                            if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2), suit.get(3))), "Regular"));
                            }
                        }
                        if (suit.size() >= 5 && !computer.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                        }
                        if (suit.size() >= 5 && computer.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                            }
                        }
                    }
                }
            } else {

                if (suit.size() > 0) {
                    if (Suit.returnSuitByName(computer.getCall().getSuit()).trumpCheck(suit.get(0)) && !computer.getCall().getSuit().equals("Suns")) {
                        if (suit.size() == 1) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                            }
                        }
                        if (suit.size() == 2) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1))), "Regular"));
                            }
                        }
                        if (suit.size() == 3) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                            }
                        }
                        if (suit.size() == 4) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                                if (suit.get(2) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(2), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            }
                            if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2), suit.get(3))), "Regular"));
                            }
                        }
                        if (suit.size() >= 5) {
                            for (int i = 0; i < suitMap.get(suit).size(); i++) {
                                if (suit.get(i) % 13 == 0) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                                }
                                if (suit.get(i) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 10) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                            }
                        }

                    } else {
                        if (suit.size() == 1) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                            }
                        }

                        if (suit.size() == 2) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1))), "Regular"));
                            }
                        }
                        if (suit.size() == 3) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                            }
                        }
                        if (suit.size() == 4) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                                if (suit.get(2) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(2), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            }
                            if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2), suit.get(3))), "Regular"));
                            }
                        }
                        if (suit.size() >= 5 && !computer.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                }
                            }
                        }
                        if (suit.size() >= 5 && computer.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                            }
                        }
                    }
                }
            }
        }

        for (Trick trick1 : tricks) {
            for (Trick trick2 : tricks) {
                if (!trick1.equals(trick2)) {
                    if (trick1.getSacrifices().contains(trick2.getCard())) {
                        ArrayList<Integer> s = trick1.getSacrifices();
                        s.remove(s.indexOf(trick2.getCard()));
                        trick1.setSacrifices(s);
                    }
                }
            }
            trick1.sortSacrifices();
        }

        for (int i = 0; i < tricks.size(); i++) {
            for (int j = 0; j < tricks.size(); j++) {
                if (!tricks.get(i).equals(tricks.get(j))) {
                    if (tricks.get(i).getCard() % 13 > (tricks.get(j).getCard() % 13) && tricks.get(j).getCard() % 13 != 0) {
                        Trick temp = tricks.get(j);
                        tricks.set(j, tricks.get(i));
                        tricks.set(i, temp);
                    }
                }
            }
        }

//        if (computer.getCall().getTricks() > tricks.size()) {
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////            Estimation.translate(c1.getHand());
//            System.out.println("-------------\n" + tricks.size() + " trick(s) but " + computer.getCall().getTricks());
////            for (Trick trick : tricks) {
////                System.out.println("-------------");
////                System.out.println(translate(trick.getCard()) + " Sacrifices: ");
////                for (int i : trick.getSacrifices()) {
////                    System.out.println(translate(i));
////                }
////                System.out.println("-------------");
//        }
    }

    @Override
    public void addCutTricks() {
        ArrayList<Integer> hand = computer.getHand();
        Collections.reverse(hand);
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));
        HashMap<ArrayList<Integer>, ArrayList<Integer>> suitMap = new HashMap();

        for (int i = 0; i < hand.size(); i++) { //Organize hand into suits
            int card = hand.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
            }
        }
    }

    @Override
    public Boolean cardWinnable(int card) {
        Boolean win = false;
        if (card % 13 == 0) {
            win = true;
        } else {
            for (int i = 13 - (card % 13); i > 0; i--) {
                if (computer.getHand().contains(card + i)) {
//                    System.out.println((card+i)+" in hand");
                    win = true;
                } else if (computer.getRound().getCardPool().contains(card + i)) {
                    win = true;
                } else {
                    win = false;
                    break;
                }
            }
        }
        return win;
    }

    @Override
    public void removeSacrifice(Trick trick) {

    }

    public Boolean highest(int card) {
        Boolean high = false;
        for (int i : computer.getRound().getLastHand()) {
            if (Suit.returnSuitByCard(card).equals(Suit.returnSuitByCard(i))) {
                if (card > i) {
                    high = true;
                } else {
                    high = false;
                    break;
                }
            }
        }
        return high;
    }
}
