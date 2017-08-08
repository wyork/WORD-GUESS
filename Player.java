package com.GTCSoftware.wordGuess.persistentObject;

import com.GTCSoftware.wordGuess.WordGuessApp;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@JsonAutoDetect
@XmlRootElement
@Entity(name = "Player")
@Table(name = "Player")
public class Player implements PersistentObject, Serializable {

    @XmlTransient
    private static Log log = LogFactory.getLog(WordGuessApp.class);
    @XmlTransient
    private static final long serialVersionUID = 1739715377583718635L;
    @XmlTransient
    private final String tableName = "Player";
    @XmlTransient
    private final String uid = "playerId";
    @Column(name = "id")
    private String id;
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.TABLE)
    @Column(name = "player_id")
    Long playerId;
    @Column(name = "token")
    private String token;

    public Player() {
    }

    public Player(String gameId) {
        this.id = gameId;
    }

    public Player(String gameId, String token) {
        this.id = gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long aPlayerId) {
        playerId = aPlayerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getUID() {
        return this.uid;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    
    @Override
    public String toString(){
        return "Player Id: " + id + "Token: " + token;
    
    }
    private static final Logger LOG = Logger.getLogger(Player.class.getName());
}
