package com.resumeai.resume_backend;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // CREATE JOB
    @PostMapping("/jobs")
    public Job createJob(@Valid @RequestBody Job job) {
        return jobRepository.save(job);
    }

    // GET JOB BY ID
    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable String id) {
        return jobRepository.findById(id).orElse(null);
    }

    // GET ALL JOBS + SEARCH
    @GetMapping("/jobs")
    public List<Job> getJobs(@RequestParam(required = false) String title) {

        if (title != null && !title.isEmpty()) {
            return jobRepository.findByTitleContainingIgnoreCase(title);
        }

        return jobRepository.findAll();
    }

    // UPDATE JOB
    @PutMapping("/jobs/{id}")
    public Job updateJob(@PathVariable String id, @Valid @RequestBody Job jobDetails) {

        return jobRepository.findById(id)
                .map(job -> {
                    job.setTitle(jobDetails.getTitle());
                    job.setCompany(jobDetails.getCompany());
                    job.setLocation(jobDetails.getLocation());
                    job.setSalary(jobDetails.getSalary());
                    return jobRepository.save(job);
                })
                .orElse(null);
    }

    // DELETE JOB
    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable String id) {
        jobRepository.deleteById(id);
        return "Job deleted successfully";
    }
}
