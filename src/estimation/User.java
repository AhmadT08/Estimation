/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

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

    public Call openBidding() {
        System.out.println("\n" + name + "\n");
        Call c = new Call(true);
        Boolean callInvalid = true;

        while (callInvalid) {
            if (!getCall().isDashCall()) { //a bid can only be made if the player has not called dash call
                System.out.println("Make a bid of number greater than 3 + suit. Input 'Pass' to pass.");
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
                    System.out.println("Choose a suit: \n1) Suns. \n2) Spades. \n3) Hearts. \n4) Diamonds. \n5) Clubs.\n");
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
                    } else {
                        System.out.println("Your call must be larger than " + getRound().getCall().getTricks() + " " + getRound().getCall().getSuit());
                    }
                }
            }
        }

        setCall(c);
        return c;
    }

    public Call secondRoundBidding(Call call) {
        System.out.println("\n" + name + "\n");

        int bid = -1;
        Scanner sc = new Scanner(System.in);

        while (bid < 0 || bid > call.getTricks()) {
            System.out.println("Make a bid between 0 and " + call.getTricks());

            try {
                bid = Integer.parseInt(sc.nextLine());
            } catch (Exception E) {
                System.out.println("Please enter a number");
            }
        }

        Call c = new Call(bid, this);
        setCall(c);

        return c;
    }

    public Call secondRoundBidding(int limit, Call call) {
        System.out.println("\n" + name + "\n");

        int bid = -1;
        Scanner sc = new Scanner(System.in);

        if (limit == 0) {
            while (bid < 0 || bid > call.getTricks()) {
                System.out.println("Make a bid greater than " + limit + " and less than or equal to " + call.getTricks());

                try {
                    bid = Integer.parseInt(sc.nextLine());
                } catch (Exception E) {
                    System.out.println("Please enter a number");
                }
            }
        } else {
            while (bid < 0 || bid > call.getTricks()) {
                bid = limit;
                
                while (bid == limit) {
                    System.out.println("Make a bid not equal to " + limit + " and less than or equal to " + call.getTricks());

                    try {
                        bid = Integer.parseInt(sc.nextLine());
                    } catch (Exception E) {
                        System.out.println("Please enter a number");
                    }
                }
            }
        }

        Call c = new Call(bid, this);
        setCall(c);

        return c;
    }

}
