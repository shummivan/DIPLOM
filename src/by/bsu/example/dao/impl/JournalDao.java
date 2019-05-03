package by.bsu.example.dao.impl;

import by.bsu.example.dao.AbstractDao;
import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.interfaces.JournalDaoInterface;
import by.bsu.example.dao.connectionpool.*;
import by.bsu.example.entity.Journal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JournalDao extends AbstractDao<Integer, Journal> implements JournalDaoInterface {
    private static final String SQL_INSERT="INSERT INTO Journal (id, mark, miss, mdata, note, id_course, id_student) VALUES (NULL, NULL, NULL, NULL, NULL, ?, ?);";
    private static final String SQL_INSERT_CUST="INSERT INTO Journal (mark, miss, mdata, note, id_course, id_student) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_DELETE="DELETE FROM Journal WHERE id=?";
    private static final String SQL_FIND_BY_COURSE_AND_STUDENT="SELECT * FROM Journal WHERE id_course=? AND id_student=?";
    private static final String SQL_FIND_JOURNAL_BY_COURSE_ID="SELECT DISTINCT Journal.id_student, User.first_name, User.last_name  FROM Journal LEFT JOIN User ON Journal.id_student = User.id WHERE Journal.id_course=? ORDER BY User.last_name, User.first_name";

    private static final String SQL_INSERT_LESSON="INSERT INTO Journal (mark, miss, mdata, note, id_course, id_student) SELECT 0, 0, ?, '', id_course, id_student FROM Journal where Journal.id_course = ? GROUP BY id_student";

    private static final String SQL_FIND_JOURNAL_BY_STUDENT="SELECT Journal.id, Journal.mark, Journal.miss,Journal.mdata, Journal.note, Journal.id_course, Journal.id_student, User.first_name, User.last_name FROM Journal LEFT JOIN User ON Journal.id_student = User.id WHERE Journal.id_course=? ORDER BY Journal.mdata";

    private static final String SQL_FIND_JOURNAL_DAYS_BY_STUDENT="SELECT distinct Journal.mdata FROM Journal LEFT JOIN User ON Journal.id_student = User.id WHERE Journal.id_course=? ORDER BY Journal.mdata";


    private static final String SQL_FIND_BY_PK="SELECT Journal.id, Journal.mark, Journal.miss, Journal.mdata, Journal.note, Journal.id_course, Journal.id_student, User.first_name, User.last_name FROM Journal LEFT JOIN User ON Journal.id_student = User.id WHERE Journal.id=?";
    private static final String SQL_UPDATE="UPDATE Journal SET mark=?, miss=?, mdata=?, note=?, id_course=?, id_student=? WHERE id=?";
    private static final String SQL_FIND_JOURNAL_BY_SUBUNIT="SELECT Journal.id, Journal.mark, Journal.miss,Journal.mdata, Journal.note, Journal.id_course, Journal.id_student, User.first_name, User.last_name, course.name FROM Journal LEFT JOIN User ON Journal.id_student = User.id LEFT JOIN course ON Journal.id_course = course.id WHERE User.subunit=? ORDER BY User.last_name, User.first_name";
  //  private static final String SQL= "SELECT Journal.id, Journal.mark, Journal.miss,Journal.mdata, Journal.note, Journal.id_course, Journal.id_student, User.first_name, User.last_name FROM Journal LEFT JOIN User ON Journal.id_student = User.id WHERE User.subunit=? ORDER BY User.last_name, User.first_name";

    public Journal findEntityById(Integer id) throws DaoException{
        Journal entity=null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_BY_PK);
                stmt.setInt(1, id);
                resultSet = stmt.executeQuery();
                resultSet.next();
                if (resultSet.first()) {
                    entity = new Journal();
                    entity.setId(resultSet.getInt("id"));
                    entity.setMark(resultSet.getInt("mark"));
                    entity.setMiss(resultSet.getInt("miss"));
                    entity.setMdata(resultSet.getString("mdata"));
                    entity.setNote(resultSet.getString("note"));
                    entity.setCourseId(resultSet.getInt("id_course"));
                    entity.setStudentId(resultSet.getInt("id_student"));
                    entity.setStudentFirstName(resultSet.getString("first_name"));
                    entity.setStudentLastName(resultSet.getString("last_name"));
                }

            } catch (SQLException e) {
                logger.error("findEntityById error: " + e.getMessage());
                throw new DaoException("findEntityById error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connection pool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt, resultSet);
        }
        return entity;
    }

    @Override
    public boolean create(Journal entity) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, entity.getCourseId());
                stmt.setInt(2, entity.getStudentId());
                int count = stmt.executeUpdate();
                if (count != 1) {
                    logger.error("Error create with query: " + SQL_INSERT);
                    throw new DaoException("Error create with query: " + SQL_INSERT);
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
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

    public boolean createLesson(Journal entity) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt = connection.prepareStatement(SQL_INSERT_LESSON, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, entity.getMdata());
                stmt.setInt(2, entity.getCourseId());
                int count = stmt.executeUpdate();
                if (count == 0) {
                    logger.error("Error create with query: " + SQL_INSERT_LESSON);
                    throw new DaoException("Error create with query: " + SQL_INSERT_LESSON);
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
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
    public boolean delete(Integer id) throws DaoException {
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

    public Journal findByCourseAndStudent(Integer courseId, Integer studentId) throws DaoException {
        Journal journal=null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt = connection.prepareStatement(SQL_FIND_BY_COURSE_AND_STUDENT, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, courseId);
                stmt.setInt(2, studentId);
                resultSet = stmt.executeQuery();
                resultSet.next();
                if (resultSet.first()) {
                    journal = new Journal();
                    journal.setId(resultSet.getInt("id"));
                    journal.setCourseId(resultSet.getInt("id_course"));
                    journal.setStudentId(resultSet.getInt("id_student"));
                    journal.setMark(resultSet.getInt("mark"));
                    journal.setNote(resultSet.getString("note"));
                    journal.setMiss(resultSet.getInt("miss"));
                    journal.setMdata(resultSet.getString("mdata"));
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
        return journal;
    }

    public List<Journal> findByCourse(Integer courseId) throws DaoException{
        List<Journal> list= new ArrayList<Journal>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_JOURNAL_BY_COURSE_ID);
                stmt.setInt(1,courseId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Journal c = new Journal();
                    c.setStudentId(rs.getInt("id_student"));
                    c.setStudentFirstName(rs.getString("first_name"));
                    c.setStudentLastName(rs.getString("last_name"));
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

    public List<Journal> findByDays(Integer courseId) throws DaoException{
        List<Journal> list= new ArrayList<Journal>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_JOURNAL_DAYS_BY_STUDENT);
                stmt.setInt(1,courseId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Journal c = new Journal();
                    c.setMdata(rs.getString("mdata"));
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

    public List<Journal> findByStudent(Integer courseId) throws DaoException{
        List<Journal> list= new ArrayList<Journal>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_JOURNAL_BY_STUDENT);
                stmt.setInt(1,courseId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Journal c = new Journal();
                    c.setId(rs.getInt("id"));
                    c.setNote(rs.getString("note"));
                    c.setMark(rs.getInt("mark"));
                    c.setMdata(rs.getString("mdata"));
                    c.setMiss(rs.getInt("miss"));
                    c.setCourseId(rs.getInt("id_course"));
                    c.setStudentId(rs.getInt("id_student"));
                    c.setStudentFirstName(rs.getString("first_name"));
                    c.setStudentLastName(rs.getString("last_name"));
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


    public List<Journal> findBySubunit(String subunit) throws DaoException{
        List<Journal> list= new ArrayList<Journal>();

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_JOURNAL_BY_SUBUNIT);
                stmt.setString(1,subunit);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Journal c = new Journal();
                    c.setId(rs.getInt("id"));
                    c.setNote(rs.getString("note"));
                    c.setMark(rs.getInt("mark"));
                    c.setMdata(rs.getString("mdata"));
                    c.setMiss(rs.getInt("miss"));
                    c.setCourseId(rs.getInt("id_course"));
                    c.setStudentId(rs.getInt("id_student"));
                    c.setStudentFirstName(rs.getString("first_name"));
                    c.setStudentLastName(rs.getString("last_name"));
                    c.setStudentcource(rs.getString("name"));
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

    @Override
    public Journal update(Journal entity) throws DaoException{
        Connection connection = null;
        PreparedStatement stmt = null;
        //ResultSet resultSet=null;
        try {
            connection = pool.takeConnection();
            try {
                // `note`=?, `id_course`=?, `id_student`=?
                stmt=connection.prepareStatement(SQL_UPDATE);
                stmt.setInt(1, entity.getMark());
                stmt.setInt(2, entity.getMiss());
                stmt.setString(3, entity.getMdata());
                stmt.setString(4, entity.getNote());
                stmt.setInt(5, entity.getCourseId());
                stmt.setInt(6, entity.getStudentId());
                stmt.setInt(7, entity.getId());

                stmt.executeUpdate();


            } catch (SQLException e) {
                logger.error("update error: " + e.getMessage());
                throw new DaoException("update error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt);
        }
        return entity;
    }

    public static void main(String[] args) throws DaoException{
       JournalDao dao = new JournalDao();
       Journal journal = new Journal();
       journal.setMiss(77);
       journal.setMark(77);
       journal.setMdata("66.99.77");
       journal.setNote("hhhhhrrrrruuuuu");
       journal.setCourseId(13);
       journal.setStudentId(1);
       journal.setId(1);
    }

}

