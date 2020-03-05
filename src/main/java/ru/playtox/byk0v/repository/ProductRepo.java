package ru.playtox.byk0v.repository;

import org.springframework.data.repository.CrudRepository;
import ru.playtox.byk0v.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {
    Product findById(int id);
}
