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
    
    public Boolean dashCall(){
        return false;
    }

    public void openBidding() {
        System.out.println("Make a bid of number greater than 3 + suit");
        Scanner sc = new Scanner(System.in);

        int number = 0;
        while (number < 4 || number > 13) {
            System.out.println("Please make a bid between 4 and 13");
            number = sc.nextInt();
        }

        int s = 0;
        String [] suits = {"Suns","Spades","Hearts","Diamonds","Clubs"};
        System.out.println("Choose a suit: \n1) Suns. \n2) Spades. \n3) Hearts. \n4) Diamonds. \n5) Clubs.\n");
        while (s < 1 || s > 5) {
            System.out.println("Please choose a number between 1 and 5");
            s = sc.nextInt();
        }
        
        System.out.println("Your call is "+number+" "+suits[s-1]);
        
    }
}
