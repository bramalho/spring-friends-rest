package com.bruno.FriendsREST;

import com.bruno.FriendsREST.controller.FriendController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FriendsRestApplicationTests {

	@Autowired
	FriendController friendController;

	@Test
	void contextLoads() {
		Assert.assertNotNull(friendController);
	}
}
