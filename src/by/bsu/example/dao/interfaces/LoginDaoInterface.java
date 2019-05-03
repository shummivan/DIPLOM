package by.bsu.example.dao.interfaces;

import by.bsu.example.entity.Login;

public interface LoginDaoInterface extends AbstractEntityInterface<Integer, Login>{
    Login findByLoginAndPassword(String userLogin, String userPassword);
    Login findByLogin(String userLogin);
}
