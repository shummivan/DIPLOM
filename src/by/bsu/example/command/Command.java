package by.bsu.example.command;

import javax.servlet.http.HttpServletRequest;


public interface Command {
    String execute(HttpServletRequest request);
}
