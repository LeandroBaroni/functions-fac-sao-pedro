package com.leandrobaroni2103.functions_fac_sao_pedro.controllers;

import com.leandrobaroni2103.functions_fac_sao_pedro.dtos.users.CreateUserDto;
import com.leandrobaroni2103.functions_fac_sao_pedro.entities.User;
import com.leandrobaroni2103.functions_fac_sao_pedro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/")
  public String create(@RequestBody CreateUserDto createUser) throws ExecutionException, InterruptedException {
    System.out.println(createUser);

    User user = new User();

    user.setActive(true);
    user.setName(createUser.getName());
    user.setEmail(createUser.getEmail());
    user.setPhone(createUser.getPhone());
    user.setSex(createUser.getSex());

    return this.userService.add(user);
  }

  @GetMapping("")
  public List<User> getUsers() throws ExecutionException, InterruptedException {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
    return this.userService.getById(id);
  }

  @PutMapping("/{id}")
  public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
    return this.userService.update(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    this.userService.delete(id);
  }
}
