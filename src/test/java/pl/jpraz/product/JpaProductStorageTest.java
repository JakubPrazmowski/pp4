package pl.jpraz.product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class JpaProductStorageTest {

    @Autowired ProductDataCRUD productDataCRUD;

    @Test
    void storeAndLoadProduct() {
        ProductData data = new ProductData("my-id", "nice one");

        productDataCRUD.save(data);

        ProductData loaded = productDataCRUD
                .findById(data.getId()).get();

        assertEquals(data.getId(), loaded.getId());
    }
}
