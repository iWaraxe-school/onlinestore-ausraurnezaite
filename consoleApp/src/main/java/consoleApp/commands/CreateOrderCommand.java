package consoleApp.commands;

import store.helpers.StoreHelper;

public class CreateOrderCommand implements Command{
    StoreHelper helper;

    public CreateOrderCommand(StoreHelper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.createOrder();
    }
}
