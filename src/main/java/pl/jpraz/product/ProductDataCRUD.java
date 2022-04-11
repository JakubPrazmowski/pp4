package pl.jpraz.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductDataCRUD
        extends CrudRepository<ProductData, String> {
}
