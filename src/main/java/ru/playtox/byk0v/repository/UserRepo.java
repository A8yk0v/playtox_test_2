package ru.playtox.byk0v.repository;

import org.springframework.data.repository.CrudRepository;
import ru.playtox.byk0v.entity.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
