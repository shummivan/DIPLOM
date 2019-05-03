package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.command.Command;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.controller.SessionParamName;
import by.bsu.example.entity.Login;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.JournalLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;

public class JournalLessonAdd extends AbstractCommand{
    @Override
    public String execute(HttpServletRequest request) {

        String page = JspPagesManager.getProperty(JspPageParamName.JOURNAL_PAGE);
        String course = request.getParameter(CommandParameterName.PARAM_NAME_ID);
        String mdata = request.getParameter(CommandParameterName.PARAM_NAME_MDATA);
        System.out.println(course);
        System.out.println(mdata);

        if (mdata != null && !mdata.isEmpty()) {
            JournalLogic jl = JournalLogic.getInstance();
            try {
                if (jl.addLesson(mdata, new Integer(course))) {
                    request.setAttribute("actionresult", MessageParamName.ACTION_COURSE_ADDED);
                    page = JspPagesManager.getProperty(JspPageParamName.COURSES_ACTION_FINISHED_PAGE);
                } else {
                    request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.COURSE_NOT_CREATED));
                }
            } catch (LogicException e) {
                logger.error(e.getMessage());
            }

        } else {
            request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.EMPTY_NAME_FIELD_MESSAGE));
        }

        return page;
    }
}
