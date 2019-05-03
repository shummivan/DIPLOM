package by.bsu.example.dao.interfaces;

import by.bsu.example.dao.DaoException;
import by.bsu.example.entity.Course;

import java.util.List;

/*
Интерфейс для для Курсов, добавляем метод позволяющий получать список курсов на которые подписан определенный студент
 */
public interface CourseDaoInterface extends AbstractEntityInterface<Integer, Course> {
    List<Course> findAllBySubsctibe(Integer studentId) throws DaoException;
}

