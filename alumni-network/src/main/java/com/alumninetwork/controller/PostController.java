package com.alumninetwork.controller;

import com.alumninetwork.model.Post;
import com.alumninetwork.model.Comment;
import com.alumninetwork.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/category/{category}")
    public List<Post> getPostsByCategory(@PathVariable("category") String category) {
        return postService.getPostsByCategory(category);
    }

    @PostMapping
    public Post createPost(@RequestParam("post") String postJson, 
                           @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(postJson, Post.class);
        return postService.createPostWithFile(post, file);
    }

    @PostMapping("/{postId}/like")
    public Post likePost(@PathVariable("postId") Long postId, @RequestParam("userId") Long userId) {
        return postService.likePost(postId, userId);
    }

    @PostMapping("/{postId}/share")
    public Post sharePost(@PathVariable("postId") Long postId) {
        return postService.sharePost(postId);
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getComments(@PathVariable("postId") Long postId) {
        return postService.getCommentsByPostId(postId);
    }

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable("postId") Long postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        return postService.addComment(comment);
    }

    @GetMapping("/author/{authorId}")
    public List<Post> getPostsByAuthor(@PathVariable("authorId") Long authorId) {
        return postService.getPostsByAuthorId(authorId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
    }
}
