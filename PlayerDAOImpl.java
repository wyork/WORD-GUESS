/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import com.GTCSoftware.wordGuess.persistentObject.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bill
 */
@Repository
public class PlayerDAOImpl extends DAOImpl implements PlayerDAO {

    private static Log logger = LogFactory.getLog(PlayerDAOImpl.class);

    @Override
    public Player findById(String id) {
        String sql = "select * from player where id = ?";
        ArrayList players = (ArrayList) this.getJdbcTemplate().query(sql, new PlayerMapper(), id);
        return (Player) players.get(0);
     }
    @Override
    public Player getPlayerName(Long id) {
        String sql = "select * from player where player_id = ?";
        ArrayList players = (ArrayList) this.getJdbcTemplate().query(sql, new PlayerMapper(), id);
        return (Player) players.get(0);
     }
   @Override
    public boolean deletePlayer(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public int registerPlayer(PersistentObject po) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("id", ((Player) po).getId());
        parameters.put("token", ((Player) po).getToken());
        int bool = this.getInsertData(po).execute(parameters);
        if (bool == 1) {
            logger.info("Successful Player insert");
        } else {
           logger.info("unsuccessful Player insert");
        }
        return bool;
    }

   
    private static final class PlayerMapper implements RowMapper {
        
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            
            player.setPlayerId(rs.getLong("player_id"));
            player.setId(rs.getString("id"));
            player.setToken(rs.getString("token"));
            return player;

        }
   
    }
    private static final Logger LOG = Logger.getLogger(PlayerDAOImpl.class.getName());
}
