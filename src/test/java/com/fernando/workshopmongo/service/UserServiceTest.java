package com.fernando.workshopmongo.service;

import com.fernando.workshopmongo.domain.User;
import com.fernando.workshopmongo.repository.UserRepository;
import com.fernando.workshopmongo.services.UserService;
import com.fernando.workshopmongo.services.exceptions.ObjectNotFoundException;
import com.fernando.workshopmongo.stub.UserStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
//        BDDMockito.when(repository.insert(ArgumentMatchers.any(User.class))).thenReturn(UserStub.buildSingleUser());
        BDDMockito.when(repository.findAll()).thenReturn(UserStub.buildUserList());
        BDDMockito.when(repository.findById("1")).thenReturn(UserStub.buildOptionalUser());
    }

    @Test
    public void findAllUsersSuccessfullyTest() {
        List<User> allUsers = userService.findAll();
        Assertions.assertEquals(UserStub.buildUserList(), allUsers);
    }

    @Test
    public void findUserByIdSuccessfullyTest() {
        User user = userService.findById("1");
        Assertions.assertEquals(UserStub.buildSingleUser(), user);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void failedToFindUserByIdTest() {
        userService.findById(null);
    }

    @Test
    public void insertNewUserSuccessfullyTest() {
        userService.insert(UserStub.buildSingleUser());
        BDDMockito.verify(repository, Mockito.times(1)).insert(UserStub.buildSingleUser());
    }

    @Test
    public void deleteUserSuccessfullyTest() {
        userService.delete(UserStub.buildSingleUser().getId());
        BDDMockito.verify(repository, Mockito.times(1)).deleteById(UserStub.buildSingleUser().getId());
    }

    @Test
    public void updateUserSuccessfullyTest() {
        User user = UserStub.buildSingleUser();
        userService.updateData(user, UserStub.buildUpdateUser());
        Assertions.assertEquals(UserStub.buildUpdateUser(), user);
    }
}
