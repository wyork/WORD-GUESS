/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.service;

import com.GTCSoftware.wordGuess.DAO.GameStateDAO;
import com.GTCSoftware.wordGuess.persistentObject.GameState;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bill
 */
@Transactional(readOnly = false)
@Service
public class GameStateServiceImpl implements GameStateService {
    private static final Logger LOG = Logger.getLogger(GameStateServiceImpl.class.getName());

    @Autowired
    private GameStateDAO gameStateDAO;

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Override
    public GameStateDAO getGameStateDAO() {
        return gameStateDAO;
    }

    @Override
    public boolean addPlayerToGame(PersistentObject po) {
        this.getGameStateDAO().addPlayerToGame(po);
        return true;
    }

    /**
     * @param gameStateDAO the gameStateDAO to set
     */
    public void setGameStateDAO(GameStateDAO gameStateDAO) {
        this.gameStateDAO = gameStateDAO;
    }

    @Override
    public GameState findGamePlayer(Long aGameId, Long aPlayerId) {
        return this.getGameStateDAO().findGamePlayer(aGameId, aPlayerId);

    }
    
    @Override
      public int startRandomGame(GameState gameState){
          SqlRowSet rs;
          rs = this.getGameStateDAO().searchForOpenGame(gameState.getPlayerId());
          if (rs.first()){
             gameState.setGameId(rs.getLong("game_id"));
             gameState.setOpponentPlayerId(rs.getString("id"));
             return this.getGameStateDAO().startGame(gameState);
          }else{
             return 0;
              
          }
             
          
      }
    
    @Override
    public int startGame(GameState gameState){
          return this.getGameStateDAO().startGame(gameState);
    
    }
    

    @Override
    public int updateSubmittedWordGuess(GameState aGameState) {
        int success;
        success = this.getGameStateDAO().updateSubmittedWordGuess(aGameState);
        return success;
    }
    
    @Override
    public GameState getDetailedGameStateInfo(GameState gameState){
        return this.gameStateDAO.getDetailedGameStateInfo(gameState);
        
            
    
    
    }
    
     @Override
    public int acceptGameRequest(GameState gameState){
     return this.gameStateDAO.acceptGameRequest(gameState);
           
    
    }
     
   
}
