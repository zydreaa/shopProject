package product;

import java.util.ArrayList;
import java.util.UUID;

public class ProductService {

    private final ArrayList<Product> products = new ArrayList<>();
    public String addProduct(Product product){
        try {
            if (this.validateProduct(product)){
                this.products.add(product);
                return "Product Successfully Added";
            }
        } catch (Exception exception) {
            return exception.getMessage();
        }

        return "There was a problem adding product";
    }

    private boolean validateProduct(Product product) throws Exception {
        if (product.getName().isEmpty() || product.getName() == null){
            throw new Exception("Please provide name field");
        }
        if (product.getQuantity() == null){
            throw new Exception("Please provide product quantity");
        }
        if (product.getPrice() == null || product.getPrice() < 0){
            throw new Exception("Please provide product price");
        }
        if (product.getDescription().isEmpty() || product.getDescription() == null){
            throw new Exception("Please provide product description");
        }
        if (product.getAvailable() == null){
            throw new Exception("Please provide product availability");
        }

        return true;
    }

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public Product findProductById(UUID productId) throws Exception {
        for(Product currentProduct: this.products){
            if (currentProduct.getId().equals(productId)) return currentProduct;
        }

        throw new Exception("product with id " + productId + " not found");
    }

    public String updateProduct(Product product) {
        boolean updateSuccessful = false;
        for(Product currentProduct: this.products){
            if (currentProduct.getId().equals(product.getId())) {
                currentProduct.setQuantity(product.getQuantity());
                currentProduct.setAvailable(product.getAvailable());
                currentProduct.setName(product.getName());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setDescription(product.getDescription());

                updateSuccessful = true;
                /*stop only the loop after changing the required product(s)*/
                break;

                //return ""; // also possible to use return which will end the method and the loop
            }
        }

        return updateSuccessful ?
                "Product with name "+ product.getName() + " updated successfully" :
                "problem occurred during update";
    }
}