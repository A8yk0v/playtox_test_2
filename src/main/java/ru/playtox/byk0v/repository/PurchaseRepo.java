package ru.playtox.byk0v.repository;

import org.springframework.data.repository.CrudRepository;
import ru.playtox.byk0v.entity.Purchase;

public interface PurchaseRepo extends CrudRepository<Purchase, Long> {

}
