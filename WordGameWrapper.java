/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.persistentObject;

import java.util.List;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author William
 */
@XmlRootElement(name = "wordGame")
public class WordGameWrapper {

    private static final Logger LOG = Logger.getLogger(WordGameWrapper.class.getName());
    private List<Game> games;
    private GameState gameState;
    private int responseState = 0;
   

    public WordGameWrapper() {
    }

    public WordGameWrapper(List<Game> games, GameState gameState) {
        this.games = games;
        this.gameState = gameState;
    }
    
       
    /**
     * @return the game
     */
    @XmlElement(name = "game")
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    /**
     * @return the game
     */
    @XmlElement(name = "gameState")
    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    

    /**
     * @return the responseState
     */
     @XmlElement(name = "response")
    public int getResponseState() {
        return responseState;
    }

    /**
     * @param responseState the responseState to set
     */
    public void setResponseState(int responseState) {
        this.responseState = responseState;
    }
    
 
   
}
