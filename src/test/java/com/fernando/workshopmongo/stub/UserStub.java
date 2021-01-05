package com.fernando.workshopmongo.stub;

import com.fernando.workshopmongo.domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserStub {

    public static User buildSingleUser() {
        return new User("1", "Fernando", "fernando@gmail.com");
    }

    public static User buildUpdateUser() {
        return new User("1", "Fernando Rohr", "fernando@gmail.com");
    }

    public static Optional<User> buildOptionalUser() {
        return Optional.of(new User("1", "Fernando", "fernando@gmail.com"));
    }

    public static List<User> buildUserList() {
        return Arrays.asList(
                new User("1", "Fernando", "fernando@gmail.com"),
                new User("2", "Eliton", "eliton@gmail.com"),
                new User("3", "Augusto", "augusto@gmail.com")
        );
    }
}
