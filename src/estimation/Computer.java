/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ahmad
 */
public class Computer extends Player {

    public Computer(String n) {
        super();
        name = n;
    }

    public int numberOfMasters(ArrayList<Integer> suit) {
        int masterCounter = 0;

        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 > 9 || suit.get(i) % 13 == 0) {
                masterCounter++;
            }
        }
        return masterCounter;
    }

    public int suitPoints(ArrayList<Integer> suit) {
        int points = 0;
        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 == 0) {
                points += 13;
            } else {
                points += (suit.get(i) % 13);
            }
        }
        return points;
    }

    public int determineSuitTricks(ArrayList<Integer> suit, int Case) {
        int tricks, suitSize;
        tricks = 0;
        suitSize = suit.size();
        int lowestNonMaster = 0;
        int masters = numberOfMasters(suit);
        Boolean ace, king, queen, jack;
        ace = king = queen = jack = false;

        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 == 0) {
                ace = true;
            }
            if (suit.get(i) % 13 == 12) {
                king = true;
            }
            if (suit.get(i) % 13 == 11) {
                queen = true;
            }
            if (suit.get(i) % 13 == 10) {
                jack = true;
            }
            if (suit.get(i) % 13 < 10 && suit.get(i) % 13 < lowestNonMaster) {
                lowestNonMaster = suit.get(i) % 13;
            }
        }

        switch (Case) {
            case 3:
                tricks += suit.size();
                break;
            case 5:
                if (masters == 2 && suitSize == 5) {
                    tricks += (suitSize - 2);
                } else if (masters > 2) {
                    if (suitSize < 7) {
                        tricks += (suitSize - 2);
                    } else {
                        if (ace && king) {
                            tricks += suitSize;
                        } else {
                            tricks += (suitSize - 1);
                        }
                    }
                }
                break;
            case 6: //trump suit
                if (queen && suitSize >= 3) {
                    if (lowestNonMaster > 5) {
                        tricks++;
                    }
                }
                if (king && suitSize >= 2) {
                    tricks++;
                }
                if (ace) {
                    tricks++;
                }
                if (!ace && !king && !queen && !jack && suitSize > 4) {
                    tricks += (suitSize - 4);
                } else if (ace || king || queen || jack) {
                    if (suitSize >= 5 && masters == 2) {
                        tricks += (suitSize - 4);
                    }
                }
                break;
            case 7: //non-trump suit
                if (king && suitSize >= 2) {
                    tricks++;
                }
                if (ace) {
                    tricks++;
                }
                break;
            default:
                if (queen && suit.size() >= 3) {
                    tricks++;
                }
                if (king && suit.size() >= 2) {
                    tricks++;
                }
                if (ace) {
                    tricks++;
                }
                break;
        }

//        if (Case == 3) {
//            tricks += suit.size();
//        } else if (Case < 5 && Case != 3) {
//            if (queen && suit.size() >= 3) {
//                tricks++;
//            }
//            if (king && suit.size() >= 2) {
//                tricks++;
//            }
//            if (ace) {
//                tricks++;
//            }
//        } else if (Case == 5) {
//            if (masters == 2 && suitSize == 5) {
//                tricks += (suitSize - 2);
//            } else if (masters > 2) {
//                if (suitSize < 7) {
//                    tricks += (suitSize - 2);
//                } else {
//                    if (ace && king) {
//                        tricks += suitSize;
//                    } else {
//                        tricks += (suitSize - 1);
//                    }
//                }
//            }
//        }
        return tricks;
    }

    public int determineMaxSuitTricks(ArrayList<Integer> suit, int Case) {
        int tricks, suitSize;
        tricks = 0;
        suitSize = suit.size();
        int masters = numberOfMasters(suit);
        Boolean ace, king, queen, jack;
        ace = king = queen = jack = false;

        for (int i = 0; i < suitSize; i++) {
            if (suit.get(i) % 13 == 0) {
                ace = true;
            }
            if (suit.get(i) % 13 == 12) {
                king = true;
            }
            if (suit.get(i) % 13 == 11) {
                queen = true;
            }
            if (suit.get(i) % 13 == 10) {
                jack = true;
            }
        }

        switch (Case) {
            case 3:
                tricks += suitSize;
                break;
            case 5:
                if (masters == 2 && suitSize == 5) {
                    if (ace && king) {
                        tricks += (suitSize - 1);
                    } else {
                        tricks += (suitSize - 2);
                    }
                } else if (masters > 2) {
                    if (suitSize < 7) {
                        tricks += (suitSize - 1);
                    } else {
                        tricks += suitSize;
                    }
                }
                break;
            case 6:
                if (jack && suitSize >= 4) {
                    tricks += (suitSize - 3);
                }
                if (queen && suitSize >= 3) {
                    tricks++;
                }
                if (king && suitSize >= 2) {
                    tricks++;
                }
                if (ace) {
                    tricks++;
                }
                if (!ace && !king && !queen && !jack && suitSize > 4) {
                    tricks += (suitSize - 4);
                } else if (ace || king || queen || jack) {
                    if (suitSize >= 5 && masters == 2) {
                        tricks += (suitSize - 4);
                    }
                }
                break;
            case 7:
                if (queen && suitSize >= 3) {
                    tricks++;
                }
                if (king && suitSize >= 2) {
                    tricks++;
                }
                if (ace) {
                    tricks++;
                }
                break;
            default:
                if (jack && suitSize >= 4) {
                    tricks++;
                }
                if (queen && suitSize >= 3) {
                    tricks++;
                }
                if (king && suitSize >= 2) {
                    tricks++;
                }
                if (ace) {
                    tricks++;
                }
                break;
        }

//        if (Case == 3) {
//            tricks += suitSize;
//        } else if (Case < 5 && Case != 3) {
//            if (jack && suitSize >= 4) {
//                tricks++;
//            }
//            if (queen && suitSize >= 3) {
//                tricks++;
//            }
//            if (king && suitSize >= 2) {
//                tricks++;
//            }
//            if (ace) {
//                tricks++;
//            }
//        } else if (Case == 5) {
//            if (masters == 2 && suitSize == 5) {
//                if (ace && king) {
//                    tricks += (suitSize - 1);
//                } else {
//                    tricks += (suitSize - 2);
//                }
//            } else if (masters > 2) {
//                if (suitSize < 7) {
//                    tricks += (suitSize - 1);
//                } else {
//                    tricks += suitSize;
//                }
//            }
//        } else if (Case == 6) {
//            if (jack && suitSize >= 4) {
//                tricks += (suitSize - 3);
//            }
//            if (queen && suitSize >= 3) {
//                tricks++;
//            }
//            if (king && suitSize >= 2) {
//                tricks++;
//            }
//            if (ace) {
//                tricks++;
//            }
//            if (!ace && !king && !queen && !jack && suitSize > 4) {
//                tricks += (suitSize - 4);
//            } else if (ace || king || queen || jack) {
//                if (suitSize >= 5 && masters == 2) {
//                    tricks += (suitSize - 4);
//                }
//            }
//        }
        return tricks;
    }

    public Call maxOpenBidding() {
        Call c = new Call(true);

        ArrayList<Integer> h = getHand();
        ArrayList<Integer> masters = new ArrayList();
        int aceCounter = 0;
        int tricks = 0;
        int highestTricks = 0;
        int greatestMaster = 0;
        int totalMasterPoints = 0;
        String callSuit = "";
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    clubMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    diamondMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    heartMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    spadeMasters.add(card);
                }
            }
        }

        for (int i = 0; i < masters.size(); i++) {
            if (masters.get(i) % 13 == 0) {
                greatestMaster = 13;
                aceCounter++;
                totalMasterPoints += 13;
            } else if ((masters.get(i) % 13) > greatestMaster) {
                greatestMaster = (masters.get(i) % 13);
            }
            totalMasterPoints += (masters.get(i) % 13);
        }

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);
            int suitTricks = 0;

            //Rule 1: If the total amount of masters is greater than 4,
            //        and the sum of the master points is greater than 42,
            //        and the greatest master is higher than a Queen
            if (masters.size() > 4 && totalMasterPoints > 42 && greatestMaster > 11) {
//                System.out.println("\t\t\tRule 1");
//                System.out.println(determineMaxSuitTricks(temp, 1));
                tricks += determineMaxSuitTricks(temp, 1);
                suitTricks = determineMaxSuitTricks(temp, 1);
                if (determineMaxSuitTricks(temp, 1) > highestTricks) {
                    highestTricks = determineMaxSuitTricks(temp, 1);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 2: If the hand contains 4 Aces
            if (aceCounter == 4 && masters.size() == 4) {
//                System.out.println("\t\t\tRule 2");
//                System.out.println((determineMaxSuitTricks(temp, 2)));
                tricks += determineMaxSuitTricks(temp, 2);
                suitTricks = determineMaxSuitTricks(temp, 2);
                if (determineMaxSuitTricks(temp, 2) > highestTricks) {
                    highestTricks = determineMaxSuitTricks(temp, 2);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 3: If the suit contains a full house (Ace, King, Queen and Jack)
            if (temp.equals(spades)) {
                if (spadeMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 4: If a suit has more than 2 master cards,
            //        and more than 4 cards,
            //        and the total number of master cards in the hand is greater than 4
            if (temp.equals(spades)) {
                if (spadeMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 5: If a suit has two or more master cards,
            //        and five or more cards
            if (temp.equals(spades)) {
                if (spadeMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Spades";
                        }
                    }
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Hearts";
                        }
                    }
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Diamonds";
                        }
                    }
                }
            } else {
                if (clubMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Clubs";
                        }
                    }
                }
            }
        }
//        System.out.println(tricks + " " + callSuit);

        if (tricks > 3) {
            c = new Call(tricks, callSuit, false, new Player());
        }

        return c;
    }

    @Override
    public Call openBidding() {
        Call c = new Call(true);

        System.out.println("\n\n");
        translate();
        System.out.println("\n\n");

        ArrayList<Integer> h = getHand();
        ArrayList<Integer> masters = new ArrayList();
        int aceCounter = 0;
        int tricks = 0;
        int highestTricks = 0;
        int greatestMaster = 0;
        int totalMasterPoints = 0;
        String callSuit = "";
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    clubMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    diamondMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    heartMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    spadeMasters.add(card);
                }
            }
        }

        for (int i = 0; i < masters.size(); i++) {
            if (masters.get(i) % 13 == 0) {
                greatestMaster = 13;
                aceCounter++;
                totalMasterPoints += 13;
            } else if ((masters.get(i) % 13) > greatestMaster) {
                greatestMaster = (masters.get(i) % 13);
            }
            totalMasterPoints += (masters.get(i) % 13);
        }

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);
            int suitTricks = 0;

            //Rule 1: If the total amount of masters is greater than 4,
            //        and the sum of the master points is greater than 42,
            //        and the greatest master is higher than a Queen
            if (masters.size() > 4 && totalMasterPoints > 42 && greatestMaster > 11) {
//                System.out.println("\t\t\tRule 1");
//                System.out.println(determineSuitTricks(temp, 1));
                tricks += determineSuitTricks(temp, 1);
                suitTricks = determineSuitTricks(temp, 1);
                if (determineSuitTricks(temp, 1) > highestTricks) {
                    highestTricks = determineSuitTricks(temp, 1);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 2: If the hand contains 4 Aces
            if (aceCounter == 4 && masters.size() == 4) {
//                System.out.println("\t\t\tRule 2");
//                System.out.println((determineSuitTricks(temp, 2)));
                tricks += determineSuitTricks(temp, 2);
                suitTricks = determineSuitTricks(temp, 2);
                if (determineSuitTricks(temp, 2) > highestTricks) {
                    highestTricks = determineSuitTricks(temp, 2);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 3: If the suit contains a full house (Ace, King, Queen and Jack)
            if (temp.equals(spades)) {
                if (spadeMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 4: If a suit has more than 2 master cards,
            //        and more than 4 cards,
            //        and the total number of master cards in the hand is greater than 4
            if (temp.equals(spades)) {
                if (spadeMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 5: If a suit has two or more master cards,
            //        and five or more cards
            if (temp.equals(spades)) {
                if (spadeMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Spades";
                        }
                    }
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Hearts";
                        }
                    }
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Diamonds";
                        }
                    }
                }
            } else {
                if (clubMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Clubs";
                        }
                    }
                }
            }
        }
//        System.out.println(tricks + " " + callSuit);

        if (tricks > 3) {
            if (new Call(tricks, callSuit, false, this).isLargerThan(getRound().getCall())) {
                c = new Call(tricks, callSuit, false, this);
                System.out.println("Call is " + tricks + " " + callSuit);
                getRound().setCall(c);
            } else if (maxOpenBidding().isLargerThan(getRound().getCall())) {
                c = maxOpenBidding();
                System.out.println("Call is " + c.getTricks() + " " + c.getSuit());
                getRound().setCall(c);
            }
        }

        setCall(c);
        return c;
    }

    @Override
    public Boolean dashCall() {
        Boolean b = true;
//        System.out.println(name);
//        translate();
//        System.out.println();

        ArrayList<Integer> h = getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

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

        int aceCounter = 0;

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);

            if (temp.size() > 0 && (temp.get(0) % 13 == 0 && temp.get(temp.size() - 1) % 13 == 0)) {
                aceCounter++;
            }

            //Rule 1: If a suit in the hand has two cards,
            //        and one of them is a master card (A/K/Q/J),
            //        dash call cannot be called
            if ((temp.size() == 2 && temp.size() > 0)
                    && ((temp.get(temp.size() - 1) % 13) > 9 || (temp.get(0) % 13) == 0
                    || (temp.get(0) % 13) > 9)) {
//                System.out.println("\t\t\tRule 1");
                b = false;
            }

            //Rule 2: If a suit in the hand has less than 4 cards,
            //        and one of them is a master card higher than a queen (A/K),
            //        dash call cannot be called
            if ((temp.size() <= 3 && temp.size() > 0)
                    && (((temp.get(0) % 13 == 12) || (temp.get(0) % 13) == 0))) {
//                System.out.println("\t\t\tRule 2");
                b = false;
            }

            //Rule 3: If a suit in the hand has less than 5 cards,
            //        and one of them is an Ace,
            //        dash call cannot be called
            if ((temp.size() <= 4 && temp.size() > 0)
                    && (temp.get(0) % 13 == 0)) {
//                System.out.println("\t\t\tRule 3");
                b = false;
            }

            //Rule 4: If a suit in the hand 1 card,
            //        and it is a master card (A/K/Q/J),
            //        dash call cannot be called
            if (((temp.size() == 1) && (((temp.get(0) % 13) > 9)
                    || (temp.get(0) % 13) == 0))) {
//                System.out.println("\t\t\tRule 4");
                b = false;
            }

            //Rule 5: If a suit in the hand has 3 cards,
            //        and two of them are master cards (A/K/Q/J),
            //        dash call cannot be called
            if ((temp.size() == 3) && (((temp.get(1) % 13) > 9))) {
//                System.out.println("\t\t\tRule 5");
                b = false;
            }

            //Rule 6: If the hand contains more than 2 aces,
            //        dash call cannot be called
            if (aceCounter > 2) {
//                System.out.println("\t\t\tRule 6");
                b = false;
            }

            //Rule 7: If the smallest card in a suit in the hand is greater than 5
            //        dash call cannot be called
            if (temp.size() > 0 && temp.size() < 4) {
                if (temp.get(temp.size() - 1) > 5) {
//                    System.out.println("\t\t\tRule 7");
                    b = false;
                }
            }
        }

        if (b == true) {
//            System.out.println("\t DASHCALL!");
            System.out.println("\n\n");
            translate();
            System.out.println("\n\n");
            setCall(new Call(true, this));
        }
        return b;
    }

    @Override
    public Call secondRoundBidding(Call call) {
        ArrayList<Integer> hand = getHand();
        int bid = 0;
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        HashMap<String, ArrayList> handSuits = new HashMap();
        handSuits.put("Spades", spades);
        handSuits.put("Hearts", hearts);
        handSuits.put("Diamonds", diamonds);
        handSuits.put("Clubs", clubs);

        for (Integer card : hand) {
            //Organize hand into suits

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

        switch (call.getSuit()) {
            case "Suns":
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 1);
                }
                break;
            default:
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    if (handSuit.getKey().equals(call.getSuit())) { //if trump suit
                        bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 6);
                    } else {
                        bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 7);
                    }
                }
                break;
        }

        if (bid > call.getTricks()) {
            bid = call.getTricks();
        }

        Call c = new Call(bid, this, call.getSuit());
        setCall(c);
        translate();
        System.out.println(bid + " tricks \n\n");
        return c;
    }

    @Override
    public Call secondRoundBidding(int limit, Call call) {
        ArrayList<Integer> hand = getHand();
        int bid = 0;
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        HashMap<String, ArrayList> handSuits = new HashMap();
        handSuits.put("Spades", spades);
        handSuits.put("Hearts", hearts);
        handSuits.put("Diamonds", diamonds);
        handSuits.put("Clubs", clubs);

        for (Integer card : hand) {
            //Organize hand into suits

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

        switch (call.getSuit()) {
            case "Suns":
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    bid += determineSuitTricks((ArrayList<Integer>) handSuit.getValue(), 1);
                }
                break;
            default:
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    if (handSuit.getKey().equals(call.getSuit())) { //if trump suit
                        bid += determineSuitTricks((ArrayList<Integer>) handSuit.getValue(), 6);
                    } else {
                        bid += determineSuitTricks((ArrayList<Integer>) handSuit.getValue(), 7);
                    }
                }
                break;
        }

        switch (bid - limit) {
            case -2:
                bid++;
                break;
            case -1:
                break;
            case 0:
                bid = 0;
                switch (call.getSuit()) {
                    case "Suns":
                        for (HashMap.Entry handSuit : handSuits.entrySet()) {
                            bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 1);
                        }
                        break;
                    default:
                        for (HashMap.Entry handSuit : handSuits.entrySet()) {
                            if (handSuit.getKey().equals(call.getSuit())) { //if trump suit
                                bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 6);
                            } else {
                                bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 7);
                            }
                        }
                        break;
                }
                break;
            case 1:
                break;
            case 2:
                bid--;
                break;
        }

        if (bid == limit) {
            if (limit == call.getTricks()) {
                bid--;
            } else {
                bid++;
            }
        }

        int risk = Math.abs((bid - limit) / 2);

        Call c = new Call(bid, this, call.getSuit(), risk);
        setCall(c);
        translate();
        System.out.println(bid + " tricks \n\n");
        return c;
    }

    @Override
    public Call fastBidding(Suit suit) {
        ArrayList<Integer> hand = getHand();
        int bid = 0;
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        HashMap<String, ArrayList> handSuits = new HashMap();
        handSuits.put("Spades", spades);
        handSuits.put("Hearts", hearts);
        handSuits.put("Diamonds", diamonds);
        handSuits.put("Clubs", clubs);

        for (Integer card : hand) {
            //Organize hand into suits

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

        switch (suit.getName()) {
            case "Suns":
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 1);
                }
                break;
            default:
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    if (handSuit.getKey().equals(suit.getName())) { //if trump suit
                        bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 6);
                    } else {
                        bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 7);
                    }
                }
                break;
        }

        Call c = new Call(bid, this, suit.getName());
        setCall(c);
        translate();
        System.out.println(bid + " " + suit.getName() + "\n\n");
        return c;
    }

    @Override
    public Call fastBidding(Suit suit, int limit) {
        ArrayList<Integer> hand = getHand();
        int bid = 0;
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        HashMap<String, ArrayList> handSuits = new HashMap();
        handSuits.put("Spades", spades);
        handSuits.put("Hearts", hearts);
        handSuits.put("Diamonds", diamonds);
        handSuits.put("Clubs", clubs);

        for (Integer card : hand) {
            //Organize hand into suits

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

        switch (suit.getName()) {
            case "Suns":
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    bid += determineSuitTricks((ArrayList<Integer>) handSuit.getValue(), 1);
                }
                break;
            default:
                for (HashMap.Entry handSuit : handSuits.entrySet()) {
                    if (handSuit.getKey().equals(suit.getName())) { //if trump suit
                        bid += determineSuitTricks((ArrayList<Integer>) handSuit.getValue(), 6);
                    } else {
                        bid += determineSuitTricks((ArrayList<Integer>) handSuit.getValue(), 7);
                    }
                }
                break;
        }

        switch (bid - limit) {
            case -2:
                bid++;
                break;
            case -1:
                break;
            case 0:
                bid = 0;
                switch (suit.getName()) {
                    case "Suns":
                        for (HashMap.Entry handSuit : handSuits.entrySet()) {
                            bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 1);
                        }
                        break;
                    default:
                        for (HashMap.Entry handSuit : handSuits.entrySet()) {
                            if (handSuit.getKey().equals(suit.getName())) { //if trump suit
                                bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 6);
                            } else {
                                bid += determineMaxSuitTricks((ArrayList<Integer>) handSuit.getValue(), 7);
                            }
                        }
                        break;
                }
                break;
            case 1:
                break;
            case 2:
                bid--;
                break;
        }

        if (bid == limit) {
            if (limit == 0) {
                bid++;
            } else {
                bid--;
            }
        }

        int risk = Math.abs((bid - limit) / 2);

        Call c = new Call(bid, this, suit.getName(), risk);
        setCall(c);
        translate();
        System.out.println(bid + " tricks \n\n");
        return c;
    }

    @Override
    public int playCard() {
        return 0;
    }
}
