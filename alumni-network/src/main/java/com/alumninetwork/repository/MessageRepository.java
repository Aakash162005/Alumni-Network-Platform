package com.alumninetwork.repository;

import com.alumninetwork.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.senderId = :id1 AND m.receiverId = :id2) OR (m.senderId = :id2 AND m.receiverId = :id1) ORDER BY m.timestamp ASC")
    List<Message> findChatHistory(@Param("id1") Long id1, @Param("id2") Long id2);
}
