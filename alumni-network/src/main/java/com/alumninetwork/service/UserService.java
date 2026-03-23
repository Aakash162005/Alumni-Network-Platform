package com.alumninetwork.service;

import com.alumninetwork.model.User;
import com.alumninetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFullName(userDetails.getFullName());
        user.setCompany(userDetails.getCompany());
        user.setJobTitle(userDetails.getJobTitle());
        user.setGraduationYear(userDetails.getGraduationYear());
        user.setDepartment(userDetails.getDepartment());
        user.setAbout(userDetails.getAbout());
        user.setSkills(userDetails.getSkills());
        user.setLocation(userDetails.getLocation());
        user.setHeadline(userDetails.getHeadline());
        user.setExperience(userDetails.getExperience());
        user.setEducation(userDetails.getEducation());
        user.setProjects(userDetails.getProjects());
        if (userDetails.getProfilePicture() != null) {
            user.setProfilePicture(userDetails.getProfilePicture());
        }
        return userRepository.save(user);
    }

    public User updateProfilePicture(Long id, MultipartFile file) throws Exception {
        User user = userRepository.findById(id).orElseThrow();
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/profiles/");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (java.io.InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                user.setProfilePicture("/uploads/profiles/" + fileName);
            }
        }
        return userRepository.save(user);
    }
}
