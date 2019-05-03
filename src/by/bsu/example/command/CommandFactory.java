package by.bsu.example.command;

import by.bsu.example.controller.CommandParameterName;
import by.bsu.example.controller.MessageParamName;
import by.bsu.example.locales.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class CommandFactory {

    private static final Logger logger = Logger.getRootLogger();

    private static CommandFactory instance=null;

    private CommandFactory(){}

    public static CommandFactory getInstance(){
        if(instance == null){
            instance=new CommandFactory();
        }
        return instance;
    }


    public Command defineCommand(HttpServletRequest request) {


        String command = request.getParameter(CommandParameterName.COMMAND_NAME);
        if (command == null || command.isEmpty()) {
            // if command not found
            command = (String)request.getAttribute(CommandParameterName.COMMAND_NAME);
            if (command == null || command.isEmpty()) {
//                command = "COURSESVIEW";
 return null;
            }
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(command.toUpperCase());
            return currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.info(MessageManager.getInstance().getMessage(MessageParamName.WRONG_ACTION_MESSAGE));
            request.setAttribute("wrongAction", command
                    + MessageManager.getInstance().getMessage(MessageParamName.WRONG_ACTION_MESSAGE));
            return null;
        }

    }

    public boolean isFreeAccess(String commandName){
        boolean result=false;
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(commandName.toUpperCase());
            result=currentEnum.getIsFreeAccess();
        } catch (IllegalArgumentException e) {
            logger.warn("Wrong command: "+commandName);
        }
        return result;
    }

}

