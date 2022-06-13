package pl.jpraz.sales.products;

import pl.jpraz.sales.products.ProductDetails;

import java.util.Optional;

public interface ProductDetailsProvider {
    Optional<ProductDetails> findById(String productId);
}
