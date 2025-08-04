package ru.netology.nosql_mongodb.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.netology.nosql_mongodb.service.UserService;
import ru.netology.nosql_mongodb.model.User;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {

        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {

        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    @Secured({"ROLE_READ"})
    @PostAuthorize("returnObject.name == authentication.principal.username")
    public User getUserById(@PathVariable String id) {

        return service.getUserById(id);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("(#name == authentication.principal.username)")
    public List<User> getUsersByName(@PathVariable String name) {

        return service.getUsersByName(name);
    }


    @GetMapping("/age/{age}")
    @Secured({"ROLE_READ"})
    public List<User> getUsersByAge(@PathVariable int age) {

        return service.getUsersByAge(age);
    }

    @PostMapping("/add_user")
    @RolesAllowed({"ROLE_WRITE"})
    public User addUser(@RequestBody @Valid User user) {

        return service.addUser(user);
    }

    @PutMapping("/update_user")
    @Secured({"ROLE_UPDATE"})
    public User updateUser(@RequestBody @Valid User user) {

        return service.updateUser(user);
    }

    @GetMapping("/del_user/{id}")
    @PreAuthorize("(hasAnyRole({'ROLE_WRITE', 'ROLE_DELETE'}))")
    public void deleteUser(@PathVariable String id) {

        service.deleteUser(id);
    }
}
