package by.bsu.example.logic;


import by.bsu.example.dao.DaoException;
import by.bsu.example.dao.DaoFactory;
import by.bsu.example.dao.impl.CommandDao;
import by.bsu.example.dao.impl.LoginDao;
import by.bsu.example.dao.impl.RoleDao;
import by.bsu.example.entity.Command;
import by.bsu.example.entity.Login;
import by.bsu.example.entity.Role;

import java.util.List;


public class RoleLogic {

    private volatile static RoleLogic instance=null;
    private RoleLogic(){}

    public static RoleLogic getInstance(){
        if(instance==null) {
            synchronized (RoleLogic.class){
            if (instance == null) {
                instance = new RoleLogic();}
            }

        }
        return instance;
    }

    public List<Role> getAll()throws LogicException{
        List<Role> items=null;
        try {
            RoleDao dao = DaoFactory.getInstance().getRoleDao();
            items = dao.findAll();
        }catch(DaoException e){

            throw new LogicException(e.getMessage());
        }
        return items;
    }

}

