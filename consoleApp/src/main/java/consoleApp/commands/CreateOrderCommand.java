package consoleApp.commands;

import store.helpers.Helper;

public class CreateOrderCommand implements Command {
    Helper helper;

    public CreateOrderCommand(Helper helper) {
        this.helper = helper;
    }

    @Override
    public void execute() {
        helper.createOrder();
    }
}
