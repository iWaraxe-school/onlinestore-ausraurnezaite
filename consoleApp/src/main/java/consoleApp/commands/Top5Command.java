package consoleApp.commands;

import store.helpers.Helper;

public class Top5Command implements Command{
   Helper helper;

    public Top5Command(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.top5();
    }
}
