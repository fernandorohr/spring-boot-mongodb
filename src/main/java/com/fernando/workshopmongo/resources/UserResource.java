package com.fernando.workshopmongo.resources;

import com.fernando.workshopmongo.application.assembler.UserAssembler;
import com.fernando.workshopmongo.domain.User;
import com.fernando.workshopmongo.dto.UserDto;
import com.fernando.workshopmongo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserResource {

    private final UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> users = service.findAll();
        List<UserDto> usersDto = users.stream().map(UserAssembler::toDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(UserAssembler.toDto(user));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> findById(@RequestBody UserDto userDto) {
        User user = UserAssembler.toDomain(userDto);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDto userDto, @PathVariable String id) {
        User user = UserAssembler.toDomain(userDto);
        user.setId(id);
        service.update(user);
        return ResponseEntity.noContent().build();
    }
}
