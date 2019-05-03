CREATE TABLE `Commands` (
  `id` int(11) NOT NULL,
  `menuitem` tinyint(1) NOT NULL DEFAULT '0',
  `role_id` int(11) NOT NULL,
  `command` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Commands`
--

INSERT INTO `Commands` (`id`, `menuitem`, `role_id`, `command`) VALUES
(1, 1, 1, 'courses'),
(2, 0, 1, 'journal'),
(3, 1, 2, 'selectcourses'),
(5, 1, 3, 'users'),
(6, 1, 1, 'profile'),
(7, 1, 2, 'profile'),
(8, 0, 1, 'coursesadd'),
(9, 0, 1, 'coursesdelete'),
(10, 0, 1, 'coursesedit'),
(11, 0, 1, 'journaledit'),
(12, 0, 2, 'selectcoursessubscribe'),
(13, 0, 2, 'selectcoursesunsubscribe'),
(14, 0, 3, 'useredit'),
(15, 0, 3, 'useradd'),
(16, 0, 3, 'userdel'),
(17, 1, 1, 'courseteacher'),
(18, 1, 2, 'coursestudent'),
(19, 0, 4, 'journaladmin'),
(20, 1, 4, 'profile'),
(21, 1, 4, 'misscommand'),
(22, 1, 1, 'journallessonadd');

-- --------------------------------------------------------

--
-- Структура таблицы `Course`
--

CREATE TABLE `Course` (
  `id` int(11) NOT NULL,
  `date_add` date NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `id_teacher` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Course`
--

INSERT INTO `Course` (`id`, `date_add`, `name`, `id_teacher`) VALUES
(1, '2016-02-18', 'PHP в картинках', 1),
(2, '2016-02-23', 'C++ для чайников', 1),
(7, '2016-03-01', 'Новый курс MMM ЖЖЖ 000 -+', 1),
(8, '2016-03-01', 'ываываыва', 1),
(9, '2016-03-01', 'ываыва', 1),
(10, '2016-03-01', '234234', 1),
(11, '2016-03-01', '1123123', 1),
(12, '2016-03-01', 'Новый курс', 1),
(13, '2016-03-01', 'NEW COURSE 2', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Journal`
--

CREATE TABLE `Journal` (
  `id` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL,
  `miss` int(11) DEFAULT NULL,
  `mdata` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  `id_course` int(11) NOT NULL,
  `id_student` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Journal`
--

INSERT INTO `Journal` (`id`, `mark`, `miss`,  `mdata`, `note`, `id_course`, `id_student`) VALUES
(1, 3, NULL, NULL, 'this note\r\nи она отредактирована даже', 1, 2),
(13, NULL, NULL, 'this 6', NULL, 2, 2),
(15, NULL, 4, 'this 6', NULL, 1, 25),
(18, NULL, 5, 'this 6', NULL, 13, 26),
(20, NULL, NULL, 'this 6', NULL, 9, 28),
(21, NULL, NULL, 'this 6', NULL, 12, 28),
(32, NULL, NULL, 'this 6', NULL, 10, 2),
(33, NULL, NULL, 'this 6', NULL, 12, 2),
(35, NULL, NULL, 'this 6', NULL, 9, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `Role`
--

CREATE TABLE `Role` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `Role`
--

INSERT INTO `Role` (`id`, `name`) VALUES
(1, 'teacher'),
(2, 'student'),
(3, 'admin'),
(4, 'adminstud');

-- --------------------------------------------------------

--
-- Структура таблицы `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `id_role` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `subunit` varchar(50) NOT NULL,
  `date_add` datetime NOT NULL,
  `last_login` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `User`
--

INSERT INTO `User` (`id`, `login`, `password`, `id_role`, `first_name`, `last_name`, `subunit`, `date_add`, `last_login`) VALUES
(1, 'root', '7b24afc8bc80e548d66c4e7ff72171c5', 1, 'Изот updated', 'Тетерин updated', 'ПОВТ', '2016-04-10 10:22:13', '2016-05-14 06:44:31'),
(2, 'test', '98f6bcd4621d373cade4e832627b4f6', 2, 'Парфён', 'Суханов','ПОВТ', '2016-01-18 11:25:08', '0000-00-00 00:00:00'),
(24, 'admin', '21232f297a57a5a743894a0e4a801fc3', 3, 'ADMIN', 'ADMIN', 'ПОВТ', '2015-11-04 11:19:53', '2016-05-14 06:52:13'),
(25, 'student', 'cd73502828457d15655bbd7a63fb0bc8', 2, 'Сергей', 'Панкратьев', 'ПОВТ', '2016-04-19 16:20:37', '0000-00-00 00:00:00'),
(26, 'test666', 'fbb2b408b46ae729f4896f41f79ec758', 2, 'test666', 'test666', 'ПОВТ', '2016-03-16 13:19:31', '0000-00-00 00:00:00'),
(27, '1234', '81dc9bdb52d04dc20036dbd8313ed055', 1, 'Имя Отредактировано', 'Фамилия отредактирована', 'ПОВТ', '2016-05-02 15:17:38', '2016-05-14 06:19:38'),
(28, 'www', '4eae35f1b35977a00ebd8086c259d4c9', 2, 'www', 'www','ПОВТ', '2016-02-17 16:59:13', '0000-00-00 00:00:00'),
(31, 'mylogin', '9b534ea55d0b82c3a7e80003a84b6865', 1, 'Мой Нейм', 'Мой Фамил!','ПОВТ', '2016-05-02 15:13:44', '0000-00-00 00:00:00');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Commands`
--
ALTER TABLE `Commands`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- Индексы таблицы `Course`
--
ALTER TABLE `Course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_teacher` (`id_teacher`);

--
-- Индексы таблицы `Journal`
--
ALTER TABLE `Journal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_course` (`id_course`),
  ADD KEY `id_student` (`id_student`);

--
-- Индексы таблицы `Role`
--
ALTER TABLE `Role`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_role` (`id_role`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Commands`
--
ALTER TABLE `Commands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT для таблицы `Course`
--
ALTER TABLE `Course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT для таблицы `Journal`
--
ALTER TABLE `Journal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT для таблицы `Role`
--
ALTER TABLE `Role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Commands`
--
ALTER TABLE `Commands`
  ADD CONSTRAINT `Commands_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `Course`
--
ALTER TABLE `Course`
  ADD CONSTRAINT `Course_ibfk_1` FOREIGN KEY (`id_teacher`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `Journal`
--
ALTER TABLE `Journal`
  ADD CONSTRAINT `Journal_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Journal_ibfk_1` FOREIGN KEY (`id_course`) REFERENCES `Course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `User`
--
ALTER TABLE `User`
  ADD CONSTRAINT `User_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `Role` (`id`);