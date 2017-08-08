/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.GameState;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bill
 */
@Repository
public class GameStateDAOImpl extends DAOImpl implements GameStateDAO {

    String tableName = "Game_Player";
    private static final Logger Log = Logger.getLogger(GameStateDAOImpl.class.getName());

    @Override
    public GameState findGamePlayer(Long aGameId, Long aPlayerId) {
        SQLQuery query;
        query = this.getSessionFactory().getCurrentSession().createSQLQuery("select * from game_player where player_id = " + aPlayerId.toString() + " and game_id = " + aGameId.toString());
        query.addEntity(GameState.class);
        List gamePlayers = query.list();
        return (GameState) gamePlayers.get(0);
    }

    @Override
    public GameState getDetailedGameStateInfo(GameState gameState) {
        String sql = "select a.game_id, b.player_id, c.id, a.word_guess_history, b.secret_word, a.letter_pad_state from game_player a, game_player b, player c where c.player_id = b.player_id and a.game_id = ? and b.game_id = ? and a.player_id = ? and b.player_id <> ?";
        return (GameState) this.getJdbcTemplate().query(sql, new GameStateDAOImpl.GameMapper(), gameState.getGameId(), gameState.getGameId(), gameState.getPlayerId(), gameState.getPlayerId()).get(0);   
    
    }

    @Override
    public int addPlayerToGame(PersistentObject po) {
        SqlParameterSource bindValues =
                new MapSqlParameterSource().addValue("player_id", ((GameState) po).getPlayerId())
                .addValue("game_id", ((GameState) po).getGameId())
                .addValue("secret_word", ((GameState) po).getSecretWord())
                .addValue("reserved_player", ((GameState) po).getReservedPlayer());


        SimpleJdbcInsert insert = getInsertData(po);
        int bool;
        bool = insert.execute(bindValues);
        //((Game) po).setGameId(newId.longValue());
        return bool;
    }

    @Override
    public int updateSubmittedWordGuess(GameState aGameState) {

        return this.getJdbcTemplate().update("UPDATE game_player SET word_guess_history = ?, letter_pad_state = ?  WHERE game_id = ? and player_id = ?", aGameState.getWordGuessHistory(), aGameState.getLetterPadState(), aGameState.getGameId(), aGameState.getPlayerId());


    }
    
    @Override
    public SqlRowSet searchForOpenGame(Long playerId)throws DataAccessException{
        String sql = "select a.game_id, b.id from game_player a, Player b where a.player_id = b.player_id and a.reserved_player is null and game_id not in (select a.game_id from game_player a, game_player b where a.player_id <> b.player_id and a.game_id = b.game_id and a.player_id <> ?)";
        return this.getJdbcTemplate().queryForRowSet(sql, playerId);
        
    }
        
  

    @Override
    public int startGame(GameState gameState) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("game_id", gameState.getGameId());
        parameters.put("player_id", gameState.getPlayerId());
        parameters.put("secret_word", gameState.getSecretWord());
        return this.getInsertData(gameState).execute(parameters);
        
  
        }

       

   

    private static final class GameMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            GameState gameState = new GameState();
            gameState.setGameId(rs.getLong("game_id"));
            gameState.setPlayerId(rs.getLong("player_id"));
            gameState.setOpponentPlayerId(rs.getString("id"));
            gameState.setLetterPadState(rs.getString("letter_pad_state"));
            gameState.setWordGuessHistory(rs.getString("word_guess_history"));
            gameState.setOpponentSecretWord(rs.getString("secret_word"));
            return gameState;

        }
    }

    @Override
    public int acceptGameRequest(GameState aGameState) {
        String sql = "UPDATE game_player SET player_id = ?, secret_word = ?, reserved_player = ?  WHERE game_id = ? and reserved_player = ?";
        return this.getJdbcTemplate().update(sql, aGameState.getPlayerId(), aGameState.getSecretWord(), null, aGameState.getGameId(), aGameState.getReservedPlayer());
    }

    
}