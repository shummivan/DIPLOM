package by.bsu.example.dao.impl;

import by.bsu.example.dao.AbstractDao;
import by.bsu.example.dao.interfaces.CommandDaoInterface;
import by.bsu.example.dao.DaoException;
import by.bsu.example.entity.Command;
import by.bsu.example.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandDao extends AbstractDao<Integer, Command> implements CommandDaoInterface {

    private static final String SQL_FIND_BY_ROLE_ID="SELECT id, command, menuitem FROM Commands WHERE role_id=?";
    private static final String SQL_FIND_BY_PK="SELECT id, command, menuitem FROM Commands WHERE id=?";


    public List<Command> findByRoleId(int roleId) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        List<Command> list=new ArrayList<Command>();
        try {
            connection = pool.takeConnection();
            try {

                stmt=connection.prepareStatement(SQL_FIND_BY_ROLE_ID);
                stmt.setInt(1, roleId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Command c = new Command();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("command"));
                    c.setMenuItem(rs.getBoolean("menuitem"));
                    list.add(c);
                }

            } catch (SQLException e) {
                logger.error("findByRoleId error: " + e.getMessage());
                throw new DaoException("findByRoleId error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt, resultSet);
        }
        return list;
    }

    protected String getSelectQuery(){
        return SQL_FIND_BY_PK;
    }

    @Override
    public Command findEntityById(Integer id) throws DaoException{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        Command entity=null;
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_BY_PK);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    entity = new Command();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("command"));
                }

            } catch (SQLException e) {
                logger.error("findByRoleId error: " + e.getMessage());
                throw new DaoException("findByRoleId error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt, resultSet);
        }
        return entity;
    }

}
