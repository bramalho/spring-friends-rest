package com.bruno.FriendsREST;

import com.bruno.FriendsREST.model.Friend;
import com.bruno.FriendsREST.service.FriendService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

    @Autowired
    FriendService friendService;

    @Test
    public void testCreateReadDelete() {
        Friend friend = new Friend("Janice", "Hosenstein");

        Friend friendResult = friendService.save(friend);
        Iterable<Friend> friendList = friendService.findAll();
        Assertions.assertThat(friendList).extracting(Friend::getFirstName).contains("Janice");

        friendService.deleteById(friendResult.getId());
        Iterable<Friend> friendEmptyList = friendService.findAll();
        Assertions.assertThat(friendEmptyList).extracting(Friend::getFirstName).doesNotContain("Janice");
    }
}
