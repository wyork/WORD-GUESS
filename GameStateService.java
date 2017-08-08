/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.service;

import com.GTCSoftware.wordGuess.DAO.GameStateDAO;
import com.GTCSoftware.wordGuess.persistentObject.GameState;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import com.GTCSoftware.wordGuess.persistentObject.Player;
import com.GTCSoftware.wordGuess.persistentObject.WordGameWrapper;
import java.util.List;

/**
 *
 * @author Bill
 */
public interface GameStateService {
    public boolean addPlayerToGame(PersistentObject po);
    GameState findGamePlayer(Long aGameId, Long aPlayerId);
    GameStateDAO getGameStateDAO();
    int updateSubmittedWordGuess(GameState aGameState);
    GameState getDetailedGameStateInfo(GameState gameState);
    int acceptGameRequest(GameState gameState);
    int startRandomGame(GameState gameState);
    int startGame(GameState gameState);
    
}
