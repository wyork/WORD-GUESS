/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.service;

import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;


/**
 *
 * @author Bill
 */
public interface PlayerService {
    
  PersistentObject findById(String anId);  
  boolean registerPlayer(PersistentObject po);
  PersistentObject getPlayerName(Long id);
}
