/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.Game;
import java.util.List;


/**
 *
 * @author Bill
 */

public interface GameDAO extends DAO{
    
    
    List getAllGamesRequest(Long aPlayerId);
    boolean deleteGame(Game aGame);
    Game addNewGame(Game game);
    List getDetailedGameInfo(Long aGameId);
    int startGame(Game aGame);
  
    
    
}
