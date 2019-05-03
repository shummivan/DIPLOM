package by.bsu.example.command.impl;

import by.bsu.example.command.AbstractCommand;
import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.JspPageParamName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.entity.Login;
import by.bsu.example.locales.MessageManager;
import by.bsu.example.logic.CourseLogic;
import by.bsu.example.logic.LogicException;
import by.bsu.example.logic.RoleLogic;
import by.bsu.example.logic.UserLogic;
import by.bsu.example.manager.JspPagesManager;

import javax.servlet.http.HttpServletRequest;


public class UserEdit extends AbstractCommand {
    @Override
    public String execute(HttpServletRequest request) {

        UserLogic ul=UserLogic.getInstance();

        String page = JspPagesManager.getProperty(JspPageParamName.USER_EDIT_PAGE);


        try {

            Integer itemId = new Integer(request.getParameter(CommandParameterName.PARAM_NAME_ID));

            if (itemId != null) {


                Login item=ul.getById(itemId);

                if (item!=null){

                    String firstName=request.getParameter(CommandParameterName.PARAM_FIRST_NAME);
                    String lastName=request.getParameter(CommandParameterName.PARAM_LAST_NAME);
                    String login=request.getParameter(CommandParameterName.PARAM_NAME_LOGIN);
                    String password=request.getParameter(CommandParameterName.PARAM_NAME_PASSWORD);
                    String role=request.getParameter(CommandParameterName.PARAM_NAME_ROLE);

                    if(firstName!=null && lastName !=null && login!=null && password !=null && role!=null){

                        item.setFirstName(firstName);
                        item.setLastName(lastName);
                        item.setLogin(login);
                        item.setRoleId(new Integer(role));
                        if(!password.isEmpty()){
                            item.setMd5Password(password);
                        }
                        ul.updateUser(item);

                        request.setAttribute("actionresult", MessageParamName.ACTION_USER_UPDATED);
                        page = JspPagesManager.getProperty(JspPageParamName.USER_ACTION_FINISHED_PAGE);
                    }

                    request.setAttribute("edititem", item);
                    request.setAttribute("role_list", RoleLogic.getInstance().getAll());

                }else{
                    request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_ACTION_MESSAGE));
                }

            } else {

                request.setAttribute("errorMsg", MessageManager.getInstance().getMessage(MessageParamName.WRONG_ACTION_MESSAGE));
            }
        }catch(LogicException e){
            logger.error(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());
            page = JspPagesManager.getProperty(JspPageParamName.ERROR_PAGE);
        }

        return page;
    }
}
