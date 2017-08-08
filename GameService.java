/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.service;

import com.GTCSoftware.wordGuess.DAO.GameDAO;
import com.GTCSoftware.wordGuess.persistentObject.Game;
import java.util.List;





/**
 *
 * @author Bill
 */
public interface GameService {
    GameDAO getGameDAO();
    List getDetailedGameInfo(Long aGameId);
    public Game addNewGame(Game game);
    public List getAllGamesRequest(Long aPlayerId);
    int startGame(Game aGame);

   
}
