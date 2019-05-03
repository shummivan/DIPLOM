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
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;

public class CoursesEdit extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {

        CourseLogic cl= CourseLogic.getInstance();

        String page = JspPagesManager.getProperty(JspPageParamName.COURSE_EDIT_PAGE);


        try {

            Integer courseId = new Integer(request.getParameter(CommandParameterName.PARAM_NAME_ID));
            String newName=request.getParameter(CommandParameterName.PARAM_NAME_NAME);

            if (courseId != null && newName != null) {
                if (cl.updateCourse(
                        new Integer(request.getParameter(CommandParameterName.PARAM_NAME_ID)),
                        request.getParameter(CommandParameterName.PARAM_NAME_NAME)
                )) {
                    request.setAttribute("actionresult", MessageParamName.ACTION_COURSE_UPDATED);

                    page = JspPagesManager.getProperty(JspPageParamName.COURSES_ACTION_FINISHED_PAGE);
                }

            } else {

                request.setAttribute("edititem", cl.getForEdit(
                        new Integer(request.getParameter(CommandParameterName.PARAM_NAME_ID))));
            }
        }catch(LogicException e){
            logger.error(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());
            page = JspPagesManager.getProperty(JspPageParamName.ERROR_PAGE);
        }

        return page;
    }
}
