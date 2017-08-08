/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import com.GTCSoftware.wordGuess.persistentObject.PersistentObject;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


/**
 *
 * @author Bill
 */
public abstract class DAOImpl implements DAO {

    private static Log log = LogFactory.getLog(DAOImpl.class);
    private SimpleJdbcInsert insertData;
    @Autowired
    private SessionFactory sessionFactory;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     */
    @Override
    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    /**
     * @return the dataSource
     */
    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * @return the jdbcTemplate
     */
    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * @param jdbcTemplate the jdbcTemplate to set
     */
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param insertData the insertData to set
     */
    @Override
    public void setInsertData(SimpleJdbcInsert insertData) {
        this.insertData = insertData;
    }

    public SimpleJdbcInsert getInsertData(PersistentObject po) {
        return new SimpleJdbcInsert(dataSource).withTableName(po.getTableName()).usingGeneratedKeyColumns(po.getUID());

    }
}
