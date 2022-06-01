package consoleApp.commands;

import store.helpers.Helper;

public class AllProductsByPriceCommand implements Command {
    Helper helper;

    public AllProductsByPriceCommand(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.allProductsByPrice();
    }
}
