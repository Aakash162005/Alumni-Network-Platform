package com.alumninetwork.util;

import com.alumninetwork.model.Post;
import com.alumninetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        if (postRepository.count() == 0) {
            Post p1 = new Post();
            p1.setTitle("Smart India Hackathon 2026");
            p1.setContent("Join the world's biggest open innovation model.");
            p1.setOrganization("Ministry of Education");
            p1.setCategory("hackathon");
            p1.setDomain("General");
            p1.setDeadline("Oct 15, 2026");
            postRepository.save(p1);

            Post p2 = new Post();
            p2.setTitle("Software Engineering Intern");
            p2.setOrganization("Google");
            p2.setCategory("internship");
            p2.setDomain("Software");
            p2.setLocation("Hyderabad");
            p2.setStipendOrSalary("₹50,000/mo");
            postRepository.save(p2);

            Post p3 = new Post();
            p3.setTitle("Placement Drive: 450+ Offers");
            p3.setContent("TCS has successfully recruited 450 students from RCPIT.");
            p3.setOrganization("TPO Cell");
            p3.setCategory("news");
            postRepository.save(p3);
        }
    }
}
