package by.bsu.example.logic;


import by.bsu.example.controller.SessionParamName;
import by.bsu.example.entity.Command;
import by.bsu.example.entity.Login;
import by.bsu.example.dao.impl.CommandDao;
import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.DaoFactory;
import by.bsu.example.dao.impl.LoginDao;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import java.util.List;


public class UserLogic{

    private volatile static UserLogic instance=null;
    private UserLogic(){}

    public static UserLogic getInstance(){
        if(instance==null){
            synchronized (UserLogic.class){
                if(instance == null){
            instance=new UserLogic();}
            }
        }
        return (UserLogic)instance;
    }

    public List<Command> getUserAccessByRoleId(Integer roleId)throws LogicException{
        List<Command> userAccess=null;
        try {
            CommandDao cd = new CommandDao();
            userAccess = cd.findByRoleId(roleId);

        } catch (DaoException e) {

            throw new LogicException("Can't get commands list for role_id " + roleId);
        }
        return userAccess;
    }

    public Login userLogin(String userLogin, String userPassword)throws LogicException{
        Login login=null;
        try {
            LoginDao ld = DaoFactory.getInstance().getLoginDao();
            login = ld.findByLoginAndPassword(userLogin, userPassword);
        }catch(DaoException e){
            throw new LogicException(e.getMessage());
        }
        return login;
    }

    public Login updateUser(Login user)throws LogicException{
        Login login=null;
        try {
            LoginDao ld = DaoFactory.getInstance().getLoginDao();
            login = ld.update(user);
        }catch(DaoException e){
            throw new LogicException(e.getMessage());
        }
        return login;
    }

    public boolean registerUser(String userLogin, String userPassword, int roleId, String firstName, String lastName, String subunit)throws LogicException{
        Login user;
        try {
            LoginDao ld = DaoFactory.getInstance().getLoginDao();
            if(ld.findByLogin(userLogin)==null){

                user = new Login();
                user.setLogin(userLogin);
                user.setPassword(userPassword);
                user.setRoleId(roleId);
                user.setIsActive(true);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setSubunit(subunit);
                ld.create(user);
                return true;
            }
        }catch (DaoException e){

            throw new LogicException("User registration error: "+e.getMessage());
        }
        return false;
    }

    public List<Login> getAll()throws LogicException{
        List<Login> courses=null;
        try {
            LoginDao dao = DaoFactory.getInstance().getLoginDao();
            courses = dao.findAll();
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return courses;
    }

    public Login getById(Integer id) throws LogicException{
        Login user=null;
        try {
            LoginDao dao = DaoFactory.getInstance().getLoginDao();
            user = dao.findEntityById(id);
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return user;
    }

    public boolean delete(Integer id) throws LogicException{
        boolean result=false;
        try {
            LoginDao dao = DaoFactory.getInstance().getLoginDao();
            result=dao.delete(id);
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return result;
    }
}

