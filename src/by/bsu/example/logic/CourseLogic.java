package by.bsu.example.logic;

import by.bsu.example.controller.SessionParamName;
import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.DaoFactory;
import by.bsu.example.dao.impl.CourseDao;
import by.bsu.example.dao.impl.JournalDao;
import by.bsu.example.entity.Course;
import by.bsu.example.entity.Journal;
import by.bsu.example.entity.Login;

import java.util.List;

public class CourseLogic {

    private volatile static CourseLogic instance=null;

    private CourseLogic(){}

    public static CourseLogic getInstance(){
        if(instance == null){
            synchronized (CourseLogic.class) {
                if (instance == null) {
                    instance = new CourseLogic();
                }
            }
        }
        return instance;
    }

    public boolean subscribe(Integer courseId, Integer userId) throws LogicException{
        boolean result=false;
        try {
            JournalDao dao = DaoFactory.getInstance().getJournalDao();
            Journal journal = new Journal();
            journal.setStudentId(userId);
            journal.setCourseId(courseId);
            dao.create(journal);
            result=true;
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return result;
    }


    public boolean unSubscribe(Integer courseId, Integer userId) throws LogicException{
        boolean result=false;
        try {

            JournalDao dao = DaoFactory.getInstance().getJournalDao();
            Journal journal = dao.findByCourseAndStudent(courseId, userId);
            if(journal==null){
                throw new LogicException("Can't delete journal Object. Not found in DB");
            }else{
                dao.delete(journal.getId());
                result=true;
            }

        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return result;
    }


    public boolean addNewCourse(String name, Integer ownerId) throws LogicException{
        boolean result=false;
        try {
            CourseDao dao = DaoFactory.getInstance().getCourseDao();

            if (dao.create(new Course(name, ownerId))) {
                result=true;
            }
        } catch (DaoException e) {

            throw new LogicException(e.getMessage());
        }
        return result;
    }


    public Course getForEdit(Integer id) throws LogicException{
        Course course=null;
        try {
            CourseDao dao = DaoFactory.getInstance().getCourseDao();
            course = dao.findEntityById(id);
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return course;
    }


    public boolean deleteCourse(Integer id) throws LogicException{
        boolean result=false;
        try {
            CourseDao dao = DaoFactory.getInstance().getCourseDao();
            dao.delete(new Integer(id));
            result=true;
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return result;
    }


    public boolean updateCourse(Integer courseId, String courseName) throws LogicException{
        boolean result=false;
        try{
            CourseDao dao = DaoFactory.getInstance().getCourseDao();
            Course course=dao.findEntityById(courseId);
            course.setName(courseName);
            dao.update(course);
            result=true;
        }catch (DaoException e){

            throw new LogicException(e.getMessage());
        }
        return result;
    }

    public List<Course> getAll()throws LogicException{
        List<Course> courses=null;
        try {
            CourseDao dao = DaoFactory.getInstance().getCourseDao();
            courses = dao.findAll();
        }catch(DaoException e){
            //logger.error(e.getMessage());
            throw new LogicException(e.getMessage());
        }
        return courses;
    }
    public List<Course> getAllByIdTeacher(Integer teacherId)throws LogicException{
        List<Course> courses=null;
        try {
            CourseDao dao = DaoFactory.getInstance().getCourseDao();
            courses = dao.findAllByIdTeacher(teacherId);
        }catch(DaoException e){
            //logger.error(e.getMessage());
            throw new LogicException(e.getMessage());
        }
        return courses;
    }

    public List<Course> findAllBySubscribe(Integer userId)throws LogicException{
        List<Course> courses = null;
        try {

            courses = DaoFactory.getInstance().getCourseDao().findAllBySubsctibe(userId);

        }catch (DaoException e){

            throw new LogicException(e.getMessage());
        }

        return courses;
    }

    public List<Course> findAllBySubscribeStudent(Integer userId)throws LogicException{
        List<Course> courses = null;
        try {

            courses = DaoFactory.getInstance().getCourseDao().findAllBySubsctibeStudent(userId);

        }catch (DaoException e){

            throw new LogicException(e.getMessage());
        }

        return courses;
    }
}


