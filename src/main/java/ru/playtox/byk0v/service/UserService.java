package ru.playtox.byk0v.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.byk0v.entity.User;
import ru.playtox.byk0v.repository.UserRepo;

/**
 * Сервис по работе с пользователями
 */
@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepo;

    public User getUserByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    public void save(User user) {
        try {
            userRepo.save(user);
            logger.info("Добавлен новый пользователь: " + user.getLogin() + " " + user.getRole());
        } catch (Exception e) {
            logger.info("Ошибка при добавлении пользователя: " + e.getMessage());
        }
    }
}
