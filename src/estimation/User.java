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
        name = n;
    }

    public Boolean dashCall() {
        Boolean b = false;

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
                setCall(new Call(true));
                break;
            case "y":
                b = true;
                setCall(new Call(true));
                break;
            case "N":
                b = false;
                setCall(new Call(0));
                break;
            case "n":
                b = false;
                setCall(new Call(0));
                break;
        }

        return b;
    }

    public void openBidding() {
        if (!getCall().isDashCall()) { //a bid can only be made if the player has not called dash call
            System.out.println("Make a bid of number greater than 3 + suit");
            Scanner sc = new Scanner(System.in);

            int number = 0;
            while (number < 4 || number > 13) {
                System.out.println("Please make a bid between 4 and 13");
                number = sc.nextInt();
            }

            int s = 0;
            String[] suits = {"Suns", "Spades", "Hearts", "Diamonds", "Clubs"};
            System.out.println("Choose a suit: \n1) Suns. \n2) Spades. \n3) Hearts. \n4) Diamonds. \n5) Clubs.\n");
            while (s < 1 || s > 5) {
                System.out.println("Please choose a number between 1 and 5");
                s = sc.nextInt();
            }

            System.out.println("Your call is " + number + " " + suits[s - 1]);
        }

    }
}