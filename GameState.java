package com.GTCSoftware.wordGuess.persistentObject;

import com.GTCSoftware.wordGuess.WordGuessApp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@XmlRootElement
@Entity(name = "GameState")
@Table(name = "Game_Player")
public class GameState implements PersistentObject, Serializable {

    private static Log log = LogFactory.getLog(WordGuessApp.class);
    /**
     *
     */
    private static final long serialVersionUID = -6625897683573763725L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "game_state_id")
    @XmlTransient
    private Long gameStateId;
    @Column(name = "letter_pad_state")
    private java.lang.String letterPadState;
    @Column(name = "word_guess_history")
    private String wordGuessHistory;
    @Column(name = "player_id")
    private Long playerId;
    private String id;
    @Column(name = "game_id")
    private Long gameId;
    @Column(name = "secret_word")
    private String secretWord;
    @Column(name = "reserved_player")
    private String reservedPlayer;
    private final String tableName;
    private final String uid;
    private String opponentPlayerId;
    private String opponentSecretWord;

    public GameState() {
        this.uid = "game_state_id";
        this.tableName = "game_player";
    }

    public GameState(Long gameId, Long playerId, String id) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.tableName = "game_player";
        this.uid = "game_state_id";
        this.id = id;
    }

    public String getWordGuessHistory() {
        return wordGuessHistory;
    }

    public void setWordGuessHistory(String wordGuessHistory) {
        this.wordGuessHistory = wordGuessHistory;
    }

    public Long getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(Long aGameStateId) {
        this.gameStateId = aGameStateId;
    }

    public String getLetterPadState() {
        return letterPadState;
    }

    public void setLetterPadState(String letterPadState) {
        this.letterPadState = letterPadState;
    }

    /**
     * @return the playerId
     */
    @XmlTransient
    public Long getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the gameId
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the secretWord
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * @param secretWord the secretWord to set
     */
    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    /**
     * @return the reservedPlayer
     */
    public String getReservedPlayer() {
        return reservedPlayer;
    }

    /**
     * @param reservedPlayer the reservedPlayer to set
     */
    public void setReservedPlayer(String reservedPlayer) {
        this.reservedPlayer = reservedPlayer;
    }

    /**
     * @return the tableName
     */
    @Override
    @XmlTransient
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the UID
     */
    @Override
    @XmlTransient
    public String getUID() {
        return uid;
    }
    private static final Logger LOG = Logger.getLogger(GameState.class.getName());

    /**
     * @return the opponentPlayerId
     */
    public String getOpponentPlayerId() {
        return opponentPlayerId;
    }

    /**
     * @param opponentPlayerId the opponentPlayerId to set
     */
    public void setOpponentPlayerId(String opponentPlayerId) {
        this.opponentPlayerId = opponentPlayerId;
    }

    /**
     * @return the opponentSecretWord
     */
    public String getOpponentSecretWord() {
        return opponentSecretWord;
    }

    /**
     * @param opponentSecretWord the opponentSecretWord to set
     */
    public void setOpponentSecretWord(String opponentSecretWord) {
        this.opponentSecretWord = opponentSecretWord;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
