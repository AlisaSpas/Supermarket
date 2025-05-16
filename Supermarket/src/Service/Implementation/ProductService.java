package Service.Implementation;

import Models.Product;
import Models.ProductCategory;
import Service.Interface.IProductService;

import java.time.LocalDate;

public class ProductService implements IProductService {
    @Override
    public Product createProduct(int productId, String productName, double singleDeliveryFee,
                                 ProductCategory category, LocalDate expirationDate, double productPrice) {
        Product product = new Product(productId,productName,singleDeliveryFee, category,
                expirationDate, productPrice);
        return product;
    }
}
