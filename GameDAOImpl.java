/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.Game;
import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bill
 */
@Repository
public class GameDAOImpl extends DAOImpl implements GameDAO {

    private static Log log = LogFactory.getLog(GameDAOImpl.class);

    /**
     * @return the log
     */
    public static Log getLog() {
        return log;
    }

    /**
     * @param aLog the log to set
     */
    public static void setLog(Log aLog) {
        log = aLog;
    }

    @Override
    public List getDetailedGameInfo(Long aGameId) {
        String sql = "select * from Game where game_id = ?";
        ArrayList games = (ArrayList) this.getJdbcTemplate().query(sql, new GameMapper(), aGameId);
        return games;
    }

    @Override
    public boolean deleteGame(Game aGame) {

        try {
            this.getSessionFactory().getCurrentSession().delete(aGame);
        } catch (Throwable ex) {
            System.err.println("Deleting game " + aGame.toString() + " failed." + ex);
        }
        return true;

    }

    /**
     *
     * @param aGame
     * @return
     */
    @Override
    public int startGame(Game aGame) {
        int bool;
        Calendar calendar = new GregorianCalendar();
        bool = this.getJdbcTemplate().update("update game set date_started = ? where game_id = ? ",
                aGame.getGameId(), calendar);
        return bool;
    }

    @Override
    public List getAllGamesRequest(Long aPlayerId) {
        String sql = "select a.game_id, a.date_started, a.date_last_move, a.winning_player, a.round_number from game a, game_player b "
                + " where a.game_id = b.game_id"
                + " and b.player_id  = " + aPlayerId.toString();

        return this.getJdbcTemplate().query(sql, new GameMapper());

    }

    private static final class GameMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            Calendar calendar = new GregorianCalendar();
            game.setGameId(rs.getLong("game_id"));
            if (rs.getDate("date_started") != null) {
                calendar.setTime(rs.getDate("date_started"));
            }
            game.setDateStarted(calendar);
            if (rs.getDate("date_last_move") != null) {
                calendar.setTime(rs.getDate("date_last_move"));
            }
            game.setDateLastMove(calendar);
            game.setRoundNumber(rs.getInt("round_number"));
            game.setWinningPlayer(rs.getLong("winning_player"));
            return game;

        }
    }

    @Override
    public Game addNewGame(Game game) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("round_number", game.getRoundNumber());
        Long id = (Long)getInsertData(game).executeAndReturnKey(parameters);
        game.setGameId(id);
        return game;
    }
    private static final Logger LOG = Logger.getLogger(GameDAOImpl.class.getName());
}
