package by.bsu.example.dao.impl;

import by.bsu.example.dao.AbstractDao;
import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.interfaces.LoginDaoInterface;
import by.bsu.example.entity.Login;
import by.bsu.example.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LoginDao extends AbstractDao<Integer, Login> implements LoginDaoInterface {

    private static final String SQL_LOGIN_USER = "SELECT User.id, User.login, User.password, User.id_role, Role.name as role_name, User.first_name, User.last_name, User.date_add, User.last_login FROM User LEFT JOIN Role ON User.id_role=Role.id WHERE login=? AND password=MD5(?) LIMIT 1";
    private static final String SQL_FIND_BY_ID = "SELECT User.id, login, password, id_role, Role.name as role_name, first_name, last_name, User.date_add, User.last_login FROM User LEFT JOIN Role ON User.id_role=Role.id WHERE User.id=? LIMIT 1";
    private static final String SQL_FIND_BY_LOGIN = "SELECT id, login, password, id_role, Role.name as role_name, first_name, last_name, User.date_add, User.last_login FROM User LEFT JOIN Role ON User.id_role=Role.id WHERE login=? LIMIT 1";
    private static final String SQL_INSERT = "INSERT INTO User (id, login, password, id_role, first_name, last_name, subunit,date_add,last_login) VALUES (default, ?, MD5(?), ?, ?, ?, ?, NOW(), NOW())";
    private static final String SQL_UPDATE="UPDATE User SET login=?, password=?, id_role=?, first_name=?, last_name=?, last_login=? WHERE id=?";
    private static final String SQL_FIND_ALL="SELECT User.id, User.login, User.password, User.id_role, Role.name as role_name, User.first_name, User.last_name, User.date_add, User.last_login FROM User LEFT JOIN Role ON User.id_role=Role.id ORDER BY date_add DESC";
    private static final String SQL_DELETE="DELETE FROM User WHERE id=? LIMIT 1";

    @Override
    public boolean create(Login user) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {


                stmt = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.setInt(3, user.getRoleId());
                stmt.setString(4, user.getFirstName());
                stmt.setString(5, user.getLastName());
                stmt.setString(6, user.getSubunit());
                int count = stmt.executeUpdate();
                if (count != 1) {
                    logger.error("Error create with query: " + SQL_INSERT);
                    throw new DaoException("Error create with query: " + SQL_INSERT);
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    } else {
                        logger.error("Creating failed, no ID obtained.");
                        throw new DaoException("Creating failed, no ID obtained.");
                    }
                }

            } catch (SQLException e) {
                logger.error("INSERT SQL error: " + e.getMessage());
                throw new DaoException("INSERT SQL error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt, resultSet);
        }
        return true;
    }


    @Override
    public Login update(Login entity) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_UPDATE);
                stmt.setString(1, entity.getLogin());
                stmt.setString(2, entity.getPassword());
                stmt.setInt(3, entity.getRoleId());
                stmt.setString(4, entity.getFirstName());
                stmt.setString(5, entity.getLastName());
                java.util.Date lastLogin=entity.getLastLogin();
                if(lastLogin==null){
                    stmt.setString(6, "0000-00-00 00:00:00");
                }else {
                    stmt.setTimestamp(6, new java.sql.Timestamp(entity.getLastLogin().getTime()));
                }
                stmt.setInt(7, entity.getId());
                stmt.executeUpdate();

            } catch (SQLException e) {
                logger.error("update error: " + e.getMessage());
                throw new DaoException("update error: " + e.getMessage(),e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt);
        }
        return entity;

    }



    public Login findByLoginAndPassword(String userLogin, String userPassword){

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        Login login = null;
        try {
            connection = pool.takeConnection();
            try {

                stmt = connection.prepareStatement(SQL_LOGIN_USER);
                stmt.setString(1, userLogin);
                stmt.setString(2, userPassword);
                resultSet = stmt.executeQuery();
                resultSet.next();
                if (resultSet.first()) {
                    login = new Login();
                    login.setId(resultSet.getInt("id"));
                    login.setLogin(resultSet.getString("login"));
                    login.setPassword(resultSet.getString("password"));
                    login.setRoleId(resultSet.getInt("id_role"));
                    login.setRoleName(resultSet.getString("role_name"));
                    login.setFirstName(resultSet.getString("first_name"));
                    login.setLastName(resultSet.getString("last_name"));
                    login.setLastLogin(resultSet.getTimestamp("last_login"));
                    login.setDateAdd(resultSet.getTimestamp("date_add"));
                }

            } catch (SQLException e) {
                logger.error("loginUser() SQL error: " + e.getMessage());
            }
        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
        }finally {
            close(connection, stmt, resultSet);
        }
        return login;
    }

    //
    public Login findByLogin(String userLogin){
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        Login login = null;
        try {
            connection = pool.takeConnection();
            try {

                stmt = connection.prepareStatement(SQL_FIND_BY_LOGIN);
                stmt.setString(1, userLogin);
                resultSet = stmt.executeQuery();
                resultSet.next();
                if (resultSet.first()) {
                    login = new Login();
                    login.setId(resultSet.getInt("id"));
                    login.setLogin(resultSet.getString("login"));
                    login.setPassword(resultSet.getString("password"));
                    login.setRoleId(resultSet.getInt("id_role"));
                    login.setRoleName(resultSet.getString("role_name"));
                    login.setFirstName(resultSet.getString("first_name"));
                    login.setLastName(resultSet.getString("last_name"));
                    login.setSubunit(resultSet.getString("subunit"));
                    login.setLastLogin(resultSet.getTimestamp("last_login"));
                    login.setDateAdd(resultSet.getTimestamp("date_add"));
                }

            } catch (SQLException e) {
                logger.error("loginUser() SQL error: " + e.getMessage());
            }
        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
        }finally {
            close(connection, stmt, resultSet);
        }
        return login;
    }

    public Login findEntityById(Integer id){
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        Login login = null;
        try {
            connection = pool.takeConnection();
            try {

                stmt = connection.prepareStatement(SQL_FIND_BY_ID);
                stmt.setInt(1, id);
                resultSet = stmt.executeQuery();
                resultSet.next();
                if (resultSet.first()) {
                    login = new Login();
                    login.setId(resultSet.getInt("id"));
                    login.setLogin(resultSet.getString("login"));
                    login.setPassword(resultSet.getString("password"));
                    login.setRoleId(resultSet.getInt("id_role"));
                    login.setRoleName(resultSet.getString("role_name"));
                    login.setFirstName(resultSet.getString("first_name"));
                    login.setLastName(resultSet.getString("last_name"));
                    login.setLastLogin(resultSet.getTimestamp("last_login"));
                    login.setDateAdd(resultSet.getTimestamp("date_add"));
                }

            } catch (SQLException e) {
                logger.error("loginUser() SQL error: " + e.getMessage());
            }
        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
        }finally {
            close(connection, stmt, resultSet);
        }
        return login;
    }

    public List<Login> findAll() throws DaoException {
        List<Login> list= new ArrayList<Login>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try{
                stmt=connection.prepareStatement(SQL_FIND_ALL);
                resultSet = stmt.executeQuery();

                while (resultSet.next()) {

                    Login login = new Login();
                    login.setId(resultSet.getInt("id"));
                    login.setLogin(resultSet.getString("login"));
                    login.setPassword(resultSet.getString("password"));
                    login.setRoleId(resultSet.getInt("id_role"));
                    login.setRoleName(resultSet.getString("role_name"));
                    login.setFirstName(resultSet.getString("first_name"));
                    login.setLastName(resultSet.getString("last_name"));
                    login.setLastLogin(resultSet.getTimestamp("last_login"));
                    login.setDateAdd(resultSet.getTimestamp("date_add"));

                    list.add(login);
                }



            } catch (SQLException e) {
                logger.error("findAll error: " + e.getMessage());
                throw new DaoException("findAll error",e);
            }

        }catch(ConnectionPoolException e){
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
                result=true;

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

