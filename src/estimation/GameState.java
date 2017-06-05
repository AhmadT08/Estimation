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
    
    protected ArrayList<Trick> tricks;
    protected Computer computer;
    
    public abstract int playCard();
    
    public abstract int playCard(Suit suit, Suit trumpSuit);
    
    public abstract void determineTrickPaths();
    
    public abstract void addCutTricks();
    
    public abstract void removeSacrifice(Trick trick);
    
    public abstract Boolean cardWinnable(int card);
}
