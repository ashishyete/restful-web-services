package com.example.rest.webservices.controller;

import com.example.rest.webservices.bean.User;
import com.example.rest.webservices.bean.UserRepository;
import com.example.rest.webservices.dao.UserDaoService;
import com.example.rest.webservices.exception.InvalidInputException;
import com.example.rest.webservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    @Autowired
    UserDaoService userDaoService;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {

        return userRepo.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        if (id == 0) //|| !Pattern.matches("[0-9]{3}]", String.valueOf(id))
            throw new InvalidInputException("Invalid Input Provided");
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Not Found with id : " + id);
        }
        int nextId = ++id;
        int prevId = --id;
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder nextUser = linkTo(methodOn(this.getClass()).retrieveUser(nextId));
        WebMvcLinkBuilder prevUser = linkTo(methodOn(this.getClass()).retrieveUser(prevId));
        entityModel.add(linkToUser.withRel("all-users"));
        entityModel.add(nextUser.withRel("next-user"));
        entityModel.add(prevUser.withRel("prev-user"));
        return entityModel;
    }

    @PostMapping("/jpa/user")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userRepo.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/user/{id}")
    public List<User> deleteUser(@PathVariable Integer id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("User Do not exist for id = " + id);
         userRepo.delete(user);
         return retrieveAllUsers();
    }

}
