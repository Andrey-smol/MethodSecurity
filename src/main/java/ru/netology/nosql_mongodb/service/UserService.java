package ru.netology.nosql_mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.nosql_mongodb.exception.ErrorFindUserException;
import ru.netology.nosql_mongodb.reporitory.UserRepository;
import ru.netology.nosql_mongodb.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {

        this.repository = repository;
    }

    public List<User> getUsers() {

        return repository.findAll();
    }

    public User getUserById(String id) {

        return findObjectById(id);
    }

    public List<User> getUsersByName(String name) {

        return repository.findUserByName(name);
    }

    public List<User> getUsersByAge(int age) {

        return repository.findUserByAge(age);
    }

    public User addUser(User entity) {

        return repository.save(entity);
    }

    public User updateUser(User entity) {

        User user = findObjectById(entity.getId());
        user.setAge(entity.getAge());
        user.setEmail(entity.getEmail());
        user.setName(entity.getName());
        return repository.save(user);
    }

    public void deleteUser(String id) {

        repository.deleteById(id);
    }

    private User findObjectById(String id) {

        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new ErrorFindUserException("Не удалось найти объект с данным id [" + id + "]");
    }
}
