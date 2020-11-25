package com.fernando.workshopmongo.config;

import com.fernando.workshopmongo.domain.Post;
import com.fernando.workshopmongo.domain.User;
import com.fernando.workshopmongo.dto.AuthorDto;
import com.fernando.workshopmongo.repository.PostRepository;
import com.fernando.workshopmongo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User user1 = new User(null, "Maria Brown", "maria@gmail.com");
        User user2 = new User(null, "Alex Green", "alex@gmail.com");
        User user3 = new User(null, "Bob Grey", "bob@gmail.com");
        
        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajara para São Paulo", new AuthorDto(user1));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei feliz hoje", new AuthorDto(user2));

        postRepository.saveAll(Arrays.asList(post1, post2));

        user1.getPosts().add(post1);
        user2.getPosts().add(post2);

        userRepository.saveAll(Arrays.asList(user1, user2));
    }
}
