package com.resumeai.resume_backend;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {

    // Search by job title (case-insensitive)
    List<Job> findByTitleContainingIgnoreCase(String title);
}
