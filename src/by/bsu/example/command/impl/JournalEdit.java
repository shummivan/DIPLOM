package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.command.Command;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.entity.Journal;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.JournalLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class JournalEdit extends AbstractCommand {
    public String execute(HttpServletRequest request) {

        String page = JspPagesManager.getProperty(JspPageParamName.JOURNAL_RECORD_EDIT);

        JournalLogic jl = JournalLogic.getInstance();
        CourseLogic cl = CourseLogic.getInstance();

        String mark = request.getParameter(CommandParameterName.PARAM_NAME_MARK);
        String note = request.getParameter(CommandParameterName.PARAM_NAME_NOTE);
        String miss = request.getParameter(CommandParameterName.PARAM_NAME_MISS);
        String mdata = request.getParameter(CommandParameterName.PARAM_NAME_MDATA);

        System.out.println(mark);
        System.out.println(note);
        System.out.println(miss);
        System.out.println(mdata);

        Journal journal;

        String requestId=request.getParameter(CommandParameterName.PARAM_NAME_ID);

        System.out.println(requestId);


        if(requestId==null){
            request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_REQUEST_MESSAGE));
            page = JspPagesManager.getProperty(JspPageParamName.ERROR_PAGE);
        }else {
            Integer itemId=new Integer(requestId);
            try {

                if (mark != null && note != null && miss != null && mdata != null) {

                    journal = jl.findById(itemId);
                    jl.setMark(itemId, new Integer(mark), new Integer(miss), mdata , note);
                    request.setAttribute("course", cl.getForEdit(journal.getCourseId()));

                    request.setAttribute("edititem", journal);
                    request.setAttribute("actionresult", MessageParamName.JORNAL_RECORD_UPDATED);
                    page = JspPagesManager.getProperty(JspPageParamName.JOURNAL_PAGE);

                } else {
                    journal = jl.findById(itemId);
                    request.setAttribute("edititem", journal);
                    request.setAttribute("course", cl.getForEdit(journal.getCourseId()));
                }
            }catch(LogicException e){
                logger.error(e.getMessage());
            }
        }

        return page;
    }

}
