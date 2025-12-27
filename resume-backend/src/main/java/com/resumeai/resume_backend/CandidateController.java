package com.resumeai.resume_backend;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateRepository repo;

    public CandidateController(CandidateRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Candidate> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        return repo.save(candidate);
    }
}


