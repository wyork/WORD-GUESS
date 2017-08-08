/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.service;

import com.GTCSoftware.wordGuess.DAO.PlayerDAO;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    @Override
    public boolean registerPlayer(PersistentObject po) {
        this.getPlayerDAO().registerPlayer(po);
        return true;
    }

    @Override
    public PersistentObject findById(String anId) {
        return this.getPlayerDAO().findById(anId);
    }
    
    public PersistentObject getPlayerName(Long id){
       return this.getPlayerDAO().getPlayerName(id);
    
    }
    

    /**
     * @param playerDAO the playerDAO to set
     */
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
    private static final Logger LOG = Logger.getLogger(PlayerServiceImpl.class.getName());
}
