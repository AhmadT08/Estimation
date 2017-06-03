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
public class GameUnder extends GameState{
    
    public GameUnder(){
        tricks = new ArrayList();
    }
            
    public int playCard (Computer c1){
        
        return 0;
    }

    @Override
    public int playCard(Computer c1, Suit suit, Suit trumpSuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void determineTrickPaths(Computer c1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
