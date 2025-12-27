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

    @GetMapping("/{id}")
    public Candidate getById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        return repo.save(candidate);
    }

    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable String id, @RequestBody Candidate updatedCandidate) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(updatedCandidate.getName());
                    existing.setEmail(updatedCandidate.getEmail());
                    existing.setSkills(updatedCandidate.getSkills());
                    return repo.save(existing);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable String id) {
        repo.deleteById(id);
    }
}
