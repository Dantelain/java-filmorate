package ru.yandex.practicum.filmorate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, UserService> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") Long userId, @PathVariable Long friendId) {
        service.addFriend(userId, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable("id") Long userId, @PathVariable Long friendId) {
        service.deleteFriend(userId, friendId);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getListCommonFriend(@PathVariable("id") Long userId, @PathVariable Long otherId) {
        return service.getListCommonFriend(userId, otherId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getListFriend(@PathVariable("id") Long userId) {
        return service.getListFriend(userId);
    }

}
