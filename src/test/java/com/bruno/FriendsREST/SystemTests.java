package com.bruno.FriendsREST;

import com.bruno.FriendsREST.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class SystemTests {

    @Test
    public void testCreateReadDelete() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/friend";

        Friend friend = new Friend("Janice", "Hosenstein");
        ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);

        Friend[] friendList = restTemplate.getForObject(url, Friend[].class);
        Assertions.assertThat(friendList).extracting(Friend::getFirstName).contains("Janice");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        Friend[] friendEmptyList = restTemplate.getForObject(url, Friend[].class);
        Assertions.assertThat(friendEmptyList).extracting(Friend::getFirstName).doesNotContain("Janice");
    }

    @Test
    public void testErrorHandlingReturnsBadRequest() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.getForEntity("http://localhost:8080/wrong", String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }
}
