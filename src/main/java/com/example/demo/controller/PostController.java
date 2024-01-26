package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity showFormAdd(@RequestBody Post post) {
        return new ResponseEntity(postRepository.save(post), HttpStatus.CREATED);

    }
    @PutMapping("{id}")
    public ResponseEntity save(@RequestBody Post post, @PathVariable Long id){
    post.setId(id);
        return new ResponseEntity<>(postRepository.save(post),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity showEdit( @PathVariable Long id) {
        return new ResponseEntity(postRepository.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new ResponseEntity("delete done", HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Post>> findByKeyword(@RequestParam String keyword) {
        List<Post> blogs = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
    @GetMapping("/searchUser/{id}")
    public ResponseEntity findByKeyword1(@PathVariable Long id) {
        List<Post> list = postRepository.findAllByUserId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("searchLikes")
    public ResponseEntity findByLikes(){
        return new ResponseEntity<>(postRepository.findAllByOrderByLikesDesc(),HttpStatus.OK);
    }
    @GetMapping("top4Likes")
    public ResponseEntity findByTopLikes(){
        PageRequest pageRequest = PageRequest.of(0, 4);
        return new ResponseEntity<>(postRepository.findTop4ByOrderByLikesDesc(pageRequest),HttpStatus.OK);
    }
    @GetMapping("only")
    public ResponseEntity SttOnlyMe(@RequestParam Long id){
        return new ResponseEntity<>(postRepository.nhuanhcho(id),HttpStatus.OK);
    }
}
