package org.example.consumer;

import org.example.UserService.UserService;
import org.example.domain.User;

public class ConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yupi");
        user.setAge(18);
        User user1 = userService.getUser(user);
        if (user1 != null) {
            System.out.println(user1.getName()+" - "+user1.getAge());
        }else {
            System.out.println("user1 is null");
        }
    }
}
