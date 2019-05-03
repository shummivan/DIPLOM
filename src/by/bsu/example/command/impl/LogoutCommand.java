package by.bsu.example.command.impl;

import by.bsu.example.command.Command;
import by.bsu.example.controller.SessionParamName;

import javax.servlet.http.HttpServletRequest;


public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionParamName.USER_DATA);
        return null;
    }
}
