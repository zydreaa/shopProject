package shop;

import product.Product;
import product.ProductService;

import java.util.ArrayList;

public class ShopController {//responsible for using services and data of products

    private ProductService productService;

    public ShopController(ProductService productService){
        this.productService = productService;
    }


}
