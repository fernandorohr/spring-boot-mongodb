package com.fernando.workshopmongo.config;

import com.fernando.workshopmongo.domain.Post;
import com.fernando.workshopmongo.domain.User;
import com.fernando.workshopmongo.dto.AuthorDto;
import com.fernando.workshopmongo.repository.PostRepository;
import com.fernando.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajara para São Paulo", new AuthorDto(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei feliz hoje", new AuthorDto(alex));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().add(post1);
        alex.getPosts().add(post2);

        userRepository.saveAll(Arrays.asList(maria, alex));
    }
}
