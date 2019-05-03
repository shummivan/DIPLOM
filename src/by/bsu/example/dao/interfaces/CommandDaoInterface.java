package by.bsu.example.dao.interfaces;

import by.bsu.example.dao.DaoException;
import by.bsu.example.entity.Command;

import java.util.List;

/*
 Интерфейс для entity Command, к стандартным методам добавляем метод выбоки списка комнад доступные определенной
 группе пользователя, чтобы обрабатывать права доступа пользователей к различным командам
 */
public interface CommandDaoInterface extends AbstractEntityInterface<Integer, Command> {
    List<Command> findByRoleId(int roleId) throws DaoException;
}
