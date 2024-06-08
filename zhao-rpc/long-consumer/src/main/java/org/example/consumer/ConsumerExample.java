package org.example.consumer;

import org.example.UserService.UserService;
import org.example.domain.User;

public class ConsumerExample {
    public static void main(String[] args) {
        UserService userService = null;
        User user = new User();
        user.setName("yupi");
        User user1 = userService.getUser(user);
        if (user1 != null) {
            System.out.println(user1.getName());
        }else {
            System.out.println("null");
        }
    }
}
