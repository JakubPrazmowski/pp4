package pl.jpraz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.jpraz.credit.NameTransformer;
import pl.jpraz.credit.NamesProvider;
import pl.jpraz.product.ProductCatalog;
import pl.jpraz.product.MapProductStorage;
import pl.jpraz.product.ProductStorage;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

    @Bean
    NamesProvider create(NameTransformer nameTransformer) {
        return new NamesProvider(nameTransformer);
    }

    @Bean
    NameTransformer createNT(){
        return new NameTransformer();
    }

    @Bean
    ProductStorage createMyProductStorage() {
        return new MapProductStorage();
    }

    @Bean
    ProductCatalog createMyProductCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);
        String productId1 = productCatalog.addProduct("lego-set-1", "Nice Lego set");
        productCatalog.assignImage(productId1, "https://picsum.photos/id/237/200/300");
        productCatalog.assignPrice(productId1, BigDecimal.TEN);
        productCatalog.publish(productId1);

        String productId2 = productCatalog.addProduct("lego-set-2", "Even nicer Lego set");
        productCatalog.assignImage(productId2, "https://picsum.photos/id/238/200/300");
        productCatalog.assignPrice(productId2, BigDecimal.valueOf(20.20));
        productCatalog.publish(productId2);

        return productCatalog;
    }

    @Bean
    Sales createSales(ProductDetailsProvider productDetailsProvider) {
        return new Sales(
                new CartStorage(),
                productDetailsProvider,
                new DummyPaymentGateway(),
                new ReservationStorage()
        );
    }

    @Bean
    ProductDetailsProvider detailsProvider(ProductCatalog catalog) {
        return (productId -> {
            ProductData data = catalog.getDetails(productId);
            return java.util.Optional.of(new ProductDetails(
                    data.getId(),
                    data.getName(),
                    data.getPrice()));
        });
    }
}

