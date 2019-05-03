package by.bsu.example.dao.impl;

import by.bsu.example.dao.AbstractDao;
import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.connectionpool.ConnectionPoolException;
import by.bsu.example.dao.interfaces.CourseDaoInterface;
import by.bsu.example.dao.interfaces.RoleDaoInterface;
import by.bsu.example.entity.Course;
import by.bsu.example.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoleDao extends AbstractDao<Integer, Role> implements RoleDaoInterface {

     private static final String SQL_GET_ALL="SELECT Role.id, Role.name FROM Role ORDER BY name";
     private static final String SQL_DELETE="DELETE FROM Course WHERE id=?";

    @Override
    public List<Role> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        List<Role> list=new ArrayList<Role>();
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_GET_ALL);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Role c = new Role();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    list.add(c);
                }

            } catch (SQLException e) {
                logger.error("findAll error: " + e.getMessage());
                throw new DaoException("findAll error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt, resultSet);
        }
        return list;
    }

    public boolean delete(Integer id)  throws DaoException{
        boolean result=false;
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_DELETE);
                stmt.setInt(1, id);
                stmt.executeUpdate();

            } catch (SQLException e) {
                logger.error("delete error: " + e.getMessage());
                throw new DaoException("delete error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt);
        }

        return result;
    }

}
