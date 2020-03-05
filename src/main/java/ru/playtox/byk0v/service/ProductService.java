package ru.playtox.byk0v.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.byk0v.entity.Product;
import ru.playtox.byk0v.repository.ProductRepo;

/**
 * Сервис по работе с товарами
 */
@Service
public class ProductService {

    private static final Logger logger = LogManager.getLogger(ProductService.class);
    @Autowired
    private ProductRepo productRepo;

    public Iterable<Product> getAll() {
        return productRepo.findAll();
    }

    public boolean add(Product product) {
        try {
            Product product_tmp = productRepo.save(product);
            logger.info("Добавлен новый товар: " + product.getName() + ", с ценой=" + product.getPrice() + ", в количестве=" + product.getQuantity());
            return true;
        } catch (Exception e) {
            logger.info("Ошибка при ДОБАВЛЕНИИ товара: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteById(long product_id) {
        try {
            productRepo.deleteById(product_id);
            logger.info("Товар с id=" + product_id + " удален");
            return true;
        } catch (Exception e) {
            logger.info("Ошибка при УДАЛЕНИИ товара: " + e.getMessage());
            return false;
        }
    }

    public boolean edit(Product product) {
        try {
            productRepo.save(product);
            System.out.println("Product edited");
            logger.info("Товар с id=" + product.getId() + ", с именем=" + product.getName() + " удален");
            return true;
        } catch (Exception e) {
            logger.info("Ошибка при РЕДАКТИРОВАНИИ товара: " + e.getMessage());
            return false;
        }
    }
}
