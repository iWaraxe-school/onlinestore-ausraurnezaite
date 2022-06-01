package consoleApp.commands;

import store.helpers.DBHelper;
import store.helpers.Helper;

import java.sql.SQLException;

public class QuitCommand implements Command {
    Helper helper;

    public QuitCommand(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.shutDownExecutorService();
        System.out.println("bye");

        if (helper instanceof DBHelper) {
            try {
                DBHelper.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
