package com.bruno.FriendsREST.controller;

import com.bruno.FriendsREST.model.Friend;
import com.bruno.FriendsREST.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/friend")
    Iterable<Friend> read() {
        return friendService.findAll();
    }

    @PostMapping("/friend")
    Friend save(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @PutMapping("friend")
    Friend update(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @DeleteMapping("friend/{id}")
    void delete(@PathVariable int id) {
        friendService.deleteById(id);
    }
}
