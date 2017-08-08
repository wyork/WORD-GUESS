/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess.DAO;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;




/**
 *
 * @author Bill
 */
public interface DAO {
    void setSessionFactory(SessionFactory sessionFactory);
    SessionFactory getSessionFactory();
    DataSource getDataSource();
    void setDataSource(DataSource dataSource);
    JdbcTemplate getJdbcTemplate();
    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    void setInsertData(SimpleJdbcInsert insertData);
  
   
    
    
    
    
    
}
