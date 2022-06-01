package consoleApp.commands;

import store.helpers.Helper;

public class CategoriesSortedByPriceCommand implements Command {
    Helper helper;

    public CategoriesSortedByPriceCommand(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.printSortedStore();
    }
}
