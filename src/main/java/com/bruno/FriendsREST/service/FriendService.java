package com.bruno.FriendsREST.service;

import com.bruno.FriendsREST.model.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendService extends CrudRepository<Friend, Integer> {
}
