package by.bsu.example.dao.impl;

import by.bsu.example.dao.AbstractDao;
import by.bsu.example.dao.interfaces.CourseDaoInterface;
import by.bsu.example.dao.DaoException;
import by.bsu.example.entity.Course;
import by.bsu.example.dao.connectionpool.ConnectionPoolException;
//import by.bsu.example.entity.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseDao extends AbstractDao<Integer, Course> implements CourseDaoInterface {

    private static final String SQL_GET_ALL_BY_ID_TEACHER="SELECT Course.id, Course.date_add, Course.name, Course.id_teacher, User.first_name, User.last_name, count(Journal.id_student) as student_count FROM facultative.course JOIN facultative.user ON Course.id_teacher=User.id LEFT JOIN facultative.journal ON Course.id=Journal.id_course where id_teacher = ? GROUP BY Course.id ORDER BY date_add DESC";
    private static final String SQL_GET_ALL="SELECT Course.id, Course.date_add, Course.name, Course.id_teacher, User.first_name, User.last_name, count(Journal.id_student) as student_count FROM Course LEFT JOIN User ON Course.id_teacher=User.id LEFT JOIN Journal ON Course.id=Journal.id_course GROUP BY Course.id ORDER BY date_add DESC";
    private static final String SQL_INSERT="INSERT INTO Course (date_add, name, id_teacher) VALUES (?, ?, ?)";
    private static final String SQL_FIND_BY_PK="SELECT Course.id, Course.date_add, Course.name, Course.id_teacher, User.first_name, User.last_name FROM Course  LEFT JOIN User ON Course.id_teacher=User.id WHERE Course.id=? LIMIT 1";
    private static final String SQL_UPDATE="UPDATE Course SET date_add=?, name=?, id_teacher=? WHERE id=?";
    private static final String SQL_DELETE="DELETE FROM Course WHERE id=?";
    private static final String SQL_GET_ALL_WITH_SUBSCRIBE="SELECT Course.id, Course.date_add, Course.name, Course.id_teacher, u1.first_name, u1.last_name, count(IF(id_student=?,1,null)) as sub, IF(id_student=?,j.mark,null) as mark, IF(id_student=?,j.note,null) as note FROM Course LEFT JOIN User AS u1 ON Course.id_teacher=u1.id LEFT JOIN Journal as j ON Course.id=j.id_course GROUP BY Course.id ORDER BY date_add DESC";
    private static final String SQL_GET_ALL_WITH_SUBSCRIBEST="SELECT Course.id, Course.date_add, Course.name, Course.id_teacher, u1.first_name, u1.last_name, count(IF(id_student=?,1,null)) as sub, IF(id_student=?,j.mark,null) as mark, IF(id_student=?,j.note,null) as note FROM Course LEFT JOIN User AS u1 ON Course.id_teacher=u1.id LEFT JOIN Journal as j ON Course.id=j.id_course where id_student=? GROUP BY Course.id ORDER BY date_add DESC";


    @Override
    public boolean create(Course entity) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;

        try {
            connection = pool.takeConnection();
            try {
                stmt = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, entity.getDateAddStr());
                stmt.setString(2, entity.getName());
                stmt.setInt(3, entity.getTeacherId());
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

    @Override
    public List<Course> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        List<Course> list=new ArrayList<Course>();
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_GET_ALL);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Course c = new Course();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setTeacherFirstName(rs.getString("first_name"));
                    c.setTeacherLastName(rs.getString("last_name"));
                    c.setTeacherId(rs.getInt("id_teacher"));
                    c.setDateAdd(rs.getDate("date_add"));
                    c.setStudentsCount(rs.getInt("student_count"));
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

    public List<Course> findAllByIdTeacher(Integer teacherId) throws DaoException {
       // Login entity=null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        List<Course> list=new ArrayList<Course>();
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_GET_ALL_BY_ID_TEACHER);
                stmt.setInt(1, teacherId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Course c = new Course();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setTeacherFirstName(rs.getString("first_name"));
                    c.setTeacherLastName(rs.getString("last_name"));
                    c.setTeacherId(rs.getInt("id_teacher"));
                    c.setDateAdd(rs.getDate("date_add"));
                    c.setStudentsCount(rs.getInt("student_count"));
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
    public Course findEntityById(Integer id) throws DaoException{
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        Course entity=null;
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_FIND_BY_PK);
                stmt.setInt(1, id);
                resultSet = stmt.executeQuery();
                resultSet.next();
                if (resultSet.first()) {
                    entity = new Course();
                    entity.setId(resultSet.getInt("id"));
                    entity.setName(resultSet.getString("name"));
                    entity.setTeacherFirstName(resultSet.getString("first_name"));
                    entity.setTeacherLastName(resultSet.getString("last_name"));
                    entity.setTeacherId(resultSet.getInt("id_teacher"));
                    entity.setDateAdd(resultSet.getDate("date_add"));
                }

            } catch (SQLException e) {
                logger.error("findEntityById error: " + e.getMessage());
                throw new DaoException("findEntityById error",e);
            }

        }catch (ConnectionPoolException e){
            logger.error("take connection from connectionpool error: "+e.getMessage());
            throw new DaoException("take connection from connectionpool error", e);
        }finally {
            close(connection, stmt, resultSet);
        }
        return entity;
    }

    @Override
    public Course update(Course entity) throws DaoException{
        Connection connection = null;
        PreparedStatement stmt = null;
        //ResultSet resultSet=null;
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_UPDATE);
                stmt.setString(1, entity.getDateAddStr());
                stmt.setString(2, entity.getName());
                stmt.setInt(3, entity.getTeacherId());
                stmt.setInt(4, entity.getId());
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

    public List<Course> findAllBySubsctibe(Integer studentId) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        List<Course> list=new ArrayList<Course>();
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_GET_ALL_WITH_SUBSCRIBE);
                stmt.setInt(1, studentId);
                stmt.setInt(2, studentId);
                stmt.setInt(3, studentId);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Course c = new Course();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setTeacherFirstName(rs.getString("first_name"));
                    c.setTeacherLastName(rs.getString("last_name"));
                    c.setTeacherId(rs.getInt("id_teacher"));
                    c.setDateAdd(rs.getDate("date_add"));
                    c.setSubscribe(rs.getBoolean("sub"));
                    c.setMark(rs.getInt("mark"));
                    c.setNote(rs.getString("note"));
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

    public List<Course> findAllBySubsctibeStudent(Integer studentId) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet=null;
        List<Course> list=new ArrayList<Course>();
        try {
            connection = pool.takeConnection();
            try {
                stmt=connection.prepareStatement(SQL_GET_ALL_WITH_SUBSCRIBEST);
                stmt.setInt(1, studentId);
                stmt.setInt(2, studentId);
                stmt.setInt(3, studentId);
                stmt.setInt(4, studentId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Course c = new Course();
                    c.setId(rs.getInt("id"));
                    c.setName(rs.getString("name"));
                    c.setTeacherFirstName(rs.getString("first_name"));
                    c.setTeacherLastName(rs.getString("last_name"));
                    c.setTeacherId(rs.getInt("id_teacher"));
                    c.setDateAdd(rs.getDate("date_add"));
                    c.setSubscribe(rs.getBoolean("sub"));
                    c.setMark(rs.getInt("mark"));
                    c.setNote(rs.getString("note"));
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
}
