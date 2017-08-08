package com.GTCSoftware.wordGuess.persistentObject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author William
 */
@XmlRootElement
@Entity(name = "com.GTCSoftware.wordGuess.persistentObject.Game")
@Table(name = "Game")
public class Game implements PersistentObject, Serializable {

    @XmlTransient
    private static Log log = LogFactory.getLog(Game.class);
    @XmlTransient
    private static final long serialVersionUID = -3418745895765544020L;
    @Transient
    @XmlTransient
    private final String tableName = "Game";
    @Transient
    @XmlTransient
    private final String uid = "game_id";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "game_id")
    Long gameId;
    @Column(name = "date_started")
    @Temporal(javax.persistence.TemporalType.DATE)
    Calendar dateStarted;
    @Column(name = "date_last_move")
    @Temporal(javax.persistence.TemporalType.DATE)
    Calendar dateLastMove;
    @Column(name = "round_number")
    private int roundNumber;
    @Column(name = "winning_player")
    private Long winningPlayer;

    /**
     * @return the tableName
     */
    @Override
    @XmlTransient
    public String getTableName() {
        return this.tableName;
    }

    /**
     * @return the UID
     */
    @Override
    @XmlTransient
    public String getUID() {
        return uid;
    }
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Game_Player", joinColumns = {
        @JoinColumn(name = "game_id", referencedColumnName = "game_id")}, inverseJoinColumns = {
        @JoinColumn(name = "player_id", referencedColumnName = "player_id")})
    private Set<Player> players = new HashSet<>(0);

    public Game() {
    }

    public Game(Long gameId) {
        this.gameId = gameId;
    }

    @XmlTransient
    public Set<Player> getPlayers() {
        return this.players;
    }
   
    public Calendar getDateLastMove() {
        return dateLastMove;
    }

    public Calendar getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Calendar dateStarted) {
        this.dateStarted = dateStarted;
    }

    public void setDateLastMove(Calendar dateLastMove) {
        this.dateLastMove = dateLastMove;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long id) {
        this.gameId = id;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void setRoundNumber(int aRoundNumber) {
        roundNumber = aRoundNumber;
    }

     /**
     * @return the winningPlayer
     */
    public Long getWinningPlayer() {
        return winningPlayer;
    }

    /**
     * @param winningPlayer the winningPlayer to set
     */
    public void setWinningPlayer(Long winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    /**
     * @return the roundNumber
     */
    public int getRoundNumber() {
        return roundNumber;
    }
   
    
}
