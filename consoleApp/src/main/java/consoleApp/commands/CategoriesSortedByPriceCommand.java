package consoleApp.commands;

import store.helpers.StoreHelper;

public class CategoriesSortedByPriceCommand implements Command {
    StoreHelper helper;

    public CategoriesSortedByPriceCommand(StoreHelper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.printSortedStore();
    }
}
