package consoleApp.commands;

import store.helpers.StoreHelper;

public class Top5Command implements Command{
    StoreHelper helper;

    public Top5Command(StoreHelper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.top5();
    }
}
