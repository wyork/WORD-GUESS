/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.webservices.rest;

import com.GTCSoftware.wordGuess.persistentObject.Game;
import com.GTCSoftware.wordGuess.persistentObject.GameState;
import com.GTCSoftware.wordGuess.persistentObject.WordGameWrapper;
import com.GTCSoftware.wordGuess.persistentObject.Player;
import com.GTCSoftware.wordGuess.service.GameService;
import com.GTCSoftware.wordGuess.service.GameStateService;
import com.GTCSoftware.wordGuess.service.PlayerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Bill
 */
@Controller
public class WordGameController {

    private static final Logger logger = Logger.getLogger(WordGameController.class);
    @Autowired
    private GameService gameService;
    @Autowired
    private GameStateService gameStateService;
    @Autowired
    private PlayerService playerService;
    WordGameWrapper gameStateList = null;

    @RequestMapping(value = "/getAllGamesRequest/{playerId}", method = RequestMethod.GET)
    public ModelAndView getAllGamesRequest(@PathVariable("playerId") Long playerId) {
        WordGameWrapper wrapper = new WordGameWrapper();
        wrapper.setGames(gameService.getAllGamesRequest(playerId));
        return new ModelAndView("xmlViewer", "wrapper", wrapper);

    }

    @RequestMapping(value = "/registerPlayerRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void registerPlayerRequest(@RequestBody Player player) {
        logger.info("Creating Player: " + player.toString());
        getPlayerService().registerPlayer(player);

    }

    @RequestMapping(value = "/submittedGuessWordRequest", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView submittedGuessWordRequest(@RequestBody GameState gameState) {
        Player player  = (Player) this.getPlayerService().findById(gameState.getId());
        gameState.setPlayerId(player.getPlayerId());
        WordGameWrapper wrapper = new WordGameWrapper();
        wrapper.setResponseState(getGameStateService().updateSubmittedWordGuess(gameState));
        return new ModelAndView("xmlViewer", "wrapper", wrapper);
        
    }

    @RequestMapping(value = "/getDetailedGameInfoRequest/{gameId}/{playerId}", method = RequestMethod.GET)
    public ModelAndView getDetailedGameInfoRequest(@PathVariable("gameId") Long gameId, @PathVariable("playerId") String playerId){
        Player player = (Player)playerService.findById(playerId);
        GameState gameState = new GameState(gameId, player.getPlayerId(), player.getId());
        WordGameWrapper wrapper = new WordGameWrapper();
        wrapper.setGames( gameService.getDetailedGameInfo(gameState.getGameId()));
        wrapper.setGameState(getGameStateService().getDetailedGameStateInfo(gameState));
        return new ModelAndView("xmlViewer", "wrapper", wrapper);
        
    }
    
    @RequestMapping(value = "/acceptGameRequest", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String acceptGameRequest(@RequestBody GameState gameState) {
        Player player = (Player)playerService.findById(gameState.getReservedPlayer());
        gameState.setPlayerId(player.getPlayerId());
        int success = getGameStateService().acceptGameRequest(gameState);
        if (success == 1) {
            return "Successfully updated Game Player";

        } else {
            return "unsuccessfully updated Game Player";
        }
        
        
    }
    
    @RequestMapping(value = "/startRandomGameRequest", method = RequestMethod.POST)
    public ModelAndView startRandomGameRequest(@RequestBody GameState gameState) {
        Player player  = (Player) this.getPlayerService().findById(gameState.getId());
        gameState.setPlayerId(player.getPlayerId());
        WordGameWrapper wrapper = new WordGameWrapper();
        wrapper.setGameState(gameState);
        ModelAndView mav = new ModelAndView("xmlViewer", "wrapper", wrapper);
        int started = this.getGameStateService().startRandomGame(gameState);
  
        if(started == 0)
        {
          Game game;
          game =  this.getGameService().addNewGame(new Game());
          gameState.setGameId(game.getGameId());
          started = this.getGameStateService().startGame(gameState);
          
        }
        
        if (started == 1){
             wrapper.setResponseState(started);
             return mav;
        }
        else
             return mav; 
                   
                   
        }

    

    /**
     * @return the gameService
     */
    public GameService getGameService() {
        return gameService;
    }

    /**
     * @param gameService the gameService to set
     */
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * @return the playerService
     */
    public PlayerService getPlayerService() {
        return playerService;
    }

    /**
     * @param playerService the playerService to set
     */
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(WordGameController.class.getName());

    /**
     * @return the gameStateService
     */
    public GameStateService getGameStateService() {
        return gameStateService;
    }

    /**
     * @param gameStateService the gameStateService to set
     */
    public void setGameStateService(GameStateService gameStateService) {
        this.gameStateService = gameStateService;
    }
}
