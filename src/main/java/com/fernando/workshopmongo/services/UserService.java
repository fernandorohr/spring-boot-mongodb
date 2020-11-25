package com.fernando.workshopmongo.services;

import com.fernando.workshopmongo.domain.User;
import com.fernando.workshopmongo.repository.UserRepository;
import com.fernando.workshopmongo.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(final String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Unexpected error: user not found"));
    }

    public User insert(final User user) {
        return repository.insert(user);
    }

    public void delete(final String id) {
        findById(id);
        repository.deleteById(id);
    }

    public void update (final User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        repository.save(newUser);
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }
}
