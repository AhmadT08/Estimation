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
public abstract class GameState {
    
    public ArrayList<Trick> tricks;
    
    public abstract int playCard(Computer c1);
    
    public abstract int playCard(Computer c1, Suit suit, Suit trumpSuit);
    
    public abstract void determineTrickPaths(Computer c1);
}
