/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import com.GTCSoftware.wordGuess.persistentObject.Player;

/**
 *
 * @author Bill
 */
public interface PlayerDAO extends DAO{
    
    Player findById(String id);
    boolean deletePlayer(Player aPlayer);
    int registerPlayer(PersistentObject po);
    Player getPlayerName(Long id);
  
    
   
     
}
