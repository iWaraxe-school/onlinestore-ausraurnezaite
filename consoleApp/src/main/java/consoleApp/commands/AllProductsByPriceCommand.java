package consoleApp.commands;

import store.comparators.helpers.StoreHelper;

public class AllProductsByPriceCommand implements Command {
    StoreHelper helper;

    public AllProductsByPriceCommand(StoreHelper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.allProductsByPrice();
    }
}
