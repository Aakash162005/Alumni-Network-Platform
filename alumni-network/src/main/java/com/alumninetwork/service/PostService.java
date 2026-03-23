package com.alumninetwork.service;

import com.alumninetwork.model.Post;
import com.alumninetwork.model.Comment;
import com.alumninetwork.repository.PostRepository;
import com.alumninetwork.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.HashSet;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post createPostWithFile(Post post, MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (java.io.InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                post.setMediaUrl("/uploads/" + fileName);
                String contentType = file.getContentType();
                post.setMediaType(contentType != null && contentType.startsWith("video") ? "video" : "image");
            }
        }
        return postRepository.save(post);
    }

    public Post likePost(Long postId, Long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (post.getLikedByUserIds() == null) {
                post.setLikedByUserIds(new HashSet<>());
            }
            
            if (!post.getLikedByUserIds().contains(userId)) {
                post.getLikedByUserIds().add(userId);
                post.setLikesCount(post.getLikedByUserIds().size());
                return postRepository.save(post);
            }
            return post;
        }
        return null;
    }

    public Post sharePost(Long postId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            post.setSharesCount(post.getSharesCount() + 1);
            return postRepository.save(post);
        }
        return null;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
    
    public List<Post> getPostsByAuthorId(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
