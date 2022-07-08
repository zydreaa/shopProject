import product.ProductService;
import sales.SalesService;
import shop.ShopController;
import users.User;
import users.UserService;
import sales.SalesService;

import javax.swing.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ShopController shopController = new ShopController(
                new ProductService(),
                new UserService(),
                new SalesService()
        );

        shopController.createProduct();
        shopController.createProduct();

        JOptionPane.showMessageDialog(null, "You will now create an account");
        shopController.collectUserInfoAndRegisterUser();
        JOptionPane.showMessageDialog(null, "You will now enter your login details");
        shopController.collectUserInfoAndVerifyUser();

        shopController.viewProducts();

        shopController.sellProduct();
        shopController.viewProducts();

        shopController.sellProduct();
        shopController.viewSales();


    }
}
