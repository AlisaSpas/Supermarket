package Service.Interface;

import Models.Product;
import Models.ProductCategory;

import java.time.LocalDate;

public interface IProductService {
    public Product createProduct(int productId, String productName, double singleDeliveryFee,
                                 ProductCategory category, LocalDate expirationDate,
                                 double productPrice, double quantity);

}
