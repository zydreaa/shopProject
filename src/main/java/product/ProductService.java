package product;

import java.util.ArrayList;

public class ProductService {

    private ArrayList<Product> products = new ArrayList<>();

    String addProduct(Product product){
        this.products.add(product);
        return "Product successfully added";
    }
    ArrayList<Product> getAllProducts(){ //returns all products
        return products;
    }

    Product findProductById(){
        return new Product();
    }
}
