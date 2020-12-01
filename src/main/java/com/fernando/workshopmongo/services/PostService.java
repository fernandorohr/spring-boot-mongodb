package com.fernando.workshopmongo.services;

import com.fernando.workshopmongo.domain.Post;
import com.fernando.workshopmongo.repository.PostRepository;
import com.fernando.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Unexpected error: post not found"));
    }

    public List<Post> findByTitle (String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }
}
