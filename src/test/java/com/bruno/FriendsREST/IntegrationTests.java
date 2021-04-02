package com.bruno.FriendsREST;

import com.bruno.FriendsREST.controller.FriendController;
import com.bruno.FriendsREST.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

    @Autowired
    FriendController friendController;

    @Test
    public void testCreateReadDelete() {
        Friend friend = new Friend("Janice", "Hosenstein");

        Friend friendResult = friendController.save(friend);
        Iterable<Friend> friendList = friendController.read();
        Assertions.assertThat(friendList).extracting(Friend::getFirstName).contains("Janice");

        friendController.delete(friendResult.getId());
        Iterable<Friend> friendEmptyList = friendController.read();
        Assertions.assertThat(friendEmptyList).extracting(Friend::getFirstName).doesNotContain("Janice");
    }
}
