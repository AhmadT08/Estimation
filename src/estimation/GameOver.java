/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Ahmad
 */
public class GameOver extends GameState {

    public GameOver() {
        tricks = new ArrayList();
    }

    @Override
    public int playCard(Computer c1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int playCard(Computer c1, Suit suit, Suit trumpSuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void determineTrickPaths(Computer c1) {
        ArrayList<Integer> hand = c1.getHand();
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
            if (c1.isCaller()) {
                if (suit.size() > 0) {
                    if (Suit.returnSuitByName(c1.getCall().getSuit()).trumpCheck(suit.get(0)) && !c1.getCall().getSuit().equals("Suns")) {
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
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
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
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
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
                        if (suit.size() == 5 && !c1.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                        }
                        if (suit.size() >= 5 && c1.getCall().getSuit().equals("Suns")) {
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
                    if (Suit.returnSuitByName(c1.getCall().getSuit()).trumpCheck(suit.get(0)) && !c1.getCall().getSuit().equals("Suns")) {
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
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
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
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
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
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
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
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
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
                        if (suit.size() == 5 && !c1.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                        }
                        if (suit.size() >= 5 && c1.getCall().getSuit().equals("Suns")) {
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
        }
    }

}
