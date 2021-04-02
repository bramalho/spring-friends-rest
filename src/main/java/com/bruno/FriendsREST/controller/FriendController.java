package com.bruno.FriendsREST.controller;

import com.bruno.FriendsREST.model.Friend;
import com.bruno.FriendsREST.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/friend")
    Iterable<Friend> read() {
        return friendService.findAll();
    }

    @PostMapping("/friend")
    Friend save(@Valid @RequestBody Friend friend){
        return friendService.save(friend);
    }

    @PutMapping("friend")
    ResponseEntity<Friend> update(@RequestBody Friend friend) {
        if (friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("friend/{id}")
    void delete(@PathVariable int id) {
        friendService.deleteById(id);
    }

    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable Integer id) {
        return friendService.findById(id);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName)
    {
        if (firstName != null && lastName != null) {
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        }

        if (firstName != null) {
            return friendService.findByFirstName(firstName);
        }

        if (lastName != null) {
            return friendService.findByLastName(lastName);
        }

        return Collections.emptyList();
    }
}
