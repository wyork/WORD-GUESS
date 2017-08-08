/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.service;

import com.GTCSoftware.wordGuess.DAO.GameDAO;
import com.GTCSoftware.wordGuess.persistentObject.Game;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bill
 */
@Transactional
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDAO gameDAO;

    @Override
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public GameDAO getGameDAO() {
        return gameDAO;
    }

    @Override
      public List getDetailedGameInfo(Long aGameId) {
        return this.getGameDAO().getDetailedGameInfo(aGameId);
    }

    @Override
    public Game addNewGame(Game game) {
        this.getGameDAO().addNewGame(game);
        return game;
    }

    @Override
    public List getAllGamesRequest(Long aPlayerId) {
        return this.getGameDAO().getAllGamesRequest(aPlayerId);
    }

    @Override
    public int startGame(Game aGame) {
        return this.getGameDAO().startGame(aGame);

    }

    /**
     *
     * @param gameDAO
     */
    public void setGameDAO(GameDAO aGameDAO) {
        gameDAO = aGameDAO;
    }
    private static final Logger LOG = Logger.getLogger(GameServiceImpl.class.getName());
}
