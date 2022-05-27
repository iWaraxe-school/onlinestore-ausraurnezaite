package consoleApp.commands;

import store.helpers.StoreHelper;

public class QuitCommand implements Command {
    StoreHelper helper;

    public QuitCommand(StoreHelper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.shutDownExecutorService();
        System.out.println("bye");
    }
}
