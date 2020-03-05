package ru.playtox.byk0v.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.byk0v.entity.Product;
import ru.playtox.byk0v.entity.Purchase;
import ru.playtox.byk0v.entity.User;
import ru.playtox.byk0v.repository.ProductRepo;
import ru.playtox.byk0v.repository.PurchaseRepo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

/**
 * Класс отвечает за функционал магазина
 * связанный с покупками товара
 */
@Service
public class ShoppingService {

    private static final Logger logger = LogManager.getLogger(ShoppingService.class);
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private PurchaseRepo purchaseRepo;

    //@Transactional - так и не заработало :/
    public boolean buy(User user, long product_id, int quantity) {
        try {
            Optional<Product> product = productRepo.findById(product_id);

            if (!product.isPresent() || product.get().getQuantity() < quantity)
                throw new SQLException();

            product.get().setQuantity(product.get().getQuantity() - quantity);
            productRepo.save(product.get());

            Date date = new Date();
            purchaseRepo.save(new Purchase(user, product.get(), date, product.get().getPrice(), quantity));

            logger.info("New purchase: user: " + user.getLogin() + ", buy: " + product.get().getName() + ", quantity: " + quantity + ", date: " + date);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
