package by.bsu.example.dao;

import by.bsu.example.entity.Entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;

import by.bsu.example.dao.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;


public abstract class AbstractDao<K, T extends Entity> {

    static protected final Logger logger=Logger.getRootLogger();
    protected Connection connection;

    static protected ConnectionPool pool = ConnectionPool.getInstance();

    public AbstractDao(){};

    public void close(Statement statement) {

        try{
            if(statement!=null){
                statement.close();
            };
        }catch(SQLException e){
            logger.error("Cannot close Statement");
        }
    }

    public void close(Connection connection, Statement statement) {
        pool.closeConnection(connection, statement);
    }

    public void close(Connection connection, Statement statement, ResultSet results) {
        pool.closeConnection(connection, statement, results);
    }

    public void close(Connection connection){
        pool.closeConnection(connection);
    }

    // not implemented methods

    public T update(T entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public boolean create(T entity) throws DaoException{
        throw new UnsupportedOperationException();
    }

    public T findEntityById(K id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public boolean delete(K id)  throws DaoException{
        throw new UnsupportedOperationException();
    }

    public boolean delete(T entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<T> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

}
