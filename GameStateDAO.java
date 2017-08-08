/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.GameState;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import com.GTCSoftware.wordGuess.persistentObject.Player;
import com.GTCSoftware.wordGuess.persistentObject.WordGameWrapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author Bill
 */
public interface GameStateDAO extends DAO {
    
    GameState findGamePlayer(Long aGameId, Long aPlayerId);
    int addPlayerToGame(PersistentObject po);
    int updateSubmittedWordGuess(GameState aGameState);
    GameState getDetailedGameStateInfo(GameState gameState );
    int acceptGameRequest(GameState gameState);
    SqlRowSet searchForOpenGame(Long playerId);
    int startGame(GameState gameState);
   
    
}
