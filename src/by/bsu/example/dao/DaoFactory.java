package by.bsu.example.dao;


import by.bsu.example.dao.impl.*;
import by.bsu.example.dao.interfaces.DaoCreator;

import java.util.HashMap;


public class DaoFactory {

    private volatile static DaoFactory instance=null;


    public static DaoFactory getInstance(){
        if(instance==null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {

                    instance = new DaoFactory();
                }
            }
        }
        return instance;
    }

    HashMap<Class, DaoCreator> creators = new HashMap<Class, DaoCreator>();

        private DaoFactory() {
        creators.put(LoginDao.class, new DaoCreator() {
            @Override
            public LoginDao create() {
                return new LoginDao();
            }
        });

        creators.put(CommandDao.class, new DaoCreator() {
            @Override
            public CommandDao create() {
                return new CommandDao();
            }
        });

        creators.put(CourseDao.class, new DaoCreator() {
            @Override
            public CourseDao create() {
                return new CourseDao();
            }
        });

        creators.put(JournalDao.class, new DaoCreator() {
            @Override
            public JournalDao create() {
                return new JournalDao();
            }
        });

        creators.put(RoleDao.class, new DaoCreator() {
            @Override
            public RoleDao create() {
                return new RoleDao();
            }
        });

    }

    // возвращает дао обхект по имени класса объекта, или бросает исключение, если такого не найдено в хэшмапе
    protected AbstractDao getDao(Class dtoClass) throws DaoException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new DaoException("Dao object for " + dtoClass + " not found.");
        } return creator.create();
    }

    public LoginDao getLoginDao() throws DaoException{
        return (LoginDao) getDao(LoginDao.class);
    }

    public CommandDao getCommandDao() throws DaoException{
        return (CommandDao) getDao(CommandDao.class);
    }

    public CourseDao getCourseDao() throws DaoException{
        return (CourseDao) getDao(CourseDao.class);
    }

    public JournalDao getJournalDao() throws DaoException{
        return (JournalDao) getDao(JournalDao.class);
    }

    public RoleDao getRoleDao() throws DaoException{
        return (RoleDao) getDao(RoleDao.class);
    }
}
