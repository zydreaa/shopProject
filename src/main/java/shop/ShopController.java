package shop;

import product.Product;
import product.ProductService;
import sales.Sale;
import sales.SalesService;
import users.InvalidUserException;
import users.User;
import users.UserService;
import sales.SalesService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class ShopController {
    private JFrame frame;
    private final ProductService productService;
    private final UserService userService;
    private  final SalesService salesService;


    public ShopController(ProductService productService, UserService userService, SalesService salesService){
        this.productService = productService;
        this.userService = userService;
        this.salesService = salesService;
        frame = new JFrame();
    }

    public void createProduct(){
        try{
            Product product = new Product();
            product.setName(JOptionPane.showInputDialog(frame, "Enter name of product"));
            product.setDescription(JOptionPane.showInputDialog(frame, "Enter Description of Product"));
            product.setPrice(Double.valueOf(JOptionPane.showInputDialog(frame, "Enter Product Price")));
            product.setQuantity(Long.valueOf(JOptionPane.showInputDialog(frame, "Enter Product Quantity")));

            product.setAvailable(
                    JOptionPane.showConfirmDialog(frame, "Is product available?") == JOptionPane.YES_OPTION
            );

            String response = this.productService.addProduct(product);

            JOptionPane.showMessageDialog(frame, response);
        } catch (NullPointerException | NumberFormatException exception){
            JOptionPane.showMessageDialog(frame, "Problem occured, try again");
        }

    }

    public void sellProduct(){
        try {
            ArrayList<Product> availableProducts = this.productService.getAllProducts();
            Product selectedProduct = (Product) JOptionPane.showInputDialog(
                    this.frame,
                    "Select a product:",
                    "Buy product",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    availableProducts.toArray(),
                    availableProducts.toArray()[0]
            );
            Integer quantity =  Integer.valueOf(JOptionPane.showInputDialog("How many do you want to buy?"));
            double totalCostOfProduct = selectedProduct.getPrice() * quantity;
            if (selectedProduct.getQuantity() < quantity) throw new Exception("Not enough product");

            User activeUser = this.userService.getActiveUser();
            if (activeUser.getBalance() < totalCostOfProduct) throw new Exception("Not enough balance");

            selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
            activeUser.setBalance(activeUser.getBalance() - totalCostOfProduct);

            this.productService.updateProduct(selectedProduct);
            this.userService.updateUserBalance(activeUser);

            this.userService.syncActiveUser();

            this.salesService.addSale(new Sale(selectedProduct,activeUser, totalCostOfProduct, quantity, LocalDateTime.now()));

            // do some more validation
            JOptionPane.showMessageDialog(this.frame, "Product Purchased Successfully");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.frame, e.getMessage());
        }
    }


    public void viewProducts(){
        String[] col = {"id", "name", "description", "price", "quantity", "is available"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        this.productService.getAllProducts().forEach( product -> tableModel.addRow(
                        new String[]{
                                String.valueOf(product.getId()),
                                product.getName(),
                                product.getDescription(),
                                String.valueOf(product.getPrice()),
                                String.valueOf(product.getQuantity()),
                                product.getAvailable() ? "Available": "Not Available",

                        }
                )
        );

        displayTable(tableModel);
    }

    private void displayTable(DefaultTableModel tableModel){
        JTable table = new JTable(tableModel);

        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void viewSales(){
        String[] col = {"id", "product name", "user name", "total cost", "total quantity", "purchase date"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        this.salesService.getAll().forEach( sale -> tableModel.addRow(
                        new String[]{
                                String.valueOf(sale.getId()),
                                sale.getProduct().getName(),
                                sale.getUser().getUsername(),
                                String.valueOf(sale.getTotalCost()),
                                String.valueOf(sale.getTotalAmount()),
                                sale.getPurchaseDate().toString(),
                        }
                )
        );

        displayTable(tableModel);
    }

    public void collectUserInfoAndRegisterUser() {
        try{
        String userName = JOptionPane.showInputDialog("Enter your username:");
        String pin = JOptionPane.showInputDialog("Enter your security PIN:");
        Double balance = Double.valueOf(JOptionPane.showInputDialog("Enter your budget for shopping:"));

        userService.createUser(new User(userName, pin, balance));
    } catch (Exception exception){
            JOptionPane.showMessageDialog(frame, exception.getMessage());
        }
    }

    public void collectUserInfoAndVerifyUser() {
        try {
            String userName = JOptionPane.showInputDialog("Enter your username:");
            String pin = JOptionPane.showInputDialog("Enter your security PIN:");

            User selectedUser = userService.findUserByUsername(userName);
            userService.setActiveUser(selectedUser, pin);
            JOptionPane.showMessageDialog(frame, "Login successful");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, exception.getMessage());
        }
    }

}

/* Regular for loop example1 convert product to table compactable format
        you can use this regular for loop or  example

        for (Product product: this.productService.getAllProducts()){
            String[] convertedProduct = {
                    String.valueOf(product.getId()),
                    product.getName(),
                    product.getDescription(),
                    String.valueOf(product.getPrice()),
                    String.valueOf(product.getQuantity()),
                    product.getAvailable() ? "Available" : "Not Available",

            };
            tableModel.addRow(convertedProduct);
        } * */


   /* longer version show confirm dialog and make decision
            boolean isAvailable = false;
            int isAvailableResponse = JOptionPane.showConfirmDialog(frame, "Is Product Available?");
            if (isAvailableResponse == JOptionPane.YES_OPTION) {
                isAvailable = true;
            }
            product.setAvailable(isAvailable);
        */