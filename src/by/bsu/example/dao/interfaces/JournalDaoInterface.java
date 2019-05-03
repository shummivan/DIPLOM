package by.bsu.example.dao.interfaces;

import by.bsu.example.dao.DaoException;
import by.bsu.example.entity.Journal;

import java.util.List;

/*
Интерфейс для журнала. Добавляет 2 метода.
- поиск в журнале записи о конкретном студенте конкретного курса. нужен для возможности отписки от курса
- поиск записией в журнале, чтобы получить список студентов подписанных на определенный курс, чтобы препод. мог проставлять
 */
public interface JournalDaoInterface extends AbstractEntityInterface<Integer, Journal> {
    Journal findByCourseAndStudent(Integer courseId, Integer studentId) throws DaoException;
    List<Journal> findByCourse(Integer course_id) throws DaoException;
}

